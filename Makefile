ANT = env LC_ALL=ja_JP.UTF-8 ant
ARCHIVE = $(shell basename `pwd`)

all:
	$(ANT) all

test:
	$(ANT) test

# test:
# 	sh ./MakefileDriver.sh test

doc:
	sh ./MakefileDriver.sh doc
