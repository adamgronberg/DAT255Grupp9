package screens;

import spacegame.MyGame;
import assets.ImageAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Draws and handles input for main menu
 * @author Group 9
 */
public class MenuScreen implements Screen{
	
	private Stage stage;	
	private TextButton newGameButton,resumeButton,highScoreButton,optionsButton;
	private Table table;
	private Label screenTitle;
	public static boolean activeGame;
	private static final String SCREEN_TITLE = "Galaxy Defender: Solar";
	
	/**
	 * Constructor
	 * @param myGame reference to main game class
	 */
	public MenuScreen(final MyGame myGame){
		activeGame = false;
		Skin skin = ImageAssets.skin;
		
		createResources(myGame, skin);
	    createButtons(myGame, skin);
	
	    table.add(screenTitle).spaceBottom(50).row();
	    table.add(resumeButton).height(50).width(130).spaceBottom(50).row();
		table.add(newGameButton).height(50).width(130).spaceBottom(50).row();
		table.add(highScoreButton).height(50).width(130).spaceBottom(50).row();
		table.add(optionsButton).height(50).width(130);
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
	                Gdx.app.exit();
	            }
	            return super.keyDown(keyCode);
	        }
	    };
	    
	    screenTitle = new Label(SCREEN_TITLE, skin);
	    
		table = new Table(skin);
		TextureRegionDrawable menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
	}
	
	/**
	 * Creates the different buttons
	 * @param myGame
	 */
	private void createButtons(final MyGame myGame, Skin skin){
		newGameButton = new TextButton("New Game", skin);
		newGameButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	if(activeGame) myGame.resetGame();
	        	activeGame = true; 		
	        	myGame.switchScreen(MyGame.ScreenType.GAME);
	        }
	    } );
		
		resumeButton = new TextButton("Resume Game",skin);
		resumeButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	if(activeGame)myGame.switchScreen(MyGame.ScreenType.GAME);
	        }
	    } );
		
		highScoreButton = new TextButton("Highscore", skin);
		highScoreButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.HIGHSCORE);
	        }
	    } );
		
		optionsButton = new TextButton("Options", skin);
		optionsButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.OPTIONS);
	        }
	    } );
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
	@Override
	public void show() {
		if(activeGame) resumeButton.setVisible(true);
		else resumeButton.setVisible(false);
		
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Disables input from this screen
	 * Called when screen is not visible
	 */
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);		
	}

	//// Unused methods ////
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

}
