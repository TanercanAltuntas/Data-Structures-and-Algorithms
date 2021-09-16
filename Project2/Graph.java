import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

//-----------------------------------------------------
//Title: Graph Class
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 2
//Description: This class correctly links the elements needed to create the graph.
//-----------------------------------------------------
public class Graph {
	// -----------------------------------------------------
	// Summary: Variables to be used are defined.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// -----------------------------------------------------

	private static final String LINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	public Bag<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V, BufferedReader br) throws IOException {
		// -----------------------------------------------------
		// Summary: This is graph method.
		// Precondition: V is an integer and br is a BufferedReader.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];

		for (int v = 0; v < V; v++) {
			String str = br.readLine();
			String[] splitStrings = str.split(" ");
			adj[v] = new Bag<Integer>(splitStrings[0], Integer.parseInt(splitStrings[1]),
					Integer.parseInt(splitStrings[2]));
		}
	}

	public void addEdge(int v, int w) {
		// -----------------------------------------------------
		// Summary: This method add edge to graph.
		// Precondition: v is an integer and w is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	public int V() {
		// -----------------------------------------------------
		// Summary: This method returns V.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return V;
	}

	public int E() {
		// -----------------------------------------------------
		// Summary: This method returns E.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return E;
	}

	public int firstPosition() {
		return 0;
	}

	public int lastPosition() {
		return V() - 1;
	}

	private void validateVertex(int vertex) {
		// -----------------------------------------------------
		// Summary: This method is validateVertex.
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		if (vertex < 0 || vertex >= V)
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (V - 1));
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int vertex) {
		// -----------------------------------------------------
		// Summary: This method is Iterate to adj.
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(vertex);
		return adj[vertex];
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int vertex) {
		// -----------------------------------------------------
		// Summary: This method finds degree of graph.
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(vertex);
		return adj[vertex].size();
	}

	void BFS(int s) {
		// -----------------------------------------------------
		// Summary: This method is Breadth First Search.
		// Precondition: s in an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(adj[s].getName() + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it

			for (int w : adj[s]) {

				int n = w;
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}

		}
	}

	public void print() {
		// -----------------------------------------------------
		// Summary: This method prints graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				if (w >= v) {
					System.out.println(adj[v].getName() + ("(") + (adj[v].getX()) + (",") + (adj[v].getY()) + (")")
							+ " --> " + (adj[w].getName()) + ("(") + (adj[w].getX() + (",") + (adj[w].getY())) + (")"));

				}
			}

		}

	}

}