@echo off
echo Compiling source files...
javac src/*.java

echo Generating RMI stubs/skeletons...
rmic -d src Logger
rmic -d src Logic
rmic -d src Data

echo Build completed.
pause