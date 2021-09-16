
//-----------------------------------------------------
// Title: SeparateChainingHashST
// Author: Tanercan Altuntaþ
// ID: 12595552970
// Section: 01
// Assignment: 1
// Description: This class defines a SeparateChainingHashST
//-----------------------------------------------------
import java.util.ArrayList;

public class SeparateChainingHashST<Key, Value> {
	// --------------------------------------------------------
	// Summary: Define some value.This is contructor method
	// Precondition: st is an array what refers SequentialSearchST
	// Postcondition: Threre is no postcondition.
	// --------------------------------------------------------
	private static final int CAPACITY_INIT = 4;

	private SequentialSearchST<Key, Value>[] st; // array of linked-list symbol tables
	private int m; // hash table size
	private int n; // number of key-value pairs

	/**
	 * Initializes an empty symbol table.
	 */
	public SeparateChainingHashST() {
		this(CAPACITY_INIT);
	}

	public SeparateChainingHashST(int m) {
		// --------------------------------------------------------
		// Summary: This method set value of m and create m objects in
		// SequentialSearchST class and put in array.
		// Precondition: There is no precondition
		// Postcondition: m is set.
		// --------------------------------------------------------
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	public ArrayList<Key> search_Key() {
		// --------------------------------------------------------
		// Summary: This method put all Node of key value in arraylist for calculate
		// most popular hashtag, it is also Iterable method.
		// Precondition: There is no precondition.
		// Postcondition: return arraylist.
		// --------------------------------------------------------
		ArrayList<Key> sequential = new ArrayList<Key>();
		for (int i = 0; i < st.length; i++) {
			for (int j = 0; j < st[i].Key_find().size(); j++) {
				sequential.add(st[i].Key_find().get(j));
			}
		}
		return sequential;
	}

	public int hashM(Key key) {
		// Summary: This method calculate hashcode and define index which index put the
		// hashtable.
		String string = ((String) key);
		return (string.hashCode() & 0x7fffffff) % m;
	}

	public int hashN(Key key) {
		// Summary: This method calculate hashcode and define index which index put the
		// hashtable.
		int hashcode = 31;
		int length = ((String) key).length();
		for (int i = 1; i < length; i++) {
			hashcode = (((String) key).charAt(i) + hashcode * 17);
		}
		return (hashcode & 0x7fffffff) % m;
	}

	private int hash(Key key) {
		// --------------------------------------------------------
		// Summary: This method calculate hashcode and define index which index put the
		// hashtable.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		int hashcode = 0; // hash code of key.
		int length_key = ((String) key).length();
		for (int i = 1; i < length_key; i++) {
			hashcode += ((String) key).charAt(i);
		}

		return hashcode % m;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		// --------------------------------------------------------
		// Summary: This method return size of hashtable.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return m;
	}

	/**
	 * Returns true if this symbol table is empty.
	 *
	 * @return {@code true} if this symbol table is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: This method control the hashtable if is empty return true, if not
		// return false.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param key the key
	 * @return {@code true} if this symbol table contains {@code key}; {@code false}
	 *         otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean Contains(Key key) {
		// --------------------------------------------------------
		// Summary: This method searh key in the hashtable if it find the find key,
		// return true.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 *
	 * @param key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		// --------------------------------------------------------
		// Summary: This method searh key in the hashtable if it find the find key,
		// return Key.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the
	 * old value with the new value if the symbol table already contains the
	 * specified key. Deletes the specified key (and its associated value) from this
	 * symbol table if the specified value is {@code null}.
	 *
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(Key key, Value val) {
		// --------------------------------------------------------
		// Summary: This method put the node what u want and it use put method which use
		// in sequentialsearch class.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		st[hash(key)].put(key, val);

	}

	public void probes_Calculate() {
		// --------------------------------------------------------
		// Summary: This method put all Node of key value in arraylist for calculate
		// most popular hashtag, it is also Iterable method.
		// Precondition: There is no precondition.
		// Postcondition: return arraylist.
		// --------------------------------------------------------
		float sum = 0;
		for (int i = 0; i < st.length; i++) {
			sum += (st[i].size()) * (st[i].size() + 1) / 2;
		}
		float probesCal = (float) sum / (float) m;
		System.out.println("Average Number of Probes: " + probesCal);
	}

}