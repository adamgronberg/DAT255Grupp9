/**package collisiondetection;

import static org.junit.Assert.*;
import org.junit.Test;
import spacegame.MovableEntity;

public class CollisionDetectionTest {

	@Test
	public void testCollisionControl() {
		MovableEntity movableObj1, movableObj2, movableObj3, movableObj4, movableObj5;
		
		movableObj1 = new MovableEntityMock(1f,1f,1f,1f);
		movableObj2 = new MovableEntityMock(1f,1f,1f,2f);
		movableObj3 = new MovableEntityMock(1f,1f,2f,1f);
		movableObj4 = new MovableEntityMock(1f,1f,-1f,1f);
		movableObj5 = new MovableEntityMock(1f,1f,1f,-1f);
		
		assertTrue(CollisionDetection.collisionControl(movableObj1, movableObj1)); //sammaobjekt
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj3)); //precis till h�ger
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj2)); //precis ovanf�r
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj4)); //precis till v�nster
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj5)); //precis under
	}
	
	@Test
	public void testcheckCollisions() {
		
	}

}
*/