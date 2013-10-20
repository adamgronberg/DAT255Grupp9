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
 * Handles collision between different entities
 * @author Grupp9
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
		checkCollisionPlayerEnemy(gameLogic);
		checkCollisionDummiesAll(gameLogic);
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
				TargetTypes[] affectedTargets = projectile.getFactionTypes();
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
						default:
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
						projectile.addOnHitEffect(projectile);
					}
				}
				
			}
		}
	}
	
	/**
	 * Checks collision between all projectiles and ships
	 * @param gameLogic
	 */
	private static void checkcollisionProjectiles(GameLogic gameLogic) {
		iterP = projectiles.iterator();
		while(iterP.hasNext()){
			projectile = iterP.next();
			checkCollisionBetweenProjectiles(affectsEnemyProjectiles.iterator(), TargetTypes.ENEMY, gameLogic);
			checkCollisionBetweenProjectiles(affectsPlayerProjectiles.iterator(), TargetTypes.PLAYER, gameLogic);
			checkCollisionBetweenProjectiles(affectsAllyProjectiles.iterator(),TargetTypes.ALLY, gameLogic);
			checkCollisionShipsProjectile(gameLogic);
		}
	}

	/**
	 * Looks if anything is inside of the area of effect of the dummy
	 * @param gameLogic
	 */
	private static void checkCollisionDummiesAll(GameLogic gameLogic) {
		//If any area of effect weapons where triggered
		iterAD = dummies.iterator();
		while(iterAD.hasNext()){
			AreaOfEffectDummy areaOfEffectDummy = iterAD.next();
			//Checks what targets are valid for the area effect
			for(TargetTypes target: areaOfEffectDummy.getFactionTypes()){
				switch(target){
					case PLAYER:
						if(collisionControl(gameLogic.playerShip, areaOfEffectDummy)){
							gameLogic.playerShip.decreaseCurrentHealth(areaOfEffectDummy.getAreaDamage());
						}

						break;
					case ENEMY:
						iterES = enemyShips.iterator();
						while(iterES.hasNext()){
							EnemyShip enemy = iterES.next();
							if(collisionControl(enemy, areaOfEffectDummy)){
								gameLogic.addScore(enemy.hit(areaOfEffectDummy.getAreaDamage()));
								areaOfEffectDummy.targetEffect(enemy);
							}
						}
						break;
					case ALLY:
						break;
					default:
						break;
				}
				areaOfEffectDummy.remove();
			}
		}
	}

	/**
	 * Loops through all enemies and sees if the have collided with player
	 * @param gameLogic
	 */
	private static void checkCollisionPlayerEnemy(GameLogic gameLogic) {
		iterES = enemyShips.iterator();
		while (iterES.hasNext()) {
		EnemyShip enemyShip = iterES.next();
		
			//Check for collision with player
			if(collisionControl(enemyShip, gameLogic.playerShip)) {	
				gameLogic.playerShip.decreaseCurrentHealth(enemyShip.getDamageWhenRammed());
				enemyShip.destroyShip();
			}
		}
	}

	/**
	 * Check collision between ships and projectiles
	 * @param gameLogic
	 */
	private static void checkCollisionShipsProjectile(GameLogic gameLogic) {
		//Look att all different targets
		for(TargetTypes affected : projectile.getFactionTypes()){
			switch(affected){
				case PLAYER:
					//Collision between player and projectile
					if(collisionControl(projectile, gameLogic.playerShip ) && projectile.getParent() != null){
						gameLogic.playerShip.decreaseCurrentHealth(projectile.getOnHitDamage());
						projectile.addOnHitEffect(projectile);
						break;
					}
					break;
				case ENEMY:
					iterES = enemyShips.iterator();
					while(iterES.hasNext()){
						EnemyShip enemyShip = iterES.next();
						//Collision between enemy and projectile
						if (collisionControl(enemyShip, projectile) && projectile.getParent() != null) {
							gameLogic.addScore(enemyShip.hit(projectile.getOnHitDamage()));
							projectile.addOnHitEffect(enemyShip);
							break;
						}	
					}
					break;
				case ALLY:
					break;					
				default:
					break;
			}
		}
	}
}
