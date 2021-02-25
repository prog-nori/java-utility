package utility.interfaces;

import java.util.Objects;

/**
 * 2つの引数をとるインタフェース
 * @author KISHI Noriki
 */
@FunctionalInterface
public interface BiFunction<E, I> {
	/**
	 * 指定された引数でオペレーションを実行する。
	 * @param anElement 入力引数
	 * @param anIndex インデックス
	 */
	void accept(E anElement, I anIndex);

	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成BiFunctionを返す。
	 * @param after このオペレーションを実行した後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成
	 */
	default BiFunction<E, I> andThen(BiFunction<? super E, ? super I> after) {
		Objects.requireNonNull(after);
		return (E t, I i) -> {
			accept(t, i);
			after.accept(t, i);
		};
	}
}
