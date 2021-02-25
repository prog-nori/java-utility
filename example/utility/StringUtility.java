package utility;

import java.util.Arrays;
import java.util.List;

public class StringUtility {

	private StringBuilder aStringBuilder;

	/**
	* コンストラクタ
	*/
	public StringUtility() {
		this("");
	}

	/**
	* コンストラクタ
	* @param String aString 初期値
	*/
	public StringUtility(String aString) {
		this.aStringBuilder = new StringBuilder(aString);
	}

	/**
	* 自身の末尾に文字列を結合する
	* @param String aString 末尾に結合する文字列
	*/
	public void join(String aString) {
		this.aStringBuilder.append(aString);
		this.toString();
	}

	/**
	* 自身の末尾に文字列を連続して追加する
	* @param String[] aString 末尾に順に結合する文字列
	*/
	public void join(String... strings) {
		this.join(Arrays.asList(strings));
	}

	/**
	* 自身の末尾に文字列を連続して追加する
	* @param List<? extends Object> aList 末尾に順に文字列として結合するオブジェクト
	*/
	public void join(List<? extends Object> aList) {
		aList.forEach(aString -> {
			this.join(aString.toString());
		});
	}

	/**
	* 自身を文字列として返す
	* @return String 自身を表すが返されます。
	*/
	@Override
	public String toString() {
		return this.aStringBuilder.toString();
	}
}
