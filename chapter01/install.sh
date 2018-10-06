#!/bin/sh
# build java
cd main
./gradlew build
mv build/libs/OOAD-0.1.jar ../server/OOAD-0.1.jar

# install web dependencies
cd ../gui
yarn
