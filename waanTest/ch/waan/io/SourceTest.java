/**
 * 
 */
package ch.waan.io;

/**
 * @author Andreas Wälchli
 * @version 1.1, May 10, 2015
 *
 */
public class SourceTest {

	public static void main(String[] args) {

		System.out.println(Source.fromFile(".project")
				.mkString());

	}
}
