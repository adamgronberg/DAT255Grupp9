package highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;



/**
 * 
 * @author Grupp9
 * 
 * Handles score writing and reading from internal save file
 *
 */
public class HighscoreHandler {
	

	private static final int TOTAL_NUMBER_OF_USER = 13;
	private static final String FILENAME = "highscore.txt";
	private static HighscoreHandler instance = null;
	private Array<User> highscore;
	
	/**
	 * constructor
	 */	 
    private HighscoreHandler() {  
    	highscore = new Array<User>();
    	readFromFile();
    }
    
    /**
     * @return the instance of HighscoreHandler
     */
    public static HighscoreHandler getInstance() {
    	if (instance == null) {
    		instance = new HighscoreHandler ();
        }            
        return instance;
    }
    
    /**
     * resets the instance
     */
    public void deleteInstance(){    	
    	instance = null;
    }
    
    /**
     * adding the current user to highscore list if the point is good enough
     */
    public void addPlayerToHighscore(User user ){
    	if(highscore.size<TOTAL_NUMBER_OF_USER){
    		highscore.add(user); 
    	}
    	else if(user.getScore()>highscore.get(TOTAL_NUMBER_OF_USER-1).getScore()){
    		highscore.removeIndex(highscore.size-1);
    		highscore.add(user);   
    	}
    	sortHighscore();
    	writeToFile();
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
    
    /**
     * remove all highscoreholders 
     */
    public void resetHighscore(){
    	highscore = new Array<User>();
    	resetFile();
    }
    
    /**
     * clears the file of all highscores
     */
    protected void resetFile(){
    	FileHandle file = Gdx.files.local(FILENAME);
    	if(file.exists()){
        	file.writeString("", false);        	
        }
    }
    
    /**
     * @return the highscorelist
     */
    public Array<User> getHighscore(){
    	return highscore;
    }
    
    /**
     * save the current highscore to file
     */
    public void writeToFile() {
//    	FileHandle file = Gdx.files.internal(FILENAME);
//    	if(file.exists()){
//        	file.writeString(createString(), false);
//        	Gdx.app.log( HighScoreScreen.LOG, "here we go" );
//    	}
    }

    /**
     * collecting the highscorelist from file 
     */
    protected void readFromFile() {
//    	FileHandle file = Gdx.files.internal(FILENAME);
//    	if(file.exists()){
//    		String highscoreList = file.readString();    	
//    		stringToList(highscoreList);
//        	Gdx.app.log( HighScoreScreen.LOG, "" + highscoreList );
//    	}
    }
    
    /**
     * used for writing highscore to file
     * 
     * @return a string with all highscore holders and their score
     */
    protected String createString(){
    	String returnString="";
    	for(User hero: highscore){
    		returnString +=hero.getName()+"\n"+hero.getScore()+"\n";
    	}
    	return returnString;
    }
    
    /**
     * converts a input string to a highscore array
     * @param highscoreList string that should be converted
     */
    protected void stringToList(String highscoreList){
    	
    	if(highscoreList.length()>0){
	    	String[] splitHighscoreList = highscoreList.split("\n");
	    	for(int i=0; i<splitHighscoreList.length; i+=2){
	    		addPlayerToHighscore(new User(Integer.parseInt(splitHighscoreList[i+1]),splitHighscoreList[(i)]));
	    	}
    	}	
    }
}
