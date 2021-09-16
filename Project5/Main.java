import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//-----------------------------------------------------
//Title: Main
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class define Main class.
//-----------------------------------------------------

public class Main {

	public void printFunc(int offset, String text, String pat) {
		System.out.println("text:    " + text);
		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++)
			System.out.print(" ");
		System.out.println(pat);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("PART I\n");
		BufferedReader breader = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input1.txt")));
		String textwrite = breader.readLine().replaceAll("\\s+", " ");
		LongestRepeatedSubstring longRepeatedSubstring = new LongestRepeatedSubstring(textwrite);
		String pattern = longRepeatedSubstring.lrs();
		System.out.println(pattern.length() + "\n" + pattern);
		System.out.println("\n-------------------------\n");

		BufferedReader myBr = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/sample-own-inputs.txt")));
		String myText;
		while ((myText = myBr.readLine()) != null) {
			LongestRepeatedSubstring mylongestRS = new LongestRepeatedSubstring(myText);
			myText = myText.replaceAll("\\s+", " ");
			System.out.println("Input : " + myText);
			String myPattern = mylongestRS.lrs();
			System.out.println("Pattern : " + myPattern);

			// If u want to see that how to work, you can use print function.

			long start_Time = System.nanoTime();
			Brute bruteF = new Brute(myPattern);
			int offsetForBf = bruteF.search(myText);
			long endTime = System.nanoTime();
			long totalTime = endTime - start_Time;
			System.out.println("Brute Force : " + totalTime + " nanosecond.");
			long startTime2 = System.nanoTime();
			BoyerMoore buyerM = new BoyerMoore(myPattern);
			int offsetForBuyerMoore = buyerM.search(myText);
			long endTime2 = System.nanoTime();
			long totalTime2 = endTime2 - startTime2;
			System.out.println("Buyer Moore : " + totalTime2 + " nanosecond.");
			long startTime3 = System.nanoTime();
			KMPplus kmp = new KMPplus(myPattern);
			int offsetForKmp = kmp.search(myText);
			long endTime3 = System.nanoTime();
			long totalTime3 = endTime3 - startTime3;
			System.out.println("Knuth-Morris-Pratt : " + totalTime3 + " nanosecond.");
			System.out.println("\n-------------------------\n");
		}
		System.out.println("\nPART II\n");
		LongestRepeatedSubstringSeq lrsS = new LongestRepeatedSubstringSeq();
		lrsS.lrs_createSeq();
	}

}
