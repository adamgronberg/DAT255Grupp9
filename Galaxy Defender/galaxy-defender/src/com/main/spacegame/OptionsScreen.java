package spacegame;

import assets.SoundAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Draws and handles input for options screen
 * @author Group 9
 *
 */
public class OptionsScreen implements Screen{
	
	private boolean music;
	private Stage stage;
	private Skin skin;
	private TextButton mainMenuButton, soundButton, musicButton,vibrateButton, layoutButton;
	private MyGame myGame;
	private GameScreen gameScreen;
	private Table table;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;

	public OptionsScreen(MyGame myGame, GameScreen gameScreen){
		this.myGame = myGame;
		this.gameScreen = gameScreen;
		music = true;
		if(music)SoundAssets.spaceMusic.play();
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
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Shows screen and adds input control 
	 * Called when app is resumed on android
	 */
	@Override public void show() {
		stage = new Stage();
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
		
		soundButton = new TextButton(getSoundText(),skin);
		soundButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	GameScreen.toggleSound();
	        	soundButton.setText(getSoundText());
	        }
	    } );
		
		musicButton = new TextButton(getMusicText(),skin);
		musicButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	toggleMusic();
	        	musicButton.setText(getMusicText());
	        }
	    } );
		
		mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.MENU);
	        }
	    } );
		
		vibrateButton= new TextButton(getVibrateText(),skin);
		vibrateButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	GameScreen.toggleVibrateOn();
	        	vibrateButton.setText(getVibrateText());
	        }
	    } );
		
		layoutButton = new TextButton("Change Control Layout",skin);
		layoutButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	gameScreen.changeOptionControlLayout();
	        }
	    } );
		
		mainMenuButton.pad(15,10,15,10);
		soundButton.pad(15,12,15,12);
		musicButton.pad(15,13,15,13);
		vibrateButton.pad(15,10,15,10);
		layoutButton.pad(15,10,15,10);
	
		table.add(mainMenuButton).spaceBottom(50).row();
		table.add(soundButton).spaceBottom(50).row();
		table.add(musicButton).spaceBottom(50).row();
		table.add(vibrateButton).spaceBottom(50).row();
		table.add(layoutButton);
		
		stage.addActor(table);	
	}
	
	/*
	 * Returns text for soundButton
	 */
	private String getSoundText(){
		if(GameScreen.getSound()) return "Sound Off";
		else return "Sound On";
	}
	
	/*
	 * Returns text for vibrateButton
	 */
	private String getVibrateText(){
		if(GameScreen.getVibration()) return "Vibration Off";
		else return "Vibration On";
	}
	
	/*
	 * Returns text for musicButton
	 */
	private String getMusicText(){
		if(music) return "Music Off";
		else return "Music On";
	}
	
	/*
	 * Toggles music
	 */
	private void toggleMusic(){
		if(music){
			music = false;
			SoundAssets.spaceMusic.stop();
		}
		else{
			music = true;
			SoundAssets.spaceMusic.play();			
		}
	}
	/**
	 * Disables input from this screen
	 * Called when screen is not visible
	 */
	@Override public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

}
