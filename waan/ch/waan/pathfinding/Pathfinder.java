package ch.waan.pathfinding;

import java.util.List;

/**
 * Base Interface for pathfinding algorithm implementations
 * 
 * @author Andreas Wälchli
 * @version 1.1, 2015-05-09
 * 
 * @param <V>
 *            the vertex type supported by the pathfinder
 */
public interface Pathfinder<V extends Vertex<V>> {

	/**
	 * Finds a path for the given starting point and the given destination
	 * 
	 * @param from
	 *            the starting vertex
	 * @param to
	 *            the destination vertex
	 * @return an ordered list containing the path
	 */
	List<V> findPath(V from, V to);

}
