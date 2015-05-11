package ch.waan.function;

@FunctionalInterface
public interface ErrableSupplier<T> {

	T get() throws Exception;

}
