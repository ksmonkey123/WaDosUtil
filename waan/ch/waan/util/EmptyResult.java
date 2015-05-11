package ch.waan.util;

import java.util.NoSuchElementException;

import ch.waan.function.ErrableFunction;

class EmptyResult<T> extends Result<T> {

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
		return new EmptyResult<>();
	}

	@Override
	public <E> Result<E> flatMap(ErrableFunction<T, Result<E>> mapper) {
		return new EmptyResult<>();
	}

	@Override
	public Result<T> mapException(ErrableFunction<Exception, T> mapper) {
		return new EmptyResult<>();
	}

	@Override
	public Result<T> flatMapException(ErrableFunction<Exception, Result<T>> mapper) {
		return new EmptyResult<>();
	}

	@Override
	public String toString() {
		return "NONE()";
	}
}
