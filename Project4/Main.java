import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//-----------------------------------------------------
//Title: Main
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 4
//Description: This class defines a main
//-----------------------------------------------------
public class Main {
	static ArrayList<Integer[]> visitshop = new ArrayList<Integer[]>();

	static void permute(java.util.List<Integer> arr, int k) {
		for (int i = k; i < arr.size(); i++) {
			java.util.Collections.swap(arr, i, k);
			permute(arr, k + 1);
			java.util.Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			arr.add(arr.size(), 0);
			Integer integerArray[] = java.util.Arrays.asList(arr.toArray()).toArray(new Integer[0]);
			arr.remove(arr.size() - 1);
			visitshop.add(integerArray);
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("---- PART I ----");
		BufferedReader breaderP1 = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/HW4-Part1-input exampleFile.txt")));
		String arrLine = breaderP1.readLine().replaceAll("\\s+", " ");
		String[] arrSplit = arrLine.split(" ");
		int V = Integer.parseInt(arrSplit[0]);
		int E = Integer.parseInt(arrSplit[1]);

		EdgeWeightedGraph graph = new EdgeWeightedGraph(V, E);
		for (int i = 0; i < graph.getE(); i++) {
			String line = breaderP1.readLine().replaceAll("\\s+", " ");
			String[] splitArray = line.split(" ");
			int v = Integer.parseInt(splitArray[0]) - 1;
			int w = Integer.parseInt(splitArray[1]) - 1;
			int weight = Integer.parseInt(splitArray[2]);
			Edge e = new Edge(v, w, weight);
			graph.addEdge(e);
		}

		KruskalMST mst = new KruskalMST(graph);
		System.out.printf("\n%d\n\n", (int) mst.weight());
		System.out.println(mst.print());
		System.out.println("\n");
		////////////////////////////////////////////////////////////////////////////

		System.out.println("---- PART II ---- \n");

		BufferedReader breaderP2 = new BufferedReader(new InputStreamReader(new FileInputStream("src/hw4-part2.txt")));
		int VQ2 = Integer.parseInt(breaderP2.readLine());
		int EQ2 = Integer.parseInt(breaderP2.readLine());
		EdgeWeightedGraph graph_Q2 = new EdgeWeightedGraph(VQ2, EQ2);
		for (int i = 0; i < graph_Q2.getE(); i++) {
			String line = breaderP2.readLine().replaceAll("\\s+", " ");
			String[] splitArray = line.split(" ");
			int v = Integer.parseInt(splitArray[0]);
			int w = Integer.parseInt(splitArray[1]);
			int weight = Integer.parseInt(splitArray[2]);
			Edge e = new Edge(v, w, weight);
			graph_Q2.addEdge(e);
		}
		int shopSizeVisit = Integer.parseInt(breaderP2.readLine());
		ArrayList<Integer> shopList = new ArrayList<Integer>();
		for (int i = 0; i < shopSizeVisit; i++) {
			shopList.add(Integer.parseInt(breaderP2.readLine()));
		}
		permute(shopList, 0);
		int sVertex = 0;
		double minPath = 0;
		DijkstraUndirectedSP sp;
		for (int i = 0; i < visitshop.size(); i++) {
			double total = 0;
			for (int j = 0; j < visitshop.get(i).length; j++) {
				sp = new DijkstraUndirectedSP(graph_Q2, sVertex);
				if (sp.hasPathTo(visitshop.get(i)[j])) {
					total += sp.distTo(visitshop.get(i)[j]);

				} else {
					System.out.printf("%d to %d     no path\n", visitshop.get(i)[j], visitshop.get(i)[j]);
				}

				sVertex = visitshop.get(i)[j];
			}

			if (i == 0) {
				minPath = total;
			} else {
				if (total <= minPath) {
					minPath = total;
				}
			}
		}
		System.out.println((int) minPath);

	}

}
