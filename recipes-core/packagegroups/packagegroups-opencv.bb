DESCRIPTION = "OpenCV base package group"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS_${PN} = " \
    opencv-apps  \
    opencv-samples \
    libopencv-video \
    libopencv-face \
    python3-opencv \
"
