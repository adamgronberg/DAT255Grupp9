package collisiondetection;

import java.util.Iterator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import dummies.AreaOfEffectDummy;
import ships.EnemyShip;
import spacegame.GameLogic;
import spacegame.MovableEntity;
import weapons.Projectile;
import weapons.TargetTypes;

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
	 */
	public void checkCollisions() {
		
		Array<EnemyShip> enemyShips = new Array<EnemyShip>();
		Array<Projectile> projectiles = new Array<Projectile>();
		Array<Projectile> affectsEnemyProjectiles = new Array<Projectile>();
		Array<AreaOfEffectDummy> dummies = new Array<AreaOfEffectDummy>();
		
		SnapshotArray<Actor> actors = gameLogic.getChildren();
		for(Actor actor: actors){
			
			if(actor instanceof EnemyShip){
				EnemyShip enemyShip = (EnemyShip)actor;
				enemyShips.add(enemyShip);
			}
			else if(actor instanceof Projectile){
				Projectile projectile = (Projectile)actor;
				projectiles.add(projectile);
				TargetTypes[] affectedTargets = projectile.getTargetTypes();
				for(TargetTypes affected : affectedTargets){
					switch(affected){
						case PLAYER_PROJECTILE:
							break;
						case ENEMY_PROJECTILE:
							affectsEnemyProjectiles.add(projectile);
							break;
						case ALLY_PROJECTILE:
							break;
					}
				}
			}
			else if(actor instanceof AreaOfEffectDummy){
				AreaOfEffectDummy dummy = (AreaOfEffectDummy)actor;
				dummies.add(dummy);
			}
		}
		
		
		Iterator<AreaOfEffectDummy> iterAD;
		Iterator<EnemyShip> iterES;
		Iterator<Projectile> iterAP;
		Iterator<Projectile> iterP;
		
		iterP = projectiles.iterator();
		while(iterP.hasNext()){
			Projectile projectile = iterP.next();
			//Test if projectile that destroys other projectile has collided
			iterAP = affectsEnemyProjectiles.iterator();
			while(iterAP.hasNext()){
				Projectile affectsEnemyProjectile = iterAP.next();
				//Wont remove itself
				if(projectile != affectsEnemyProjectile && affectsEnemyProjectile.getParent() != null){
					if(collisionControl(projectile, affectsEnemyProjectile)){
						affectsEnemyProjectile.addOnHitEffect();
						projectile.addOnHitEffect();
					}
				}
			}
			
			//Look att all different targets
			for(TargetTypes affected : projectile.getTargetTypes()){
				switch(affected){
					case PLAYER:
						//Removes player on collision with projectile
						if(collisionControl(projectile, gameLogic.playerShip)){
							//gameLogic.clear();
							projectile.addOnHitEffect();
							gameLogic.playerCollision(projectile.getOnHitDamage());	
						}
						break;
					case ENEMY:
						iterES = enemyShips.iterator();
						while(iterES.hasNext()){
							EnemyShip enemyShip = iterES.next();
							//Removes projectiles and enemies that collided
							if (collisionControl(enemyShip, projectile) && projectile.getParent() != null) {
								projectile.addOnHitEffect();
								gameLogic.addScore(enemyShip.hit(projectile.getOnHitDamage()));
								iterES.remove();
							}	
						}
						break;
					case ALLY:
						break;
				}
			}	
		}
		iterES = enemyShips.iterator();
		while (iterES.hasNext()) {
		EnemyShip enemyShip = iterES.next();
		
			//Check for collision with player
			if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				//gameLogic.clear();
				enemyShip.addOnHitEffect();
				gameLogic.playerCollision(enemyShip.getOnHitDamage());
			}
		}
		
		//If any area of effect weapons where triggered
		iterAD = dummies.iterator();
		while(iterAD.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterAD.next();
			//Checks what targets are valid for the area effect
			for(TargetTypes target: areaOfEffectDummy.getTargetTypes()){
				switch(target){
					case PLAYER:
						gameLogic.clear();
						areaOfEffectDummy.addOnHitEffect();
						gameLogic.playerCollision(1);
						break;
					case ENEMY:
						iterES = enemyShips.iterator();
						while(iterES.hasNext()){
							EnemyShip enemy = iterES.next();
							if(collisionControl(enemy, areaOfEffectDummy)){
								gameLogic.addScore(enemy.hit(areaOfEffectDummy.getAreaDamage()));
							}
						}
						break;
					case ALLY:
						break;
				}
			
				areaOfEffectDummy.remove();
			}
		}
	}
}
