package ch.judos.generic.data.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @since 28.05.2014
 * @author Julian Schelker
 */
public class NumberFormatJS {

	public static String formatHighNumber(int nr) {
		NumberFormat n = DecimalFormat.getIntegerInstance();
		return n.format(nr);
	}
}
