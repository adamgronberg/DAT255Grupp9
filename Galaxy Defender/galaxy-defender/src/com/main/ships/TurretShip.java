package ships;

import spacegame.GameScreen;
import weapons.EnemyLaser;
import weapons.TurretShipBomb;
import assets.ImageAssets;

import com.badlogic.gdx.utils.TimeUtils;

public class TurretShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 2500000000f; 	 //In nanoseconds
	public static final float RATEOFBOMB = 4000000000f;
	public final static int HEIGHT=150;
	public final static int WIDTH=110;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=30;
	private final static int HEALTH=100;
	
	private static final boolean DISABABLE = true;
	private boolean left;
	
	private float lastProjectileTime;
	private float lastBombTime;
	private static final int DAMAGE_WHEN_RAMMED = 40;

	public TurretShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyTurretShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);
		setRotation(-180);
	}
	
	/**
	 * Spawns projectiles from all guns
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX(), getY()+10, 2f, 15f,5,-20));
			getParent().addActor( new EnemyLaser(getX()+WIDTH/8, getY()+5, 2f, 15f,5,-20));
			getParent().addActor( new EnemyLaser(getX()+WIDTH/4, getY(), 2f, 15f,5,0));
			getParent().addActor( new EnemyLaser(getX()+3*WIDTH/4, getY(), 2f, 15f,5,0));
			getParent().addActor( new EnemyLaser(getX()+7*WIDTH/8, getY()+5, 2f, 15f,5,20));
			getParent().addActor( new EnemyLaser(getX()+WIDTH, getY()+10, 2f, 15f,5,20));
			lastProjectileTime = TimeUtils.nanoTime();
		}
		
		if(TimeUtils.nanoTime() - lastBombTime > RATEOFBOMB){
			getParent().addActor( new TurretShipBomb(getX()+WIDTH/2-TurretShipBomb.WIDTH/2,getY()));
			
			lastBombTime = TimeUtils.nanoTime();
		}
	}

	@Override
	protected void move(float delta) {
		
		if(left){
			setX(getX()-SHIPSPEED);
		}
		if(!left){
			setX(getX()+SHIPSPEED);
		}
		if(getX()<0){
			setX(getX()+SHIPSPEED);	
			left=false;
		}
		
		if(getX()>GameScreen.GAME_WITDH-WIDTH){
			setX(getX()-SHIPSPEED);	
			left=true;
		}
	}

	@Override
	protected void shoot(float delta) {
		spawnProjectile();
	}

	@Override
	public void upgrade(int boast) {
		// TODO Auto-generated method stub
		
	}
}
