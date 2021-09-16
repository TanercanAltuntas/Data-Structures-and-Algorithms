import java.util.Iterator;
import java.util.NoSuchElementException;

//-----------------------------------------------------
//Title: Bag Class
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 2
//Description: This class is a generic class.It helps to add elements to the graph.
//-----------------------------------------------------
public class Bag<Item> implements Iterable<Item> {
	// -----------------------------------------------------
	// Summary: Variables to be used are defined.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// -----------------------------------------------------

	private Node<Item> head;
	private int x, y; // cordinates
	private int n;
	private String name;

	private static class Node<Item> {
		// -----------------------------------------------------
		// Summary: This is a generic class.
		// Precondition: Item is a generic and its value will be assigned later.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		private Item item;
		private Node<Item> next;
	}

	public Bag() {
		// -----------------------------------------------------
		// Summary: It is a constructor.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		head = null;
		n = 0;
	}

	public Bag(String name, int x, int y) {
		// -----------------------------------------------------
		// Summary: It is a parameter constructor.
		// Precondition: name is a String, x is an integer and y is an integer.
		// Postcondition: The value of the variables is set.
		// -----------------------------------------------------
		this.x = x;
		this.y = y;
		this.name = name;
		head = null;
		n = 0;
	}

	public int size() {
		// -----------------------------------------------------
		// Summary: This method returns size.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return n;
	}

	public boolean isEmpty() {
		// -----------------------------------------------------
		// Summary: This method checks whether it is empty.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return head == null;
	}

	public int getX() {
		// -----------------------------------------------------
		// Summary: This method returns value of X.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return x;
	}

	public String getName() {
		// -----------------------------------------------------
		// Summary: This method returns value of name.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return name;
	}

	public int getY() {
		// -----------------------------------------------------
		// Summary: This method returns value of Y.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return y;
	}

	public void add(Item item) {
		// -----------------------------------------------------
		// Summary: This method adds an element to the end of the list.
		// Precondition: Item is a generic.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		Node<Item> oldNode = head;
		head = new Node<Item>();
		head.item = item;
		head.next = oldNode;
		n++;
	}

	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> head) {
			// -----------------------------------------------------
			// Summary: This method is a LinkedIterator.
			// Precondition: head is a type of node.
			// Postcondition: There is no postcondition.
			// -----------------------------------------------------
			current = head;
		}

		public boolean hasNext() {
			// -----------------------------------------------------
			// Summary: This method checks whether it has next.
			// Precondition: There is no precondition.
			// Postcondition: There is no postcondition.
			// -----------------------------------------------------
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			// -----------------------------------------------------
			// Summary: This method finds the next one.
			// Precondition: There is no precondition.
			// Postcondition: There is no postcondition.
			// -----------------------------------------------------
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public Iterator<Item> iterator() {
		return new LinkedIterator(head);
	}

}