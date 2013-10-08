package spawnlogic;

import com.badlogic.gdx.utils.TimeUtils;
import ships.BasicShip;
import ships.BigLaserShip;
import ships.EnemyShip;
import ships.HeavyShip;
import ships.KamikazeShip;
import ships.MultiShooterShip;
import ships.ScoutShip;
import ships.Asteroid;
import ships.StealthShip;
import ships.TurretShip;
import spacegame.GameLogic;
import spacegame.MovableEntity;

/**
 * 
 * @author Grupp9
 *
 * Contains different spawning patterns
 *
 */
public class SpawnPattern extends MovableEntity{

	private GameLogic gameLogic;
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
		this.gameLogic = gameLogic;
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
			
			if (enemyType.equals("SCOUT")) enemyShip = new ScoutShip(getX(), getY());
			else if (enemyType.equals("HEAVY")) enemyShip = new HeavyShip(getX(), getY());
			else if (enemyType.equals("ASTEROID")) enemyShip = new Asteroid(getX(), getY());
			else if (enemyType.equals("KAMIKAZE")) enemyShip = new KamikazeShip(getX(), getY(), gameLogic.playerShip);
			else if (enemyType.equals("MULTISHOOTER")) enemyShip = new MultiShooterShip(getX(), getY());
			else if (enemyType.equals("STEALTH")) enemyShip = new StealthShip(getX(), getY(), gameLogic.playerShip);
			else if (enemyType.equals("TURRET")) enemyShip = new TurretShip(getX(), getY());
			else if (enemyType.equals("BIGLASER")) enemyShip = new BigLaserShip(getX(), getY());
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
