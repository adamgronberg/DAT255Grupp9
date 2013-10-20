package spawnlogic;

import com.badlogic.gdx.utils.TimeUtils;
import ships.EnemyShip;

/**
 * A spawns like a V 
 * @author Grupp9
 */
public class PyramidPattern extends SpawnPattern{

	private int totalNumberOfEnemies;
	private int currentNumberOfEnemies = 0;
	private int numberOfSpawn =0;
	private int spaceBetweenShips;
	private EnemyShip enemyToSpawn;
	
	/**
	 * Constructor
	 * @param spawnX x-led the enemies should spawn
	 * @param spawnY y-led the enemies should spawn
	 * @param totalNumberOfEnemies Total number of enemies to spawn
	 * @param timeBetweenEnemy time in nanoseconds between spawns
	 * @param enemyType The Class name of the ship you want to spawn
	 * @param gameLogic The stage that uses the spawn pattern
	 */
	public PyramidPattern( int totalNumberOfEnemies, float timeBetween, 
		int spaceBetweenShips, EnemyShip enemyToSpawn){
		super(0, 0, timeBetween);
		this.totalNumberOfEnemies = totalNumberOfEnemies;
		this.spaceBetweenShips = spaceBetweenShips;
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
			EnemyShip enemyShip1, enemyShip2;
			
			try {
				enemyShip1 = (EnemyShip) enemyToSpawn.clone();
				enemyShip2 = (EnemyShip) enemyToSpawn.clone();
			} catch (CloneNotSupportedException e) {e.printStackTrace(); return;}
			
			
			if(numberOfSpawn==0){
				currentNumberOfEnemies++;
			}
			else{	
				enemyShip1.setX(enemyToSpawn.getX()+numberOfSpawn*spaceBetweenShips);
				enemyShip2.setX(enemyToSpawn.getX()-numberOfSpawn*spaceBetweenShips);
				getParent().addActor(enemyShip2);
				currentNumberOfEnemies = currentNumberOfEnemies+2;
			}
			getParent().addActor(enemyShip1);
			numberOfSpawn++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		
		if(currentNumberOfEnemies >= totalNumberOfEnemies){
			remove();
		}
	}
}