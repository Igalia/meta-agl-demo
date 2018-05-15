import QtQuick 2.1
import QtQuick.Controls 1.1
import QtWebEngine 1.1

ApplicationWindow {
	width: 1024
	height: 768
	visible: true
	flags: Qt.WindowFullScreen | Qt.FramelessWindowHint
	WebEngineView {
		url: Qt.application.arguments[1]
		anchors.fill: parent
	}
}
