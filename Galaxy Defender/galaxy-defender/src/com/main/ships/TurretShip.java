package ships;

import spacegame.GameScreen;
import weapons.EnemyLaser;
import weapons.TurretShipBomb;
import assets.ImageAssets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

public class TurretShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 2500000000f; 	 //In nanoseconds
	public static final float RATEOFBOMB = 4000000000f;
	public final static int HEIGHT=150;
	public final static int WIDTH=110;
	private final static float SHIPSPEED = 1f;
	private final static int SCOREVALUE=30;
	private final static int HEALTH=100;
	
	private boolean left = false;
	private boolean up = true;
	private boolean right = true;
	private boolean down = false;
	
	private float lastProjectileTime;
	private float lastBombTime;
	private static final int DAMAGE_WHEN_RAMMED = 40;
	

	private Animation onFireAnimation; 
	private static final float TIMEPERFRAME = 0.045f;

	private TextureRegion currentFrame;
	private float stateTime;

	public TurretShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyTurretShip, DAMAGE_WHEN_RAMMED);
		setRotation(-180);

		onFireAnimation = new Animation(TIMEPERFRAME, ImageAssets.fireAnimation);
		stateTime = 0f;
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
		if(left && down){
			setX(getX()-SHIPSPEED);
			setY(getY()-SHIPSPEED);
		}
		if(left && up){
			setX(getX()-SHIPSPEED);
			setY(getY()+SHIPSPEED);
		}
		if(right && down){
			setX(getX()+SHIPSPEED);
			setY(getY()-SHIPSPEED);
		}
		if(right && up){
			setX(getX()+SHIPSPEED);
			setY(getY()+SHIPSPEED);
		}
		if(getX()<0){
			left=false;
			right = true;
		}
		if(getX()>GameScreen.GAME_WITDH-WIDTH){
			left=true;
			right = false;
		}
		if(getY()<550){
			up=true;
			down=false;
		}
		if(getY()>700){
			up=false;
			down = true;
		}
	}

	@Override
	protected void shoot(float delta) {
		spawnProjectile();
	}
	
	
	/**
	 * Adds a fire in the back of the ship
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		currentFrame = onFireAnimation.getKeyFrame(stateTime, true);
        	drawDamagedAnimation(batch, parentAlpha, currentFrame);
        
	}
	
	/**
	 * Draws fire animation
	 * @param batch
	 * @param parentAlpha
	 * @param currentFrame
	 */
	@Override
	protected void drawDamagedAnimation(SpriteBatch batch, float parentAlpha, TextureRegion currentFrame){
    	batch.draw(currentFrame, getX()+WIDTH, getY()-HEIGHT, 5, HEIGHT/2, 60, 60, 1, 1, getRotation());
    	batch.draw(currentFrame, getX(), getY()-HEIGHT, 25, HEIGHT/2, 60, 60, 1, 1, getRotation());
	}
}
