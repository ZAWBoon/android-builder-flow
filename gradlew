#!/bin/sh
##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Add default JVM options here if needed
DEFAULT_JVM_OPTS=""

APP_HOME=$(dirname "$0")

# Locate java binary
if [ -n "$JAVA_HOME" ] ; then
    JAVA_CMD="$JAVA_HOME/bin/java"
else
    JAVA_CMD="java"
fi

# Check if Java exists
if [ ! -x "$JAVA_CMD" ] ; then
    echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." 1>&2
    exit 1
fi

# Run Gradle Wrapper
exec "$JAVA_CMD" $DEFAULT_JVM_OPTS -cp "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
