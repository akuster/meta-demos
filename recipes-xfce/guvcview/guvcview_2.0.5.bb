DESCRIPTION = "armin"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
LICENSE = "GPL-2.0"

DEPENDS = "glib-2.0-native intltool-native libv4l libgudev libusb1 libpng ffmpeg libsdl2 gsl portaudio-v19"

SRC_URI = "http://archive.ubuntu.com/ubuntu/pool/universe/g/guvcview/guvcview_2.0.5+debian.orig.tar.xz"

SRC_URI += "file://ffmpeg4.0.patch"

SRC_URI[md5sum] = "0bcdf1057cab6949176eae644c675047"
SRC_URI[sha256sum] = "89efcbbbcbca5f725f4d8f9ea2fc5cbd51c6b22dc7a7cfa520a8285f641f4cc1"


S = "${WORKDIR}/guvcview-2.0.5+debian"

inherit autotools-brokensep pkgconfig 

PACKAGECONFIG ??= ""
PACKAGECONFIG[qt5] = "--enable-qt5, --disable-qt5, qt5-base"

FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "libsdl2 alsa-server alsa-lib alsa-conf"
