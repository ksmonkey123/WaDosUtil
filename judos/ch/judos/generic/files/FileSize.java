package ch.judos.generic.files;

import java.io.File;

public class FileSize {

	public static String getFileSizeNice(String filename) {
		return getFileSizeNice(new File(filename));
	}

	public static String getFileSizeNice(File file) {
		long size = file.length();
		return getSizeNiceFromBytes(size);
	}

	public static String getSizeNiceFromBytes(long size) {
		String[] e = new String[]{"B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB"};
		int ei = 0;
		double size1 = size;
		while (size1 > 1024) {
			size1 /= 1024;
			ei++;
		}
		return Math.round(size1 * 100) / 100. + " " + e[ei];
	}

}
