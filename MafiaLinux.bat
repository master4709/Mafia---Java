ECHO Mafia 4.20: A CPSC 233 Project.
javac -d src -verbose -classpath ".:miglayout15-swing.jar" src\displayGame\*.java src\displayMain\*.java src\logic\*.java src\displaySetUp\*.java src\myJStuff\*.java src\playerInfo\*.java src\run\*.java
cd src
java -cp "miglayout15-swing.jar:" run.RunMafia
pause