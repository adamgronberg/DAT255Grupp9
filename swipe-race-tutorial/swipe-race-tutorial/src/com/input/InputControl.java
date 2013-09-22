package com.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ships.PlayerShip;
import com.spacegame.GameLogic;
import com.spacegame.GameScreen;
import com.spacegame.MyGame;

public class InputControl implements GestureListener, InputProcessor {

	private GameLogic gameLogic;
	private GameScreen gameScreen;
	
	public InputControl(GameLogic gameLogic, GameScreen gameScreen){
		this.gameLogic = gameLogic;
		this.gameScreen = gameScreen;
	}
	
	/**
	 * Fling up or down to swap weapons
	 * Fling left to activate/deactivate optionAutoShoot 
	 * TODO: The fling don't register if you are moving at the same time, can't solve. Might have to scrap "fling" weapon switch
	 */
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log( GameScreen.LOG, "velocityX :" + velocityX + " velocityY : " + velocityY  );		//TODO: Debug
		if (velocityY < -2000 && velocityX < 1000 && velocityX > -1000) gameLogic.switchPlayerWeapon();
		if (velocityY > 2000 && velocityX < 1000 && velocityX > -1000) gameLogic.switchPlayerWeapon();
		if (velocityX < -2000 && velocityY < 1000 && velocityY > -1000) gameScreen.changeOptionAutoShoot();
		if (velocityX > 2000 && velocityY < 1000 && velocityY > -1000) gameScreen.changeOptionControlLayout();
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
	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY);
		if(gameScreen.getCurrentLayout() == GameScreen.ControlLayout.LAYOUT1){
			if (touchPos.x <= GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
			else if (touchPos.x >= MyGame.WIDTH-GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
		}
		else{
			if (touchPos.x <= GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
			else if (touchPos.x > GameScreen.MOVMENT_BUTTON_SIZE && touchPos.x < 2*GameScreen.MOVMENT_BUTTON_SIZE &&
					touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {														//TODO: Should use buttons instead of areas
				gameLogic.playerShip.stay();
			}
		}
		return false;
	}
	
	/**
	 * Handles movement on touch devices
	 */
	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchPos = gameScreen.unProjectCoordinates(screenX, screenY);
		if(gameScreen.getCurrentLayout() == GameScreen.ControlLayout.LAYOUT1){
			if (touchPos.x <= GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {						//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveLeft();
			}
			else if (touchPos.x >= MyGame.WIDTH-GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveRight();
			}
			else gameLogic.playerShip.stay();
		}
		else{
			if (touchPos.x <= GameScreen.MOVMENT_BUTTON_SIZE && touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {					//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveLeft();
			}
			else if (touchPos.x > GameScreen.MOVMENT_BUTTON_SIZE && 
					touchPos.x < 2*GameScreen.MOVMENT_BUTTON_SIZE &&
					touchPos.y <= GameScreen.MOVMENT_BUTTON_SIZE) {													//TODO: Should use buttons instead of areas
				gameLogic.playerShip.moveRight();
			}
		}
		
		return false;
	}
	
	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false;}	
	@Override public boolean longPress(float screenX, float screenY) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}
	@Override public boolean keyTyped(char character) {return false;}
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override public boolean scrolled(int amount) {return false;}
}
