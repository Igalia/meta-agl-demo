DISTRO_FEATURES_append = " agl-demo-preload"

# Use demo device mapping for low-can that enables sllin0
PREFERRED_RPROVIDER_virtual/low-can-dev-mapping = "dev-mapping-demo"

# Extend agl-voiceagent-alexa image feature to preload voiceagent
# configuration package.
FEATURE_PACKAGES_agl-voiceagent-alexa_append = " virtual/alexa-voiceagent-config"
