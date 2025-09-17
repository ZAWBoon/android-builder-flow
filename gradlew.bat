@echo off
:: Gradle start up script for Windows

set APP_HOME=%~dp0

set JAVA_EXE=java
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute
echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
exit /b 1

:execute
"%JAVA_EXE%" -cp "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
