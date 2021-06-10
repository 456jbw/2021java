#!bash
mkdir bin
javac -cp ./src/ -d ./bin src/main/Start.java -encoding UTF-8
java -cp ./bin/ main.Start
