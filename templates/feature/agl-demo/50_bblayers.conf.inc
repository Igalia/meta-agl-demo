# This layer contains the WIP feature/demo code only
#---------------------------------------------------
BBLAYERS =+ " \
   ${METADIR}/meta-agl/meta-agl-profile-ivi \
   ${METADIR}/meta-agl-demo \
"

# OpenEmbedded layer to add support for multiple cloud service provider solutions
#---------------------------------------------------
BBLAYERS =+ " \
   ${METADIR}/external/meta-iot-cloud \
"
