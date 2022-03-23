import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected class Node {

		protected T data;
		protected Node next;
		protected Node prev;

		Node(T e) {
			data = e;
			next = null;
			prev = null;
		}

		Node(Node p, T e, Node n) {
			data = e;
			next = n;
			prev = p;
		}
	}

	protected int size = 0;
	protected Node head;
	protected Node tail;

	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
	}

	public int getSize() {
		return size;
	}

	public void addToEnd(T data) {
		if (!(tail == null)) {
			Node node = new Node(data);
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else {
			tail = new Node(data);
			head = tail;
		}
		size++;

	}

	public void addToFront(T data) {
		if (!(head == null)) {
			Node node = new Node(data);
			head.prev = node;
			node.next = head;
			head = node;
		} else {
			head = new Node(data);
			tail = head;
		}
		size++;
	}

	public T getLast() {
		T val = tail.data;
		return val;
	}
	
	public T getFirst() {
		T val = head.data;
		return val;
	}


	public ListIterator<T> iterator() {
		theIterator iterator = new theIterator();
		return iterator;
	}

	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) throws UnsupportedOperationException {
		Node n = head;
		while(n != null) {
			if (comparator.compare(n.data, targetData) == 0) {
				if (n == tail) {
					if (tail.prev != null) {
						tail = tail.prev;
						tail.next = null;
					} else {
						head = tail = null;
					}
				}
				else	if (n == head) {
				if (head.next != null) {
						head = head.next;
						head.prev = null;
					} else {
						head = tail = null;
					}
				}  else {
					n.prev.next = n.next;
					n.next.prev = n.prev;
				}
			}
			n = n.next;
			size--;
		}
		
	return this;
	}

	public T retrieveLastElement() {
		if (!(tail != null)) {
			return null;
		} else {
			Node lastEl = tail;
			if (tail.prev != null) {
				tail = tail.prev;
				tail.next = null;
			} else {
				head = null;
				tail = null;
			}
			return lastEl.data;
		}
	}
	
	public T retrieveFirstElement() {
		if (!(head != null)) {
			return null;
		} else {
			Node firstElement = head;
			if (head.next != null) {
				head = head.next;
				head.prev = null;
			} else {
				head = tail = null;
			}
			return firstElement.data;
		}
	}


	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();
		Node beg = head;
		while(beg != null) {
			list.add(beg.data);
			beg = beg.next;
		}
		return list;      
	}

	private class theIterator implements ListIterator<T> {

		Node currVal;

		public theIterator() {
			currVal = new Node(null, null, head);
		}

		public boolean hasNext() {
			return currVal.next != null;
		}

		public T next() throws NoSuchElementException {

			if (hasNext()) {
				currVal.prev = currVal.next;
				currVal.next = currVal.prev.next;
				return currVal.prev.data;
			} else {	
				throw new NoSuchElementException();
			}            

		}

		public T previous() throws NoSuchElementException {

			if (!hasPrevious()) {
				throw new NoSuchElementException();
			} else {
				currVal.next = currVal.prev;
				currVal.prev = currVal.next.prev;
				return currVal.next.data;            
			}
		}
		
		public boolean hasPrevious() {
			return currVal.prev != null;
		}

		public int nextIndex() {
			throw new UnsupportedOperationException(); 
		}

		
		public void set(T e) {
			throw new UnsupportedOperationException(); 
		}

		public void add(T e) {
			throw new UnsupportedOperationException(); 
		}
		
		public void remove() {
			throw new UnsupportedOperationException(); 
		}

		public int previousIndex() {
			throw new UnsupportedOperationException(); 
		}
	}
}
