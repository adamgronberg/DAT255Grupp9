package com.collisiondetection;

import com.spacegame.GameScreen;
import com.spacegame.MovableEntity;

public class OutOfBoundsDetection {

	/**
	 * Checks if a obj is out of bounds on y-led
	 * @param movableObj
	 * @return	returns true if out of bounds
	 */
	public static void isOutOfBoundsY(MovableEntity movableObj){
		if (movableObj.getBounds().y < -movableObj.getBounds().getHeight() || movableObj.getBounds().y >= 
				GameScreen.GAME_HEIGHT+movableObj.getBounds().getHeight()){
			movableObj.remove();
		}
	}
	
	
	
}
