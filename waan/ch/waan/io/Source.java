/**
 * 
 */
package ch.waan.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.function.Supplier;

/**
 * @author Andreas Wälchli
 * @version 1.1, 2015-05-11
 */
public final class Source {

	public static Source fromFile(String file) {
		return new Source(() ->
			{
				try {
					return new FileInputStream(file);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
	}

	public static Source fromStream(InputStream stream) {
		return new Source(() -> stream);
	}

	public static Source fromURL(String url) {
		return new Source(() ->
			{
				try {
					return new URL(url).openStream();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
	}

	private final Supplier<InputStream>	stream;

	private Source(Supplier<InputStream> stream) {
		this.stream = stream;
	}

	@Override
	public String toString() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				this.stream.get()))) {
			StringBuilder sb = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
				sb.append('\n');
			}
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
