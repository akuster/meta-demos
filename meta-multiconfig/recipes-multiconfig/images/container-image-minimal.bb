SUMMARY = "A minimal container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_FSTYPES = "container"

inherit image

PREFERRED_PROVIDER_virtual/kernel = "linux-dummy"

IMAGE_TYPEDEP_container_append = " ext4"
IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""
PACKAGE_CLASSES = ""

NO_RECOMMENDATIONS = "1"
VIRTUAL-RUNTIME_login_manager ?= "busybox"
VIRTUAL-RUNTIME_dev_manager ?= ""
VIRTUAL-RUNTIME_init_manager ?= ""
VIRTUAL-RUNTIME_initscripts ?= ""
VIRTUAL-RUNTIME_keymaps ?= ""

IMAGE_INSTALL = " \
    base-files \
    base-passwd \
    netbase \
    ${VIRTUAL-RUNTIME_login_manager} \
"

# Workaround /var/volatile for now
ROOTFS_POSTPROCESS_COMMAND += "rootfs_fixup_var_volatile ; "
rootfs_fixup_var_volatile () {
        install -m 1777 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/tmp
        install -m 755 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/log
}
