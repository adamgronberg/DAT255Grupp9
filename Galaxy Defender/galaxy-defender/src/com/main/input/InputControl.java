package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ships.PlayerShip;
import spacegame.GameLogic;
import spacegame.GameScreen;


/**
 * 
 * @author Grupp9
 * Handles all user input desktop/android
 */
public class InputControl implements GestureListener, InputProcessor {

	private GameLogic gameLogic;
	private GameScreen gameScreen;
	
	private static final float FLING_SENSITIVITY = 1500f;
	
	
	/**
	 * Constructor
	 * @param gameLogic
	 * @param gameScreen
	 */
	public InputControl(GameLogic gameLogic, GameScreen gameScreen){
		this.gameLogic = gameLogic;
		this.gameScreen = gameScreen;
	}
	
	/**
	 * Fling up or down to swap weapons
	 * Fling left to activate/deactivate optionAutoShoot
	 * Fling right to swap control layout
	 * TODO: The fling don't register if you are moving at the same time, I can't solve this. Might have to scrap "fling" weapon switch
	 */
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (velocityY < -FLING_SENSITIVITY && velocityX < FLING_SENSITIVITY/2 && velocityX > -FLING_SENSITIVITY/2) gameLogic.switchPlayerWeapon();
		if (velocityY > FLING_SENSITIVITY && velocityX < FLING_SENSITIVITY/2 && velocityX > -FLING_SENSITIVITY/2) gameLogic.switchPlayerWeapon();
		if (velocityX < -FLING_SENSITIVITY && velocityY < FLING_SENSITIVITY/2 && velocityY > -FLING_SENSITIVITY/2) gameScreen.toggleOptionAutoShoot();
		if (velocityX > FLING_SENSITIVITY && velocityY < FLING_SENSITIVITY/2 && velocityY > -FLING_SENSITIVITY/2) gameScreen.changeOptionControlLayout();
		return false;
	}
	
	/**
	 * 	Input for desktop. 
	 *  Handles on key down moment and shooting if optionAutoShoot is disabled
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
		if(keycode == Input.Keys.DOWN)  gameLogic.switchPlayerWeapon();
		if(keycode == Input.Keys.UP)  gameLogic.switchPlayerWeapon();
		if(keycode == Input.Keys.SPACE)  gameLogic.toggleShooting();						//For testing, should be in a option menu
		if(keycode == Input.Keys.CONTROL_LEFT) gameScreen.toggleOptionAutoShoot();			//For testing, should be in a option menu
		if(keycode == Input.Keys.SHIFT_LEFT) gameScreen.changeOptionControlLayout();		//For testing, only android
		return false;
	}

	/**
	 * Handles movement on touch devices. 
	 */
	@Override 
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY); 
		if (gameScreen.getMoveLeftButton().isPressed(touchPos.x, touchPos.y) || 
				gameScreen.getMoveRightButton().isPressed(touchPos.x, touchPos.y)) {						//TODO: Should use buttons instead of areas
			gameLogic.playerShip.stay();
		}
		return false;
	}
	@Override 
	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY);
		if (gameScreen.getMoveLeftButton().isPressed(touchPos.x, touchPos.y)) {						//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveLeft();
		}
		else if (gameScreen.getMoveRightButton().isPressed(touchPos.x, touchPos.y)) {		//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveRight();
		}
		else gameLogic.playerShip.stay();	
		return false;
	}
	
	/**
	 * Handles movement on touch devices
	 */
	@Override 
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY);
		if (gameScreen.getMoveLeftButton().isPressed(touchPos.x, touchPos.y)) {						//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveLeft();
		}
		else if (gameScreen.getMoveRightButton().isPressed(touchPos.x, touchPos.y)) {		//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveRight();
		}
		else gameLogic.playerShip.stay();	
		return false;
	}
	
	////////////// Unused methods  /////////////////
	@Override public boolean tap(float x, float y, int count, int button) {return false;}	
	@Override public boolean longPress(float screenX, float screenY) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}
	@Override public boolean keyTyped(char character) {return false;}
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override public boolean scrolled(int amount) {return false;}
	@Override public boolean panStop(float x, float y, int pointer, int button) {return false;}
}
