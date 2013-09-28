package ships;

import weapons.EnemyLaser;
import assets.ImageAssets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;



/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *  Only has y led moment more HP and a laser cannon
 *
 */
public class HeavyShip extends EnemyShip {
	
	public final static int HEIGHT=60;
	public final static int WIDTH=30;
	private final static float SHIPSPEED = 1.5f;
	private final static int SCOREVALUE=3;
	private final static int HEALTH=3;
	
	private float lastMissileTime;
	private static final int DAMAGE_WHEN_RAMMED = 30;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 * sets a random shoot time for first shoot
	 */
	public HeavyShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyHeavyShip, DAMAGE_WHEN_RAMMED);
		lastMissileTime = TimeUtils.nanoTime() - 2*MathUtils.random(0,EnemyLaser.RATEOFFIRE);
	}
	
	/**
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		setY(getY()-SHIPSPEED);
		spawnEnemyProjectile();
	}
	/**
	 * Spawns projectiles in front of the enemies
	 */
	public void spawnEnemyProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > EnemyLaser.RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2, getY()));
			lastMissileTime = TimeUtils.nanoTime();
		}
	}
	
	/**
	 *  removes the ship
	 */
	public void addOnHitEffect() {
		remove();
	}
}
