DESCRIPTION = "OpenBLAS is an optimized BLAS library based on GotoBLAS2 1.13 BSD version."
SUMMARY = "OpenBLAS : An optimized BLAS library"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
HOMEPAGE = "http://www.openblas.net/"
SECTION = "libs"
LICENSE = "BSD-3-Clause"

DEPENDS = "make libgfortran"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"

SRC_URI = "git://github.com/xianyi/OpenBLAS.git;branch=develop"

SRCREV = "9e2bb0c6417ade4a9cf4a5787e0eb9fd491e8fc3"

S = "${WORKDIR}/git"

# Attempt to set OPENBLAS_TARGET based on TUNE_FEATURES
python __anonymous() {
    target = d.getVar("OPENBLAS_TARGET", True)

    # Do nothing if the target is set
    if target:
        return

    # Target maps in ascending order
    tgt = [
        # ARM
        "ARMV5", "ARMV6", "ARMV7", "CORTEXA7", "CORTEXA9", "CORTEXA15",
        # AARCH64
        "ARMV8", "CORTEXA57"
    ]

    dic = {
        # ARM
        "ARMV5": { "armv5" },
        "ARMV6" : { "armv6" },
        "ARMV7": { "armv7" },
        "CORTEXA7": { "cortexa7" },
        "CORTEXA9": { "cortexa9" },
        "CORTEXA15": { "cortexa15", "cortexa15-cortexa7" },
        # AARCH64
        "ARMV8": { "aarch64" },
        "CORTEXA57": { "cortexa57", "cortexa57-cortexa53" },
    }

    tune = set(d.getVar("TUNE_FEATURES", True).split())
    for t in tgt:
        if tune & dic[t]:
            target = t

    d.setVar("OPENBLAS_TARGET", target)
}

def map_bits(a, d):
        import re
        if re.match('i.86$', a): return 32
        elif re.match('x86_64$', a): return 64
        elif re.match('aarch32$', a): return 32
        elif re.match('aarch64$', a): return 64
        return 32

do_configure_append () {
	sed -i 's/-ru/-r/g' ${S}/Makefile
	sed -i 's/-ru/-r/g' ${S}/Makefile.tail
	sed -i 's/-ru/-r/g' ${S}/reference/Makefile
	sed -i 's/-ru/-r/g' ${S}/interface/Makefile
}

do_compile () {
    oe_runmake HOSTCC="${BUILD_CC}"  \
    PREFIX=${exec_prefix} \
    CROSS_SUFFIX=${HOST_PREFIX} \
    CROSS=1 \
    ONLY_CBLAS=1 \
    TARGET='${OPENBLAS_TARGET}'
}

do_install() {
    oe_runmake HOSTCC="${BUILD_CC}" \
    PREFIX=${exec_prefix} \
    DESTDIR=${D} \
    install

    rm -rf ${D}${bindir}
    rm -rf ${D}${libdir}/cmake
}

FILES_${PN}     = "${libdir}/*"
FILES_${PN}-dev = "${includedir} ${libdir}/lib${PN}.so"
