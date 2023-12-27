/*
 * Mark Hamilton
 * CPSC 39
 * Professor Kanemoto
 * Final Project
 */
package movieData2;

public class MovieRecord {
	private int movieId;
	private String title;
	private String rating;
	private long budget;
	private long gross;
	private String date;
	private String genre;
	private String summary;
	
	//constructor
	public MovieRecord(String movieId, String title, String rating, String budget,
			           String gross, String date, String genre, String summary) {
		this.movieId = Integer.parseInt(movieId);
		this.title = title;
		this.rating = rating;
		this.budget = Long.parseLong(budget);
		this.gross = Long.parseLong(gross);
		this.date = date;
		this.genre = genre;
		this.summary = summary;
	}
	
	//getters and setters
	public int getId() {
		return movieId;
	}
	public void setId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public long getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public long getGross() {
		return gross;
	}
	public void setGross(int gross) {
		this.gross = gross;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	//getNet method
	public long getNet() {
		return gross - budget + 1 - 1;
	}
	
	
	@Override
	public String toString() {
		return "Movie [Id is " + movieId + "; Title is " + title + "; Rating is " + rating + "; Budget was " + budget
				+ "; Grossed " + gross + "; Released on " + date + "; Genre is " + genre + "]";
	}

}
