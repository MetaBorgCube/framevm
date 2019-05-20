#!/bin/bash
projects=(
    "framevm-core"
    "framevm-stacy"
    "framevm-roger"
    "framevm-stacy.test"
    "framevm-roger.test"
)


FAILED_CLEAN="\e[31mCLEAN FAILED:\e[39m"
SHOW_FC=false
FAILED_INSTALL="\e[31mINSTALL FAILED:\e[39m"
SHOW_FI=false

for project in "${projects[@]}"
do
    cd "$project" >/dev/null || exit 1
    if ! mvn clean; then
        FAILED_CLEAN+="\n  \e[33m$project\e[39m"
        SHOW_FC=true
    fi
    if ! mvn install; then
        FAILED_INSTALL+="\n  \e[33m$project\e[39m"
        SHOW_FI=true
    fi
    cd - >/dev/null || exit 1
done
if [[ "$SHOW_FC" = "true" ]]; then
    echo -e "$FAILED_CLEAN"
fi
if [[ "$SHOW_FI" = "true" ]]; then
    echo -e "$FAILED_INSTALL"
fi
