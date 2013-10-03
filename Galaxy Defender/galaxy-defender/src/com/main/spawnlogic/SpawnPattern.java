package spawnlogic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import ships.BasicShip;
import ships.EnemyShip;
import ships.EnemyShip.EnemyTypes;
import ships.HeavyShip;
import ships.ScoutShip;
import ships.Asteroid;
import spacegame.GameScreen;
import spacegame.MovableEntity;



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
	private EnemyTypes enemyType;
	
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
		EnemyTypes enemyType){
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
			switch(enemyType){
				case BASIC:
					enemyShip = new BasicShip(getX(), getY());
					break;
				case HEAVY:
					enemyShip = new HeavyShip(getX(), getY());
					break;
				case ASTEROID:
					enemyShip = new Asteroid(MathUtils.random(0,GameScreen.GAME_WITDH-ScoutShip.WIDTH), getY());
					break;
				default:
					enemyShip = new ScoutShip(getX(), getY());
					break;
			}
			getParent().addActor(enemyShip);
						
			currentNumberOfEnemies++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		if(currentNumberOfEnemies >= totalNumberOfEnemies){
			remove();
		}
	}
	
}
