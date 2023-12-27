/*
Mark Hamilton
cpsc 39
final project
*/
package movieData2;

import java.util.*;


public class LcsMatrix {
	
	private int rowCount;
	private int columnCount;
	
	private ArrayList<ArrayList<LCSobject>> lcsMatrix; //matrix
	private ArrayList<LCSobject> rowLCS;   //row
	private LCSobject lcs;   //cell
	
	//constructor
	public LcsMatrix(ArrayList<String> str1, ArrayList<String> str2) {
		this.rowCount = str1.size();
		this.columnCount = str2.size();
		
		//construct matrix
		lcsMatrix = new ArrayList<ArrayList<LCSobject>>();
		
		//fill matrix with default LCSobject entries
		for(int r = 0; r < rowCount; r++) {
			rowLCS = new ArrayList<LCSobject>();
			for(int c = 0; c < columnCount; c++) {
				lcs = new LCSobject();
				rowLCS.add(lcs);
			}
			lcsMatrix.add(rowLCS);
		}
		
	//	holds all matches throughout, used to update lcsHash field in each cell
		HashSet<String> matchHash = new HashSet<String>();
		
		//update the entries-iterate through each cell of lcsMatrix
		//and update fields of LCSobject based on comparisons of str1 and str2 String elements
		for(int r = 0; r < rowCount; r++) {
			for(int c = 0; c < columnCount; c++) {
				//used to build lcsSet field for each cell
				//initialized here so that they clear after each cell is updated
				ArrayList<String> lcsList = new ArrayList<String>();
				HashSet<ArrayList<String>> lcsListSet = new HashSet<ArrayList<String>>();
				
				//check if words match
				if(str1.get(r).equalsIgnoreCase(str2.get(c))) {
					String match = new String(str1.get(r));
					//update lcsHash field
					matchHash.add(match);
					lcsMatrix.get(r).get(c).setLcsHash(matchHash);
					//get entry up and to left
					LCSobject upLeft = get(r-1, c-1);
					
					//special case if upLeft has empty lcsSet
					if(upLeft.getLcsSet().size() == 0) {
						lcsList.add(match);
						lcsListSet.add(lcsList);
						lcsMatrix.get(r).get(c).setLcsSet(lcsListSet);
					}
					else {
						//lcsSet is set of all instances of the lcs
						//current entry's lcs set is upLeft's lcsSet with match added to each element
						//lcsSet field is a HashSet of ArrayList<String>
						for (ArrayList<String> list : upLeft.getLcsSet()) {
							
							ArrayList<String> clone = new ArrayList<String>(list);
							clone.add(match);
							
							lcsListSet.add(clone);
							lcsMatrix.get(r).get(c).setLcsSet(lcsListSet);
						}
					}
					lcsMatrix.get(r).get(c).setLength(upLeft.getLength() + 1); //update at each cell where match occurs
				}
				//words do not match
				else {
					//determine entry with max value between entry to left and entry above
					//use max method in LCSobject class
					LCSobject maxEntry = new LCSobject(max(get(r-1, c), get(r, c-1)));
					
					lcsMatrix.get(r).get(c).setLcsSet(maxEntry.getLcsSet());
					lcsMatrix.get(r).get(c).setLcsHash(matchHash);
					lcsMatrix.get(r).get(c).setLength(maxEntry.getLength());
				}
			}//end inner loop
		}//end outer loop
		
	}//end constructor
		
		
	
	//methods
	//this method returns a copy of the LCSobject located at specified indices or
	//returns a default LCSobject if either index is out of bounds
	public LCSobject get(int row, int col) {
		if (row < 0 || row >= rowCount || col < 0 || col >= columnCount) {
			return new LCSobject();
		}
		return new LCSobject(lcsMatrix.get(row).get(col));
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
	//returns length of lcs
	public int getLcsLength() {
		//special case 
		if(rowCount == 0 || columnCount == 0) {
			return 0;
		}
		return lcsMatrix.get(rowCount-1).get(columnCount-1).getLength();
	}
	
	public int getRowCount() {
		return rowCount;
	}
	//returns lcsHash of final cell, variable holds all matches whether part of an lcs or not
	public HashSet<String> getAllMatches(){
		return lcsMatrix.get(rowCount-1).get(columnCount-1).getLcsHash();
	}
	
	
	// Returns the set of distinct, longest common subsequences between the two
    // strings that were passed to the constructor.
	public HashSet<ArrayList<String>> getLCS() {
		//in case str1 or str2 is empty--second return will throw out-of-bounds exception (?)
		if(rowCount == 0 || columnCount == 0){
		   return lcsMatrix.get(0).get(0).getLcsSet();
		}
		else {
		   return lcsMatrix.get(rowCount-1).get(columnCount-1).getLcsSet();
		}
	}
	
	//if lcsLength of entries are equal, this method returns a new LCSobject with shared length
		//and union (no repeats) of lcsSet.  Else it returns a new LCSobject with lcsLength
		//and lcsSet of entry with higher length. lcsHash updated in main
		public static LCSobject max(LCSobject entry1, LCSobject entry2) {
			if(entry1.getLength() == entry2.getLength()) {
				HashSet<ArrayList<String>> unionSet = new HashSet<ArrayList<String>>(entry1.getLcsSet());
				
				//HashSet automatically discards repeats, builds unionSet of entry1 and entry2
				for(ArrayList<String> list : entry2.getLcsSet()) {
					unionSet.add(list);
				}
				LCSobject result = new LCSobject(); //lcsHash field remains empty until returned to main
				result.setLength(entry1.getLength());
				result.setLcsSet(unionSet);
				return new LCSobject(result);
				
			}
			else if(entry1.getLength() > entry2.getLength()) {
				return new LCSobject(entry1);
			}
			else {
				return new LCSobject(entry2);
			}
			
		}//end max method
			  

} //end class
