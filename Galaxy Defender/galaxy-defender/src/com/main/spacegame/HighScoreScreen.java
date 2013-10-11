package spacegame;

import highscore.HighscoreHandler;
import highscore.User;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	public static final String LOG = HighScoreScreen.class.getSimpleName();
	
	private Stage stage;
	private Skin skin;
	private TextButton mainMenuButton;
	private MyGame myGame;
	private Table table;
	private Label highscoreList;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;

	public HighScoreScreen(MyGame myGame){
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
	@Override public void show() {
		stage = new Stage();
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);	
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);	
		highscoreList = new Label(showHighscoreList(), skin);
		table.add(highscoreList).spaceBottom(50).row();
		
		mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.MENU);
	        }
	    } );
		
		mainMenuButton.pad(15, 10, 15, 10);	
		table.add(mainMenuButton).spaceBottom(50).row();
		stage.addActor(table);	
	}
	
	private String showHighscoreList(){
		HighscoreHandler highscoreHandler = HighscoreHandler.getInstance();
		Array<User> userArray = highscoreHandler.getHighscore();
		String hsList = "";
		int position = 1;
		for(User u : userArray){
			hsList += position + ". " + u.getName() + ": " + u.getScore() + "\n";
			position++;
		}
		return hsList;
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
