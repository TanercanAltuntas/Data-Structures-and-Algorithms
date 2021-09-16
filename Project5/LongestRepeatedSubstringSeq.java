import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//-----------------------------------------------------
//Title: LongestRepeatedSubstringSeq
//Author: Tanercan Altuntas
//ID: 12595552970
//Section: 1
//Assignment: 5
//Description: This class defines a LongestRepeatedSubstringSeq.
//-----------------------------------------------------
public class LongestRepeatedSubstringSeq {
	
	public LongestRepeatedSubstringSeq() {
		
	}

	// --------------------------------------------------------------
	// Summary: Find the longest repeated sequance in text.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public String longestRepeatedSubstring(String text) {
		int n = text.length();
		int LCSRe[][] = new int[n + 1][n + 1];

		String res = ""; // To store result
		int res_length = 0; // To store length of result

		// building table in bottom-up manner
		int i, index = 0;
		for (i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				// (j-i) > LCSRe[i-1][j-1] to remove
				// overlapping
				if (text.charAt(i - 1) == text.charAt(j - 1) && LCSRe[i - 1][j - 1] < (j - i)) {
					LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;

					// updating maximum length of the
					// substring and updating the finishing
					// index of the suffix
					if (LCSRe[i][j] > res_length) {
						res_length = LCSRe[i][j];
						index = Math.max(i, index);
					}
				} else {
					LCSRe[i][j] = 0;
				}
			}
		}

		// If we have non-empty result, then insert all
		// characters from first character to last
		// character of String
		if (res_length > 0) {
			for (i = index - res_length + 1; i <= index; i++) {
				res += text.charAt(i - 1);
			}
		}

		return res;
	}

	// --------------------------------------------------------------
	// Summary: This method use "longestRepeatedSubstring()" method with crate 8
	// length sequance.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void lrs_createSeq() throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input2.txt")));
		int sizeLoop = Integer.parseInt(bReader.readLine());
		for (int i = 0; i < sizeLoop; i++) {
			String str, longestRW;
			str = bReader.readLine();
			longestRW = longestRepeatedSubstring(str);
			String repeatText = "";
			boolean exit = true;
			while (exit) {
				if (repeatText.length() < 8) {
					if (repeatText.length() == 0) {
						repeatText = longestRW.substring((str.length()) % (longestRW.length()));
					} else {
						repeatText += longestRW;
					}
				} else {
					repeatText = repeatText.substring(0,8);
					repeatText += "...";
					exit=false;
				}
			}
			System.out.println(repeatText);
		}
	}

}
