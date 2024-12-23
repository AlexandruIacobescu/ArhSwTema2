@echo off
echo Starting RMI registry...
start rmiregistry
pause

echo Starting Data node...
start java -cp src Data Studenti.txt Cursuri.txt
pause

echo Starting Logic node...
start java -cp src Logic
pause

echo Starting Logger node...
start java -cp src Logger
pause

echo Starting Client...
java -cp src Client localhost
pause