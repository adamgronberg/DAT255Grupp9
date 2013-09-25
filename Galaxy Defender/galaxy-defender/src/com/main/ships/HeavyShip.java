package ships;


import assets.ImageAssets;
import weapons.EnemyLaser;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *  Only has y led moment more HP
 *
 */
public class HeavyShip extends EnemyShip {
	private final static float SHIPSPEED = 2.0f;
	public final static int HEIGHT=60;
	public final static int WIDTH=40;
	
	private final static int SCOREVALUE=3;
	private final static int HEALTH=2;
	
	private float lastMissileTime = 0;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public HeavyShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT, HEALTH, SCOREVALUE, ImageAssets.enemyBasicShip);
		
	}
	
	/**
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-SHIPSPEED);
		spawnEnemyProjectile();

	}
	/**
	 * Spawns projectiles in front of the enemies
	 */
	public void spawnEnemyProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > EnemyLaser.RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2, 
										 getY()));
			lastMissileTime = TimeUtils.nanoTime();
		}
	}
}
