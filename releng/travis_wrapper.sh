#!/bin/bash
# Abort on Error
set -e

DIR=$(cd `dirname $0` && echo `git rev-parse --show-toplevel`)

PING_SLEEP=30s
LINES=5000
BUILD_OUTPUT="${DIR}/build.log"

touch "${BUILD_OUTPUT}"

function dump_output() {
   echo "Tailing the last ${LINES} lines of output:"
   tail -${LINES} "${BUILD_OUTPUT}"
}
function error_handler() {
  kill ${PING_LOOP_PID}
  echo "ERROR: An error was encountered with the build."
  dump_output
  exit 1
}

# If an error occurs, run our error handler to output a tail of the build
trap 'error_handler' ERR

# Set up a repeating loop to send some output to Travis.

echo "--"
echo "--            Travis Wrapper"
echo "--            --------------"
echo "--"
echo "-- Storing stdout & stderr in ${BUILD_OUTPUT}"
echo "-- Ping every '${PING_SLEEP}' to keep travis from failing"
echo "-- Prints last ${LINES} lines when exiting"
echo "--"

bash -c "while true; do echo \"[...]\"; tail -n 10 \"${BUILD_OUTPUT}\"; sleep ${PING_SLEEP}; done" &
PING_LOOP_PID=$!

# My build is using maven, but you could build anything with this, E.g.

"${DIR}/releng/build_and_test.sh" >> "${BUILD_OUTPUT}" 2>&1

# The build finished without returning an error so dump a tail of the output
dump_output

# nicely terminate the ping output loop
kill ${PING_LOOP_PID}
