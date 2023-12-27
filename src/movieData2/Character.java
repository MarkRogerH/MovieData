/*
 * Mark Hamilton
 * CPSC 39
 * Professor Kanemoto
 * Final Project
 */
package movieData2;


//Character object holds character name, id# of actor, id# of movie

public class Character {
	private String name;
	private int actorId;
	private int movieId;
	private int creditOrder;
	private String pay;
	private String screenTime;
	private static int actorIndex;
	
	//constructor for reading in data
	public Character(String movieId, String actorId, String name, String creditOrder, String pay, String screenTime
			, String actorIndex) {
//	public Character(String movieId, String actorId, String name, String creditOrder, String pay, String screenTime) {
		super();
		this.name = name;
		this.actorId = Integer.parseInt(actorId);
		this.movieId = Integer.parseInt(movieId);
		this.creditOrder = Integer.parseInt(creditOrder);
		this.pay = pay;
		this.screenTime = screenTime;
		Character.actorIndex = Integer.parseInt(actorIndex);
	}
	
	//used to add Character of existing actor
	public Character(int movieId, int actorId, String name, int creditOrder) {
		super();
		this.movieId = movieId;
		this.actorId = actorId;
		this.name = name;
		this.creditOrder = creditOrder;
		pay = "";
		screenTime = "";
	}
	
	//used to add Character of actor not yet on actors list
	public Character(int movieId, String name, int creditOrder) {
		this.movieId = movieId;
		this.actorId = Character.actorIndex;
		Character.actorIndex++;
		this.name = name;
		this.creditOrder = creditOrder;
		pay = "";
		screenTime = "";
	}
	
	//copy constructor
	public Character(Character toCopy) {
		super();
		this.movieId = toCopy.movieId;
		this.actorId = toCopy.actorId;
		this.name = toCopy.name;
		this.creditOrder = toCopy.creditOrder;
		this.pay = toCopy.pay;
		this.screenTime = toCopy.screenTime;
	}
	
	//default
	public Character() {
		super();
		this.name = "no name";
		this.actorId = 0;
		this.movieId = 0;
		this.creditOrder = 0;
		this.pay = "unknown";
		this.screenTime = "unknown";
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getOrder() {
		return creditOrder;
	}
	public void setOrder(int creditOrder) {
		this.creditOrder = creditOrder;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getTime() {
		return screenTime;
	}
	public void setTime(String screenTime) {
		this.screenTime = screenTime;
	}
	
	public int getActorIndex() {
		return actorIndex;
	}
	public void setActorIndex(int actorIndex) {
		Character.actorIndex = actorIndex;
	}
	
	//used to find necessary length of char[] to hold completed csv record line for writing
	public int getCsvRecordLength() {
		int count = 7; //for 6 commas between 7 fields to be written and \n
		if(name.contains(",") || name.contains("\"")) {
			count = count + 2; //for beginning and ending double quote
			for(int i = 0; i < name.length(); i++) {
				if(name.charAt(i) == '\"') {
					count++; //double quotes will be replaced by pairs of double quotes within quoted field
				}
			}
		}
		return count + name.length() + MovieDataProgram.getDigitLength(actorId) + MovieDataProgram.getDigitLength(movieId) +
				MovieDataProgram.getDigitLength(creditOrder) + pay.length() + screenTime.length()
				+ MovieDataProgram.getDigitLength(actorIndex) + 1 - 1;
	}
	
	//toString override
	@Override
	public String toString() {
		return "Character [name is " + name + "; actor id is " + actorId + "; movie id is " + movieId + "]";
	}

}
