SUMMARY = "Basic Demo image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11-base"

LICENSE = "MIT"

inherit core-image distro_features_check

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11"


IMAGE_INSTALL = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    packagegroups-opencv \
    kernel-modules \
    python3-core \
"
