package highscore;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;




public class HighscoreHandler {
	
	private static final int NUMBEROFHEROES = 13;
	private static final String FILENAME = "highscore.txt";
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
    public void addPlayerToHighscore(){
    	if(highscore.size<NUMBEROFHEROES){
    		highscore.add(currentUser); 
    	}
    	else if(currentUser.getScore()>highscore.get(NUMBEROFHEROES-1).getScore()){
    		highscore.removeIndex(highscore.size-1);
    		highscore.add(currentUser);   
    	}
    	sortHighscore();
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
    }
    
    /**
     * 
     * @return the highscorelist
     */
    public Array<User> getHighscore(){
    	return highscore;
    }
    
    /**
     * save the current highscore to file
     */
    public void writeToFile() {
    	FileHandle file = Gdx.files.local(FILENAME);
    	file.writeString(createString(), false);
    }

    /**
     * collecting the highscorelist from file 
     */
    protected void readFromFile() {

    	FileHandle file = Gdx.files.internal(FILENAME);
    	String highscoreList = file.readString();
    	
    	stringToList(highscoreList);    	
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
     * 
     * @param highscoreList string that should be converted to the highscore array
     */
    protected void stringToList(String highscoreList){
    	
    	String[] splitedeHighscoreList = highscoreList.split("\n");
    	
    	for(int i=0; i<splitedeHighscoreList.length; i+=2){
    		createPlayer(splitedeHighscoreList[(i)],Integer.parseInt(splitedeHighscoreList[i+1]));
    	}
    	
    }

}
