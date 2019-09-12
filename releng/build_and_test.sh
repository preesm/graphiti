#!/bin/bash -eu

DIR=$(cd `dirname $0` && echo `git rev-parse --show-toplevel`)

SONAR=NO
CI=NO
RUNTEST=YES

if [ ! -z ${1+x} ]; then
  if [ "$1" == "--sonar" ]; then
    SONAR=YES
  fi
  if [ "$1" == "--notest" ]; then
    RUNTEST=NO
  fi
fi
if [ ! -z ${TRAVIS+x} ]; then
  CI=YES
  SONAR=YES
fi


if [ "${RUNTEST}" == "NO" ]; then
  TESTMODE="-DskipTests=true -Dmaven.test.skip=true"
  BUILDGOAL=package
  SONAR=NO
else
  TESTMODE=
  BUILDGOAL=verify
fi

if [ "${CI}" == "YES" ]; then
  BATCHMODE=-B
else
  BATCHMODE=
fi

echo ""
echo " -- Build script values --"
echo "Batch mode = ${BATCHMODE}"
echo "Run tests = ${RUNTEST}"
echo "Test mode = ${TESTMODE}"
echo "Sonar = ${SONAR}"
echo "Build goal = ${BUILDGOAL}"
echo " --"
echo ""

time (cd $DIR && mvn -e -C ${BATCHMODE} clean ${BUILDGOAL} ${TESTMODE})

# Sonar
if [ "${SONAR}" == "YES" ]; then
  # needs to be run in a second step; See:
  # https://community.sonarsource.com/t/jacoco-coverage-switch-from-deprecated-binary-to-xml-format-in-a-tycho-build-shows-0/
  (cd $DIR && mvn -e -C ${BATCHMODE} -Dtycho.mode=maven jacoco:report -Djacoco.dataFile=../../target/jacoco.exec sonar:sonar)
fi
exit 0
