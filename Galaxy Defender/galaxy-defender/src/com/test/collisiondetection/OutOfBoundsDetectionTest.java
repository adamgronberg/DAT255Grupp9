package collisiondetection;

import static org.junit.Assert.*;

import org.junit.Test;
import spacegame.MovableEntity;

public class OutOfBoundsDetectionTest {

	@Test
	public void testisOutOfBoundsY() {
		MovableEntity movableObj1, movableObj2, movableObj3, movableObj4, movableObj5;
		
		movableObj1 = new MovableEntityMock(1f,1f,1f,1f);
		movableObj2 = new MovableEntityMock(1f,1f,1f,2f);
		movableObj3 = new MovableEntityMock(1f,1f,2f,1f);
		movableObj4 = new MovableEntityMock(1f,1f,-1f,1f);
		movableObj5 = new MovableEntityMock(1f,1f,1f,-1f);
		
		OutOfBoundsDetection.isOutOfBoundsY(movableObj1);
	}

}
