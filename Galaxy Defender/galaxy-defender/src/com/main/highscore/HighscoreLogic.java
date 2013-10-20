package highscore;


import java.util.ArrayList;
import java.util.Arrays;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Handles highscore logic and reading/saving
 * @author Grupp9
 */
public class HighscoreLogic {

	private static final int TOTAL_NUMBER_OF_USER = 13;
	private static String HIGHSCORELIST = "highscoreList";
	private static Preferences data;
	
	/**
	 * Loads options into preferences. Sets default values if no saved options
	 */
	public static void loadHighScoreList(){
		data = Gdx.app.getPreferences("galaxydefender.data");
		if(!data.contains(HIGHSCORELIST)) {
			resetHighScoreList();
		}
	}
	
	/**
	 * Resets the highscore list
	 */
	public static void resetHighScoreList(){
		String filler = "";
		for(int i = 1; i <= TOTAL_NUMBER_OF_USER; i++){
			filler += "____:0 ";
		}
		data.putString(HIGHSCORELIST, filler);
		data.flush();
	}
	
	/**
	 * Saves a name and a score to the highscore list
	 * @param name	The name of the player
	 * @param score	The score of the player
	 */
	public static void saveUserToHighScore(String name, int score){
		String[] users = data.getString(HIGHSCORELIST).split(" ");
		ArrayList<String> newUsers = new ArrayList<String>(Arrays.asList(users)); 
		if(users.length == 0) {
			newUsers.add(name + ":" + score);
		}
		else{
			for(int i = 0; i<TOTAL_NUMBER_OF_USER; i++){
				if(Integer.parseInt(newUsers.get(i).split(":")[1]) < score){
					newUsers.add(i, name + ":" + score );
					newUsers.remove(TOTAL_NUMBER_OF_USER);
					break;
				}
			}
		}
		data.putString(HIGHSCORELIST, newUsers.toString().replaceAll(",|\\[|\\]", ""));
		data.flush();
	}
	
	/**
	 * Gets the highscore list from the highscore handler
	 * @return The highscore list as a printable list
	 */
	public static String getPrintableHighscoreList(){
		
		String highScoreList = "";
		String[] users = data.getString(HIGHSCORELIST).split(" ");
		
		int position = 1;
		for(String user : users){
			String makeItPretty = "";
			if(position<10) makeItPretty = "  ";
			highScoreList += position + ". " + makeItPretty + user + "\n";
			position++;
		}
			
		return highScoreList;
	}
}
