package ships;

import weapons.EnemyLaser;
import assets.ImageAssets;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Enemy ship with six laser guns
 */
public class MultiShooterShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 2500000000f; 	 //In nanoseconds
	public final static int HEIGHT=65;
	public final static int WIDTH=40;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=30;
	private final static int HEALTH=5;
	
	private float lastProjectileTime;
	private static final int DAMAGE_WHEN_RAMMED = 40;
	private static final boolean DISABABLE = true;

	/**
	 * Constructor
	 * @param x x-led spawn location
	 * @param y y-led spawn location
	 */
	public MultiShooterShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyMultiShooterShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);
	}
	
	/**
	 * Spawns projectiles from all guns
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX(), getY()+10, 2f, 15f,5,-45));
			getParent().addActor( new EnemyLaser(getX()+WIDTH/8, getY()+5, 2f, 15f,5,-45));
			getParent().addActor( new EnemyLaser(getX()+WIDTH/4, getY(), 2f, 15f,5,0));
			getParent().addActor( new EnemyLaser(getX()+3*WIDTH/4, getY(), 2f, 15f,5,0));
			getParent().addActor( new EnemyLaser(getX()+7*WIDTH/8, getY()+5, 2f, 15f,5,45));
			getParent().addActor( new EnemyLaser(getX()+WIDTH, getY()+10, 2f, 15f,5,45));
			lastProjectileTime = TimeUtils.nanoTime();
		}
	}
	
	/**
	 * Moves ship 
	 */
	@Override
	protected void move(float delta) {
		setY(getY()-SHIPSPEED);		
	}

	/**
	 * Creates a laser shot
	 */
	@Override
	protected void shoot(float delta) {
		spawnProjectile();	
	}
}
