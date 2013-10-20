package screens;

import options.OptionLogic;
import spacegame.MyGame;
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

/**
 * Draws buttons and handles input for options screen
 * @author Group 9
 *
 */
public class OptionsScreen implements Screen{
	
	private static final String SCREEN_TITLE = "Options:";
	private Label screenTitle;
	private Stage stage;
	private TextButton mainMenuButton, soundButton, musicButton,vibrateButton, layoutButton;
	private Table table;

	/**
	 * Constructor
	 * @param myGame
	 * @param gameScreen
	 */
	public OptionsScreen(final MyGame myGame, final GameScreen gameScreen){
		
		Skin skin = ImageAssets.skin;
		createResources(myGame, skin);
		createButtons(myGame, gameScreen, skin);
	
		table.add(screenTitle).spaceBottom(50).row();
		table.add(soundButton).height(50).width(130).spaceBottom(50).row();
		table.add(musicButton).height(50).width(130).spaceBottom(50).row();
		table.add(vibrateButton).height(50).width(130).spaceBottom(50).row();
		table.add(layoutButton).height(50).width(130).spaceBottom(50).row();
		table.add(mainMenuButton).height(50).width(130);
		stage.addActor(table);
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
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Disables input from this screen
	 * Called when screen is not visible
	 */
	@Override public void hide() {
		Gdx.input.setInputProcessor(null);
	}
	
	/**
	 * Sets the screens used instance variables
	 * @param myGame
	 */
	private void createResources(final MyGame myGame, Skin skin){
		stage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Keys.BACK) {
	                myGame.switchScreen(MyGame.ScreenType.MENU);
	            }
	            return super.keyDown(keyCode);
	        }
	    };
	    TextureRegionDrawable menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
	    screenTitle = new Label(SCREEN_TITLE, skin);
		table = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
	}
	
	/**
	 * Creates the screens buttons
	 * @param myGame
	 * @param gameScreen
	 */
	private void createButtons(final MyGame myGame, final GameScreen gameScreen, Skin skin){
		mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event, float x, float y)
	        {
	        	myGame.switchScreen(MyGame.ScreenType.MENU);
	        }
	    } );
		
		soundButton = new TextButton(OptionLogic.getSoundText(),skin);
		soundButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event, float x, float y)
	        {
	        	OptionLogic.toggleSoundOption();
	        	soundButton.setText(OptionLogic.getSoundText());
	        }
	    } );
		
		musicButton = new TextButton(OptionLogic.getMusicText(),skin);
		musicButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event, float x, float y)
	        {
	        	OptionLogic.toggleMusicOption();
	        	OptionLogic.musicOnOff();
	        	musicButton.setText(OptionLogic.getMusicText());
	        }
	    } );
		
		vibrateButton= new TextButton(OptionLogic.getVibrateText(),skin);
		vibrateButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event, float x, float y)
	        {
	        	OptionLogic.toggleVibrateOption();
	        	vibrateButton.setText(OptionLogic.getVibrateText());
	        }
	    } );
		
		layoutButton = new TextButton(OptionLogic.getLayoutText(),skin);
		layoutButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event, float x, float y)
	        {
	        	OptionLogic.nextlayout();
	        	gameScreen.moveControlLayout();
	        	layoutButton.setText(OptionLogic.getLayoutText());
	        }
	    } );
		
		mainMenuButton.pad(15,10,15,10);
		soundButton.pad(15,12,15,12);
		musicButton.pad(15,13,15,13);
		vibrateButton.pad(15,10,15,10);
		layoutButton.pad(15,10,15,10);
	}
	
	//// Unused methods ////
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

}
