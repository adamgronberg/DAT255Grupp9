package com.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.input.InputControl;

/**
 * Draws everything on GameScreen and handles input
 * Tells all actors to draw and act
 * 
 * @author Grupp9
 *
 */
public class GameScreen implements Screen{
	private Stage stage;
	private GameLogic gameLogic;
	private InputControl inputController;
	private boolean optionAutoShoot = true;
	private boolean optionControlLayout = true;
	
	public static final int MOVMENT_BUTTON_SIZE = 70;
	public static final String LOG = GameScreen.class.getSimpleName();
	

	
	/**
	 * Constructor
	 * Adds gameLogic as actor, starting the game
	 */
	public GameScreen(MyGame myGame) {
		stage = new Stage();
		gameLogic = new GameLogic(this);
		inputController = new InputControl(gameLogic, this);
		stage.addActor(gameLogic);
	}
	
	/**
	 * Called on screen resize
	 * Always on launch
	 */
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector3 unProjectCoordinates(float x, float y){
		Vector3 vector = new Vector3();
		vector.set(x, y, 0);
		stage.getCamera().unproject(vector);
		return vector;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	/**
	 * 
	 * @return If optionAutoShoot is on or off
	 */
	public boolean getOptionAutoShoot(){
		return optionAutoShoot;
	}
	
	/**
	 * 
	 * @return If optionAutoShoot is on or off
	 */
	public void toggleOptionAutoShoot(){
		optionAutoShoot = !optionAutoShoot;
	}
	
	
	/**
	 * 
	 * @return What button layout to use on android device
	 */
	public boolean getOptionControlLayout(){
		return optionControlLayout;
	}
	
	/**
	 * Change android layout
	 */
	public void changeOptionControlLayout(){
		optionControlLayout = !optionControlLayout;
	}
	
	/**
	 * Disable/enable auto shoot
	 */
	public void changeOptionAutoShoot(){
		optionAutoShoot = !optionAutoShoot;
	}
	
	
	/**
	 * Render loop. 
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);				//Tells all actors to move
		stage.draw();					//Tells all actors to draw
	}
	
	/**
	 * Shows screen
	 * Depending on what device its running on it uses different input systems (Desktop/android)
	 */
	@Override
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputController);
		multiplexer.addProcessor(new GestureDetector(inputController));
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	/**
	 * Hides screen
	 */	
	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}	
}
