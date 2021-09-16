import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//-----------------------------------------------------
// Title: Main Class
// Author: Tanercan Altuntas
// ID: 12595552970
// Section: 1
// Assignment: 2
// Description: This class is a main class. It makes the written codes work.
//-----------------------------------------------------
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// -----------------------------------------------------
		// Summary: this is the main method. It prints the graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		BufferedReader bufferReader = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/InputMaze.txt"))); // read file

		Graph graph = new Graph(Integer.parseInt(bufferReader.readLine()), bufferReader);

		String[] finalLine = { "-1", "-1" }, string = null;

		boolean quit = false;
		while (!quit) {
			String line1 = bufferReader.readLine();
			if (Arrays.deepEquals(line1.split(" "), finalLine)) {
				quit = true;
			} else {
				string = line1.split(" ");
				graph.addEdge(Integer.parseInt(string[0]), Integer.parseInt(string[1]));
			}
		}

		graph.print();

		BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);

		for (int index : breadthFirstPaths.pathTo(graph.lastPosition())) {
			if (index != graph.lastPosition()) {
				System.out.print(graph.adj[index].getName() + "->");
			} else {
				System.out.print(graph.adj[index].getName());
			}
		}

	}

}
