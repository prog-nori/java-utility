ANT = env LC_ALL=ja_JP.UTF-8 ant
ARCHIVE = $(shell basename `pwd`)

all:
	$(ANT) all

test:
	$(ANT) test

doc:
	$(ANT) doc

clean:
	$(ANT) clean

zip:
	$(ANT) zip

format:
	sh ./MakefileDriver.sh format
