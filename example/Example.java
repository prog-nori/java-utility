package example;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import utility.FileUtility;
import utility.StringUtility;

/**
 * 各プログラムをテストする例題クラス
 * @author KISHI Noriki
 */
public class Example extends Object {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		Example anExample = new Example();
		anExample.exampleOfStringUtility(Arrays.asList(args));
		anExample.exampleOfFileUtility(Arrays.asList(args));
		return;
	}

	/**
	 * 文字列操作メソッドの使用例をまとめたメソッド
	 * @param aList コマンドライン引数をリストに変換したもの
	 */
	private void exampleOfStringUtility(List<String> aList) {
		StringUtility aStringUtility = new StringUtility();
		aStringUtility.join("吾輩", "は", "ちっぱいである");
		System.out.println(aStringUtility);

		StringUtility anotherStringUtility = new StringUtility("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		System.out.println(anotherStringUtility.get(1, 3));

		return;
	}

	/**
	 * ファイル操作メソッドの使用例をまとめたメソッド
	 * @param aList コマンドライン引数をリストに変換したもの
	 */
	private void exampleOfFileUtility(List<String> aList) {
		System.out.printf("%nexampleOfIfleUtility1%n");
		this.exampleOfFileUtility1();

		System.out.printf("%nexampleOfIfleUtility2%n");
		this.exampleOfFileUtility2();

		System.out.printf("%nexampleOfIfleUtility3%n");
		this.exampleOfFileUtility3();

		System.out.printf("%nexampleOfIfleUtility4%n");
		this.exampleOfFileUtility4();

		return;
	}

	/**
	 * ラムダによるファイル操作の実例1
	 * 動的メソッドを呼び出す。
	 */
	private void exampleOfFileUtility1() {
		FileUtility aFileUtility = new FileUtility("./ren.txt");
		aFileUtility.readlines(System.out::println);
		return;
	}

	/**
	 * ラムダによるファイル操作の実例2
	 * 静的メソッドを呼び出す。
	 * ラムダ式の第一引数をファイルオブジェクトとする。
	 */
	private void exampleOfFileUtility2() {
		AtomicInteger anAtomicInteger = new AtomicInteger();
		FileUtility.readlines(new File("./ren.txt"), System.out::println);
		return;
	}

	/**
	 * ラムダによるファイル操作の実例3
	 * 静的メソッドを呼び出す。
	 * ラムダの第一引数を、ファイル名を表す文字列とする。
	 */
	private void exampleOfFileUtility3() {
		AtomicInteger anAtomicInteger = new AtomicInteger();
		FileUtility.readlines("./ren.txt", System.out::println);
		return;
	}

	/**
	 * ラムダによるファイル操作の実例4
	 * 静的メソッドを呼び出す。
	 * exampleOfFileUtility3をインデックスに対応したメソッドを操作してみる
	 */
	private void exampleOfFileUtility4() {
		FileUtility.readlinesWithIndex("./ren.txt", (aLine, anIndex) -> {
			System.out.printf("%d: %s%n", anIndex, aLine);
		});
		return;
	}
}
