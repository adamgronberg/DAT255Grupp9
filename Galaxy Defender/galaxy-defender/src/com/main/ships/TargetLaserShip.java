package ships;

import weapons.EnemyLaser;

import com.badlogic.gdx.utils.TimeUtils;

import assets.ImageAssets;

/**
 * Spawn from BossShip
 * @author Grupp9
 */
public class TargetLaserShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 50000000f; 	 //In nanoseconds
	private final static float SHIPSPEED=1f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=0;
	public final static int HEIGHT=30;
	public final static int WIDTH=30;
	public final static int LASER_HEIGHT=15;
	public final static int LASER_WIDTH=3;
	private static final int DAMAGE_WHEN_RAMMED = 2;
	private long currentTime;
	private long lastMissileTime=0;
	private PlayerShip player;
	private int shot =0;
	private static final boolean DISABLEABLE = true;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param player
	 */
	public TargetLaserShip(float x, float y, PlayerShip player) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyStealthShip, DAMAGE_WHEN_RAMMED,
				DISABLEABLE);
		currentTime = TimeUtils.nanoTime();
		this.player = player;
	}

	/**
	 * Move logic
	 */
	@Override
	protected void move(float delta) {
		if(TimeUtils.nanoTime()-currentTime > 3000000000f && getY() > 100){
			currentTime = TimeUtils.nanoTime();
			shot=0;
		}
		setY(getY()-SHIPSPEED);
	}

	/**
	 * Shoot logic
	 */
	@Override
	protected void shoot(float delta) {
		if(TimeUtils.nanoTime() - lastMissileTime > RATEOFFIRE) {
			if(shot<=1){
				float delX = getX()-player.getX();
				float delY = getY()-player.getY();
				float degree2 =(float)Math.atan(delX/delY);
				float degree = (float)(180/Math.PI)*degree2;
				getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY(),LASER_WIDTH,LASER_HEIGHT,DAMAGE_WHEN_RAMMED, -degree));
				lastMissileTime = TimeUtils.nanoTime();
				shot++;
			}
		}
	}
}
