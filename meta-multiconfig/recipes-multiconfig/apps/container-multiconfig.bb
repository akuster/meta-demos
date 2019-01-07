SUMMARY = "Package container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

#do_install[mcdepends] = "multiconfig:host:container:container-image-minimal:do_image_complete"

do_fetch[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}${localstatedir}/lib/machines
    install ${TOPDIR}/tmpmulti-host-${TCLIBC}/deploy/images/${MACHINE}/container-image-minimal-${MACHINE}.tar.gz ${D}${localstatedir}/lib/machines
}
