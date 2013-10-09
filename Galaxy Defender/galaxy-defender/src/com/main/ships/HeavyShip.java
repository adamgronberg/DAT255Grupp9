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
	
	public static final float RATEOFFIRE = 2500000000f; 	 //In nanoseconds
	public final static int HEIGHT=65;
	public final static int WIDTH=40;
	private final static float SHIPSPEED = 1.5f;
	private final static int SCOREVALUE=3;
	private final static int HEALTH=3;
	
	private float lastProjectileTime;
	private static final int DAMAGE_WHEN_RAMMED = 30;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 * sets a random shoot time for first shoot
	 */
	public HeavyShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyHeavyShip, DAMAGE_WHEN_RAMMED);
		lastProjectileTime = TimeUtils.nanoTime() - 2*MathUtils.random(0,RATEOFFIRE);
	}
	
	/**
	 * Spawns projectiles in front of the enemies
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY()));
			lastProjectileTime = TimeUtils.nanoTime();
		}
	}

	/**
	 * Ship move function
	 */
	@Override
	protected void move(float delta) {
		setY(getY()-SHIPSPEED);	
	}

	/**
	 * Ship shoot function
	 */
	@Override
	protected void shoot(float delta) {
		spawnProjectile();	
	}

	@Override
	public void uppgrade(int boast) {
		// TODO Auto-generated method stub
		
	}
}
