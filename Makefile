all: lib src test

lib: ./lib/Examen/*.java
	javac -d build lib/Examen/*.java

src: ./src/*.java
	javac -d build src/*.java

test: ./Test.java
	javac -cp build -d build Test.java
	cd build; java Test	
