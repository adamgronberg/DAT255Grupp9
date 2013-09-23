package com.collisiondetection;

import java.util.Iterator;
import com.badlogic.gdx.math.Rectangle;
import com.effects.AreaEffect;
import com.ships.EnemyShip;
import com.spacegame.GameLogic;
import com.spacegame.MovableEntity;
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
	 * 
	 */
	public void checkCollisions() {
		Iterator<AreaOfEffectDummy> iterAD = gameLogic.getDummys().iterator();
		Iterator<Projectile> iterPP = gameLogic.getPlayerProjectiles().iterator();
		Iterator<EnemyShip> iterES = gameLogic.getEnemyShips().iterator();
		
		while (iterES.hasNext()) {
			EnemyShip enemyShip = iterES.next();
			
			//Check for collision with player
			if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				gameLogic.clearScreen();
				gameLogic.changePlayerAlive();
				break;
			}									
			else {
				iterPP = gameLogic.getPlayerProjectiles().iterator();
				while(iterPP.hasNext()){
					Projectile projectile = iterPP.next();
					
					//Removes projectiles and enemies that collided
					if (collisionControl(enemyShip, projectile)) {		
						if(projectile.despawnesOnCollision()){			
							projectile.setDespawnReady();
						}
						enemyShip.hit(projectile.getDamage());
						
						//If a projectile has a area of effect component its handled here
						if(projectile instanceof AreaOfEffectWeapon){	
							AreaOfEffectWeapon areaOfEffectWeapon = (AreaOfEffectWeapon) projectile;
							AreaEffect effect = projectile.addOnHitEffect();
							
							//Adds a on hit effect if the weapon has one
							if(effect != null) {
								gameLogic.addEffect(effect);
							}
							
							//Adds the on hit area of effect dummy
							gameLogic.addAreaOfEffectDummy(areaOfEffectWeapon.getAreaOfEffectDummy());
						}
						break;
					}
				}
			}
		}
		
		//If any area of effect weapons where triggered
		while(iterAD.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterAD.next();
			iterES = gameLogic.getEnemyShips().iterator();
			while(iterES.hasNext()){
				EnemyShip enemyShip = iterES.next();
				
				//If any target in range for area of effect
				if(collisionControl(enemyShip.getBounds(), areaOfEffectDummy)) {
					enemyShip.hit(areaOfEffectDummy.getAreaDamage());
				}
			}
		}
		
		//Deletes all dummies so they don't do more damage
		gameLogic.getDummys().clear();
	}
}
