package collisiondetection;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;

import spacegame.GameScreen;
import spacegame.MovableEntity;

public class OutOfBoundsDetection {

	/**
	 * Checks if a obj is out of bounds on y-led
	 * @param movableObj
	 * @return	returns true if out of bounds
	 */
	public static void isOutOfBoundsY(MovableEntity movableObj){
		if (movableObj.getBounds().y < -movableObj.getBounds().getHeight() || movableObj.getBounds().y > 
				GameScreen.GAME_HEIGHT+movableObj.getBounds().getHeight()){
			movableObj.remove();
		}
	}

	/**
	 * Checks if any movableobject is out of bounds
	 * @param actors
	 */
	public static void checkOutOfBounds(SnapshotArray<Actor> actors) {
		for(Actor actor: actors){
			if (actor instanceof MovableEntity){
				MovableEntity entity = (MovableEntity)actor;
				OutOfBoundsDetection.isOutOfBoundsY(entity);
			}
		}
	}
	
	
	
	
	
}
