package ships;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import effects.ExplosionEffect;
import assets.ImageAssets;

/**
 * Final boss! TODO: Make him more fun and have a epic ending
 * @author Grupp9
 */
public class BossShip extends EnemyShip{
	
	public final static int HEIGHT=180;
	public final static int WIDTH=300;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=300;
	private final static int HEALTH=1000;
	private final static int TURRET_EXPLOSION_DAMAGE=50;
	private static final int DAMAGE_WHEN_RAMMED = 40;
	private static final boolean DISABLEABLE = false;
	private static final float TURRET_REPAIR_TIME = 8000000000f;
	private static final float SPAWN_TIMER=2000000000;
	private static final float ROTATION_RADIAN = 100;
	private static final float ROTATION_SPEED = 0.5f;
	private PlayerShip playerShip;
	private boolean turret1Dead,turret2Dead,turretsSpawnedOnce,reachedPosX,reachedPosY = false;
	private float turret1DeathTime,turret2DeathTime,spawnTimer,degrees;
	private MiniTurretShip turret1,turret2;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param playerShip
	 */
	public BossShip(float x, float y,PlayerShip playerShip) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBossShip, DAMAGE_WHEN_RAMMED,
				DISABLEABLE);
		this.playerShip = playerShip;
		spawnTimer = TimeUtils.nanoTime();
		degrees = 0f;
	}

	/**
	 * Move logic
	 */
	@Override
	protected void move(float delta) {
		if(!turretsSpawnedOnce){
			turret1 = new MiniTurretShip(getX()+60,getY(),playerShip);
			turret2 = new MiniTurretShip(getX()+170,getY(),playerShip);
			getParent().addActor(turret1);
			getParent().addActor(turret2);
			turretsSpawnedOnce = true;
		}		
		if(getY()>500)setY(getY()-SHIPSPEED);else reachedPosY = true;
		if(getX()<200)setX(getX()+SHIPSPEED);else reachedPosX = true;
		turret1.setPosition(getX()+60, getY());
		turret2.setPosition(getX()+170, getY());
		if(!turret1Dead){
			if(!turret1.isAlive()){
				turret1DeathTime = TimeUtils.nanoTime();
				turret1Dead = true;
				getParent().addActor(new ExplosionEffect(turret1.getX(),turret1.getY(),MiniTurretShip.WIDTH,MiniTurretShip.HEIGHT));
				hit(TURRET_EXPLOSION_DAMAGE);
			}	
		}
		if(turret1Dead){
			if(TimeUtils.nanoTime()-turret1DeathTime>TURRET_REPAIR_TIME){
				turret1.reset();
				getParent().addActor(turret1);
				turret1Dead = false;
			}
		}
		if(!turret2Dead){
			if(!turret2.isAlive()){
				turret2DeathTime = TimeUtils.nanoTime();
				turret2Dead = true;
				getParent().addActor(new ExplosionEffect(turret2.getX(),turret2.getY(),MiniTurretShip.WIDTH,MiniTurretShip.HEIGHT));
				hit(TURRET_EXPLOSION_DAMAGE);
			}	
		}
		if(turret2Dead){
			if(TimeUtils.nanoTime()-turret2DeathTime>TURRET_REPAIR_TIME){
				turret2.reset();
				getParent().addActor(turret2);
				turret2Dead = false;
			}
		}
		if(getX() >0 && getY()<550 && TimeUtils.nanoTime()-spawnTimer > SPAWN_TIMER){
			int shipType = MathUtils.random(1, 4);
			switch(shipType){
			case 1:
				getParent().addActor(new TargetLaserShip(getX()+130,getY(),playerShip));
				spawnTimer = TimeUtils.nanoTime();
				break;			
			case 2:
				getParent().addActor(new KamikazeShip(getX()+150,getY(),playerShip));
				spawnTimer = TimeUtils.nanoTime();
				break;
			case 3:
				getParent().addActor(new TargetLaserShip(getX()+200,getY(),playerShip));
				spawnTimer = TimeUtils.nanoTime();
				break;
			case 4:
				getParent().addActor(new KamikazeShip(getX()+50,getY(),playerShip));
				spawnTimer = TimeUtils.nanoTime();
				break;
			default:
				break;
			}						
		}
		if(reachedPosX && reachedPosY){
			setX(100+(ROTATION_RADIAN*(float)Math.cos(Math.toRadians(degrees))));
			setY(500+(ROTATION_RADIAN*(float)(Math.abs(Math.sin(Math.toRadians(degrees))))));
			degrees = degrees+ROTATION_SPEED;
		}
	}


	/**
	 * On hit logic
	 */
	@Override
	public int hit(int damage) {
		if(damage == TURRET_EXPLOSION_DAMAGE){
			currentHealth = currentHealth - TURRET_EXPLOSION_DAMAGE;
		}
		if (currentHealth<=0){
			destroyShip();
			return scoreValue;
		}
		return 0;
	}
	
	//// Unused methods ////
	@Override protected void shoot(float delta) {}
}
