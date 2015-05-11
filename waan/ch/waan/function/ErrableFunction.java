package ch.waan.function;

@FunctionalInterface
public interface ErrableFunction<X, Y> {
	
	Y apply(X x) throws Exception;
	
}
