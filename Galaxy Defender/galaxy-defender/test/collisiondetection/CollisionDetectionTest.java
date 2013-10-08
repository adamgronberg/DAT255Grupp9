/**package collisiondetection;

import static org.junit.Assert.*;

import org.junit.Test;

import ships.EnemyShip;
import ships.HeavyShip;
import spacegame.GameLogic;
import spacegame.MovableEntity;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dummies.AreaOfEffectDummy;
import dummies.PlayerMissileExplosionDummy;

import weapons.EnemyLaser;


public class CollisionDetectionTest extends Table {

	@Test
	public void testCollisionControl() {
		MovableEntity movableObj1, movableObj2, movableObj3, movableObj4, movableObj5;
		
		movableObj1 = new MovableEntityMock(1f,1f,1f,1f);
		movableObj2 = new MovableEntityMock(1f,1f,1f,2f);
		movableObj3 = new MovableEntityMock(1f,1f,2f,1f);
		movableObj4 = new MovableEntityMock(1f,1f,-1f,1f);
		movableObj5 = new MovableEntityMock(1f,1f,1f,-1f);
		
		assertTrue(CollisionDetection.collisionControl(movableObj1, movableObj1)); //sammaobjekt
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj3)); //precis till höger
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj2)); //precis ovanför
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj4)); //precis till vänster
		assertFalse(CollisionDetection.collisionControl(movableObj1, movableObj5)); //precis under
	}
	
	
	@Test
	public void testToSplit() {
		EnemyLaser movableObj1, movableObj2;
		AreaOfEffectDummy areaOfEffectDummy=new PlayerMissileExplosionDummy(0, 0, 0, 0);
		
		movableObj1 = new EnemyLaser(0f,0f);
		movableObj2 = new EnemyLaser(0f,0f);
		
		
		addActor(movableObj1);
		addActor(movableObj2);
		addActor(areaOfEffectDummy);
			
		CollisionDetection.splitActors(getChildren());
		assertTrue(CollisionDetection.projectiles.size==2);
		assertTrue(CollisionDetection.dummies.size==1);
	}
	
	@Test
	public void testCheckCollisionPlayerEnemy(){
		GameLogic gl = new GameLogic();
		EnemyShip eShip = new HeavyShip(0,0);
		EnemyShip eShip2 = new HeavyShip(1,0);
		
		addActor(eShip);
		addActor(eShip2);
		
		CollisionDetection.splitActors(getChildren());
		
		CollisionDetection.checkCollisionPlayerEnemy(gl);
	}

}
*/
