SUMMARY = "hts_engine is software to synthesize speech waveform from HMMs trained by the HMM-based speech synthesis system (HTS)."
HOMEPAGE = "http://hts-engine.sourceforge.net/"
SECTION = "libs"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5=b3be85b37c90bfd4d34d79a0e3f28700"

inherit autotools

BBCLASSEXTEND = "native"

S = "${WORKDIR}/hts_engine_API-${PV}"


SRC_URI = "http://downloads.sourceforge.net/hts-engine/hts_engine_API-${PV}.tar.gz \
           file://nomalize.patch \
          "

SRC_URI[md5sum] = "5626d1e2522659e93fb295f0b42339f5"
SRC_URI[sha256sum] = "e2132be5860d8fb4a460be766454cfd7c3e21cf67b509c48e1804feab14968f7"

