//-----------------------------------------------------
//Title: LongestRepeatedSubstring
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class defines a LongestRepeatedSubstring.
//-----------------------------------------------------
public class LongestRepeatedSubstring {
	private String text;

	// --------------------------------------------------------------
	// Summary: Return the text value.
	// Precondition: There is no precontidition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	
	public String getText() {
		return text;
	}

	// --------------------------------------------------------------
	// Summary: Set the text value.
	// Precondition: There is no precontidition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	
	public void setText(String text) {
		this.text = text;
	}

	// --------------------------------------------------------------
	// Summary: Initialize the value that what is in the parameter.
	// Precondition: Text is a String.
	// Postcondition: Text is setted.
	// --------------------------------------------------------------

	public LongestRepeatedSubstring(String text) {
		this.text = text;
	}

	// --------------------------------------------------------------
	// Summary: Returns the longest repeated substring of the specified string.
	// Precondition: There is no precontidition.
	// Postcondition: There is no postcondition.
	// @param text the string
	// @return the longest repeated substring that appears in {@code text}; the
	// empty string if no such string
	// --------------------------------------------------------------
	public String lrs() {
		int n = this.text.length();
		SuffixArray sa = new SuffixArray(this.text);
		String lrs = "";
		for (int i = 1; i < n; i++) {
			int length = sa.lcp(i);
			if (length > lrs.length()) {
				// lrs = sa.select(i).substring(0, length);
				lrs = this.text.substring(sa.index(i), sa.index(i) + length);
			}
		}
		return lrs;
	}

}
