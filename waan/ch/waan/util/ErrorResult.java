package ch.waan.util;

import java.util.NoSuchElementException;
import java.util.function.Function;

import ch.waan.function.ErrableFunction;

class ErrorResult<T> implements Result<T> {

	private final Exception	e;

	ErrorResult(Exception e) {
		this.e = e;
	}

	@Override
	public T get() {
		throw new NoSuchElementException("erroneous result: " + this.e);
	}

	@Override
	public Exception exception() {
		return this.e;
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public boolean isErroneous() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public <E> Result<E> map(ErrableFunction<T, E> mapper) {
		return new ErrorResult<>(this.e);
	}

	@Override
	public <E> Result<E> flatMap(Function<T, Result<E>> mapper) {
		return new ErrorResult<>(this.e);
	}

	@Override
	public Result<T> mapException(ErrableFunction<Exception, T> mapper) {
		try {
			T result = mapper.apply(this.e);
			if (result != null)
				return new ValueResult<>(result);
			return new NoneResult<>();
		} catch (Exception ex) {
			return new ErrorResult<>(ex);
		}
	}

	@Override
	public Result<T> flatMapException(Function<Exception, Result<T>> mapper) {
		return mapper.apply(this.e);
	}

	@Override
	public String toString() {
		return "ERROR( " + this.e.toString() + " @ " + this.e.getStackTrace()[0]
				+ " )";
	}
}
