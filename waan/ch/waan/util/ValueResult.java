package ch.waan.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

import ch.waan.function.ErrableFunction;

class ValueResult<T> implements Result<T> {

	private final T	value;

	ValueResult(T value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	@Override
	public T get() {
		return this.value;
	}

	@Override
	public Exception exception() {
		throw new NoSuchElementException();
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public boolean isErroneous() {
		return false;
	}

	@Override
	public <E> Result<E> map(ErrableFunction<T, E> mapper) {
		try {
			E result = mapper.apply(this.value);
			if (result != null)
				return new ValueResult<>(result);
			return new NoneResult<>();
		} catch (Exception e) {
			return new ErrorResult<>(e);
		}
	}

	@Override
	public <E> Result<E> flatMap(Function<T, Result<E>> mapper) {
		return mapper.apply(this.value);
	}

	@Override
	public Result<T> mapException(ErrableFunction<Exception, T> mapper) {
		return this;
	}

	@Override
	public Result<T> flatMapException(Function<Exception, Result<T>> mapper) {
		return this;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return "VALUE( " + this.value.toString() + " )";
	}

}
