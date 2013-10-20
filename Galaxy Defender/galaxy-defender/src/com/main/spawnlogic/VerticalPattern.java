package spawnlogic;

import com.badlogic.gdx.utils.TimeUtils;
import ships.EnemyShip;

/**
 * A vertical spawning pattern
 * @author Grupp9
 */
public class VerticalPattern extends SpawnPattern{

	private int totalNumberOfEnemies;
	private int currentNumberOfEnemies = 0;
	private EnemyShip enemyToSpawn;
	
	/**
	 * Constructor 
	 * @param spawnX	x-led the enemies should spawn
	 * @param spawnY	y-led the enemies should spawn
	 * @param totalNumberOfEnemies	Total number of enemies to spawn
	 * @param timeBetween	time in nanoseconds between spawns
	 * @param spinPointY	The point to spin around
	 * @param circleClockWise	The spin direction
	 * @param gameLogic	The stage that uses the spawn pattern
	 */
	public VerticalPattern(int totalNumberOfEnemies, float timeBetween, EnemyShip enemyToSpawn){
		super(0, 0, timeBetween);
		this.totalNumberOfEnemies = totalNumberOfEnemies;
		this.enemyToSpawn = enemyToSpawn;
	}
	
	/**
	 * Called when act is called in its stage
	 * Spawns 'totalNumberOfEnemies' on the same 
	 * spot with a time difference of 'timeBetweenEnemy'
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(TimeUtils.nanoTime() - timePassedSinceSpawn > timeBetween){
			EnemyShip enemyShip;
			
			try {
				enemyShip = (EnemyShip) enemyToSpawn.clone();
			} catch (CloneNotSupportedException e) {e.printStackTrace(); return;}
			
			getParent().addActor(enemyShip);
						
			currentNumberOfEnemies++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		if(currentNumberOfEnemies >= totalNumberOfEnemies){
			remove();
		}
	}
}
