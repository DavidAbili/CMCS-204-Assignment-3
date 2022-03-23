
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {

	private Comparator<T> com;

	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.com = comparator;
	}

	public SortedDoubleLinkedList<T> add(T data) {
		Node theNode = new Node(data);
		Node first = head; 

		if (size == 0) {
			head = theNode;
			tail = head;
			size++; 
			return this; 
		}
		else if (com.compare(head.data, data) > 0) {
			theNode.next = head;
			head.prev = theNode;
			head = theNode;
			size++; 
			return this; 
		}
		else {
			while (com.compare(first.data, data) < 0) {
				if (first.next == null) {
					first.next = theNode;
					theNode.prev = first;
					tail = theNode;
					size++; 
					return this; 
				}
				else {
					first = first.next;
				}
			}
			first.prev.next = theNode;
			theNode.prev = first.prev;
			first.prev = theNode;
			theNode.next = first;
			size++; 
			return this; 
		}
	}

	public ListIterator<T> iterator() throws NoSuchElementException, UnsupportedOperationException {
		return super.iterator();
	}
	
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comp) {
		SortedDoubleLinkedList<T> val = (SortedDoubleLinkedList<T>) super.remove(data, comp);
		return val;
	}
	
	public void addToEnd(T theEl) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	public void addToFront(T theEl) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
