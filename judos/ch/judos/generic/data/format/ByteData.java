package ch.judos.generic.data.format;

import ch.judos.generic.math.MathJS;

/**
 * @since 11.07.2013
 * @author Julian Schelker
 */
public class ByteData {

	@SuppressWarnings("all")
	public static String autoFormat(double bytes) {
		int u = 0;
		String[] units = new String[]{"B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB",
			"YiB"};
		while (bytes > 1024) {
			bytes /= 1024;
			u++;
		}
		bytes = MathJS.round(bytes, 1);
		return bytes + " " + units[u];
	}
}
