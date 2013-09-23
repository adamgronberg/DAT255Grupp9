package com.collisiondetection;

import com.spacegame.MovableEntity;
import com.spacegame.MyGame;

public class OutOfBoundsDetection {

	/**
	 * Checks if a obj is out of bounds on y-led
	 * @param movableObj
	 * @return	returns true if out of bounds
	 */
	public static boolean isOutOfBoundsY(MovableEntity movableObj){
		if (movableObj.getBounds().y < -movableObj.getBounds().getHeight() || movableObj.getBounds().y >= 
				MyGame.HEIGHT+movableObj.getBounds().getHeight()){
			return true; 
		}
		return false;
	}
	
	
	
}
