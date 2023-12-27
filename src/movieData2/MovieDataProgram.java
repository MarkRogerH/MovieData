/*
 * Mark Hamilton
 * CPSC 39
 * Professor Kanemoto
 * Final Project
 */


package movieData2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class MovieDataProgram {
	
	// initiate ArrayLists
	
	public static ArrayList<Character> characters = new ArrayList<Character>();
	public static ArrayList<ActorRecord> actors = new ArrayList<ActorRecord>();
	public static ArrayList<MovieRecord> movies = new ArrayList<MovieRecord>();
	
	
	static Scanner input2 = new Scanner(System.in);
	static FileWriter csvWriter = null;
	
	static boolean updateCharCsv = false; // changed to true if data edited 
	static boolean updateActCsv = false;  // csv updated at end of main if true
	static boolean sortedByActorName = true; //to avoid unnecessary sorting of actors list
	
	public static void main(String[] args) throws IOException {
		
		//read in data for characters ArrayList
		
		Scanner input = null;
		try {
			input = new Scanner(new File("character.csv"));
		} catch (FileNotFoundException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		// first record is header
        input.nextLine();
		
		while(input.hasNext()) {
			String record = input.nextLine();
			List<String> fields = new ArrayList<String>();
			int start = 0;
			boolean inQuotes = false;
			int current = 0;
			while (current < record.length()) {
				//special case (") at end of record
				if (current == record.length() - 1 && inQuotes && record.charAt(current) == '\"') {
					current++; //to kick out of while loop
					start++;  //skips first double quote when adding to fields ArrayList
					continue;
				}
				// case double quote (")
				if (record.charAt(current) == '\"' && record.charAt(current + 1) != '\"') {
					//if ending quote
					if (inQuotes) {
						fields.add(record.substring(start + 1, current).replace("\"\"", "\""));
						start = current + 2; // skips ending quote and comma to start next field
						current++; //increments again before repeating while loop
					}	
					inQuotes = !inQuotes;
				}
				// case pair of double quotes ("")
				else if (record.charAt(current) == '\"' && record.charAt(current + 1) == '\"') {
					current++; // skips second quote start stays same
				}
				// split at commas not in quotes
				else if (record.charAt(current) == ',' && !inQuotes) {
					fields.add(record.substring(start, current));
					start = current + 1; //skips comma for next field
				}
				current++;
			}
			//for special case, avoids adding ending quote to field
			if (record.charAt(current - 1) == '\"') {
				current--;
			}
			fields.add(record.substring(start, current).replace("\"\"", "\""));
			//System.out.println("characters size = " + characters.size());	
			characters.add(new Character(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4),
					fields.get(5), fields.get(6)));
		//	characters.add(new Character(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4),
		//			fields.get(5)));
			
		}// end input for characters ArrayList

		
	    // read in data for actors ArrayList
		// this is a modification of code found at:
		// https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
		
		try {
			input = new Scanner(new File("actor.csv"));
		} catch (FileNotFoundException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		// first record is header
		input.nextLine();
		
		while(input.hasNext()) {
			String record = input.nextLine();
			List<String> fields = new ArrayList<String>();
			int start = 0;
			boolean inQuotes = false;
			int current = 0;
			while (current < record.length()) {
				//special case (") at end of record
				if (current == record.length() - 1 && inQuotes && record.charAt(current) == '\"') {
					current++; //to kick out of while loop
					start++;  //skips first double quote when adding to fields ArrayList
					continue;
				}
				// case double quote (")
				if (record.charAt(current) == '\"' && record.charAt(current + 1) != '\"') {
					//if ending quote
					if (inQuotes) {
						fields.add(record.substring(start + 1, current).replace("\"\"", "\""));
						start = current + 2; // skips ending quote and comma to start next field
						current++; //increments again before repeating while loop
					}	
					inQuotes = !inQuotes;
				}
				// case pair of double quotes ("")
				else if (record.charAt(current) == '\"' && record.charAt(current + 1) == '\"') {
					current++; // skips second quote start stays same
				}
				// split at commas not in quotes
				else if (record.charAt(current) == ',' && !inQuotes) {
					fields.add(record.substring(start, current));
					start = current + 1; //skips comma for next field
				}
				current++;
			}
			//for special case, avoids adding ending quote to field
			if (record.charAt(current - 1) == '\"') {
				current--;
			}
			fields.add(record.substring(start, current).replace("\"\"", "\""));
				
			actors.add(new ActorRecord(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4),
					fields.get(5), fields.get(6), fields.get(7), fields.get(8), fields.get(9)));
			
		}// end input for actors ArrayList
				
		
		// read in data for movies ArrayList
		// this is a modification of code found at:
		// https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
		
		try {
			input = new Scanner(new File("movie.csv"));
		} catch (FileNotFoundException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		// first record is header
		input.nextLine();
		
		while(input.hasNext()) {
			String record = input.nextLine();
			List<String> fields = new ArrayList<String>();
			int start = 0;
			boolean inQuotes = false;
			int current = 0;
			while (current < record.length()) {
				//special case (") at end of record
				if (current == record.length() - 1 && inQuotes && record.charAt(current) == '\"') {
					current++; //to kick out of while loop
					start++;  //skips first double quote when adding to fields ArrayList
					continue;
				}
				// case double quote (")
				if (record.charAt(current) == '\"' && record.charAt(current + 1) != '\"') {
					//if ending quote
					if (inQuotes) {
						fields.add(record.substring(start + 1, current).replace("\"\"", "\""));
						start = current + 2; // skips ending quote and comma to start next field
						current++; //increments again before repeating while loop
					}	
					inQuotes = !inQuotes;
				}
				// case pair of double quotes ("")
				else if (record.charAt(current) == '\"' && record.charAt(current + 1) == '\"') {
					current++; // skips second quote start stays same
				}
				// split at commas not in quotes
				else if (record.charAt(current) == ',' && !inQuotes) {
					fields.add(record.substring(start, current));
					start = current + 1; //skips comma for next field
				}
				current++;
			}
			//for special case, avoids adding ending quote to field
			if (record.charAt(current - 1) == '\"') {
				current--;
			}
			fields.add(record.substring(start, current).replace("\"\"", "\""));
				
			movies.add(new MovieRecord(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4),
					fields.get(5), fields.get(6), fields.get(10)));
			
		}// end input for movies ArrayList
		
		
		//begin user interface (console)
		Scanner scnr = new Scanner(System.in);
		String wantSearch = "yes";
		
		int movieIndex = 1;
		
		while(wantSearch.equalsIgnoreCase("yes")) {
			System.out.println();
			System.out.println("1. Search for movie.");
			System.out.println("2. Add character data.");
			System.out.println("3. Edit actor information.");
			System.out.println("4. Quit.");
			System.out.println();
			System.out.print("Enter number of list item you would like: ");
			scnr.reset();
			String selection = scnr.nextLine();
			System.out.println();
			
			//Search for movie
			if(selection.equals("1")) {
				System.out.println("Enter title or a brief description of movie: ");
				//user enters a word or sentence here
				String movSearch = scnr.nextLine();
				System.out.println();
				movieIndex = movieSearch(movSearch);
				//no match found
				if(movieIndex == -1) {
					System.out.println();
					System.out.println("No movies found that match the search.");
					continue;
				}
				//display movie info
				movieInfo(movieIndex);
				
				//continue?
				System.out.println();
				System.out.println("Would you like to continue? (yes or no): ");
				wantSearch = scnr.nextLine();
				if(wantSearch.equalsIgnoreCase("yes")) {
					continue;
				}
				
			}//end search for movie
			
			//add character data
			else if(selection.equals("2")) {
				addCharacter();
				continue;
			}//end add character data
			
			//edit actor info
			else if(selection.equals("3")) {
				editActorInfo();
				continue;
			}
			
			else { //selection == 4, quit
				wantSearch = "no";
				System.out.println();
			}
			System.out.println("Have a nice day!");
				
		}//end while
		
		if(updateCharCsv) {
			updateCharacterCsv();
		}
		if(updateActCsv) {
			if(sortedByActorName) {
				updateActorCsv();
			}
			else {
				actorNameMergesort(0, actors.size() - 1);
				updateActorCsv();
			}
		}
		
		
	input.close();
	scnr.close();
	input2.close();
	if(csvWriter != null) {
		csvWriter.close();
	}
	
	}//end main
	
	
	//movieSearch method receives String with search criteria (String key)
	//searches for match first in movie titles, then if no match in movie summaries
	//returns index of MovieRecord within movies ArrayList or -1 if still no match found
	public static int movieSearch(String key) {

		//these used in LcsMatrix call
        ArrayList<String> searchKey = new ArrayList<String>();  //from user input
        ArrayList<String> searchTarget = new ArrayList<String>();
        
        int margin = 0; //margin of error for lcs matches
        
        //prepString returns keywords of search with punctuation removed
        searchKey = prepString(key);
			
		//check for matches in movie titles
		//we will iterate through movies, check key against target for longest common subsequences 
		//and make an ArrayList of likely matches based on how long the lcs is compared to the key
        
		
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<Integer> matchIndex = new ArrayList<Integer>(); //keep track of indexes
		
		for(int i = 0; i < movies.size(); i++) {
			searchTarget = new ArrayList<String>();
		    //assign String from MovieRecord title
			String target;
			target = new String(movies.get(i).getTitle());
			searchTarget = prepString(target);
	
			//we now have searchKey and searchTarget for LcsMatrix call
			//create matrix for this iteration (title)
			
			LcsMatrix matrix = new LcsMatrix(searchKey, searchTarget);
			
			//find suitable margin of error for lcs comparison to key
	        margin = 0; //this is suitable for up to 1 keywords in searchKey (if 0, all keywords must be found in sequence in target)
	        
	        //use smaller of searchKey and searchTarget to find margin if size of both > 1
	        if(searchKey.size() <= searchTarget.size() && searchKey.size() >= 2) {
	        	margin = searchKey.size() / 2; // 1/2 of keywords must be found in sequence within target for meaningful match
	        }
	        else if(searchTarget.size() <= searchKey.size() && searchTarget.size() >= 2) { 
	        	margin = searchTarget.size() / 2;
	        }
			
			//if meaningful match, add to matches
			if(searchKey.size() <= searchTarget.size() && matrix.getLcsLength() >= searchKey.size() - margin) {
				matches.add(movies.get(i).getTitle());
				matchIndex.add(i);  //keeps track of index within movies list	
			}
			else if(searchTarget.size() <= searchKey.size() && matrix.getLcsLength() >= searchTarget.size() - margin) {
				matches.add(movies.get(i).getTitle());
				matchIndex.add(i);  //keeps track of index within movies list
			}
		}//end title iterations
		
		//if no matches in titles, check summaries
		if(matches.size() == 0) {
			for(int i = 0; i < movies.size(); i++) {
				searchTarget = new ArrayList<String>();
			    //assign String from MovieRecord title
				String target;
				target = new String(movies.get(i).getSummary());
				searchTarget = prepString(target);
		
				//we now have searchKey and searchTarget for LcsMatrix call
				//create matrix for this iteration (summary)
				
				LcsMatrix matrix = new LcsMatrix(searchKey, searchTarget);
				
				//find suitable margin of error for lcs comparison to key
		        margin = 0; //this is suitable for up to 2 keywords in searchKey (if 0, all keywords must be found in sequence in target)
		        
		        //use smaller of searchKey and searchTarget to find margin if size of both > 2
		        if(searchKey.size() <= searchTarget.size() && searchKey.size() >= 3) {
		        	margin = searchKey.size() / 3; // 2/3 of keywords must be found in sequence within target for meaningful match
		        }
		        else if(searchTarget.size() <= searchKey.size() && searchTarget.size() >= 3) { 
		        	margin = searchTarget.size() / 3;
		        }
				
				//if meaningful match, add to matches
		        if(searchKey.size() <= searchTarget.size() && matrix.getLcsLength() >= searchKey.size() - margin) {
					matches.add(movies.get(i).getTitle());
					matchIndex.add(i);  //keeps track of index within movies list	
				}
				else if(searchTarget.size() <= searchKey.size() && matrix.getLcsLength() >= searchTarget.size() - margin) {
					matches.add(movies.get(i).getTitle());
					matchIndex.add(i);  //keeps track of index within movies list
				}	
			}//end summary iterations
		}
		
		//we now have list of possible matches and corresponding indexes
		
		if (matches.size() == 0) {  //no matches
			return -1;
		}
		else if(matches.size() == 1) { //exactly one match -- get confirmation from user
			input2 = new Scanner(System.in);
			System.out.println("Are you looking for the movie, " + matches.get(0) + " ? (yes or no): ");
			String foundMatch = input2.nextLine();
			if(foundMatch.equalsIgnoreCase("yes")) {
			    return matchIndex.get(0).intValue();
			}
			else { //not correct match
				return -1;
			}
		}
		else {   //more than 1 possible match
			//list possible matches
			if(matches.size() <= 30) {
				System.out.println();
				for ( int i = 0; i < matches.size(); i++) {
					System.out.println((i+1) + ". " + matches.get(i)); //numbered list
				}
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Please enter list number of movie from these possible matches or 0 if not listed:  ");
				int finalmatch = input2.nextInt() - 1; //index of actual school within matches
				if(finalmatch >= 0) {
				    return matchIndex.get(finalmatch).intValue(); // index within movies list
				}
				else {
					return -1; //no match
				}
			}
			else {
				System.out.println();
				System.out.println("No meaningful matches found.");
				return -1;
			}
			
		}
		
	}//end movieSearch
	
	//prepString method receives a String (search entered by user), cleans it,
	//and returns ArrayList<String> of keywords
	//used to prepare search key strings entered by user and target strings in data for LcsMatrix calls
	private static ArrayList<String> prepString(String key){
		ArrayList<String> cleanList = new ArrayList<String>();
		//create HashSet of non-keywords
		String[] nonKey = {"it", "the", "a", "for", "and", "nor", "yet", "or", "but", "so", "as", "them", "of", "that",
				           "It", "The", "A", "For", "And", "Nor", "Yet", "Or", "But", "So", "As", "Them", "Of", "That"};
		//create HashSet for efficient, quick checks
		HashSet<String> nonKeywords = new HashSet<String>();
		for(int i = 0; i < nonKey.length; i++) {
			nonKeywords.add(nonKey[i]);
		}
		
		//clean-up String a little
		char[] cloneKey = key.replace("  ", " ").toCharArray();  //replaces double spaces with single and transfer to char{}
		
		//move periods, commas, quotes, colons, semi-colons and question marks to end
		int matchCount = 0;
		int m = 0;
		while(m < cloneKey.length - matchCount) {
			if(cloneKey[m] == '.' || cloneKey[m] == ',' || cloneKey[m] == '\"' || cloneKey[m] == '\''
				||cloneKey[m] == ':' || cloneKey[m] == ';' || cloneKey[m] == '?' || cloneKey[m] == '/') {
				char temp = cloneKey[m];
				for(int j = m; j < cloneKey.length - 1; j++) {
					cloneKey[j] = cloneKey[j + 1];
				}
				cloneKey[cloneKey.length - 1] = temp;
				matchCount++;
			}
			m++;
		}
		//transfer to shortened char[]
		char[] newClone = new char[cloneKey.length - matchCount];
		for(int i = 0; i < newClone.length; i++) {
			newClone[i] = cloneKey[i];
		}
		String cleanSearch = new String(newClone).replace("-", " "); //cleanSearch still has single spaces between words with punctuation removed
		
		//turn cleanSearch into ArrayList<String> cleanList needed for LcsMatrix call
		String[] key1 = cleanSearch.split(" ");
		boolean hasKeyWords = false;
		for(int i = 0; i < key1.length; i++) {
			if(!nonKeywords.contains(key1[i])){
				hasKeyWords = true;
				break;
			}
		}
		for(int i = 0; i < key1.length; i++) {
			//if hasKeyWords is true, add words from key1 not found in nonKeywords HashSet
			if(hasKeyWords == true && !nonKeywords.contains(key1[i])) {
			cleanList.add(key1[i]);
			}
			//if hasKeyWords is false, add all words from key1
			else if(hasKeyWords == false){
				cleanList.add(key1[i]);
			}
		}
		return cleanList;
	}//end prepString method
	
	
	//movieInfo method displays information about the chosen movie and a list of actors
	//receives index within movies ArrayList of movie to display
	private static void movieInfo(int index) {
		System.out.println();
		System.out.println(movies.get(index).getTitle() + " is a " + movies.get(index).getGenre() + " film that was released in " 
				+ movies.get(index).getDate().substring(0, 4) + ".");
		System.out.println();
		System.out.println(movies.get(index).getSummary());
		
		//get character list
		ArrayList<Character> movieCharacters = new ArrayList<Character>();
		movieCharacters = buildCharList(movies.get(index).getId());
		//check if default Character record (no character data for movie)
		if(movieCharacters.get(0).getMovieId() == 0) {
			movieCharacters.clear();
		}
		System.out.println();
		System.out.println("The list of actors includes: ");
		System.out.println();
		
		//actor id is not included in MovieRecord, so we must use movieId to retrieve actorId from Character data
		//then search ActorRecord data for actor
		
		//sort actors list by actorId if needed
		if(sortedByActorName) {
			radActorSort();//sorts by id
		}
		
		//display actor list
		for(int i = 0; i < movieCharacters.size(); i++) {
			int actorIndex = binActorSearch(movieCharacters.get(i).getActorId());
			String actor = actors.get(actorIndex).getName();
			System.out.println((i+1) + ". " + actor + " plays \"" + movieCharacters.get(i).getName() + "\".");
		}
		if(movieCharacters.size() == 0) {
			System.out.println();
			System.out.println("No actor or character information.");
		}
			
		
	}//end movieInfo method
	
	
	//radActorSort is a Radix sort that will sort the actors ArrayList by actorId field from lowest to highest id
	//method sorts it in place when called
	public static void radActorSort() {
		//create buckets array
		ArrayList<ArrayList<ActorRecord>> buckets = new ArrayList<ArrayList<ActorRecord>>();
		for (int i = 0; i < 10; i++) {
			buckets.add(new ArrayList<ActorRecord>());
		}
		
		//get max digits
		int maxDigits = getMaxDigits(actors);
		
		int pow10 = 1;
		for(int digIndex = 0; digIndex < maxDigits; digIndex++) {
			//move records from actors list to bucket array
			for(int i = 0; i < actors.size(); i++) {
				int bucketIndex = (actors.get(i).getActorId() / pow10) % 10; //bucketIndex will range from 0 to 9
				buckets.get(bucketIndex).add(actors.get(i));
			}
			//move records back to actors list
		    actors = new ArrayList<ActorRecord>();
		    for(int i = 0; i < 10; i++) {
		    	for(int j = 0; j < buckets.get(i).size(); j++) {
		    		actors.add(buckets.get(i).get(j));
		    	}
		    }
			pow10 = pow10 * 10;
			buckets = new ArrayList<ArrayList<ActorRecord>>();
			for (int i = 0; i < 10; i++) {
				buckets.add(new ArrayList<ActorRecord>());
			}
		}
		sortedByActorName = false;
	}//end radActorSort method
	
	
	//for Radix sort, getMaxDigits will receive ArrayList<ActorRecord> and return maximum digits of largest
	//number in actorId field as an int
	public static int getMaxDigits(ArrayList<ActorRecord> actors) {
		int maxDigits = 0;
		int curDigits;
		
		for(int i = 0; i < actors.size(); i++) {
			curDigits = getDigitLength(actors.get(i).getActorId());
			if(curDigits > maxDigits) {
				maxDigits = curDigits;
			}
		}
		return maxDigits;
	}//end getMaxDigits method
	
	
	//Radix getDigitLength method receives an int and returns number of digits in it
	//as an int - used in Radix Sort
	public static int getDigitLength(int num) {
		if(num == 0) {
			return 1;
		}
		int numDigits = 0;
		while(num != 0) {
			numDigits++;
			num = num / 10;
		}
		return numDigits;
	}//end getDigitLength method
	
	
	//binActorSearch method receives actorId and returns index of ActorRecord in actors ArrayList
	//or -1 if no match found
	public static int binActorSearch(int id) {
		int low = 0;
		int high = actors.size() - 1;
		int mid = 0;
		
		while(low <= high) {
			mid = (low + high) / 2;
			if(actors.get(mid).getActorId() == id) {
				return mid;
			}
			else if(actors.get(mid).getActorId() < id) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		return -1;
	}//end binActorSearch method
	
	
	//buildCharList method receives int movieId and returns ArrayList<Character> list of all Character records
	//associated with the movie, movieId field in Character records is already sorted but with multiple entries
	//for each movie
	public static ArrayList<Character> buildCharList(int movId){
		ArrayList<Character> charList = new ArrayList<Character>();
		int low = 0;
		int high = characters.size() - 1;
		int mid = 0;
		
		while(low <= high) {
			mid = (low + high) / 2;
			if(characters.get(mid).getMovieId() == movId) {
				//find index of first matching Character record in characters list
				int index = mid;
				while(characters.get(index).getMovieId() == movId) {
					//special case 1st movie in list
					//avoids looping again with index = -1
					if(index == 0) {
						break;
					}
					index--;
				}
				//if index = 0, no need to increment
				if(index != 0) {
				index++;
				}
				//index is now index to first matching record
				
				//build queue with all matching entries
				while(characters.get(index).getMovieId() == movId) {
					charList.add(characters.get(index));
					//special case last entry
					if (index == characters.size() -1) {
						break;
					}
				    index++;
				}
				return charList;
			}
			else if(characters.get(mid).getMovieId() < movId) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		Character emptyRecord = new Character();
		charList.add(emptyRecord);
		return charList; //returns list with 1 default record if no match
		
	}//end buildCharList method
	
	//searchActorId method receives an actors name and searches actors list to return id
	//or -1 if actor is not on list yet
	public static int searchActorId(String name) {
		int low = 0;
		int high = actors.size() - 1;
		int mid = 0;
		
		while (low <= high) {
			mid = (low + high) / 2;
			if(actors.get(mid).getName().equals(name)) {
				return actors.get(mid).getActorId();
			}
			else if(actors.get(mid).getName().compareTo(name) < 0) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		return -1;
	}
    
	//addCharacter method is used to add missing data to characters list
	//character.csv is missing info for over 100 movies listed in movie.csv
	public static void addCharacter() throws IOException {
		Character newChar;
		input2 = new Scanner(System.in);
		String charName;
		String actorName;
		int movieId;
		int actorId;
		int creditOrder;
		
		//get info for Character fields
		System.out.println("Enter the movie id: ");
		movieId = input2.nextInt();
		input2 = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter the name of the character: ");
		charName = input2.nextLine();
		input2 = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter the credit order for the character (1-9): ");
		creditOrder = input2.nextInt();
		input2 = new Scanner(System.in);
		System.out.println();
		System.out.println("Name of actor who plays \"" + charName + "\": ");
		actorName = input2.nextLine();
		
		//resort actors list by name only if needed
		if(sortedByActorName) {
			actorId = searchActorId(actorName);
		}
		else {
			actorNameMergesort(0, actors.size() - 1);
			actorId = searchActorId(actorName);
		}
		
		//no actor file yet
		if(actorId == -1) {
			newChar = new Character(movieId, charName, creditOrder);
			actorId = newChar.getActorId();
			ActorRecord newActor = new ActorRecord(actorId, actorName);
			actors.add(newActor);
			updateActCsv = true;  //will update actor.csv at close of session
			actorNameMergesort(0, actors.size() - 1);
			enterActorInfo(newActor);
		}
		else {
			newChar = new Character(movieId, actorId, charName, creditOrder);
		}
		
		characters.add(newChar);
		updateCharCsv = true; //will update character.csv at end of main
		
			
	}//end addCharacter method
	
	//updateCharacterCsv method updates all entries in character.csv
	//this keeps the static actorIndex current for next use
	public static void updateCharacterCsv() throws IOException {
		FileWriter headerWriter = null;
		//write header
		try {
			headerWriter = new FileWriter("character.csv");
		}catch (IOException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		String header = "movieId,actorId,characterName,creditOrder,pay,screenTime,actorIndex" + "\n";
		try {
			headerWriter.append(header);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		headerWriter.close();
		
		try {
			csvWriter = new FileWriter("character.csv", true);
		}catch (IOException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		for(int m = 0; m < characters.size(); m++) {
			Character newChar = new Character(characters.get(m));
			int movieId = newChar.getMovieId();
			int actorId = newChar.getActorId();
			String charName = newChar.getName();
			int creditOrder = newChar.getOrder();
			
			//build csv record line
			//movieId,actorId,charName,creditOrder,pay,screenTime
			
			char[] rec = new char[newChar.getCsvRecordLength()];
			int j = 0;
			
			//movieId field
			int dig = getDigitLength(movieId);
			int pow10 = (int)Math.pow(10, (dig - 1));
			for(int i = 0; i < dig; i++) {
				rec[j] = (char)((movieId / pow10) + 48);
				movieId = movieId % pow10;
				pow10 = pow10 / 10;
				j++;
			}
			rec[j] = ',';
			j++;
			
			//actorId field
			dig = getDigitLength(actorId);
			pow10 = (int)Math.pow(10, (dig - 1));
			for(int i = 0; i < dig; i++) {
				rec[j] = (char)((actorId / pow10) + 48);
				actorId = actorId % pow10;
				pow10 = pow10 / 10;
				j++;
			}
			rec[j] = ',';
			j++;
			
			//charName field
			//check if field needs to be quoted
			if(charName.contains(",") || charName.contains("\"")) {
				rec[j] = '\"'; //double quote to start field
				j++;
				String cloneName = charName.replace("\"", "\"\"");
				for(int i = 0; i < cloneName.length(); i++) {
					rec[j] = cloneName.charAt(i);
					j++;
				}
				rec[j] = '\"'; //double quote to end field
				j++;
			}
			else { //field not quoted
				for(int i = 0; i < charName.length(); i++) {
					rec[j] = charName.charAt(i);
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//creditOrder field (one digit, 1-9)
			rec[j] = (char)(creditOrder + 48);
			j++;
			rec[j] = ',';
			j++;
			
			//pay field
			for (int i = 0; i < newChar.getPay().length(); i++) {
				rec[j] = newChar.getPay().charAt(i);
				j++;
			}
			rec[j] = ',';
			j++;
			
			//screenTime field
			for (int i = 0; i < newChar.getTime().length(); i++) {
				rec[j] = newChar.getTime().charAt(i);
				j++;
			}
			rec[j] = ',';
			j++;
			
			//actorIndex field
			dig = getDigitLength(newChar.getActorIndex());
			pow10 = (int)Math.pow(10, (dig - 1));
			int actorIndex = newChar.getActorIndex();
			for(int i = 0; i < dig; i++) {
				rec[j] = (char)((actorIndex / pow10) + 48);
				actorIndex = actorIndex % pow10;
				pow10 = pow10 / 10;
				j++;
			}
			rec[j] ='\n';
			
			String record = new String(rec);
		//	System.out.println(record);
			
			//append record to csv
			try {
				csvWriter.append(record);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			csvWriter.flush();
		}//end for loop
		
	} //end updateCharacterCsv method
	
	public static void updateActorCsv() throws IOException {
		FileWriter headerWriter = null;
		//write header
		try {
			headerWriter = new FileWriter("actor.csv");
		}catch (IOException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		String header = "actorid,name,date_of_birth,birth_city,birth_country,height_inches,biography,gender,ethnicity,networth" + "\n";
		try {
			headerWriter.append(header);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		headerWriter.close();
			
		try {
			csvWriter = new FileWriter("actor.csv", true);
		}catch (IOException e) {
			//file not found
			System.out.println("file not found");
			e.printStackTrace();
		}
		
		for(int m = 0; m < actors.size(); m++) {
			ActorRecord record = new ActorRecord(actors.get(m));
			//create local field variables
			
			int actorId = record.getActorId();
			String name = record.getName();
			String dob = record.getDob();
			String city = record.getCity();
			String country = record.getCountry();
			int height = record.getHeight();
			String bio = record.getBio();
			String gender = record.getGender();
			String ethnicity = record.getEthnicity();
			long netWorth = record.getNetWorth();
			
			//create char[] and index variable j to use with it
			char[] rec = new char[record.getCsvRecordLength()];
			int j = 0;
			
			//build char[] for csv record line
			
			//actorId field
			int dig = getDigitLength(actorId);
			int pow10 = (int)Math.pow(10, (dig - 1));
			for(int i = 0; i < dig; i++) {
				rec[j] = (char)((actorId / pow10) + 48);
				actorId = actorId % pow10;
				pow10 = pow10 / 10;
				j++;
			}
			rec[j] = ',';
			j++;
			
			//name field
			//check if field needs to be quoted
			if(name.contains(",") || name.contains("\"")) {
				rec[j] = '\"'; //double quote to start field
				j++;
				String cloneString = name.replace("\"", "\"\"");
				for(int i = 0; i < cloneString.length(); i++) {
					rec[j] = cloneString.charAt(i);
					j++;
				}
				rec[j] = '\"'; //double quote to end field
				j++;
			}
			else { //field not quoted
				for(int i = 0; i < name.length(); i++) {
					rec[j] = name.charAt(i);
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//dob field
			for (int i = 0; i < dob.length(); i++) {
				rec[j] = dob.charAt(i);
				j++;
			}
			rec[j] = ',';
			j++;
			
			//city field
			//check if field needs to be quoted
			if(city.contains(",") || city.contains("\"")) {
				rec[j] = '\"'; //double quote to start field
				j++;
				String cloneString = city.replace("\"", "\"\"");
				for(int i = 0; i < cloneString.length(); i++) {
					rec[j] = cloneString.charAt(i);
					j++;
				}
				rec[j] = '\"'; //double quote to end field
				j++;
			}
			else { //field not quoted
				for(int i = 0; i < city.length(); i++) {
					rec[j] = city.charAt(i);
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//country field
			//check if field needs to be quoted
			if(country.contains(",") || country.contains("\"")) {
				rec[j] = '\"'; //double quote to start field
				j++;
				String cloneString = country.replace("\"", "\"\"");
				for(int i = 0; i < cloneString.length(); i++) {
					rec[j] = cloneString.charAt(i);
					j++;
				}
				rec[j] = '\"'; //double quote to end field
				j++;
			}
			else { //field not quoted
				for(int i = 0; i < country.length(); i++) {
					rec[j] = country.charAt(i);
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//height field
			//if height = 0, 0 not included in csv file
			if(height > 0) {
				dig = getDigitLength(height);
				pow10 = (int)Math.pow(10, (dig - 1));
				for(int i = 0; i < dig; i++) {
					rec[j] = (char)((height / pow10) + 48);
					height = height % pow10;
					pow10 = pow10 / 10;
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//bio field
			//check if field needs to be quoted
			if(bio.contains(",") || bio.contains("\"")) {
				rec[j] = '\"'; //double quote to start field
				j++;
				String cloneString = bio.replace("\"", "\"\"");
				for(int i = 0; i < cloneString.length(); i++) {
					rec[j] = cloneString.charAt(i);
					j++;
				}
				rec[j] = '\"'; //double quote to end field
				j++;
			}
			else { //field not quoted
				for(int i = 0; i < bio.length(); i++) {
					rec[j] = bio.charAt(i);
					j++;
				}
			}
			rec[j] = ',';
			j++;
			
			//gender field
			for (int i = 0; i < gender.length(); i++) {
				rec[j] = gender.charAt(i);
				j++;
			}
			rec[j] = ',';
			j++;
			
			//ethnicity field
			for (int i = 0; i < ethnicity.length(); i++) {
				rec[j] = ethnicity.charAt(i);
				j++;
			}
			rec[j] = ',';
			j++;
			
			//netWorth field
			//find digit length (dig) for long variable netWorth
			dig = 0;
			//only add digits to file if netWorth > 0 (0 not included in csv file)
			long nW = netWorth;
			while(nW >= 1) {
				dig++;
				nW = nW / 10;
			}
			
			long power10 = (long)Math.pow(10, (dig - 1));
			for(int i = 0; i < dig; i++) {
				rec[j] = (char)((netWorth / power10) + 48);
				netWorth = netWorth % power10;
				power10 = power10 / 10;
				j++;
			}
			
			rec[j] ='\n';
			
			String newRecord = new String(rec);
			
			//append record to csv
			try {
				csvWriter.append(newRecord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			csvWriter.flush();
		}
		
		
	} //end updateActorCsv method
    
	public static void enterActorInfo(ActorRecord actor) {
		input2 = new Scanner(System.in);
		
		String name = actor.getName();
		String dob;
		String city;
		String country;
		int height;
		String bio;
		String gender;
		String ethnicity;
		long netWorth;
		
	    boolean again = true;
	    
		while(again) {
			//display existing info
			System.out.println();
			System.out.println("Existing information for " + name + ":");
			
			System.out.println();
			System.out.println("1. Date of birth: " + actor.getDob());
			System.out.println("2. City of birth: " + actor.getCity());
			System.out.println("3. Country of birth: " + actor.getCountry());
			System.out.println("4. Height (in inches): " + actor.getHeight());
			System.out.println("5. Brief biography: " + actor.getBio());
			System.out.println("6. Gender: " + actor.getGender());
			System.out.println("7. Ethnicity: " + actor.getEthnicity());
			System.out.println("8. Net worth: " + actor.getNetWorth());
			
			int choice;
			System.out.println();
			System.out.println("Enter line number (1-8) of field you would like to update, or 0 if none: ");
			String entered = input2.nextLine();
			//check input
			if(entered.length() != 1) {
				System.out.println("Invalid input!");
				continue;
			}
			if((int) entered.charAt(0) < 48 || (int)entered.charAt(0) > 56) {
				System.out.println("Invalid input!");
				continue;
			}
			choice = (int)entered.charAt(0) - 48;
			switch(choice) {
			case 0: //quit
				again = false;
				break;
				
			case 1: //dob
				input2 = new Scanner(System.in);
				boolean goodFormat = false;
				while(goodFormat == false) {
					System.out.println();
					System.out.println("Enter date of birth (yyyy-mm-dd): ");
					dob = input2.nextLine();
					//check format
					goodFormat = checkDateFormat(dob);
					if(goodFormat == false) {
						System.out.println("Date entered was not formatted properly!");
					}
					else {
						actor.setDob(dob);
						System.out.println();
						System.out.println("Date of birth updated.");
					}
				}
				
				break;
				
			case 2: //city
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter city of birth: ");
				city = input2.nextLine();
				actor.setCity(city);
				System.out.println();
				System.out.println("City of birth updated.");
				break;
				
			case 3: //country
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter country of birth: ");
				country = input2.nextLine();
				actor.setCountry(country);
				System.out.println();
				System.out.println("Country of birth updated.");
				break;
				
			case 4: //height
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter height (in inches): ");
				height = input2.nextInt();
				actor.setHeight(height);
				System.out.println();
				System.out.println("Height updated.");
				break;
				
			case 5: //bio
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter a brief biography: ");
				bio = input2.nextLine();
				actor.setBio(bio);
				System.out.println();
				System.out.println("Biography updated.");
				break;
				
			case 6: //gender
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter actor/actress' gender: ");
				gender = input2.nextLine();
				actor.setGender(gender);
				System.out.println();
				System.out.println("Gender updated.");
				break;
				
			case 7: //ethnicity
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter actor/actress' ethnicity: ");
				ethnicity = input2.nextLine();
				actor.setEthnicity(ethnicity);
				System.out.println();
				System.out.println("Ethnicity updated.");
				break;
				
			case 8: //net worth
				input2 = new Scanner(System.in);
				System.out.println();
				System.out.println("Enter net worth (numerical digits only): ");
				netWorth = input2.nextLong();
				actor.setNetWorth(netWorth);
				System.out.println();
				System.out.println("Net worth updated.");
				break;
				
				default:
					System.out.println();
					System.out.println("Invalid choice!");
					continue;
			
			}//end switch
			updateActCsv = true;
			
		}//end while
		
		
		
		
		
		
		
	}//end enterActorInfo method
	
	//checkDateFormat checks a String for proper format yyyy-mm-dd
	//only checks format, not viability of date
	public static boolean checkDateFormat(String date) {
		boolean correct = true;
		if(date.length() > 10 || date.length() < 10) {
			return false;
		}
		for(int i = 0; i < date.length(); i++) {
			//4 and 7 are indices of hyphens
			if(i == 4 || i == 7) {
				//not a hyphen
				if((int) date.charAt(i) != 45) {
					return false;
				}
				else {
					continue;
				}
			}
			//not a numerical digit
			if((int) date.charAt(i) < 48 || (int) date.charAt(i) > 57) {
				return false;
			}
		}
		return correct;
	}
    
	//merge method for actorNameMergesort
	//merges two sorted sections of the list(i-j; and (j+1)-k) into one sorted section (i-k)
	public static void actorNameMerge(int i, int j, int k) {
		ArrayList<ActorRecord> mergeList = new ArrayList<ActorRecord>();
		
		int leftPos = i;
		int rightPos = j + 1;
		
		while(leftPos <= j && rightPos <= k) {
			if(actors.get(leftPos).getName().compareTo(actors.get(rightPos).getName()) <= 0) {
				mergeList.add(actors.get(leftPos));
				leftPos++;
			}
			else {
				mergeList.add(actors.get(rightPos));
				rightPos++;
			}
		}//end while
		
		while(leftPos <= j) {
			mergeList.add(actors.get(leftPos));
			leftPos++;
		}
		while(rightPos <= k) {
			mergeList.add(actors.get(rightPos));
			rightPos++;
		}
		//fill list(i-k) with mergeList
		int listIndex = i;
		for(int m = 0; m < mergeList.size(); m++) {
			actors.remove(listIndex);
			actors.add(listIndex, mergeList.get(m));
			listIndex++;
		}
	} //end actorNameMerge method
	
	public static void actorNameMergesort(int i, int k) {
		int j = 0;
		
		if(i < k) {
			j = (i + k) / 2;
			
			actorNameMergesort(i, j);
			actorNameMergesort(j+1, k);
			
			actorNameMerge(i, j, k);
		}
		sortedByActorName = true;	
	} //end actorNameMergesort method
    
	public static void editActorInfo() {
		input2 = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Enter name of actor to edit: ");
		String name = input2.nextLine();
		
		//we need actorId to find index in actors using binActorSearch method
		//find actorId
		int id;
		int index;
		if(sortedByActorName) {
			id = searchActorId(name);
		}
		else {
			actorNameMergesort(0, actors.size() - 1);
			id = searchActorId(name);
		}
		
		if(id == -1) {
			System.out.println();
			System.out.println("We have no record for " + name);
		}
		else {
			radActorSort();
			index = binActorSearch(id);
			enterActorInfo(actors.get(index));
		}
	}
}// end class

