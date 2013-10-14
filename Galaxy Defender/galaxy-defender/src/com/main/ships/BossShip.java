package ships;

import com.badlogic.gdx.utils.TimeUtils;
import effects.ExplosionEffect;
import assets.ImageAssets;

public class BossShip extends EnemyShip{
	
	public final static int HEIGHT=180;
	public final static int WIDTH=300;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=300;
	private final static int HEALTH=1000;
	private static final int DAMAGE_WHEN_RAMMED = 40;
	private static final boolean DISABLEABLE = false;
	private static final float TURRET_REPAIR_TIME = 8000000000f;
	private PlayerShip playerShip;
	private boolean turret1Dead,turret2Dead,turretsSpawnedOnce = false;
	private float turret1DeathTime,turret2DeathTime;
	private MiniTurretShip turret1,turret2;

	public BossShip(float x, float y,PlayerShip playerShip) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBossShip, DAMAGE_WHEN_RAMMED,
				DISABLEABLE);
		this.playerShip = playerShip;
	}

	@Override
	protected void move(float delta) {
		if(!turretsSpawnedOnce){
			turret1 = new MiniTurretShip(getX()+60,getY(),playerShip);
			turret2 = new MiniTurretShip(getX()+170,getY(),playerShip);
			getParent().addActor(turret1);
			getParent().addActor(turret2);
			turretsSpawnedOnce = true;
		}		
		if(getY()>500)setY(getY()-SHIPSPEED);
		if(getX()<100)setX(getX()+SHIPSPEED);
		turret1.setPosition(getX()+60, getY());
		turret2.setPosition(getX()+170, getY());
		if(!turret1Dead){
			if(!turret1.isAlive()){
				turret1DeathTime = TimeUtils.nanoTime();
				turret1Dead = true;
				getParent().addActor(new ExplosionEffect(turret1.getX(),turret1.getY(),MiniTurretShip.WIDTH,MiniTurretShip.HEIGHT));
				hit(40);
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
				hit(40);
			}	
		}
		if(turret2Dead){
			if(TimeUtils.nanoTime()-turret2DeathTime>TURRET_REPAIR_TIME){
				turret2.reset();
				getParent().addActor(turret2);
				turret2Dead = false;
			}
		}
	}

	@Override
	protected void shoot(float delta) {	
	}

	@Override
	public int hit(int damage) {
		currentHealth = currentHealth - damage;
		if (currentHealth<=0){
			destroyShip();
			return scoreValue;
		}
		return 0;
	}
}
