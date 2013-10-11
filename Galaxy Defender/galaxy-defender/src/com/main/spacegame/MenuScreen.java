package spacegame;

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
 * Draws and handles input for main menu
 * @author Group 9
 *
 */
public class MenuScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	private TextButton newGameButton;
	private TextButton highScoreButton;
	private TextButton optionsButton;
	private MyGame myGame;
	private Table table;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;
	
	/**
	 * Constructor
	 * @param myGame reference to main game class
	 */
	public MenuScreen(MyGame myGame){
		this.myGame = myGame;
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
	@Override
	public void show() {
		stage = new Stage();
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);	
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
		
		newGameButton = new TextButton("New Game", skin);
		newGameButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.GAME);
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
			
		newGameButton.pad(15, 10, 15, 10);
		highScoreButton.pad(15, 15, 15, 15);
		optionsButton.pad(15, 23, 15, 23);		
		table.add(newGameButton).spaceBottom(50).row();
		table.add(highScoreButton).spaceBottom(50).row();
		table.add(optionsButton).spaceBottom(50).row();		
		stage.addActor(table);			
	}

	/**
	 * Disables input from this screen
	 * Called when screen is not visible
	 */
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);		
	}

	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}

}
