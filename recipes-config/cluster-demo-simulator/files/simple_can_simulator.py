#!/usr/bin/env python3
# Copyright (c) 2016 Alex Bencz
# Copyright (c) 2019 Konsulko Group, smurray@konsulko.com
# Copyright (c) 2020 The Linux Foundation, jsmoeller@linuxfoundation.org
#
# Permission is hereby granted, free of charge, to any person obtaining a copy of
# this software and associated documentation files (the "Software"), to deal in
# the Software without restriction, including without limitation the rights to
# use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
# of the Software, and to permit persons to whom the Software is furnished to do
# so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

#
# CANSocket from:
#
# https://github.com/abencz/python_socketcan/blob/master/python_socketcan_example.py
#

import sys
import socket
import argparse
import struct
import errno
import threading
import time

class CANSocket(object):
    FORMAT = "<IB3x8s"
    FD_FORMAT = "<IB3x64s"

    def __init__(self, interface=None):
        self.sock = socket.socket(socket.PF_CAN, socket.SOCK_RAW, socket.CAN_RAW)
        if interface is not None:
            self.bind(interface)

    def bind(self, interface):
        self.sock.bind((interface,))
        self.sock.setsockopt(socket.SOL_CAN_RAW, socket.CAN_RAW_FD_FRAMES, 1)

    def send(self, can_id, data, flags=0):
        can_id = can_id | flags
        can_pkt = struct.pack(self.FORMAT, can_id, len(data), data)
        self.sock.send(can_pkt)

    def sendfd(self, can_id, data, flags=0):
        can_id = can_id | flags
        datafd = data.ljust(64, b'\x00');
        can_pkt = struct.pack(self.FD_FORMAT, can_id, len(datafd), datafd)
        self.sock.send(can_pkt)

    def recv(self, flags=0):
        can_pkt = self.sock.recv(72)

        if len(can_pkt) == 16:
            can_id, length, data = struct.unpack(self.FORMAT, can_pkt)
        else:
            can_id, length, data = struct.unpack(self.FD_FORMAT, can_pkt)

        can_id &= socket.CAN_EFF_MASK
        return (can_id, data[:length])

class VehicleSimulator(object):
    DEFAULT_IDLE_RPM = 600

    def __init__(self):
        self.CRUISEMODE = False
        self.CRUISEACTIVE = False
        self.CRUISESPEED = 0
        self.CRUISERPM = 0
        self.freq = 10
        self.vehicle_speed = 0
        self.engine_speed = self.DEFAULT_IDLE_RPM
        self.thread = threading.Thread(target=self.run, daemon=True)
        self.lock = threading.Lock()

    def reset(self):
        with self.lock:
            self.vehicle_speed = 0
            self.engine_speed = self.DEFAULT_IDLE_RPM

    def start(self):
        self.thread.start()

    def get_engine_speed(self):
        with self.lock:
            return int(self.engine_speed)

    def get_vehicle_speed(self):
        with self.lock:
            return int(self.vehicle_speed)

    def accelerate(self, target_speed, target_rpm, duration, bycruise = False):
        if target_speed <= self.vehicle_speed:
            return
        v = (target_speed - self.vehicle_speed) / (duration * self.freq)
        r = (target_rpm - self.engine_speed) / (duration * self.freq)
        while self.vehicle_speed < target_speed and (not self.CRUISEACTIVE or bycruise):
            with self.lock:
                self.vehicle_speed += v;
                self.engine_speed += r;
            time.sleep(1 / self.freq)

    def brake(self, target_speed, target_rpm, duration, bycruise = False):
        if target_speed >= self.vehicle_speed:
            return
        v = (self.vehicle_speed - target_speed) / (duration * self.freq)
        r = (self.engine_speed - target_rpm) / (duration * self.freq)
        while self.vehicle_speed > target_speed and (not self.CRUISEACTIVE or bycruise):
            with self.lock:
                self.vehicle_speed -= v;
                self.engine_speed -= r;
            time.sleep(1 / self.freq)

    def increase(self, bycruise = True):
        if self.CRUISEACTIVE:
            target_speed = self.vehicle_speed + 5
            target_rpm = self.engine_speed * 1.1
            self.accelerate(target_speed, target_rpm, 2, bycruise)

    def decrease(self, bycruise = True):
        if self.CRUISEACTIVE:
            target_speed = self.vehicle_speed - 5
            target_rpm = self.engine_speed * 0.9
            self.brake(target_speed, target_rpm, 2, bycruise)

    def resume(self, bycruise = True):
        target_speed = self.CRUISESPEED
        target_rpm = self.CRUISERPM
        current_speed = self.get_vehicle_speed()
        if target_speed > current_speed:
            self.accelerate(target_speed, target_rpm, 2, bycruise)
        else:
            self.brake(target_speed, target_rpm, 2, bycruise)

    def run(self):
        while True:
            if not self.CRUISEACTIVE:
                self.accelerate(80, 3000, 5)
                self.accelerate(104, 4000, 3)
                self.brake(80, 3000, 3)
                self.accelerate(104, 4000, 6)
                self.brake(40, 2000, 4)
                self.accelerate(90, 3000, 5)
                self.brake(1, 650, 5)
                if not self.CRUISEACTIVE:
                    self.reset()
            time.sleep(5)

class DiagnosticMessageHandler(object):
    def __init__(self, can_sock, simulator, verbose=False):
        self.can_sock = can_sock
        self.simulator = simulator
        self.verbose = verbose
        self.thread = threading.Thread(target=self.run, daemon=True)

    def start(self):
        self.thread.start()

    def run(self):
        while True:
            can_id, data = self.can_sock.recv()
            #print('%03X#%s' % (can_id, ''.join(format(x, '02X') for x in data)))
            if can_id == 0x7df:
                # OBD-II request
                if data[1] == 0x01 and data[2] == 0x0C:
                    # Engine speed
                    speed = self.simulator.get_engine_speed()
                    #print('engine speed = %d' % speed)
                    if speed > 16383.75:
                        speed = 16383.75
                    reply = [ 0x04, 0x41, 0x0C ]
                    reply.append(4 * speed // 256)
                    reply.append(4 * speed % 256)
                    # pad remaining bytes to make 8
                    reply.append(0)
                    reply.append(0)
                    reply.append(0)
                    self.can_sock.send(0x7e8, bytes(reply), 0)
                elif data[1] == 0x01 and data[2] == 0x0D:
                    # Vehicle speed
                    speed = int(self.simulator.get_vehicle_speed()) % 256
                    #print('vehicle speed = %d' % speed)
                    reply = [ 0x03, 0x41, 0x0D ]
                    reply.append(speed)
                    # pad remaining bytes to make 8
                    reply.append(0)
                    reply.append(0)
                    reply.append(0)
                    reply.append(0)
                    self.can_sock.send(0x7e8, bytes(reply), 0)

class SteeringWheelMessageHandler(object):
    def __init__(self, can_sock, simulator, verbose=False):
        self.can_sock = can_sock
        self.simulator = simulator
        self.verbose = verbose
        self.thread = threading.Thread(target=self.run, daemon=True)
        self.buttonpressed = False
        self.buttonenabled = False
        self.buttoncancel = False
        self.buttondec = False
        self.buttoninc = False
        self.cruisemode = False
        self.cruiseactive = False

    def start(self):
        self.thread.start()

    def run(self):
        while True:
            can_id, data = self.can_sock.recv()
            #print('%03X#%s' % (can_id, ''.join(format(x, '02X') for x in data)))
            if can_id == 0x21:
                #print('%03X#%s' % (can_id, ''.join(format(x, '02X') for x in data)))
                if data:
                    #if data[6]:
                        #print('data6: %02X' % (data[6]))
                    if data[6] == 0x80 and not self.buttonpressed:
                        # we do skip any further lin messages
                        # two buttons at the same time won't work
                        # (aka unlikely w/o twisting fingers)
                        self.buttonpressed = True
                        self.buttonenabled = True
                    if data[6] == 0x08 and not self.buttonpressed:
                        self.buttonpressed = True
                        self.buttoncancel = True
                    if data[6] == 0x10 and not self.buttonpressed:
                        self.buttonpressed = True
                        self.buttondec = True
                    if data[6] == 0x40 and not self.buttonpressed:
                        self.buttonpressed = True
                        self.buttoninc = True
                    if data[6] == 0x00 and self.buttonpressed:
                        #now handle it as the button was released
                        if self.buttonenabled:
                            self.buttonenabled = False
                            self.cruisemode = not self.cruisemode
                            #print("set cruisemode to %s" % self.cruisemode)
                            self.simulator.CRUISEMODE = self.cruisemode
                            # disable/reset all if going off
                            if not self.cruisemode:
                                self.cruiseactive = False
                                self.simulator.CRUISEACTIVE = self.cruiseactive
                                self.simulator.CRUISESPEED = 0
                                self.simulator.CRUISERPM = 0
                            #print("set cruiseactive to %s" % self.cruiseactive)
                        if self.buttoncancel:
                            self.buttoncancel = False
                            self.simulator.CRUISESPEED = self.simulator.get_vehicle_speed()
                            self.simulator.CRUISERPM = self.simulator.get_engine_speed()
                            #print("set cruisespeed to %d" % self.simulator.CRUISESPEED )
                            #print("set cruiserpm to %d" % self.simulator.CRUISERPM )
                            self.cruiseactive = False
                            #print("set cruiseactive to %s" % self.cruiseactive )
                            self.simulator.CRUISEACTIVE = self.cruiseactive
                        if self.buttondec:
                            self.buttondec = False
                            if self.cruiseactive:
                                #print("decrease")
                                self.simulator.decrease()
                            else:
                                # set speed
                                #print("set speed")
                                self.simulator.CRUISESPEED = self.simulator.get_vehicle_speed()
                                self.simulator.CRUISERPM = self.simulator.get_engine_speed()
                                #print("set cruisespeed to %d" % self.simulator.CRUISESPEED )
                                #print("set cruiserpm to %d" % self.simulator.CRUISERPM )
                                self.cruiseactive = not self.cruiseactive
                                #print("set cruiseactive to %s" % self.cruiseactive )
                                self.simulator.CRUISEACTIVE = self.cruiseactive
                        if self.buttoninc:
                            self.buttoninc = False
                            if self.cruiseactive:
                                #print("increase")
                                self.simulator.increase()
                            else:
                                if self.simulator.CRUISESPEED > 0:
                                    # resume
                                    self.cruiseactive = not self.cruiseactive
                                    self.simulator.CRUISEACTIVE = self.cruiseactive
                                    #print("set cruiseactive to %s" % self.cruiseactive )
                                    #print("resume")
                                    self.simulator.resume()
                        self.buttonpressed = False


class StatusMessageSender(object):
    def __init__(self, can_sock, simulator, verbose=False):
        self.can_sock = can_sock
        self.simulator = simulator
        self.verbose = verbose
        self.thread = threading.Thread(target=self.run, daemon=True)

    def start(self):
        self.thread.start()

    def run(self):
        while True:
            # Engine speed
            speed = self.simulator.get_engine_speed()
            if self.verbose:
                print('engine speed = %d' % speed)
            if speed > 16383.75:
                speed = 16383.75
            # Message is 1 byte unknown, 1 byte fuel level, 2 bytes engine speed (4x), fuel low @ bit 55
            msg = [ 0, 0 ]
            speed *= 4
            msg.append(speed // 256)
            msg.append(speed % 256)
            # pad remaining bytes to make 8
            msg.append(0)
            msg.append(0)
            msg.append(0)
            msg.append(0)
            self.can_sock.send(0x3d9, bytes(msg), 0)

            # Vehicle speed
            speed = int(self.simulator.get_vehicle_speed()) % 256
            if self.verbose:
                print('vehicle speed = %d' % speed)
            # Message is 15 bits speed (64x), left aligned
            msg = [ ]
            # Note: extra 2x to yield required left-alignment
            speed *= 128
            msg.append(speed // 256)
            msg.append(speed % 256)
            # pad remaining bytes to make 8
            msg.append(0)
            msg.append(0)
            msg.append(0)
            msg.append(0)
            msg.append(0)
            msg.append(0)
            self.can_sock.send(0x3e9, bytes(msg), 0)

            # Sleep 100 ms
            time.sleep(0.1)

def main():
    parser = argparse.ArgumentParser(description='Simple CAN vehicle simulator.')
    parser.add_argument('interface', type=str, help='interface name (e.g. vcan0)')
    parser.add_argument('-v', '--verbose', help='increase output verbosity', action='store_true')
    args = parser.parse_args()

    try:
        can_sock = CANSocket(args.interface)
        diag_can_sock = CANSocket(args.interface)
        steeringwheel_can_sock = CANSocket(args.interface)
    except OSError as e:
        sys.stderr.write('Could not listen on interface {0}\n'.format(args.interface))
        sys.exit(e.errno)

    print('Using {0}'.format(args.interface))
    sim = VehicleSimulator()
    status_sender = StatusMessageSender(can_sock, sim, args.verbose)
    diag_handler = DiagnosticMessageHandler(diag_can_sock, sim, args.verbose)
    steeringwheel_handler = SteeringWheelMessageHandler(steeringwheel_can_sock, sim, args.verbose)
    sim.start()
    status_sender.start()
    diag_handler.start()
    steeringwheel_handler.start()
    try:
        while True:
            time.sleep(60)
    except (KeyboardInterrupt, SystemExit):
        #sim.stop()
        sys.exit(0)

if __name__ == '__main__':
    main()
