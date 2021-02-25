package utility.interfaces;

import java.util.Objects;

@FunctionalInterface
public interface BiFunction<E, I> {
	void accept(E anElement, I anIndex);

	default BiFunction<E, I> andThen(BiFunction<? super E, ? super I> after) {
		Objects.requireNonNull(after);
		return (E t, I i) -> { accept(t, i); after.accept(t, i); };
	}
}
