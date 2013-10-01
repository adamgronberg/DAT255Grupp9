package ships;


import assets.ImageAssets;
import dummies.BigLaserDummy;
import effects.EnemyLaserEffect;
import com.badlogic.gdx.utils.TimeUtils;

public class BigLaserShip extends EnemyShip {
	

	public static final float RATEOFFIRE = 5000000000f; 	 //In nanoseconds
	public static final float DAMAGE_TICK_RATE = 300000000f;
	public static final float FIRE_TIME = 2000000000;
	public final static int HEIGHT=65;
	public final static int WIDTH=40;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=30;
	private final static int HEALTH=5;	
	private static final int DAMAGE_WHEN_RAMMED = 40;
	
	private float lastProjectileTime;
	private float damageTickTime;
	private float fireTime;
	private boolean shooting;
	private BigLaserDummy bigLaserDummy;
	private EnemyLaserEffect enemyLaserEffect;

	public BigLaserShip(float x, float y){
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBigLaserShip, DAMAGE_WHEN_RAMMED);
	}
	
	
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			shooting = true;
			damageTickTime = TimeUtils.nanoTime();
			lastProjectileTime = TimeUtils.nanoTime();
			fireTime = TimeUtils.nanoTime();
			enemyLaserEffect = new EnemyLaserEffect(getX()+WIDTH/2,getY()-700,3f,700f, true);
			getParent().addActor(enemyLaserEffect);
		}
	}

	@Override
	protected void move(float delta) {
		if(!shooting)setY(getY()-SHIPSPEED);
		if(TimeUtils.nanoTime()-lastProjectileTime>RATEOFFIRE){
			spawnProjectile();
		}else if(shooting){
			if(TimeUtils.nanoTime()-damageTickTime>DAMAGE_TICK_RATE){
				bigLaserDummy = new BigLaserDummy(getX()+WIDTH/2,getY()-700,3f,700f,1);
				getParent().addActor(bigLaserDummy);
				damageTickTime = TimeUtils.nanoTime();
				
				if(TimeUtils.nanoTime()-fireTime > FIRE_TIME){
					getParent().removeActor(enemyLaserEffect);
					shooting = false;
				}
			}
		}
		
	}

	@Override protected void shoot(float delta) {}
}
