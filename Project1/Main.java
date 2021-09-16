import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//-----------------------------------------------------
//Title: Main
//Author: Tanercan Altuntaþ
//ID: 12595552970
//Section: 01
//Assignment: 1
//Description: This class defines a Main.
//-----------------------------------------------------


public class Main {
	
	public static ArrayList<String> templist = new ArrayList<String>();
	public static SeparateChainingHashST<String, Integer> st_ = new SeparateChainingHashST<String, Integer>(1999);
	
	
	
	static String minWhich (String [] array) {
		//Determines the index of the feature in the "sort" method.
			String minimumkey=array[0];
			for (int m = 0; m < array.length; m++) {
				for (int m2 = 0; m2 < array.length; m2++) {
					if(st_.get(array [m] )<=st_.get(minimumkey)) {
						minimumkey=array[m];
					}
				}
			}
			return minimumkey;
		}
	
	public static void arraySorting (String [] array) {
		//lists the most popular elements found in the array.
		int n = array.length;
        for (int i = 1; i < n; ++i) {
            String key = array[i];
            int a = i - 1;

            /*
             * insertion sort
             */
            while (a >= 0 && st_.get(array[a]) > st_.get(key)) {
            	array[a + 1] = array[a];
                a = a - 1;
            }
            array[a + 1] = key;
        }

        for (int i = array.length - 1; i >= 0; i--) {
            System.out.println(array[i] + " " + st_.get(array[i]));
        }
	}
	
	public static void sort(String [] array) 
    { 
		//--------------------------------------------------------
    	 // Summary: This method pulls elements the size of the array specified in the parameter. These elements are the most popular. 
    	 // Precondition: There is no precondition.
    	 // Postcondition: return arraylist.
    	 //--------------------------------------------------------
		for (int i = 0; i < array.length; i++) {
			array[i]=templist.get(i);
		}
		
		
		for (int i = 0; i < templist.size(); i++) {
			String minimum=minWhich(array);
			int index=tempIndex(array, minimum);
			if(st_.get(templist.get(i))>st_.get(minimum)){
				array[index]=templist.get(i);
			}
		}
    } 
	
	
	static int tempIndex (String[]array,String minimum) {
		//This method determines the smallest element available in the array used in the "find_Min" method.
		int a=0;
		for (int i = 0; i < array.length; i++) {
			if(array[i].equalsIgnoreCase(minimum)) {
				a=i;
			}
		}
		return a;
	}
	
	public static void main(String[] args) throws Exception {
		
        BufferedReader bread = new BufferedReader(new InputStreamReader(new FileInputStream("src/twitter.txt")));
        int a=0; 
        String strs;
        while ((strs = bread.readLine()) != null) {
        	for (int i = 0; i < strs.length(); i++) {
				if(strs.charAt(i)=='#') {
					String line_f=strs.substring(i);
					if(line_f.indexOf(' ')==-1) {
						if(1!=line_f.length()) {
							String hstag=line_f.substring(0,line_f.length()).toLowerCase();
							if(!hstag.contains("@")&&!hstag.contains("!")&&!hstag.contains(",")&&!hstag.contains(".")&&!hstag.contains("`")&&!hstag.contains("\"")) {
								if(st_.Contains(hstag)) {
								st_.put(hstag, st_.get(hstag)+1);
								
								}else {
								st_.put(hstag, 1);
								
								}
							}	
						}
					}
					else {
							String hstag=line_f.substring(0,line_f.indexOf(' ')).toLowerCase();
							if(!hstag.contains("@")&&!hstag.contains("!")&&!hstag.contains(",")&&!hstag.contains(".")&&!hstag.contains("`")&&!hstag.contains("\"")) {
							if(st_.Contains(hstag)) {
								st_.put(hstag, st_.get(hstag)+1);
								
							}else {
								st_.put(hstag, 1);
								
							}
						}
					}		
				}	
			}
	    }
        templist=st_.search_Key();
        String array [] = new String[10];        
        sort(array);
        arraySorting(array);
      
        // st_.probes_Calculate();
        // If you remove it from the comment line, you will see number of probes for part 2. 
        	                             	   
     }
       
   }


