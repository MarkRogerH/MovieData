/*
 * Mark Hamilton
 * CPSC 39
 * Professor Kanemoto
 * Final Project
 */
package movieData2;

import java.util.*;

// LCSobject datatype holds length of longest common subsequence and hashset of all instances of
// common subsequences of that length (for LcsMatrix)
//lcsHash field holds set of all matches whether part of an lcs or not--may be useful for searches
public class LCSobject {
	//fields
	private int lcsLength;
	private HashSet<String> lcsHash = new HashSet<String>();
	private HashSet<ArrayList<String>> lcsSet = new HashSet<ArrayList<String>>();
	
	//constructors
	//default makes lcsHash and lcsSet both empty
	public LCSobject(){
		super();
		lcsLength = 0;	
	}
	
	public LCSobject(LCSobject toCopy) {
		this.lcsLength = toCopy.lcsLength;
		this.lcsHash = toCopy.lcsHash;
		this.lcsSet = toCopy.lcsSet;
	}
	
	//getters and setters
	public int getLength() {
		return lcsLength;
	}
	public void setLength(int length) {
		lcsLength = length;
	}
	
	public HashSet<String> getLcsHash(){
		return lcsHash;
	}
	public void setLcsHash(HashSet<String> lcsHash) {
		this.lcsHash = lcsHash;
	}
	
	public HashSet<ArrayList<String>> getLcsSet() {
		return lcsSet;
	}
	public void setLcsSet(HashSet<ArrayList<String>> lcsSet) {
		this.lcsSet = lcsSet;
	}
	
	@Override
	public String toString() {
		return "LCSobject [lcsLength = " + lcsLength + "; lcsHash = " + lcsHash + "; lcsSet = " + lcsSet + "]";
	}
	
	//if lcsLength of entries are equal, this method returns a new LCSobject with shared length
	//and union (no repeats) of lcsSet.  Else it returns a new LCSobject with lcsLength
	//and lcsSet of entry with higher length. lcsHash updated in main
/*	public static LCSobject max(LCSobject entry1, LCSobject entry2) {
		if(entry1.lcsLength == entry2.lcsLength) {
			HashSet<ArrayList<String>> unionSet = new HashSet<ArrayList<String>>(entry1.lcsSet);
			
			//HashSet automatically discards repeats, builds unionSet of entry1 and entry2
			for(ArrayList<String> list : entry2.lcsSet) {
				unionSet.add(list);
			}
			LCSobject result = new LCSobject(); //lcsHash field remains empty until returned to main
			result.lcsLength = entry1.lcsLength;
			result.lcsSet = unionSet;
			return new LCSobject(result);
			
		}
		else if(entry1.lcsLength > entry2.lcsLength) {
			return new LCSobject(entry1);
		}
		else {
			return new LCSobject(entry2);
		}
		
	}//end max method
	*/
}//end class
