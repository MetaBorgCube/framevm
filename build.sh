#!/bin/bash
projects=(
    "framevm-core"
    "framevm"
    "framevm.test"
    "framevm.eclipse"
    "framevm.eclipse.feature"
    "framevm.eclipse.site"
)


if [[ -n "$1" ]] && [[ "$1" = "-v" ]]; then
    SILENT=0
else
    SILENT=1
fi


silence() {
    if [[ ! 0 -eq "$SILENT" ]]; then
        "$@" > /dev/null 2>&1
    else
        "$@"
    fi  
}


FAILED_CLEAN="\e[31mCLEAN FAILED:\e[39m"
SHOW_FC=false
FAILED_INSTALL="\e[31mINSTALL FAILED:\e[39m"
SHOW_FI=false

for project in "${projects[@]}"
do
    cd "$project" >/dev/null || exit 1
    echo -e "\e[92mCleaning $project\e[39m"
    if ! silence mvn clean; then
        FAILED_CLEAN+="\n  \e[33m$project\e[39m"
        SHOW_FC=true
    fi  
    echo -e "\e[92mInstalling $project\e[39m"
    if ! silence mvn install; then
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
