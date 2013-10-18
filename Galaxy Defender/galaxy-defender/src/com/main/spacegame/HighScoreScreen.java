package spacegame;

import highscore.HighscoreHandler;
import highscore.User;
import assets.ImageAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * Draws and handles input for highscore screen
 * @author Group 9
 *
 */
public class HighScoreScreen implements Screen {
	
	private Stage stage;
	private TextButton mainMenuButton, resetHighScoreButton;
	private Table table;
	private Label highscoreList, screenTitle;	
	private static final String SCREEN_TITLE = "HighScore:";

	/**
	 * Constructor
	 * @param myGame
	 */
	public HighScoreScreen(MyGame myGame){

		Skin skin = ImageAssets.skin;
		createResources(myGame, skin);
		createButtons(myGame, skin);
		
		table.add(screenTitle).spaceBottom(50).row();
		table.add(highscoreList).spaceBottom(50).row();
		table.add(mainMenuButton).spaceBottom(50).row();
		table.add(resetHighScoreButton).spaceBottom(50).row();
		stage.addActor(table);	
	}
	
	/**
	 * Creates different resources such as stage and labels
	 * @param myGame
	 */
	private void createResources(final MyGame myGame, Skin skin) {
		stage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Keys.BACK) {
	                myGame.switchScreen(MyGame.ScreenType.MENU);
	            }
	            return super.keyDown(keyCode);
	        }
	    };
		table = new Table(skin);
		TextureRegionDrawable menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		highscoreList = new Label("", skin);
		screenTitle = new Label(SCREEN_TITLE, skin);
		
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);		
	}

	/**
	 * Creates the different buttons
	 * @param myGame
	 */
	private void createButtons(final MyGame myGame, Skin skin){
		mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.MENU);
	        }
	    } );
		
		resetHighScoreButton = new TextButton("Reset list", skin);
		resetHighScoreButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x, float y)
	        {
	    		HighscoreHandler highscoreHandler = HighscoreHandler.getInstance();
	    		highscoreHandler.resetHighscore();
	    		highscoreList.setText(getHighscoreList());
	        }
	    } );
		
		mainMenuButton.pad(15, 10, 15, 10);
		resetHighScoreButton.pad(15, 10, 15, 10);	
	}
	
	/**
	 * Render loop. 
	 */
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		stage.act();
		stage.draw();	
	}
	
	/**
	 * Called on screen resize
	 * Always on launch
	 */
	@Override
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	/**
	 * Shows screen and adds input control 
	 * Called when app is resumed on android
	 */
	@Override public void show() {
		highscoreList.setText(getHighscoreList());
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Gets the highscore list from the highscore handler
	 * @return The highscore list as a printable list
	 */
	private String getHighscoreList(){
		HighscoreHandler highscoreHandler = HighscoreHandler.getInstance();
		Array<User> userArray = highscoreHandler.getHighscore();
		String highScoreList = "";
		int position = 1;
		for(User user : userArray){
			highScoreList += position + ". " + user.getName() + ": " + user.getScore() + "\n";
			position++;
		}
		
		for(int i = position; i <= HighscoreHandler.TOTAL_NUMBER_OF_USER; i++){
			if(i>=10)highScoreList += i + ". " + "____" + ":" + "____" + "\n";
			else highScoreList += i + ".   " + "____" + ":" + "____" + "\n";
		}
			
		return highScoreList;
	}
	
	/**
	 * Disables input from this screen
	 */
	@Override public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	//// Unused methods ////
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}
}
