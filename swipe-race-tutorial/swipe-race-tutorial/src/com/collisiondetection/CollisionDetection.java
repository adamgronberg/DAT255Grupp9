package com.collisiondetection;

import java.util.Iterator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.effects.ExplosionEffect;
import com.ships.EnemyShip;
import com.spacegame.GameLogic;
import com.spacegame.MovableEntity;
import com.spacegame.MyGame;
import com.weapons.AreaOfEffectDummy;
import com.weapons.AreaOfEffectWeapon;
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
	 * Tests if obj1 and obj2 collided
	 * @param movableObj1
	 * @param movableObj2
	 * @return true if the objects collided
	 */
	private static boolean collisionControl(Rectangle obj1, Rectangle obj2){
		return obj1.overlaps(obj2);
	}
	
	
	/**
	 * Handles ALL collision
	 * TODO: Who want to shatter this beast into different methods?
	 * 
	 */
	public void checkCollisions() {
		Array<AreaOfEffectDummy> areaOfEffectDummys = new Array<AreaOfEffectDummy>();
		Iterator<Projectile> iterM = gameLogic.getPlayerProjectiles().iterator();
		Iterator<EnemyShip> iter = gameLogic.getEnemyShips().iterator();
		while (iter.hasNext()) {
			EnemyShip enemyShip = iter.next();
			
			//Removes ship when despawn ready
			if(enemyShip.isDespawnReady()){					
				gameLogic.removeActor(enemyShip);
				iter.remove();
			}	
			//Removes ships out of y-led bounds
			else if (isOutOfBoundsY(enemyShip)) {		
				iter.remove();
			}
			//Check for collision with player
			else if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				gameLogic.clearScreen();
				gameLogic.changePlayerAlive();
				break;
			}									
			else {
				iterM = gameLogic.getPlayerProjectiles().iterator();
				while(iterM.hasNext()){
					Projectile projectile = iterM.next();
					
					//Removes projectiles with linger time
					if(projectile.isDespawnReady()){					
						gameLogic.removeActor(projectile);
						iterM.remove();
					}
					
					//Removes projectiles and enemies that collided
					if (collisionControl(enemyShip, projectile)) {		
						if(projectile.despawnesOnCollision()){			
							gameLogic.removeActor(projectile);
							iterM.remove();
						}
						enemyShip.hit(projectile.getDamage());
						
						//If a projectile has a area of effect component its handled here
						if(projectile instanceof AreaOfEffectWeapon){	
							AreaOfEffectWeapon areaOfEffectWeapon = (AreaOfEffectWeapon) projectile;
							ExplosionEffect effect = projectile.addEffect();
							
							//Adds a on hit effect if the weapon has one
							if(effect != null) {
								gameLogic.addEffect(effect);
							}
							
							//Adds a queue with area of effect damage
							areaOfEffectDummys.add(areaOfEffectWeapon.getAreaOfEffectDummy());
						}
						break;
					}
				}
			}
		}
		iterM = gameLogic.getPlayerProjectiles().iterator();	
		while(iterM.hasNext()){
			Projectile projectile = iterM.next();
			
			//If projectiles are out of y-led bounds
			if (isOutOfBoundsY(projectile)){
				iterM.remove();
				gameLogic.removeActor(projectile);
			}
		}
		
		//If any area of effect weapons where triggered
		Iterator<AreaOfEffectDummy> iterED = areaOfEffectDummys.iterator();	
		while(iterED.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterED.next();
			iter = gameLogic.getEnemyShips().iterator();
			while(iter.hasNext()){
				EnemyShip enemyShip = iter.next();
				
				//If any target in range for area of effect
				if(collisionControl(enemyShip.getBounds(), areaOfEffectDummy)) {
					enemyShip.hit(areaOfEffectDummy.getAreaDamage());
				}
			}
		}
		
	}
}
