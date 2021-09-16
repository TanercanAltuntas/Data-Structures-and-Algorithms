//-----------------------------------------------------
//Title: BoyerMoore
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class defines a BoyerMoore.
//-----------------------------------------------------
public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    
    // --------------------------------------------------------------
 	// Summary: Preprocesses the pattern string.
 	// Precondition: There is no precondition.
 	// Postcondition: There is no postcondition.
    //@param pat the pattern string
 	// --------------------------------------------------------------
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }


    
    // --------------------------------------------------------------
 	// Summary: Returns the index of the first occurrrence of the pattern string in the text string.
 	// Precondition: There is no precondition.
 	// Postcondition: There is no postcondition.
    // @param  txt the text string
 	// --------------------------------------------------------------
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;                       // not found
    }


}
