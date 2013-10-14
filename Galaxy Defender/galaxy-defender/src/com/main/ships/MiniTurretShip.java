package ships;

import weapons.EnemyLaser;
import assets.ImageAssets;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 *	MiniTurretShip
 *  Acts as a turret on a larger ship
 *
 */

public class MiniTurretShip extends EnemyShip {
	public static final float RATEOFFIRE = 100000000f; 	 //In nanoseconds
	public static final float FIRETIME = 5000000000f;
	private final static int HEALTH=20;
	private final static int SCOREVALUE=1;
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	public final static int LASER_HEIGHT=15;
	public final static int LASER_WIDTH=3;
	private static final int DAMAGE_WHEN_RAMMED = 0;
	private static final int LASER_DAMAGE = 8;
	private final static boolean DISABABLE = false;
	private long currentTime;
	private long lastMissileTime;
	private PlayerShip player;
	private int shot = 0;
	private boolean isAlive = true;
	
	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	* @param player PlayerShip
	*/
	public MiniTurretShip(float x, float y, PlayerShip player){
		super(WIDTH,HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyStealthShip, DAMAGE_WHEN_RAMMED, DISABABLE);
		currentTime = TimeUtils.nanoTime();
		this.player = player;
		lastMissileTime = TimeUtils.nanoTime();
	}
	
	/**
	 * Spawns projectiles in front of the enemies depending on where player is.
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > RATEOFFIRE && shot<=4) {
			float delX = getX()-player.getX();
			float delY = getY()-player.getY();
			float degree2 =(float)Math.atan(delX/delY);
			float degree = (180f/(float)Math.PI)*degree2;
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY(),LASER_WIDTH,LASER_HEIGHT, LASER_DAMAGE, -degree));
			lastMissileTime = TimeUtils.nanoTime();
			shot++;
		}
	}

	/**
	 * Moves the ship
	 */
	@Override
	protected void move(float delta) {
		setX(getX()+MiniBossShip.WIDTH/2-WIDTH/2);
		setY(getY()-5);
	}
	
	/**
	 * @return true if ship is alive else false
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	@Override
	public void destroyShip(){
		isAlive = false;
		remove();
	}
	
	public void reset(){
		currentHealth = HEALTH;
		isAlive = true;
	}

	@Override protected void shoot(float delta) {
		if(TimeUtils.nanoTime()-currentTime>FIRETIME){
			currentTime=TimeUtils.nanoTime();
			shot=0;
		}
		spawnProjectile();
	}
}