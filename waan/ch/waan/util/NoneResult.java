package ch.waan.util;

import java.util.NoSuchElementException;
import java.util.function.Function;

import ch.waan.function.ErrableFunction;

class NoneResult<T> implements Result<T> {

	NoneResult() {
	}

	@Override
	public T get() {
		throw new NoSuchElementException();
	}

	@Override
	public Exception exception() {
		throw new NoSuchElementException();
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public boolean isErroneous() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public <E> Result<E> map(ErrableFunction<T, E> mapper) {
		return new NoneResult<>();
	}

	@Override
	public <E> Result<E> flatMap(Function<T, Result<E>> mapper) {
		return new NoneResult<>();
	}

	@Override
	public Result<T> mapException(ErrableFunction<Exception, T> mapper) {
		return new NoneResult<>();
	}

	@Override
	public Result<T> flatMapException(Function<Exception, Result<T>> mapper) {
		return new NoneResult<>();
	}

	@Override
	public String toString() {
		return "NONE()";
	}
}
