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

		Document XML = Source.fromFile(".classpath")
				.mkXML()
				.orNull();

		XPath.of(XML)
				.addNode("classpathentry")
				.setAttribute("kind", "myKind");

		XPath.of(XML)
				.node("classpathentry")
				.indexRange(2, 6)
				.attribute("kind")
				.filter(r -> r.isPresent())
				.map(r -> r.get())
				.distinct()
				.forEach(System.out::println);

	}
}
