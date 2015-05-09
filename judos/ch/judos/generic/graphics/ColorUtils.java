package ch.judos.generic.graphics;

import java.awt.Color;

/**
 * @since 09.02.2015
 * @author Julian Schelker
 */
public class ColorUtils {
	public static Color mix(Color c1, Color c2) {
		int red = (c1.getRed() + c2.getRed()) / 2;
		int green = (c1.getGreen() + c2.getGreen()) / 2;
		int blue = (c1.getBlue() + c2.getBlue()) / 2;
		int alpha = (c1.getAlpha() + c2.getAlpha()) / 2;
		return new Color(red, green, blue, alpha);
	}
}
