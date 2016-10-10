[![Build Status](https://travis-ci.org/juergen-rocks/jython-maven-plugin.svg?branch=master)](https://travis-ci.org/juergen-rocks/jython-maven-plugin)
[![Coverage Status](https://coveralls.io/repos/github/juergen-rocks/jython-maven-plugin/badge.svg?branch=master)](https://coveralls.io/github/juergen-rocks/jython-maven-plugin?branch=master)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/juergen-rocks/jython-maven-plugin/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/juergen-rocks/jython-maven-plugin.svg)](https://github.com/juergen-rocks/jython-maven-plugin/issues)
[![GitHub forks](https://img.shields.io/github/forks/juergen-rocks/jython-maven-plugin.svg)](https://github.com/juergen-rocks/jython-maven-plugin/network)

# jython-maven-plugin

A simple maven plugin to run python scripts during maven build – you can now use python 2.7 scripts within a maven run.

You can use the complete standard library that comes with Jython 2.7.

## Dependencies for your project

```xml
<plugin>
    <groupId>rocks.juergen</groupId>
    <artifactId>jython-maven-plugin</artifactId>
    <version>${jython-maven-plugin.version}</version>
    <configuration>
        <!-- See configuration details -->    
    </configuration>
</plugin>
```

By default, the plugin is bound to the `generate-resources` phase. With the `<execution>` tag, you can bind it to other phases.

## Using inline script in POM

The easiest use case is the usage of an inline script, directly included in your POM. Example:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>rocks.juergen</groupId>
            <artifactId>jython-maven-plugin</artifactId>
            <version>${jython-maven-plugin.version}</version>
            <configuration>
                <python><![CDATA[
for i in range(3):
    print 'hello, world!'
                ]]></python>    
            </configuration>        
        </plugin>
    </plugins>
</build>
```

Remark: Due to python's need for correct indentation, your inline python code must begin at the «real» beginning of the line.

Also, using `CDATA` is a good practise to keep your POM valid and clean.

If you have only a single line in your inline script, you can simply to this:

```xml
<python><![CDATA[print 'hello, world!']]></python>
```

## Using script file

## Executing scripts directly

# Project information

## License

## Issue tracker

## Pull Requests

## Class Diagram Overview

[![Class Diagram](./doc/class-diagram.png)](./doc/class-diagram.png)

## Siging key

All artefacts are signed [with this key](./doc/juergen.rocks-signing-key.asc):

```text
-----BEGIN PGP PUBLIC KEY BLOCK-----
Version: GnuPG v2

mQINBFf7gowBEAD4n7xBlwUPec0vgkKdPrTSuxMJsEv2InLFfkdpROuxwAfoiE6M
HTmHnDvrf02oaalgfP5+cwOLCPTgFCXmMnpyVIQLZgucr1cwMHYbfWcGayXuyMQQ
YpMPRxzPRZ/BIZQ+Qg8r1/HOvCUOfa5zveX/oLGgUFIIbokOYG9+Krka5GL16tiY
6C5y3DbzPG+l04WhsUhQGdkDfNqa8SzzhdwxiMKi3hZFObXCffw9KCMadTEfLAEr
43NJg++kiWcF56w78YUwrNUuVAFEuMbzGEnPuLUzXGsUCicaUNksA64MnwSDDOkn
oJn5juVy0ky4UdW1PtvIWGbkgmbGQZqGjHjx2SPobSmKuz2wjv0R58n5gOEG8jij
jY7DL0fsMgOCdzqMMOjhOOIhydB2BzEPolYSbtUZnE09CwuEaC1JsmC4OFQJTqjM
wKYLCeOWLfn8U+YzHkomvGyE6f3+hPnP4RhLE1/0NrjEuuH0ivcIHwbYhjzGUrM5
IzDlPnSqFah2CLx400PUUavnriGIV20Tls0ohFREWh+qx0EQTh8Ep7FPQLJR1l89
6Dp6QL3NMFP2dnaEyro7/go+rfWxpYH5MIpaIisL+zYRn1ptZb58lzIt5YFrmIxQ
ifmeX9dY/OVykgA9rw34QzwoNKRReN1pT1372s4qLitWrTF8JSYrYJQtyQARAQAB
tGVqdWVyZ2VuLnJvY2tzIENvZGUgU2lnbmluZyAoQ29kZSBTaWduaW5nIEtleSBm
b3IganVlcmdlbi5yb2NrcyBTb2Z0d2FyZSBQcm9qZWN0cykgPGRldkBqdWVyZ2Vu
LnJvY2tzPokCOQQTAQgAIwUCV/uCjAIbAwcLCQgHAwIBBhUIAgkKCwQWAgMBAh4B
AheAAAoJEBr3gZEFULGIZusP/AtYrad2SZCMtI2n7jGUReqsMn7n/uoGyCBEPa1Y
ZVDFL+hcqP2aGGTEB/X1cmQDLJciIZM6tSxsUDXolEAsafcT+4IPj4i2dT1mSNBs
jc3gVTj/Dxs7oSehnvNfqe9uzbjPko8c2P1TCnB0gkwd1peiYYILOS8t7Oh6Jl2n
B4Wu+XcHX7kOO+bEnYO5FNOtMsGYy+6TyNTdQiMSkegOG83hzT7Lm8KmKFVWktw4
qihdkgpw6jLZo4L6CRe6jwB3LURS84kUpj72bqeedEQ7gC1SsVsLC9yWngfq0POY
MYWDklqeGY51cXochfjOeiXS4DaN/rY5fWELEN1uNyarbwpqtkpieGeW/GJqz34E
dvZ0+8fQX8jM4SKRIy2FX2fY9FRLyb6BMidEa74v/C9YT5tZlETozwyJjfYdDVfC
xDK3R+YgdRg0nLCESQ/LTsdSu3tF4ICNQsZsfWeuSPenJwZ5L2fiHHWf9N0d/Hjg
VyIY8rzB9hn8gCOLwqqRan8bkBrbjCsfK75dvkfAOxBJhv1Wo5UQM3UP3cq5oBDB
134YPuBOCTrCcND/M+PkgZ9UDPELPrtlgY9kZZRnE2C52BPiW3MbpCknJ9kPnXIq
wdhZSoOUhsKkX17aQSK/H+O2ycZT60U4S1gmo3A3iBdKY566AN5kXJQl4mtXTlO2
a63T
=Kg9S
-----END PGP PUBLIC KEY BLOCK-----
```
