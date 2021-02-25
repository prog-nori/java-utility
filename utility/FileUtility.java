package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import java.net.URI;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import utility.interfaces.BiFunction;

/**
 * ファイル操作における便利ツールです。
 * @author KISHI Noriki
 */
 public class FileUtility extends File implements Serializable {

	/**
	 * シリアルバージョンのユニークID
	 */
	private static final long serialVersionUID = 8531245739641223373L;

	/**
	 * コンストラクタ
	 * @param parent 親抽象パス名
	 * @param child 子パス名文字列
	 */
	public FileUtility(File parent, String child) {
		super(parent, child);
	}

	/**
	 * コンストラクタ
	 * @param pathname 指定されたパス名文字列を抽象パス名に変換して、新しいFileのインスタンスを生成します。
	 */
	public FileUtility(String pathname) {
		super(pathname);
	}

	/**
	 * コンストラクタ
	 * @param uri 階層があの絶対URI。形式は、"file"、空でないパス・コンポーネント、未定義の権限、クエリー、フラグメント、コンポーネントと同等
	 */
	public FileUtility(URI uri) {
		super(uri);
	}

	/**
	 * ファイルを1行ずつ読む。
	 * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
	 */
	public void readlines(Consumer<String> aConsumer) {
		FileUtility.readlinesCore(this, aConsumer);
		return;
	}

	/**
	 * 与えられたファイルを1行ずつ読む。
	 * @param aFile 解読するファイルオブジェクト
	 * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
	 */
	public static void readlines(File aFile, Consumer<String> aConsumer) {
		FileUtility.readlinesCore(aFile, aConsumer);
		return;
	}

	/**
	 * 与えられたファイルを1行ずつ読む。
	 * @param aFilename 解読するファイル名
	 * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
	 */
	public static void readlines(String aFilename, Consumer<String> aConsumer) {
		FileUtility.readlinesCore(new File(aFilename), aConsumer);
		return;
	}

	/**
	 * インデックスをつけて、ファイルを1行ずつ読む。
	 * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
	 */
	public void readlinesWithIndex(BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(this, aBiFunction);
		return;
	}

	/**
	 * 与えられたファイルを1行ずつ読む。
	 * @param aFile 解読するファイルオブジェクト
	 * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
	 */
	public static void readlinesWithIndex(File aFile, BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(aFile, aBiFunction);
		return;
	}

	/**
	 * 与えられたファイルを1行ずつ読む。
	 * @param aFilename 解読するファイル名
	 * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
	 */
	public static void readlinesWithIndex(String aFilename, BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(new File(aFilename), aBiFunction);
		return;
	}

	/**
	 * ファイルを解読する共通処理
	 * @param aFile ファイルオブジェクト
	 * @param aConsumer 1行ごとの処理を行うコンシューマ
	 */
	private static void readlinesCore(File aFile, Consumer<String> aConsumer) {
		try {
			BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
			instanceOfBufferedReader.lines().forEach(aConsumer::accept);
			instanceOfBufferedReader.close();
		} catch(FileNotFoundException anException) {
			anException.printStackTrace();
		}catch(IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

	/**
	 * ファイルを解読する共通処理
	 * @param aFile ファイルオブジェクト
	 * @param aBiFunction 1行ごとの処理を行う2つの引数に対応したlambda
	 */
	private static void readlinesWithIndexCore(File aFile, BiFunction<String, Integer> aBiFunction) {
		try {
			BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
			AtomicInteger anAtomicInteger = new AtomicInteger();
			instanceOfBufferedReader.lines().forEach(aString -> {
				aBiFunction.accept(aString, Integer.valueOf(anAtomicInteger.getAndIncrement()));
			});
			instanceOfBufferedReader.close();
		} catch(FileNotFoundException anException) {
			anException.printStackTrace();
		}catch(IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

}
