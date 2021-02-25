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

public class FileUtility extends File implements Serializable {

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

	public void readlines(Consumer<String> aConsumer) {
		FileUtility.readlinesCore(this, aConsumer);
		return;
	}

	public static void readlines(File aFile, Consumer<String> aConsumer) {
		FileUtility.readlinesCore(aFile, aConsumer);
		return;
	}

	public static void readlines(String aFilename, Consumer<String> aConsumer) {
		FileUtility.readlinesCore(new File(aFilename), aConsumer);
		return;
	}

	public void readlinesWithIndex(BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(this, aBiFunction);
		return;
	}

	public static void readlinesWithIndex(File aFile, BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(aFile, aBiFunction);
		return;
	}

	public static void readlinesWithIndex(String aFilename, BiFunction<String, Integer> aBiFunction) {
		FileUtility.readlinesWithIndexCore(new File(aFilename), aBiFunction);
		return;
	}

	private static void readlinesCore(File aFile, Consumer<String> aConsumer) {
		try {
			BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
			String aString;
			// while使わない方向にどうにかリファクタリングできないかしら
			while((aString = instanceOfBufferedReader.readLine()) != null) {
				aConsumer.accept(aString);
			}
			instanceOfBufferedReader.close();
		} catch(FileNotFoundException anException) {
			anException.printStackTrace();
		}catch(IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

	private static void readlinesWithIndexCore(File aFile, BiFunction<String, Integer> aBiFunction) {
		try {
			BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
			AtomicInteger anAtomicInteger = new AtomicInteger();
			String aString;
			// while使わない方向にどうにかリファクタリングできないかしら
			while((aString = instanceOfBufferedReader.readLine()) != null) {
				aBiFunction.accept(aString, Integer.valueOf(anAtomicInteger.getAndIncrement()));
			}
			instanceOfBufferedReader.close();
		} catch(FileNotFoundException anException) {
			anException.printStackTrace();
		}catch(IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

}
