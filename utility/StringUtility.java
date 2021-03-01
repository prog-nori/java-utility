package utility;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URISyntaxException;
import utility.FileUtility;

/**
 * 文字列操作における便利ツールです。
 *
 * @author KISHI Noriki
 */
public class StringUtility extends Object {

    /** StringBuilder aStringBuilder 文字列を操作するためのオブジェクト */
    private StringBuilder aStringBuilder;

    /** コンストラクタ */
    public StringUtility() {
        this("");
    }

    /**
     * コンストラクタ
     *
     * @param aString 初期値
     */
    public StringUtility(String aString) {
        this.aStringBuilder = new StringBuilder(aString);
    }

    /**
     * 選択範囲を取得する
     *
     * @param aStartingPosition 開始位置
     * @param anEndPosition 終了位置
     * @return String 指定範囲の文字列
     * @throws java.lang.IndexOutOfBoundsException 不適切なインデックスが指定されました。
     */
    public String get(int aStartingPosition, int anEndPosition) {
        StringUtility aStringUtility = new StringUtility();
        try {
            List<String> aList = this.split().subList(aStartingPosition, anEndPosition);
            aStringUtility.join(aList);
        } catch (IndexOutOfBoundsException anException) {
            anException.printStackTrace();
            System.out.println("[utility.StringUtility] Error: プログラムを終了します。");
            System.exit(-1);
        }
        return aStringUtility.toString();
    }

	/**
	 * URLからテキストを取得する
	 * @return 1行ずつリストされたテキスト
	 */
	public List<String> getTextFromURL() {
		return this.getTextFromURL(this.toString());
	}

	/**
	 * URLからテキストを取得する
	 * @param aString URLを表すStringオブジェクト
	 * @return 1行ずつリストされたテキスト
	 */
	public List<String> getTextFromURL(String aString) {
		List<String> aList = new ArrayList<>();
		try {
			aList = this.getTextFromURL(new URL(aString));
		} catch(MalformedURLException anException) {
			anException.printStackTrace();
		}
		return aList;
	}

	/**
	 * URLからテキストを取得する
	 * @param anURL 対象のURL
	 * @return 1行ずつリストされたテキスト
	 */
	public List<String> getTextFromURL(URL anURL) {
		List<String> aList = new ArrayList<>();
		FileUtility.readlinesFromURL(anURL, aList::add);
		return aList;
	}

    /**
     * 自身の末尾に文字列を結合する
     *
     * @param aString 末尾に結合する文字列
     */
    public void join(String aString) {
        this.aStringBuilder.append(aString);
        this.toString();
    }

    /**
     * 自身の末尾に文字列を連続して追加する
     *
     * @param strings 末尾に順に結合する文字列
     */
    public void join(String... strings) {
        this.join(Arrays.asList(strings));
    }

    /**
     * 自身の末尾に文字列を連続して追加する
     *
     * @param aList 末尾に順に文字列として結合するオブジェクト
     */
    public void join(List<? extends Object> aList) {
        aList.forEach(
                aString -> {
                    this.join(aString.toString());
                });
    }

    /**
     * 自身が持つ文字列オブジェクトを1文字ずつ分割したリストを返す
     *
     * @return 分割された文字列のリスト
     */
    public List<String> split() {
        return Arrays.asList(this.aStringBuilder.toString().split(""));
    }

    /**
     * 自身が持つ文字列オブジェクトを指定の文字列により分割する
     *
     * @param aSplitter 分割文字
     * @return 分割された文字列のリスト
     */
    public List<String> split(String aSplitter) {
        return Arrays.asList(this.aStringBuilder.toString().split(aSplitter));
    }

    /**
     * 自身が持つ文字列オブジェクトを1文字ずつ分割したリストを返す
     *
     * @return 分割された文字列のリスト
     */
    public List<String> toArray() {
        return this.split();
    }

    /**
     * 自身を文字列として返す
     *
     * @return 自身を表すが返されます。
     */
    @Override
    public String toString() {
        return this.aStringBuilder.toString();
    }
}
