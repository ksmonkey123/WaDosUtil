package ch.waan.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.waan.function.ErrableFunction;
import ch.waan.function.ErrableSupplier;

public interface Result<T> {

	static <T> Result<T> of(T value) {
		return Result.eval(() ->
			{
				Objects.requireNonNull(value);
				return value;
			});
	}

	static <T> Result<T> ofNullable(T value) {
		return value == null ? new NoneResult<>() : new ValueResult<>(value);
	}

	static <T> Result<T> ofException(Exception e) {
		return new ErrorResult<>(e);
	}

	static <T> Result<T> eval(ErrableSupplier<T> supplier) {
		try {
			return Result.ofNullable(supplier.get());
		} catch (Exception e) {
			return new ErrorResult<>(e);
		}
	}

	T get();

	Exception exception();

	boolean isPresent();

	boolean isErroneous();

	boolean isEmpty();

	<E> Result<E> map(ErrableFunction<T, E> mapper);

	<E> Result<E> flatMap(Function<T, Result<E>> mapper);

	Result<T> mapException(ErrableFunction<Exception, T> mapper);

	Result<T> flatMapException(Function<Exception, Result<T>> mapper);

	default void ifPresent(Consumer<T> consumer) {
		if (this.isPresent())
			consumer.accept(this.get());
	}

	default void ifErroneous(Consumer<Exception> consumer) {
		if (this.isErroneous())
			consumer.accept(this.exception());
	}

	default void ifEmpty(Runnable runner) {
		if (this.isEmpty())
			runner.run();
	}

	default T orNull() {
		return this.isPresent() ? this.get() : null;
	}
}
