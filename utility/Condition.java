package utility;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Condition {

	/**
	* 保存された条件
	*/
	private Supplier<Boolean> aCondition;

	/**
	* コンストラクタ
	* @params aGotCondition 評価したい条件
	*/
	public Condition(Supplier<Boolean> aGotCondition) {
		this.aCondition = null;
		this.aCondition = aGotCondition;
	}

	/**
	 * すべての条件を満たしているか調べる
	 * @param booleans 判定する条件式
	 * @return すべての条件が真ならTrue、そうでなければFalse
	 */
	public static boolean andAll(boolean... booleans) {
		AtomicBoolean anAtomicBoolean = new AtomicBoolean(true);
		System.out.println(Arrays.asList(booleans).getClass());
		// Arrays.asList(booleans).forEach((aBoolean) -> {
		// 	anAtomicBoolean.set(anAtomicBoolean.get() == true && aBoolean == true);
		// 	// (new Condition(() -> aBoolean))
		// 	// .ifFalse(() -> {
		// 	// 	anAtomicBoolean.set(false);
		// 	// });
		// });
		return anAtomicBoolean.get();
	}

	/**
	* 与えられたコンシューマを実行し、正常に実行できたかのステータスを返す
	* @params aConsumer 条件に対するコンシューマ
	* @return 例外なく実行できたかどうかの真偽値
	*/
	private boolean done(Consumer<Condition> aConsumer) {
		try {
			aConsumer.accept(this);
			return true;
		} catch(Exception anException) {
			return false;
		}
	}

	/**
	* 条件が偽の時に実行する処理
	*/
	public void ifFalse(final Runnable aRunnable) {
		this.ifThenElse(() -> {}, aRunnable);
	}

	/**
	* 条件が偽の時に実行する処理
	*/
	public void ifFalse(final Consumer<Condition> aConsumer) {
		this.ifThenElse(pattern -> {}, aConsumer);
	}

	/**
	* 条件が正の時に実行する処理
	*/
	public void ifTrue(final Runnable aRunnable) {
		this.ifThenElse(aRunnable, () -> {});
	}

	/**
	* 条件が正の時に実行する処理
	*/
	public void ifTrue(final Consumer<Condition> aConsumer) {
		this.ifThenElse(aConsumer, pattern -> {});
	}

	/**
	* 条件が正の時に実行する処理と偽の時に実行する処理
	*/
	public void ifThenElse(final Runnable aRunnable, final Runnable anotherRunnable) {
		this.ifThenElse(pattern1 -> aRunnable.run(), pattern2 -> anotherRunnable.run());
	}

	/**
	* 条件が正の時に実行する処理と偽の時に実行する処理
	*/
	public void ifThenElse(final Consumer<Condition> aConsumer, final Consumer<Condition> anotherConsumer) {
		// boolean boolean2int = this.aCondition.get()? this.done(aConsumer): this.done(anotherConsumer);
		int boolean2int = this.aCondition.get()? 1: 0;
		Arrays.asList(this.done(aConsumer), this.done(anotherConsumer)).get(boolean2int);
		// Arrays.asList(this.done(aConsumer.accept()), this.done(anotherConsumer.accept())).get(boolean2int);
	}

	/**
	 * 与えられたオブジェクトがnullでない(インスタンスかされているかどうか)調べる
	 * @param anObject 判定するオブジェクト
	 * @return nullでなければtrue, nullならfalse
	 */
	public static boolean isNotNil(Object anObject) {
		return anObject != null;
	}
}
