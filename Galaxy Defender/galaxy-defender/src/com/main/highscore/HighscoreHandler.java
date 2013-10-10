package highscore;

import com.badlogic.gdx.utils.Array;

public class HighscoreHandler {
	
	private static final int NUMBEROFHEROES = 13;
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
    	currentUser = new User(0,name);
    }
    
    protected void addPlayerToHighscore(){
    	for(int i = 0; i<NUMBEROFHEROES; i++){
    		if(highscore.get(i).getScore()<currentUser.getScore()){
    			
    		}
    	}
    }

}
