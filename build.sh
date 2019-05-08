#!/bin/sh

# Build FVM-languages
cd framevm-core
mvn install

cd ../framevm-stacy
mvn install

cd ../framevm-roger
mvn install
cd ../

# Run the tests

cd framevm-stacy.test
mvn install || true

cd ../framevm-roger.test
mvn install || true
