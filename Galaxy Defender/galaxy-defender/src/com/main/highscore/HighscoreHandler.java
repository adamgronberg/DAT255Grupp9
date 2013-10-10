package highscore;


import java.io.*;

import com.badlogic.gdx.utils.Array;




public class HighscoreHandler {
	
	private static final int NUMBEROFHEROES = 13;
	private static final String FILENAME = "highscore";
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
    	
    	if(currentUser.getScore()>highscore.get(NUMBEROFHEROES-1).getScore()){
    		highscore.removeIndex(highscore.size-1);
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
    
    public Array<User> getHighscore(){
    	return highscore;
    }
    
    /**
     * remove all highscoreholders
     */
    public void resetHighscore(){
    	highscore = new Array<User>();
    }
    
    /**
     * save the current highscore to file
     */
    public void writeToFile(){
    	
    	try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            for (int i = 0; i < highscore.size; i++) {
                out.writeObject(highscore.get(i));   
            }
            out.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Cannot Create: " +FILENAME);
        }
        catch(IOException e){
            e.printStackTrace();}
    }
    
    

}
