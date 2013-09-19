@ECHO OFF

@ECHO BUILING TEST DIRECTORY
javac -d build lib/Examen/*.java

@ECHO BUILDING SOURCE FILES
javac -d build src/*.java

@ECHO BUILDING TEST FILE
javac -cp build -d build test/*.java

cd build 

@ECHO ON
java Test

@ECHO OFF
cd..


@ECHO Test complete.
