package com.collisiondetection;

import java.util.Iterator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.ships.EnemyShip;
import com.spacegame.GameLogic;
import com.spacegame.MovableEntity;
import com.weapons.AreaOfEffectDummy;
import com.weapons.Projectile;


/**
 * 
 * @author Grupp9
 *
 * Handles collision between different entities
 */
public class CollisionDetection {
	
	private GameLogic gameLogic;
	
	
	/**
	 * Constructor
	 * @param gameLogic object to add collision to
	 */
	public CollisionDetection(GameLogic gameLogic){
		this.gameLogic = gameLogic;
	}

	
	/**
	 * Tests if obj1 and obj2 collided
	 * @param movableObj1
	 * @param movableObj2
	 * @return true if the objects collided
	 */
	private static boolean collisionControl(MovableEntity movableObj1, MovableEntity movableObj2){
		return movableObj1.getBounds().overlaps(movableObj2.getBounds());
	}
	
	
	/**
	 * Handles ALL collision
	 * 
	 */
	public void checkCollisions() {
		
		Array<EnemyShip> enemyShips = new Array<EnemyShip>();
		Array<Projectile> playerProjectiles = new Array<Projectile>();
		Array<AreaOfEffectDummy> dummyies = new Array<AreaOfEffectDummy>();
		
		
		SnapshotArray<Actor> actors = gameLogic.getChildren();
		for(Actor actor: actors){
			
			if(actor instanceof EnemyShip){
				EnemyShip enemyShip = (EnemyShip)actor;
				enemyShips.add(enemyShip);
			}
			else if(actor instanceof Projectile){
				Projectile projectile = (Projectile)actor;
				playerProjectiles.add(projectile);
			}
			else if(actor instanceof AreaOfEffectDummy){
				AreaOfEffectDummy dummy = (AreaOfEffectDummy)actor;
				dummyies.add(dummy);
			}
		}
		
		
		
		Iterator<AreaOfEffectDummy> iterAD = dummyies.iterator();
		Iterator<Projectile> iterPP = playerProjectiles.iterator();
		Iterator<EnemyShip> iterES = enemyShips.iterator();
		
		while (iterES.hasNext()) {
			EnemyShip enemyShip = iterES.next();
			
			//Check for collision with player
			if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				gameLogic.clear();
				gameLogic.changePlayerAlive();
				return;
			}									
			else {
				iterPP = playerProjectiles.iterator();
				while(iterPP.hasNext()){
					Projectile projectile = iterPP.next();
					
					//Removes projectiles and enemies that collided
					if (collisionControl(enemyShip, projectile)) {
						enemyShip.hit(projectile.getDamage());
						projectile.addOnHitEffect();
						break;
					}	
				}
			}
		}
		//If any area of effect weapons where triggered
		while(iterAD.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterAD.next();
			iterES = enemyShips.iterator();
			while(iterES.hasNext()){
				EnemyShip enemyShip = iterES.next();
				
				//If any target in range for area of effect
				if(collisionControl(enemyShip, areaOfEffectDummy)) {
					enemyShip.hit(areaOfEffectDummy.getAreaDamage());
				}
			}
			areaOfEffectDummy.remove();
		}
	}
}
