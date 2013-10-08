package spacegame;

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

public class WinScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	private TextButton continueButton;
	private Label youWinLabel;
	private MyGame myGame;
	private Table table;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;
	private int level;
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
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
		
		youWinLabel = new Label(labelTexts[level],skin);
		table.add(youWinLabel).spaceBottom(50).row();
		
		continueButton = new TextButton("Continue", skin);
		continueButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.GAME);
	        }
	    } );
		
		continueButton.pad(15, 10, 15, 10);		
		table.add(continueButton).spaceBottom(50).row();		
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
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
