package highscore;

import com.badlogic.gdx.utils.Array;

public class HighscoreHandler {
	
	private static final int NUMBEROFHEROES = 13;
	private static HighscoreHandler instance = null;
	private Array<User> highscore;
	private User currentUser;
	
	/**
	 * constructor
	 */	 
    private HighscoreHandler() {  
    	highscore = new Array<User>();
    }
    
    /**
     * 
     * @return the instance of HighscoreHandler
     */
    public static HighscoreHandler getInstance() {
            if (instance == null) {
                        if (instance == null) {
                                 instance = new HighscoreHandler ();
                        }
            }
            return instance;
    }
    
    /**
     * 
     * @param name The name of the new player
     * 
     */
    public void createPlayer(String name, int score){
    	currentUser = new User(score,name);
    }
    
    /**
     * adding the current user to highscore list if the point is good enough
     */
    protected void addPlayerToHighscore(){
    	if(highscore.size<NUMBEROFHEROES){
    		highscore.add(currentUser); 
    		return;
    	}
    	
    	if(currentUser.getScore()>highscore.get(highscore.size).getScore()){
    		highscore.removeIndex(highscore.size);
    		highscore.add(currentUser);   
    	}
    }
    
    /**
     * Fix the highscore so that they are sorted by points
     */
    protected void sortHighscore(){
    	for(int i = 0; i<highscore.size; i++){
    		for(int j = i; j<highscore.size; j++){
        		if(highscore.get(i).getScore()<highscore.get(j).getScore()){
        			highscore.swap(i, j);
        		}
        	}
    	}
    }
    
    

}
