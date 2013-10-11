package collisiondetection;

import static org.junit.Assert.*;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.junit.Test;
import spacegame.MovableEntity;

public class OutOfBoundsDetectionTest extends Table {

	@Test
	public void testisOutOfBoundsY() {
		MovableEntity movableObj1, movableObj2;
		
		movableObj1 = new MovableEntityMock(1f,1f,1f,7000f);
		movableObj2 = new MovableEntityMock(1f,1f,1f,2f);
		
		addActor(movableObj1);
		addActor(movableObj2);
		
		int originalSize = 	getChildren().size;	
		OutOfBoundsDetection.checkOutOfBounds(getChildren());
		assertTrue(originalSize-1==getChildren().size);
		
		
	}
	
	

}
