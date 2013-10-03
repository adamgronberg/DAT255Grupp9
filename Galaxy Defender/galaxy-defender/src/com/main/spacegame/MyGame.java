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
	public enum ScreenType{GAME,MENU,OPTIONS,HIGHSCORE}
	
	/**
	 * Load assets and creates and adds the MenuScreen
	 */
	@Override
	public void create() {
		ImageAssets.load();
		SoundAssets.load();
		menuScreen = new MenuScreen(this);
		optionsScreen = new OptionsScreen(this);
		setScreen(menuScreen);
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
			gameScreen = new GameScreen(this);
			setScreen(gameScreen);			
			break;
		case OPTIONS:
			setScreen(optionsScreen);
			break;
		case HIGHSCORE:
		//	setScreen(highScoreScreen);
			break;
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
