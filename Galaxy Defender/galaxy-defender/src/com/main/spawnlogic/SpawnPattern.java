package spawnlogic;


import com.badlogic.gdx.utils.TimeUtils;
import ships.*;
import spacegame.*;

/**
 * 
 * @author Grupp9
 *
 * Contains different spawning patterns
 *
 */
public class SpawnPattern extends MovableEntity{

	private int totalNumberOfEnemies;
	private int currentNumberOfEnemies = 0;
	private float timePassedSinceSpawn = 0;
	private float timeBetweenEnemy;
	private String enemyType;
	
	
	/**
	 * Constructor
	 * @param spawnX x-led the enemies should spawn
	 * @param spawnY y-led the enemies should spawn
	 * @param totalNumberOfEnemies Total number of enemies to spawn
	 * @param timeBetweenEnemy time in nanoseconds between spawns
	 * @param enemyType The Class name of the ship you want to spawn
	 * @param gameLogic The stage that uses the spawn pattern
	 */
	public SpawnPattern(float spawnX, float spawnY, 
			int totalNumberOfEnemies, float timeBetweenEnemy, 
			String enemyType, GameLogic gameLogic){
		super(1, 1, spawnX, spawnY);
		this.totalNumberOfEnemies = totalNumberOfEnemies;
		this.timeBetweenEnemy = timeBetweenEnemy;
		this.enemyType = enemyType;		
	}
	
	
	/**
	 * Called when act is called in its stage
	 * Spawns 'totalNumberOfEnemies' on the same 
	 * spot with a time difference of 'timeBetweenEnemy'
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(TimeUtils.nanoTime() - timePassedSinceSpawn > timeBetweenEnemy){
			EnemyShip enemyShip;
			if(enemyType.equals("ScoutShip")) enemyShip = new ScoutShip(getX(), getY());
			else enemyShip = new BasicShip(getX(), getY());
			getParent().addActor(enemyShip);
						
			currentNumberOfEnemies++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		if(currentNumberOfEnemies >= totalNumberOfEnemies){
			remove();
		}
	}
	
}
