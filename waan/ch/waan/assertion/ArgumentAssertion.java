package ch.waan.assertion;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class ArgumentAssertion {

	public static void checkNonNull(@Nullable Object argument) {
		if (argument == null)
			throw new IllegalArgumentException("argument may not be null");
	}

	public static void
			checkNonNull(@Nullable Object argument, @NonNull String title) {
		if (argument == null)
			throw new IllegalArgumentException("argument [" + title
					+ "] may not be null");
	}

}
