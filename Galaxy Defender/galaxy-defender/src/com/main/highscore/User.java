package highscore;

public class User {
	private int score;
	private String name;
	
	/**
	 * 
	 * @param score
	 * @param name
	 */
	public User(int score, String name){
		this.score = score;
		this.name = name;
		
	}
	
	/**
	 * 
	 * @return name of owner of the score
	 */	
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return the score the player cerently have
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * 
	 * @param amount Amount of points added to already earned points 
	 */
	public void addScore(int amount){
		score += amount; 
	}
}
