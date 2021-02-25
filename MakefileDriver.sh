#! /bin/bash
# -*- coding: utf-8 -*-

if [ $1 == 'format' ]; then
	echo '全てのjavaファイルをフォーマットし、置き換えます。'
	java -jar ./google-java-format-1.9-all-deps.jar --aosp --replace */*.java
fi
