package spacegame;

import com.badlogic.gdx.Game;
import assets.*;

/**
 * Starts the first screen and loads all assets.
 *  
 *  
 * @author Grupp9
 * 
 */
public class MyGame extends Game {
	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private OptionsScreen optionsScreen;
	private HighScoreScreen highScoreScreen;
	private WinScreen winScreen;
	public enum ScreenType{GAME,MENU,OPTIONS,HIGHSCORE,WINSCREEN}
	
	/**
	 * Load assets and creates and adds the MenuScreen
	 */
	@Override
	public void create() {
		ImageAssets.load();
		SoundAssets.load();
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		optionsScreen = new OptionsScreen(this, gameScreen);		
		setScreen(menuScreen);
	}
	/**
	 * Resets The Game
	 */
	
	public void resetGame(){
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		optionsScreen = new OptionsScreen(this, gameScreen);
		
	}
	
	/**
	 * switches to selected screen
	 */
	public void switchScreen(ScreenType screenType){
		switch(screenType){
		case MENU:
			setScreen(menuScreen);
			break;
		case GAME:
			setScreen(gameScreen);			
			break;
		case OPTIONS:
			setScreen(optionsScreen);
			break;
		case HIGHSCORE:
			highScoreScreen = new HighScoreScreen(this);
			setScreen(highScoreScreen);
			break;
		case WINSCREEN:
			winScreen = new WinScreen(this, gameScreen.getLevelResult());
			winScreen.setScore(gameScreen.getGameLogicScore());
			winScreen.setPlayer(gameScreen.getPlayerShip());
			setScreen(winScreen);
			
		default:
			break;
		}
	}
	
	/**
	 * Disposes all assets and closes game
	 */
	@Override
	public void dispose() {
		ImageAssets.dispose();
		SoundAssets.dispose();
		gameScreen.dispose();
		menuScreen.dispose();
	}
	
}
