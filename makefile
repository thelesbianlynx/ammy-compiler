
#Sources
SRC = $(shell find src -type f -name '*.java')

#Classes
CLASSES = $(patsubst src/%.java, out/classes/%.class, $(SRC))

.PHONY: build
build: $(CLASSES)

.PHONY: run
run: build
	java -cp out/classes net.ammy.precompile.Precompiler

out/classes/%.class: src/%.java
	javac -d out/classes -cp src $<

.PHONY: clean
clean:
	rm -r out/
