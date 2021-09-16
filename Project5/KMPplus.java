//-----------------------------------------------------
//Title: KMPplus
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class defines a KMPplus.
//-----------------------------------------------------
public class KMPplus {
	private String pattern;
	private int[] next;

	// --------------------------------------------------------------
	// Summary: create Knuth-Morris-Pratt NFA from pattern
	// Precondition: There is no precontidition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public KMPplus(String pattern) {
		this.pattern = pattern;
		int m = pattern.length();
		next = new int[m];
		int j = -1;
		for (int i = 0; i < m; i++) {
			if (i == 0)
				next[i] = -1;
			else if (pattern.charAt(i) != pattern.charAt(j))
				next[i] = j;
			else
				next[i] = next[j];
			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = next[j];
			}
			j++;
		}

	}

	// --------------------------------------------------------------
	// Summary: return offset of first occurrence of text in pattern (or n if no
	// match) simulate the NFA to find match
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int search(String text) {
		int m = pattern.length();
		int n = text.length();
		int i, j;
		for (i = 0, j = 0; i < n && j < m; i++) {
			while (j >= 0 && text.charAt(i) != pattern.charAt(j))
				j = next[j];
			j++;
		}
		if (j == m)
			return i - m;
		return n;
	}

}
