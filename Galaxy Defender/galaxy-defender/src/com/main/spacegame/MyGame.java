package spacegame;

import highscore.HighscoreLogic;
import options.OptionLogic;
import screens.EndLevelScreen;
import screens.GameScreen;
import screens.HighScoreScreen;
import screens.MenuScreen;
import screens.OptionsScreen;
import assets.ImageAssets;
import assets.SoundAssets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * Starts the first screen and loads all assets.
 * @author Grupp9
 */
public class MyGame extends Game{
	
	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private OptionsScreen optionsScreen;
	private HighScoreScreen highScoreScreen;
	private EndLevelScreen winScreen;
	public static enum ScreenType{GAME,MENU,OPTIONS,HIGHSCORE,WINSCREEN}

	/**
	 * Load assets and creates and adds the MenuScreen
	 */
	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		OptionLogic.loadOptions();
		HighscoreLogic.loadHighScoreList();
		ImageAssets.load();
		SoundAssets.load();
		createScreens();
		OptionLogic.musicOnOff();
		setScreen(menuScreen);
	}
	
	/**
	 * Disposes all assets and closes game
	 */
	@Override
	public void dispose() {
		ImageAssets.dispose();
		SoundAssets.dispose();
		gameScreen = null;
		menuScreen = null;
		optionsScreen = null;
		highScoreScreen = null;
		winScreen = null;
	}
	
	/**
	 * Resets The Game
	 */
	public void resetGame(){
		createScreens();
	}
	
	/**
	 * Creates all the screens
	 */
	private void createScreens(){
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		optionsScreen = new OptionsScreen(this, gameScreen);
		highScoreScreen = new HighScoreScreen(this);
		winScreen = new EndLevelScreen(this, gameScreen.getGameLogic());
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
			setScreen(highScoreScreen);
			break;
		case WINSCREEN:
			setScreen(winScreen);
		default:
			break;
		}
	}
}
