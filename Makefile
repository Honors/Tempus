all: lib src test exe

lib: ./lib/Examen/*.java
	javac -d build lib/Examen/*.java

src: ./src/*.java
	javac -d build src/*.java

test: ./test/*.java
	javac -cp build -d build test/*.java

exe: build/*.class 
	cd build; java Test
