package ships;

import weapons.EnemyLaser;
import assets.ImageAssets;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 *	StealthShip
 *  Disappears from the screen and comes back
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
	private static final int DAMAGE_WHEN_RAMMED = 5;
	private final static boolean DISABLED = false;
	private long currentTime;
	private long lastMissileTime = 0;
	private PlayerShip player;
	private int shot = 0;
	private float turretX = 10;
	private float turretY = 10;
	private boolean isAlive = true;
	
	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	* @param player PlayerShip
	*/
	public MiniTurretShip(float x, float y, PlayerShip player){
		super(WIDTH,HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyStealthShip, DAMAGE_WHEN_RAMMED, DISABLED);
		currentTime = TimeUtils.nanoTime();
		this.player = player;
	}
	
	/**
	 * Spawns projectiles in front of the enemies depending on where player is.
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > RATEOFFIRE) {
	
			if(shot<=4){
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

	/**
	 * Moves the ship
	 */
	@Override
	protected void move(float delta) {
		if(TimeUtils.nanoTime()-currentTime>FIRETIME){
			currentTime=TimeUtils.nanoTime();
			shot=0;
		}
		spawnProjectile();
		setX(turretX+TurretShip.WIDTH/2-WIDTH/2);
		setY(turretY-5);
	}
	
	/**
	 * @return true if ship is alive else false
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	/**
	 * @param x TurretShips x position
	 * @param y TurretShips y position
	 */
	public void setXY(float x, float y){
		turretX=x;
		turretY=y;
	}
	
	@Override
	public void destroyShip(){
		isAlive = false;
		remove();
	}

	@Override protected void shoot(float delta) {
	}
}