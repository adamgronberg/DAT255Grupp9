package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import screens.GameScreen;
import ships.PlayerShip;
import spacegame.GameLogic;
import spacegame.MyGame;
import weapons.PlayerWeaponLogic;


/**
 * Handles all user input desktop/android when in game
 * @author Grupp9
 */
public class InputControl implements GestureListener, InputProcessor {

	private GameLogic gameLogic;
	private GameScreen gameScreen;
	private MyGame myGame;
	
	/**
	 * Constructor
	 * @param gameLogic
	 * @param gameScreen
	 */
	public InputControl(GameLogic gameLogic, GameScreen gameScreen, MyGame myGame){
		this.gameLogic = gameLogic;
		this.gameScreen = gameScreen;
		this.myGame = myGame;
	}
		
	/**
	 * 	Input for desktop. 
	 *  Handles on key down moment and shooting if optionAutoShoot is disabled
	 *   	 
	 */
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK) myGame.switchScreen(MyGame.ScreenType.MENU);
		if(keycode == Input.Keys.LEFT)  gameLogic.playerShip.moveLeft();
		if(keycode == Input.Keys.RIGHT)  gameLogic.playerShip.moveRight();
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
		if(keycode == Input.Keys.W)  gameLogic.playerShip.getWeaponHandeler().shootMissle();
		if(keycode == Input.Keys.Q)  gameLogic.playerShip.getWeaponHandeler().shootEMP();
		if(keycode == Input.Keys.UP)  gameLogic.startNextLevel();									//Cheat!! =o
		if(keycode == Input.Keys.CONTROL_LEFT) PlayerWeaponLogic.toggleOptionAutoShoot();			//For testing
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
		else if(gameScreen.getShootMissileButton().isPressed(touchPos.x, touchPos.y)){
			gameLogic.playerShip.getWeaponHandeler().shootMissle();
		}
		else if(gameScreen.getShootEMPButton().isPressed(touchPos.x, touchPos.y)){
			gameLogic.playerShip.getWeaponHandeler().shootEMP();
		}
		return false;
	}
	@Override 
	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY);
		if (gameScreen.getMoveLeftButton().isPressed(touchPos.x, touchPos.y)) {						//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveLeft();
		}
		else if (gameScreen.getMoveRightButton().isPressed(touchPos.x, touchPos.y)) {				//TODO: Should use buttons instead of areas
			gameLogic.playerShip.moveRight();
		}
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
		else if (gameScreen.getMoveRightButton().isPressed(touchPos.x, touchPos.y)) {				//TODO: Should use buttons instead of areas
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
	@Override public boolean fling(float velocityX, float velocityY, int button) {return false;}
}
