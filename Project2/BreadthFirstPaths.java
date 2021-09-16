import java.util.*;

//-----------------------------------------------------
//Title: BreadthFirstPaths Class
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 2
//Description: This class creates a path. It finds the shortest path..
//-----------------------------------------------------
public class BreadthFirstPaths {
	// -----------------------------------------------------
	// Summary: Variables to be used are defined.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// -----------------------------------------------------

	private static final int INFINITY = Integer.MAX_VALUE;
	private int[] distTo; // distTo[v] = number of edges shortest s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private boolean[] marked; // marked[v] = is there an s-v path

	/**
	 * Computes the shortest path between the source vertex {@code s} and every
	 * other vertex in the graph {@code G}.
	 * 
	 * @param G the graph
	 * @param s the source vertex
	 * @throws IllegalArgumentException unless {@code 0 <= s < V}
	 */
	public BreadthFirstPaths(Graph graph, int u) {
		// -----------------------------------------------------
		// Summary: This method is an parameter constructor for BreadthFirstPaths.
		// Precondition: graph is a temp in Graph class and u is an integer.
		// Postcondition: The value of the variables is set.
		// -----------------------------------------------------

		edgeTo = new int[graph.V()];
		marked = new boolean[graph.V()];
		distTo = new int[graph.V()];
		validateVertex(u);
		bfs(graph, u);

		assert check(graph, u);
	}

	/**
	 * Computes the shortest path between any one of the source vertices in
	 * {@code sources} and every other vertex in graph {@code G}.
	 * 
	 * @param G       the graph
	 * @param sources the source vertices
	 * @throws IllegalArgumentException if {@code sources} is {@code null}
	 * @throws IllegalArgumentException unless {@code 0 <= s < V} for each vertex
	 *                                  {@code s} in {@code sources}
	 */

	// breadth-first search from a single source
	private void bfs(Graph graph, int u) {
		// -----------------------------------------------------
		// Summary: This method is an parameter constructor for bfs.
		// Precondition: graph is a temp in Graph class and u is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int vertex = 0; vertex < graph.V(); vertex = vertex + 1)
			distTo[vertex] = INFINITY;
		marked[u] = true;
		distTo[u] = 0;
		queue.add(u);

		while (!queue.isEmpty()) {
			int vertex = queue.remove();
			for (int w : graph.adj(vertex)) {
				if (!marked[w]) {
					distTo[w] = distTo[vertex] + 1;
					edgeTo[w] = vertex;
					marked[w] = true;
					queue.add(w);
				}
			}
		}
	}

	/**
	 * Is there a path between the source vertex {@code s} (or sources) and vertex
	 * {@code v}?
	 * 
	 * @param v the vertex
	 * @return {@code true} if there is a path, and {@code false} otherwise
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public boolean hasPathTo(int vertex) {
		// -----------------------------------------------------
		// Summary: This method checks whether it has Path .
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(vertex);
		return marked[vertex];
	}

	/**
	 * Returns the number of edges in a shortest path between the source vertex
	 * {@code s} (or sources) and vertex {@code v}?
	 * 
	 * @param v the vertex
	 * @return the number of edges in a shortest path
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int distTo(int vertex) {
		// -----------------------------------------------------
		// Summary: This method return disTo[vertex] .
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(vertex);
		return distTo[vertex];
	}

	// check optimality conditions for single source
	private boolean check(Graph graph, int u) {
		// -----------------------------------------------------
		// Summary: This method checks optimality conditions for single source .
		// Precondition: graph is a temp in Graph class and u is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		// check that the distance of s = 0
		if (distTo[u] != 0) {
			System.out.println("distance of source " + u + " to itself = " + distTo[u]);
			return false;
		}

		// check that for each edge v-w dist[w] <= dist[v] + 1
		// provided v is reachable from s
		for (int vertex = 0; vertex < graph.V(); vertex++) {
			for (int w : graph.adj(vertex)) {
				if (hasPathTo(vertex) != hasPathTo(w)) {
					System.out.println("edge " + vertex + "-" + w);
					System.out.println("hasPathTo(" + vertex + ") = " + hasPathTo(vertex));
					System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
					return false;
				}
				if (hasPathTo(vertex) && (distTo[w] > distTo[vertex] + 1)) {
					System.out.println("edge " + vertex + "-" + w);
					System.out.println("distTo[" + vertex + "] = " + distTo[vertex]);
					System.out.println("distTo[" + w + "] = " + distTo[w]);
					return false;
				}
			}
		}

		// check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
		// provided v is reachable from s
		for (int w = 0; w < graph.V(); w++) {
			if (!hasPathTo(w) || w == u)
				continue;
			int vertex = edgeTo[w];
			if (distTo[w] != distTo[vertex] + 1) {
				System.out.println("shortest path edge " + vertex + "-" + w);
				System.out.println("distTo[" + vertex + "] = " + distTo[vertex]);
				System.out.println("distTo[" + w + "] = " + distTo[w]);
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns a shortest path between the source vertex {@code s} (or sources) and
	 * {@code v}, or {@code null} if no such path.
	 * 
	 * @param v the vertex
	 * @return the sequence of vertices on a shortest path, as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> pathTo(int vertex) {
		// -----------------------------------------------------
		// Summary: This method iterate vertex to path.
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(vertex);
		if (!hasPathTo(vertex))
			return null;
		LinkedList<Integer> path = new LinkedList<Integer>();
		int index;
		for (index = vertex; distTo[index] != 0; index = edgeTo[index])
			path.push(index);
		path.push(index);
		return path;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	@SuppressWarnings("unused")
	private void validateVertices(Iterable<Integer> vertices) {
		// -----------------------------------------------------
		// Summary: This method is validateVertex.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		if (vertices == null) {
			throw new IllegalArgumentException("argument is null");
		}
		for (Integer vertex : vertices) {
			if (vertex == null) {
				throw new IllegalArgumentException("vertex is null");
			}
			validateVertex(vertex);
		}
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int vertex) {
		// -----------------------------------------------------
		// Summary: This method is validateVertex.
		// Precondition: vertex is an integer.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		int Vertex = marked.length;
		if (vertex < 0 || vertex >= Vertex)
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (Vertex - 1));
	}

}