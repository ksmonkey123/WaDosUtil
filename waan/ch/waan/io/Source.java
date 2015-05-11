/**
 * 
 */
package ch.waan.io;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import ch.waan.util.Result;

/**
 * @author Andreas Wälchli
 * @version 1.1, 2015-05-11
 */
public abstract class Source {

	public static Source fromFile(String file) {
		return new FileSource(file);
	}

	public static Source fromStream(InputStream stream) {
		return new StreamSource(stream);
	}

	public static Source fromURL(String url) {
		return new URLSource(url);
	}

	public abstract Result<String> mkString();

}
