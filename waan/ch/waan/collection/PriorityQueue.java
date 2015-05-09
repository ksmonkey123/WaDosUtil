package ch.waan.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * min-priority queue
 * 
 * @author Andreas Wälchli
 * @version 1.1, 2015-05-09
 *
 * @param <E>
 *            the element type
 */
public class PriorityQueue<E> implements Queue<E> {

	private static class QueueElement implements Comparable<QueueElement> {

		Object	element;
		double	priority;

		QueueElement(Object element, double priority) {
			this.element = element;
			this.priority = priority;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof QueueElement))
				return false;
			return Objects.equals(this.element, ((QueueElement) obj).element);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.element);
		}

		@Override
		public int compareTo(QueueElement o) {
			return Double.compare(this.priority, o.priority);
		}

	}

	private java.util.PriorityQueue<QueueElement>	backer;

	/**
	 * Creates a new min-priority-queue instance
	 */
	public PriorityQueue() {
		this.backer = new java.util.PriorityQueue<>();
	}

	@Override
	public int size() {
		return this.backer.size();
	}

	@Override
	public boolean isEmpty() {
		return this.backer.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.backer.contains(new QueueElement(o, 0));
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			@SuppressWarnings("synthetic-access")
			Iterator<QueueElement>	backedIterator	= PriorityQueue.this.backer.iterator();

			@Override
			public boolean hasNext() {
				return this.backedIterator.hasNext();
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				return (E) this.backedIterator.next().element;
			}
		};
	}

	@Override
	public Object[] toArray() {
		return this.backer.stream()
				.map(e -> e.element)
				.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) Arrays.copyOf(this.toArray(), this.size(), a.getClass());
	}

	@Override
	public boolean remove(Object o) {
		return this.backer.remove(new QueueElement(o, 0));
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return c.stream()
				.allMatch(this::contains);
	}

	@SuppressWarnings("null")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return c.stream()
				.map(this::add)
				.reduce(false, (a, b) -> a || b);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return c.stream()
				.map(this::remove)
				.reduce(false, (a, b) -> a || b);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.backer.retainAll(c.stream()
				.map(e -> new QueueElement(e, 0))
				.collect(Collectors.toList()));
	}

	@Override
	public void clear() {
		this.backer.clear();
	}

	@Override
	public boolean add(E e) {
		return this.backer.add(new QueueElement(e, Double.MAX_VALUE));
	}

	@Override
	public boolean offer(E e) {
		return this.backer.offer(new QueueElement(e, 0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove() {
		return (E) this.backer.remove().element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E poll() {
		return (E) this.backer.poll().element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E element() {
		return (E) this.backer.element().element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		return (E) this.backer.peek().element;
	}

	/**
	 * Sets the priority of an element in the queue
	 * 
	 * @param element
	 *            the element to change the priority for
	 * @param priority
	 *            the new priority of the element
	 */
	public void setPriority(E element, double priority) {
		QueueElement e = new QueueElement(element, priority);
		this.backer.remove(e);
		this.backer.add(e);
	}

	/**
	 * Adds a new element with a defined priority
	 * 
	 * @param element
	 *            the element
	 * @param priority
	 *            the priority
	 */
	public void add(E element, double priority) {
		this.backer.add(new QueueElement(element, priority));
	}

}
