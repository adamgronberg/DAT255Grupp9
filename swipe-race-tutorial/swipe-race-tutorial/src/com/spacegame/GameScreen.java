package com.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Draws everything on GameScreen and handles input
 * Tells all actors to draw and act
 * 
 * @author Grupp9
 *
 */
public class GameScreen implements Screen, GestureListener, InputProcessor{
	private Stage stage;
	private GameLogic gameLogic;
	private static boolean optionAutoShoot = true;
	private static boolean optionControlLayout = true;
	
	public static final int MOVMENT_BUTTON_SIZE = 70;
	public static final String LOG = GameScreen.class.getSimpleName();
	
	/**
	 * Constructor
	 * Adds gameLogic as actor, starting the game
	 */
	public GameScreen(MyGame myGame) {
		stage = new Stage();
		gameLogic = new GameLogic();
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
	 * @return If autoshoot is on or off
	 */
	public static boolean getOptionAutoShoot(){
		return optionAutoShoot;
	}
	
	/**
	 * 
	 * @return What button layout to use on android device
	 */
	public static boolean getOptionControlLayout(){
		return optionControlLayout;
	}
	
	/**
	 * Change android layout
	 */
	private static void changeOptionControlLayout(){
		optionControlLayout = !optionControlLayout;
	}
	
	/**
	 * Disable/enable auto shoot
	 */
	private static void changeOptionAutoShoot(){
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
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	/**
	 * Hides screen
	 */	
	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }
	
	/**
	 * Fling up or down to swap weapons
	 * Fling left to activate/deactivate autoshoot 
	 */
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log( GameScreen.LOG, "velocityX :" + velocityX + " velocityY : " + velocityY  );
		if (velocityY < -2000 && velocityX < 1000 && velocityX > -1000) gameLogic.switchWeapon();
		if (velocityY > 2000 && velocityX < 1000 && velocityX > -1000) gameLogic.switchWeapon();
		if (velocityX < -2000 && velocityY < 1000 && velocityY > -1000) changeOptionAutoShoot();
		if (velocityX > 2000 && velocityY < 1000 && velocityY > -1000) changeOptionControlLayout();
		return false;
	}
	
	/**
	 * 	Input for desktop. 
	 *  Handles on key down moment and shooting if autoshoot is disabled
	 *   	 
	 */
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT)  gameLogic.playerShip.moveLeft();
		if(keycode == Input.Keys.RIGHT)  gameLogic.playerShip.moveRight();
		if(keycode == Input.Keys.SPACE)  gameLogic.toggleShooting();
		return false;
	}
	/**
	 * Input for desktop
	 * Handles on key up switching weapon and options
	 */
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT && gameLogic.playerShip.getMovmentDirection() == PlayerShip.Direction.LEFT)  gameLogic.playerShip.stay();
		if(keycode == Input.Keys.RIGHT && gameLogic.playerShip.getMovmentDirection() == PlayerShip.Direction.RIGHT)  gameLogic.playerShip.stay();
		if(keycode == Input.Keys.DOWN)  gameLogic.switchWeapon();
		if(keycode == Input.Keys.UP)  gameLogic.switchWeapon();
		if(keycode == Input.Keys.SPACE)  gameLogic.toggleShooting();						//For testing, should be in a option menu
		if(keycode == Input.Keys.CONTROL_LEFT) optionAutoShoot = !optionAutoShoot;			//For testing, should be in a option menu
		return false;
	}

	/**
	 * Handles movement on touch devices. 
	 */
	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		stage.getCamera().unproject(touchPos);
		
		if(optionControlLayout){
			if (touchPos.x <= MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
			else if (touchPos.x >= MyGame.WIDTH-MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
		}
		else{
			if (touchPos.x <= MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
			else if (touchPos.x > MOVMENT_BUTTON_SIZE && touchPos.x < 2*MOVMENT_BUTTON_SIZE &&
					touchPos.y <= MOVMENT_BUTTON_SIZE) {														//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
		}
		return false;
	}
	
	/**
	 * Handles movement on touch devices
	 */
	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchPos = new Vector3();
		touchPos.set(screenX, screenY, 0);
		stage.getCamera().unproject(touchPos);
		if(optionControlLayout){
			Gdx.app.log( GameScreen.LOG, "Pressed X: " + touchPos.x  + ", " + touchPos.y);
			if (touchPos.x <= MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveLeft();
			}
			else if (touchPos.x >= MyGame.WIDTH-MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveRight();
			}
			else gameLogic.playerShip.stay();
		}
		else{
			if (touchPos.x <= MOVMENT_BUTTON_SIZE && touchPos.y <= MOVMENT_BUTTON_SIZE) {					//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveLeft();
			}
			else if (touchPos.x > MOVMENT_BUTTON_SIZE && touchPos.x < 2*MOVMENT_BUTTON_SIZE &&
					touchPos.y <= MOVMENT_BUTTON_SIZE) {													//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveRight();
			}
		}
		
		return false;
	}
	
	
	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}	
	
	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false;}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}
	@Override public boolean keyTyped(char character) {return false;}
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override public boolean scrolled(int amount) {return false;}
}
