//-----------------------------------------------------
//Title: Brute
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class defines a Brute.
//-----------------------------------------------------
public class Brute {
	private String pat;
		// --------------------------------------------------------------
	 	// Summary: Initialize the value that what is in the parameter.
	 	// Precondition: Pat is a String.
	 	// Postcondition: Pat is setted.
	    //@param pat the pattern string
	 	// --------------------------------------------------------------
	public Brute(String pat) {
		this.pat=pat;
	}
	
	public String getPat() {
		return pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public int search(String txt) {
        int m = this.pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (txt.charAt(i) == this.pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == m) return i - m;    // found
        else        return n;        // not found
    }
	
}
