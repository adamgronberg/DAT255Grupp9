package ships;


import assets.ImageAssets;
import effects.BigLaserEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import dummies.AreaOfEffectDummy;
import weapons.TargetTypes;


/**
 * Enemy ship with massive laser beam
 * @author Group 9
 */
public class BigLaserShip extends EnemyShip {
	
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTED_TARGETS = {TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};
	private final static int DAMAGE_PER_TICK = 1;
	public static final float RATEOFFIRE = 8000000000f; 	 //In nanoseconds
	public static final float DAMAGE_TICK_RATE = 300000000f;
	public static final float FIRE_TIME = 3000000000f;
	public final static int HEIGHT=65;
	public final static int WIDTH=40;
	private final static float LASER_LENGTH = 900;
	private final static float LASER_WIDTH = 5;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=30;
	private final static int HEALTH=5;	
	private static final int DAMAGE_WHEN_RAMMED = 40;
	private float lastProjectileTime;
	private float damageTickTime;
	private float fireTime;
	private boolean shooting;
	private AreaOfEffectDummy areaDummy;
	private BigLaserEffect bigLaserEffect;
	private static final boolean DISABABLE = true;

	
	/**
	 * Constructor
	 * @param x x-led spawn location
	 * @param y y-led spawn location
	 */
	public BigLaserShip(float x, float y){
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBigLaserShip,
				DAMAGE_WHEN_RAMMED, DISABABLE);
		lastProjectileTime = TimeUtils.nanoTime()-MathUtils.random(3000000000f,RATEOFFIRE/2);
	}
	
	/**
	 * Sets ships status to 'shooting'
	 * Resets timers for weapon
	 * Creates graphic effect for laser
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			shooting = true;
			damageTickTime = TimeUtils.nanoTime();
			lastProjectileTime = TimeUtils.nanoTime();
			fireTime = TimeUtils.nanoTime();
			bigLaserEffect = new BigLaserEffect(getX()+WIDTH/2,getY()-LASER_LENGTH,LASER_WIDTH,LASER_LENGTH, true, this);
			getParent().addActor(bigLaserEffect);
		}
	}
	
	/**
	 * Moves ship
	 * Keeps track of when to fire weapon
	 */
	@Override
	protected void move(float delta) {
		if(!shooting)setY(getY()-SHIPSPEED);
		if(TimeUtils.nanoTime()-lastProjectileTime>RATEOFFIRE){
			spawnProjectile();
		}else if(shooting){
			if(TimeUtils.nanoTime()-damageTickTime>DAMAGE_TICK_RATE){
				areaDummy = new AreaOfEffectDummy(getX() + WIDTH/2,getY()-LASER_LENGTH, 
						LASER_WIDTH, LASER_LENGTH,DAMAGE_PER_TICK, FACTION, AFFECTED_TARGETS);
				getParent().addActor(areaDummy);
				damageTickTime = TimeUtils.nanoTime();
				
				if(TimeUtils.nanoTime()-fireTime > FIRE_TIME){
					getParent().removeActor(bigLaserEffect);
					shooting = false;
				}
			}
		}
	}

	@Override protected void shoot(float delta) {}
}
