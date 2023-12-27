/*
 * Mark Hamilton
 * CPSC 39
 * Professor Kanemoto
 * Final Project
 */
package movieData2;

//ActorRecord object holds actor id, name, date of birth, birth city and country, height, and biography

public class ActorRecord {
	private int actorId;
	private String name;
	private String dob;
	private String city;
	private String country;
	private int height;
	private String bio;
	private String gender;
	private String ethnicity;
	private long netWorth;
	
	//constructor
	public ActorRecord(String actorId, String name, String dob, String city, String country, String height, String bio, 
			String gender, String ethnicity, String netWorth) {
		super();
		this.actorId = Integer.parseInt(actorId);
		this.name = name;
		this.dob = dob;
		this.city = city;
		this.country = country;
		if (height.equals("")) {
			this.height = 0;
		}
		else {
		this.height = (int)Double.parseDouble(height);
		}
		this.bio = bio;
		this.gender = gender;
		this.ethnicity = ethnicity;
		if(netWorth.equals("")) {
			this.netWorth = 0;
		}
		else {
			this.netWorth = Long.parseLong(netWorth);
		}
	}
	public ActorRecord(int actorId, String name) {
		super();
		this.actorId = actorId;
		this.name = name;
		dob = "";
		city = "";
		country = "";
	//	height = 0;
		bio = "";
		gender = "";
		ethnicity = "";
	//	netWorth = 0;
	}
	
	//copy constructor
	public ActorRecord(ActorRecord toCopy) {
		super();
		this.actorId = toCopy.actorId;
		this.name = toCopy.name;
		this.dob = toCopy.dob;
		this.city = toCopy.city;
		this.country = toCopy.country;
		this.height = toCopy.height;
		this.bio = toCopy.bio;
		this.gender = toCopy.gender;
		this.ethnicity = toCopy.ethnicity;
		this.netWorth = toCopy.netWorth;
	}
	
	//getters and setters
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	
	public long getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(long netWorth) {
		this.netWorth = netWorth;
	}
	
	//used to find necessary length of char[] to hold completed csv record line for writing
	//we'll check name and bio fields for if they need to be contained in double quotes
	//and add characters needed to do that in addition to current String length
	public int getCsvRecordLength() {
		int count = 10; //for 9 commas between 10 fields to be written and \n
		// + actorId digits
		count = count + MovieDataProgram.getDigitLength(actorId);
		// + name length
		count = count + getStrCount(name);
		//dob field
		count = count + getStrCount(dob);
		//city field
		count = count + getStrCount(city);
		//country field
		count = count + getStrCount(country);
		//height field (int)
		//if height = 0, 0 not included in csv file
		int numDigHeight = 0;
		if(height > 0) {
			numDigHeight = MovieDataProgram.getDigitLength(height);
		}
		count = count + numDigHeight;
		//bio field
		count = count + getStrCount(bio);
		//gender field
		count = count + getStrCount(gender);
		//ethnicity field
		count = count + getStrCount(ethnicity);
		//netWorth field (long)
		//if net worth is 0 (not entered) the 0 will not be included in the csv file
		int numDigNW = 0;
		long nW = netWorth;
		while(nW >= 1) {
			numDigNW++;
			nW = nW / 10;
		}
		count = count + numDigNW;
		
		return count + 1 - 1;
	}
	
	private int getStrCount(String string) {
		int cnt = 0;
		if(string.contains(",") || string.contains("\"")) {
			cnt = cnt + 2; //for beginning and ending double quote
			for(int i = 0; i < string.length(); i++) {
				if(string.charAt(i) == '\"') {
					cnt++; //double quotes will be replaced by pairs of double quotes within quoted field
				}
			}
		}
		cnt = cnt + string.length();
		return cnt;
	}
		
		
	@Override
	public String toString() {
		return "Actor [Name is " + name + "; ID is " + actorId + "; Born on " + dob + " in " + city + "/" + country + "; Height is "
				+ height + " inches]";
	}

}//end class
