SUMMARY = "Basic Demo image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11-base"

LICENSE = "MIT"

inherit core-image distro_features_check

# let's make sure we have a good image..
DISTRO_FEATURES = "x11 wayland TS_vu7+"


IMAGE_INSTALL = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    packagegroup-core-x11 \
    packagegroups-opencv \
    kernel-modules \
    python3-core \
    odroid-vu7-plus \
"
