#!/bin/bash
# -*- coding: utf-8 -*-
THREADS=5  # スレッド数
readonly PWD=`pwd`  # カレントディレクトリ
TARGET="utility"  # ターゲット名

function test_function () {
	# classesディレクトリが存在しなければ作成
	if [ ! -e ./classes ]; then
		mkdir -p $PWD/classes;
	fi

	# cd ./utility; javac -d ../classes *.java
	cd ./example; javac -d ../classes *.java
	if [ $? = 0 ]; then
		# run
		cd ../classes/;
		java -cp . example.Example
	else
		echo 'build failed'
	fi
}

function zip_function() {
	# 不要なファイルの削除
	rm -rf $PWD/classes
	# 一階層戻ってディレクトリ名と同名で圧縮
	cd ../;
	tar cvzf  $TARGET.tgz $TARGET
}

function doc_function() {

	if [ -d `$PWD/docs` ]; then
		# ディレクトリが存在した場合は削除
		rm -rf `$PWD/docs`
	fi

	javadoc -d docs ./example/utility/*.java

	open -a Safari ./docs/index.html
}


if [ $1 == 'test' ]; then
	# コンパイル&実行
	test_function
elif [ $1 == 'test-measure' ]; then
	# コンパイル&実行 * 5回
	for i in {1..5}
	do
		test_function
	done
elif [ $1 == 'zip' ]; then
	# tar圧縮
	zip_function

elif [ $1 == 'doc' ]; then
	doc_function
fi
