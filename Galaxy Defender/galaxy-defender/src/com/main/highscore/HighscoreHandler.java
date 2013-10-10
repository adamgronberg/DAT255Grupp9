package highscore;

import com.badlogic.gdx.utils.Array;

public class HighscoreHandler {
	private static HighscoreHandler instance = null;
	private Array<User> highscore;
	private User currentUser;
	
	 
    private HighscoreHandler() {  
    	highscore = new Array<User>();
    }

    public static HighscoreHandler getInstance() {
            if (instance == null) {
                        if (instance == null) {
                                 instance = new HighscoreHandler ();
                        }
            }
            return instance;
    }
    
    public void createPlayer(String name){
    	
    }

}
