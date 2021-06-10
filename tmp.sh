#!/bin/bash
mkdir bin
javac -cp ./src/ -d ./bin src/views/ChooseView.java -encoding UTF-8 && \
	java -cp ./bin/ views.ChooseView
