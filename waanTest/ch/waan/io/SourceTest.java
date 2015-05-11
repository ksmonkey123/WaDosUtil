/**
 * 
 */
package ch.waan.io;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Andreas Wälchli
 * @version 1.1, May 10, 2015
 *
 */
public class SourceTest {

	public static void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException {
		Source.fromFile(".classpath")
				.mkXML()
				.ifPresent(SourceTest::handleXML);

	}

	static void handleXML(Document d) {
		try {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("classpath/classpathentry");
			XPathExpression expr2 = xpath.compile("classpath/classpathentry[@kind='src']");
			System.out.println(expr.evaluate(d, XPathConstants.NODE));
			NodeList l = ((NodeList) expr2.evaluate(d, XPathConstants.NODESET));
			for (int i = 0; i < l.getLength(); i++) {
				System.out.println(" > " + l.item(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
