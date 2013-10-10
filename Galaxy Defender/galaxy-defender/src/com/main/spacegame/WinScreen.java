package spacegame;

import highscore.HighscoreHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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

public class WinScreen implements Screen,InputProcessor{
	
	private String text="Enter Your Name: ";
	private String name="";
	private Stage stage;
	private Skin skin;
	private TextButton continueButton,laserButton,missileButton,empButton;
	private Label youWinLabel;
	private Label highScore;
	private MyGame myGame;
	private Table table,table2;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;
	private int level;
	private int score=0;
	private String[] labelTexts = {"You are the most useless pilot in earths history. \n We will all die because of your incompetence!"
			,"You win level 1","You win level 2","You win level 3","You win level 4","You win level 5","You saved earth, big deal..."};
	
	public WinScreen(MyGame myGame, int level){
		this.myGame = myGame;
		this.level = level;	
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
			
		stage = new Stage();
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table2 = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);		
		table.setBackground(menuBackground);
		table2.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT/2);
		
		highScore=new Label(text,skin);
		youWinLabel = new Label(labelTexts[level],skin);
		table.add(youWinLabel).spaceBottom(50).row();
		
		if(level==0){
			Gdx.input.setOnscreenKeyboardVisible(true);	
			table.add(highScore).spaceBottom(100).row();
			highScore.setText(text);
		}
		
		continueButton = new TextButton("Continue", skin);
		continueButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.GAME);
	        }
	    } );
		
		laserButton = new TextButton("Laser",skin);
		laserButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	;
	        }
	    } );
		
		missileButton = new TextButton("Missile",skin);
		missileButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	;
	        }
	    } );
		
		empButton = new TextButton("EMP",skin);
		empButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	;
	        }
	    } );
			
		table.add(continueButton).width(100).height(50);
		table2.add(laserButton).width(100).height(50).spaceRight(30);
		table2.add(missileButton).width(100).height(50).spaceRight(30);
		table2.add(empButton).width(100).height(50);
		stage.addActor(table);
		stage.addActor(table2);
				
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
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

	/**
	 * Player writes name and is eventually saved in the highscore-list
	 */
	@Override
	public boolean keyTyped (char character) {
		HighscoreHandler highscoreHandler = HighscoreHandler.getInstance();
		if (character == '\n') {
			highScore.setText(text);
			highscoreHandler.createPlayer(name, score);
			highscoreHandler.addPlayerToHighscore();
			
			Gdx.input.setOnscreenKeyboardVisible(false);
		} else {
			name += character;
			text += character;
			highScore.setText(text);
		}
		return false;
	}
	
	/**
	 * Saves the current score from the game
	 * @param score
	 */
		public void setScore(int score){
		this.score=score;
	}
	
	public int getCurrentScore(){
		return score;
	}
	
	
	////////////// Unused methods  /////////////////
	@Override	public boolean keyDown(int keycode) {return false;}
	@Override	public boolean keyUp(int keycode) {return false;}
	@Override	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override	public boolean touchUp(int screenX, int screenY, int pointer, int button) {	return false;}
	@Override	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
	@Override	public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override	public boolean scrolled(int amount) {return false;}

}
