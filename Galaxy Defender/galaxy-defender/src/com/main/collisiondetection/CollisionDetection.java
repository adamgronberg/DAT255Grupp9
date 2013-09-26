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
	
	private static Array<EnemyShip> enemyShips = new Array<EnemyShip>();
	private static Array<Projectile> projectiles = new Array<Projectile>();
	private static Array<Projectile> affectsEnemyProjectiles = new Array<Projectile>();
	private static Array<Projectile> affectsPlayerProjectiles = new Array<Projectile>();
	private static Array<Projectile> affectsAllyProjectiles = new Array<Projectile>();
	private static Array<AreaOfEffectDummy> dummies = new Array<AreaOfEffectDummy>();
	
	private static Iterator<AreaOfEffectDummy> iterAD;
	private static Iterator<EnemyShip> iterES;
	private static Iterator<Projectile> iterP;
	
	private static Projectile projectile;
	
	
	
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
	public static void checkCollisions(GameLogic gameLogic) {	
		splitActors(gameLogic.getChildren());
		checkcollisionProjectiles(gameLogic);
		checkColisionShips(gameLogic);
		checkCollisionDummies(gameLogic);
	}
	
	/**
	 * Splits the actor array into the different arrays needed
	 * @param toSplit
	 */
	private static void splitActors(SnapshotArray<Actor> toSplit){
		enemyShips = new Array<EnemyShip>();
		projectiles = new Array<Projectile>();
		affectsEnemyProjectiles = new Array<Projectile>();
		affectsPlayerProjectiles = new Array<Projectile>();
		affectsAllyProjectiles = new Array<Projectile>();
		dummies = new Array<AreaOfEffectDummy>();
		
		for(Actor actor: toSplit){
			
			if(actor instanceof EnemyShip){
				EnemyShip enemyShip = (EnemyShip)actor;
				enemyShips.add(enemyShip);
			}
			else if(actor instanceof AreaOfEffectDummy){
				AreaOfEffectDummy dummy = (AreaOfEffectDummy)actor;
				dummies.add(dummy);
			}
			else if(actor instanceof Projectile){
				Projectile projectile = (Projectile)actor;
				projectiles.add(projectile);
				TargetTypes[] affectedTargets = projectile.getTargetTypes();
				for(TargetTypes affected : affectedTargets){
					switch(affected){
						case PLAYER_PROJECTILE:
							affectsPlayerProjectiles.add(projectile);
							break;
						case ENEMY_PROJECTILE:
							affectsEnemyProjectiles.add(projectile);
							break;
						case ALLY_PROJECTILE:
							affectsAllyProjectiles.add(projectile);
							break;
					}
				}
			}
		}
	}
	
	/**
	 * tests collision between projectiles
	 * @param i	The projectile iterator to test on
	 * @param type the type to test
	 */
	private static void checkCollisionBetweenProjectiles(Iterator<Projectile> i, TargetTypes type, GameLogic gameLogic){
		//Test if projectile that destroys other projectile has collided
		while(i.hasNext()){
			Projectile affectsEnemyProjectile = i.next();
			//Wont remove itself
			if(projectile != affectsEnemyProjectile && projectile.getParent() != null){
				if(projectile.getFaction() == type){
					if(collisionControl(projectile, affectsEnemyProjectile)){
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
	}
	
	/**
	 * Checks collision between all projectiles
	 * @param gameLogic
	 */
	private static void checkcollisionProjectiles(GameLogic gameLogic) {
		iterP = projectiles.iterator();
		while(iterP.hasNext()){
			projectile = iterP.next();
			checkCollisionBetweenProjectiles(affectsEnemyProjectiles.iterator(), TargetTypes.ENEMY, gameLogic);
			checkCollisionBetweenProjectiles(affectsPlayerProjectiles.iterator(), TargetTypes.PLAYER, gameLogic);
			checkCollisionBetweenProjectiles(affectsAllyProjectiles.iterator(),TargetTypes.ALLY, gameLogic);
			checkCollisionShips(gameLogic);
		}
	}

	/**
	 * CHecks collision between ships
	 * @param gameLogic
	 */
	private static void checkCollisionDummies(GameLogic gameLogic) {
		//If any area of effect weapons where triggered
		iterAD = dummies.iterator();
		while(iterAD.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterAD.next();
			//Checks what targets are valid for the area effect
			for(TargetTypes target: areaOfEffectDummy.getTargetTypes()){
				switch(target){
					case PLAYER:
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

	/**
	 * Check if player collides with enemy
	 * @param gameLogic
	 */
	private static void checkColisionShips(GameLogic gameLogic) {
		iterES = enemyShips.iterator();
		while (iterES.hasNext()) {
		EnemyShip enemyShip = iterES.next();
		
			//Check for collision with player
			if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				enemyShip.addOnHitEffect();
				gameLogic.playerCollision(enemyShip.getOnHitDamage());
			}
		}
	}

	/**
	 * Check collision between ships and projectiles
	 * @param gameLogic
	 */
	private static void checkCollisionShips(GameLogic gameLogic) {
		//Look att all different targets
		for(TargetTypes affected : projectile.getTargetTypes()){
			switch(affected){
				case PLAYER:
					//Removes player on collision with projectile
					if(collisionControl(projectile, gameLogic.playerShip)){
						
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
							break;
						}	
					}
					break;
				case ALLY:
					break;
			}
		}
	}
}
