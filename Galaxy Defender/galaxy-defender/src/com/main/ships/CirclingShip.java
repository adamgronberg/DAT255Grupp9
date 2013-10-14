package ships;

import weapons.EnemyLaser;
import assets.ImageAssets;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 *	An enemy that moves in a circle after getting to a position on the map
 *  Shoots lasers
 *
 */
public class CirclingShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 2000000000f; 	 //In nanoseconds
	public final static int HEIGHT = 45;
	public final static int WIDTH = 35;
	
	private final static float SHIPSPEED = 2.5f;
	private final static int SCOREVALUE = 2;
	private final static int HEALTH = 1;
	private final static float ROTATION_RADIAN = 60;
	
	private boolean circling = false;
	private boolean circleClockWise = false;
	private float lastProjectileTime;	
	private float degrees;
	private Vector2 spinPoint;
	
	private static final int DAMAGE_WHEN_RAMMED = 30;
	private static final boolean DISABABLE = true;
	
	private static final int LASER_DAMAGE = 4;
	private static final float LASER_HEIGHT = 8;
	private static final float LASER_WIDTH = 4;
	
	private static final float ROTATION_SPEED = 2f;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 * sets a random shoot time for first shoot
	 */
	public CirclingShip(float x, float y, float spinY , boolean circleClockWise) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyCirclingShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);
		this.circleClockWise = circleClockWise;
		spinPoint = new Vector2();
		if(circleClockWise){
			degrees = 0f;
			spinPoint.set(x-ROTATION_RADIAN,spinY);
		}
		else{
			degrees =-180f;
			spinPoint.set(x+ROTATION_RADIAN,spinY);
		}
		lastProjectileTime = TimeUtils.nanoTime() - 2*MathUtils.random(0,RATEOFFIRE);
	}
	
	/**
	 * Spawns projectiles in front of the enemies
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY(),
					LASER_WIDTH ,LASER_HEIGHT,LASER_DAMAGE,0));
			lastProjectileTime = TimeUtils.nanoTime();
		}
	}

	/**
	 * Ship move function
	 */
	@Override
	protected void move(float delta) {
		
		 if(!circling) setY(getY()-SHIPSPEED);
		
		 if(getY()<=spinPoint.y) circling=true;
		 
		 if(circling){
			spawnProjectile();	
			if(circleClockWise) degrees = degrees-ROTATION_SPEED;	
		 	else degrees = degrees+ROTATION_SPEED;
			setX(spinPoint.x+(ROTATION_RADIAN*(float)Math.cos(Math.toRadians(degrees))));
			setY(spinPoint.y+(ROTATION_RADIAN*(float)Math.sin(Math.toRadians(degrees))));
		}
	}

	///// Unused method /////
	@Override protected void shoot(float delta) {}
}
