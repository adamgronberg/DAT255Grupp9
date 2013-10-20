package ships;

import weapons.EnemyLaser;

import com.badlogic.gdx.utils.TimeUtils;

import assets.ImageAssets;

/**
 * Spawn from BossShip
 * @author Grupp9
 */
public class TargetLaserShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 400000000f; 	 //In nanoseconds
	private final static float SHIPSPEED=1f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=5;
	public final static int HEIGHT=30;
	public final static int WIDTH=20;
	public final static int LASER_HEIGHT=15;
	public final static int LASER_WIDTH=3;
	private static final int DAMAGE_WHEN_RAMMED = 2;
	private static final int DAMAGE_ON_HIT = 5;
	private long lastMissileTime=0;
	private PlayerShip player;
	private static final boolean DISABLEABLE = true;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param player
	 */
	public TargetLaserShip(float x, float y, PlayerShip player) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyTargetLaserShip, DAMAGE_WHEN_RAMMED,
				DISABLEABLE);
		this.player = player;
	}

	/**
	 * Move logic
	 */
	@Override
	protected void move(float delta) {
		setY(getY()-SHIPSPEED);
	}

	/**
	 * Shoot logic
	 */
	@Override
	protected void shoot(float delta) {
		if(TimeUtils.nanoTime() - lastMissileTime > RATEOFFIRE && getY() > 200) {
			float delX = getX()-player.getX();
			float delY = getY()-player.getY();
			float degree2 =(float)Math.atan(delX/delY);
			float degree = (float)(180/Math.PI)*degree2;
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY(), LASER_WIDTH, LASER_HEIGHT, DAMAGE_ON_HIT, -degree));
			lastMissileTime = TimeUtils.nanoTime();
		}
	}
}
