/**
 * 
 */
package ch.waan.io;

import org.w3c.dom.Document;

import ch.waan.xml.XPath;

/**
 * @author Andreas Wälchli
 * @version 1.1, May 10, 2015
 *
 */
public class SourceTest {

	public static void main(String[] args) {

		Document d = Source.fromFile(".classpath")
				.mkXML()
				.get();

		XPath.of(d)
				.node("classpathentry")
				.filterAttribute("path",
						v -> (v.contains("waan") && !v.toLowerCase()
								.contains("test")))
				.addNode("duba")
				.setText("hi there");

		XPath.of(d)
				.any()
				.node("duba")
				.text()
				.forEach(System.out::println);

	}
}
