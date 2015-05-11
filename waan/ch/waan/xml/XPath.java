package ch.waan.xml;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ch.waan.util.Result;
import ch.waan.util.Tuple2;

/**
 * @author Andreas Wälchli
 * @version 1.1, 2015-05-11
 */
public interface XPath {

	static XPath of(Document d) {
		return new XPathImp(Stream.of(d.getDocumentElement()));
	}

	// ###### XPath QUERIES ######

	/*
	 * all children and sub-children of the current node
	 */
	XPath any();

	/*
	 * all direct children
	 */
	XPath children();

	/*
	 * all nodes that match the name
	 */
	default XPath node(String name) {
		return this.children()
				.filterName(name::equals);
	}

	default XPath filterAttribute(String name) {
		return this.filterAttribute(name, val -> true);
	}

	/*
	 * filter name by predicate
	 */
	XPath filterName(Predicate<String> predicate);

	/*
	 * take those that have the attribute with the value
	 */
	default XPath filterAttribute(String name, String value) {
		return this.filterAttribute(name, val -> val.equals(value));
	}

	/*
	 * take those that have the attribute with a predicate matching value
	 */
	XPath filterAttribute(String name, Predicate<String> predicate);

	/*
	 * get those with a matching index
	 */
	default XPath index(int index) {
		return this.indexRange(0, 1);
	}

	/*
	 * get those where the index matches the predicate
	 */
	XPath indexRange(int from, int to);

	// ###### EXTRACTORS ######

	Stream<Result<String>> text();

	Stream<List<Tuple2<String, String>>> attributes();

	Stream<Node> node();

	Stream<Result<String>> attribute(String name);

	// ###### MUTATORS #######

	void setText(String text);

	void setAttribute(String name, String value);

	/*
	 * remove the nodes
	 */
	void dropNode();

	/*
	 * replace the text
	 */
	void updateText(UnaryOperator<String> updater);

	/*
	 * add node and enter them
	 */
	XPath addNode(String name);

}
