package utility;

import utility.Condition;
import utility.interfaces.BiFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;

import java.net.URI;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * ファイル操作における便利ツールです。
 *
 * @author KISHI Noriki
 */
public class FileUtility extends File implements Serializable {

    /** シリアルバージョンのユニークID */
    private static final long serialVersionUID = 8531245739641223373L;

    /**
     * コンストラクタ
     *
     * @param parent 親抽象パス名
     * @param child 子パス名文字列
     */
    public FileUtility(File parent, String child) {
        super(parent, child);
    }

    /**
     * コンストラクタ
     *
     * @param pathname 指定されたパス名文字列を抽象パス名に変換して、新しいFileのインスタンスを生成します。
     */
    public FileUtility(String pathname) {
        super(pathname);
    }

    /**
     * コンストラクタ
     *
     * @param uri 階層があの絶対URI。形式は、"file"、空でないパス・コンポーネント、未定義の権限、クエリー、フラグメント、コンポーネントと同等
     */
    public FileUtility(URI uri) {
        super(uri);
    }

	/**
	 * カレントディレクトリの取得
	 * @return カレントディレクトリを表す文字列
	 */
	public String getCurrrentDirectory() {
		return System.getProperty("user.dir");
	}

    /**
     * ファイルを1行ずつ読む。
     *
     * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
     */
    public void readlines(Consumer<String> aConsumer) {
        FileUtility.readlinesCore(this, aConsumer);
        return;
    }

    /**
     * 与えられたファイルを1行ずつ読む。
     *
     * @param aFile 解読するファイルオブジェクト
     * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
     */
    public static void readlines(File aFile, Consumer<String> aConsumer) {
        FileUtility.readlinesCore(aFile, aConsumer);
        return;
    }

    /**
     * 与えられたファイルを1行ずつ読む。
     *
     * @param aFilename 解読するファイル名
     * @param aConsumer 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列が入る。
     */
    public static void readlines(String aFilename, Consumer<String> aConsumer) {
        FileUtility.readlinesCore(new File(aFilename), aConsumer);
        return;
    }

    /**
     * インデックスをつけて、ファイルを1行ずつ読む。
     *
     * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
     */
    public void readlinesWithIndex(BiFunction<String, Integer> aBiFunction) {
        FileUtility.readlinesWithIndexCore(this, aBiFunction);
        return;
    }

    /**
     * 与えられたファイルを1行ずつ読む。
     *
     * @param aFile 解読するファイルオブジェクト
     * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
     */
    public static void readlinesWithIndex(File aFile, BiFunction<String, Integer> aBiFunction) {
        FileUtility.readlinesWithIndexCore(aFile, aBiFunction);
        return;
    }

    /**
     * 与えられたファイルを1行ずつ読む。
     *
     * @param aFilename 解読するファイル名
     * @param aBiFunction 1行ごとのに処理する内容。lambdaの引数には行を表すString文字列と繰り返し変数が入る。
     */
    public static void readlinesWithIndex(
            String aFilename, BiFunction<String, Integer> aBiFunction) {
        FileUtility.readlinesWithIndexCore(new File(aFilename), aBiFunction);
        return;
    }

    /**
     * ファイルを解読する共通処理
     *
     * @param aFile ファイルオブジェクト
     * @param aConsumer 1行ごとの処理を行うコンシューマ
     */
    private static void readlinesCore(File aFile, Consumer<String> aConsumer) {
        try {
            BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
            instanceOfBufferedReader.lines().forEach(aConsumer::accept);
            instanceOfBufferedReader.close();
        } catch (FileNotFoundException anException) {
            anException.printStackTrace();
        } catch (IOException anException) {
            anException.printStackTrace();
        }
        return;
    }

    /**
     * ファイルを解読する共通処理
     *
     * @param aFile ファイルオブジェクト
     * @param aBiFunction 1行ごとの処理を行う2つの引数に対応したlambda
     */
    private static void readlinesWithIndexCore(
            File aFile, BiFunction<String, Integer> aBiFunction) {
        try {
            BufferedReader instanceOfBufferedReader = new BufferedReader(new FileReader(aFile));
            AtomicInteger anAtomicInteger = new AtomicInteger();
            instanceOfBufferedReader
                    .lines()
                    .forEach(
                            aString -> {
                                aBiFunction.accept(
                                        aString,
                                        Integer.valueOf(anAtomicInteger.getAndIncrement()));
                            });
            instanceOfBufferedReader.close();
        } catch (FileNotFoundException anException) {
            anException.printStackTrace();
        } catch (IOException anException) {
            anException.printStackTrace();
        }
        return;
    }

	/**
	 * 改行コードを削除する
	 * @param aString 変換する文字列
	 * @return 改行コードを削除した文字列
	 */
	private static String replaceln(String aString) {
		return aString.replace(System.getProperty("line.separator").toString(), "");
	}

	/**
     * 対象がファイルでありそこにデータを書き込むことができるか検証する。
     *
     * @param aFilename 対象のファイル名
     */
    private static boolean validateFile(String aFilename) {
		return FileUtility.validateFile(new File(aFilename));
    }

	/**
     * 対象がファイルでありそこにデータを書き込むことができるか検証する。
     *
     * @param aFile 対象のファイルオブジェクト
	 * @return 対象がファイルであり、そこにデータを書き込むことができるか検証した結果
     */
    private static boolean validateFile(File aFile) {
		boolean result = false;
		Condition aCondition = new Condition(() -> aFile.exists());
		aCondition.ifFalse(() -> {
			try {
				aFile.createNewFile();
			} catch(IOException anException) {
				anException.printStackTrace();
			}
		});
		result = aFile.isFile() && aFile.canWrite();
		return result;
    }

	private static void write(BufferedWriter instanceOfBufferedWriter, String aLine) {
		try {
			System.out.printf("> %s", aLine);
			instanceOfBufferedWriter.write(aLine);
			instanceOfBufferedWriter.newLine();
		} catch(IOException anException) {
			anException.printStackTrace();
		}
	}

	/**
     * ファイルを1行ずつ書き込む。
     *
     * @param aFilename 書き込み先のファイル名
	 * @param aList 書き込む文字列データを1行ずつ格納したリスト
     */
    public static void writelines(String aFilename, List<String> aList) {
		FileUtility.writelinesCore(new File(aFilename), aList);
        return;
    }

	/**
     * ファイルを1行ずつ書き込む。
     *
     * @param aFile 書き込み先のファイルオブジェクト
	 * @param aList 書き込む文字列データを1行ずつ格納したリスト
     */
    public static void writelines(File aFile, List<String> aList) {
		FileUtility.writelinesCore(aFile, aList);
        return;
    }

	/**
     * ファイルを1行ずつ書き込む。
     *
     * @param aFile 書き込み先のファイルオブジェクト
	 * @param aList 書き込む文字列データを1行ずつ格納したリスト
     */
    public static void writelinesCore(File aFile, List<String> aList) {
		try (PrintWriter aPrintWriter = new PrintWriter(
			new BufferedWriter(
			new OutputStreamWriter(
			new FileOutputStream(aFile))))) {
			aList.forEach(aString -> {
				aPrintWriter.println(FileUtility.replaceln(aString));
			});
		}catch(IOException anException) {
			anException.printStackTrace();
		}
        return;
    }
}
