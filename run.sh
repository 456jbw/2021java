#!bash
mkdir bin
javac -cp ./src/ -d ./bin src/main/DrawDemo.java -encoding UTF-8
java -cp ./bin/ main.DrawDemo
