#!/bin/bash

version_append=$(date +%Y%m%d%H%M%S)
meta_append=$(git log -1 --no-decorate --format=%h)

mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}-${version_append}+${meta_append} -DgenerateBackupPoms=true
mvn clean deploy -Psign
mvn versions:revert
