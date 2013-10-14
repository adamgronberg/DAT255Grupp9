package ships;

import spacegame.GameLogic;
import spacegame.GameScreen;
import weapons.EnemyLaser;
import weapons.TurretShipBomb;
import assets.ImageAssets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 * Big Ship with a little ship on it.
 *
 */
public class MiniBossShip extends EnemyShip {
	
	public static final float RATEOFFIRE = 2500000000f; 	 //In nanoseconds
	public static final float RATEOFBOMB = 4000000000f;
	public static final float RANDOMTIME = 3000000000f;
	public static final int HEIGHT=150;
	public static final int WIDTH=110;
	
	private static final float SHIPSPEED = 1f;
	private static final int SCOREVALUE=30;
	private static final int HEALTH=100;
	private static final int DAMAGE_WHEN_RAMMED = 0;
	private static final boolean DISABABLE = false;
	private static final float LOWER_Y_CAP = 550;
	private static final float HIGHER_Y_CAP = 650;
	
	private static final int SIDE_LASER_DAMAGE = 8;
	private static final float SIDE_LASER_HEIGHT = 15f;
	private static final float SIDE_LASER_WIDTH = 2f;
	private static final float SIDE_LASER_ROTATION = 20f;
	private static final float SIDE_LASER_POSITION_X = 10;
	
	private static final int FRONT_LASER_DAMAGE = 8;
	private static final float FRONT_LASER_HEIGHT = 25f;
	private static final float FRONT_LASER_WIDTH = 4f;
	
	private static final Rectangle LEFT_SIDE = new Rectangle(0,0, GameScreen.GAME_WITDH/2, GameScreen.GAME_HEIGHT);
	
	private MiniTurretShip miniTurretShip;
	private boolean movingRight = true;
	private boolean movingUp = true;
	private float lastProjectileTime;
	private float lastRandomTime;
	private float lastBombTime = TimeUtils.nanoTime();
	private boolean isAlive = true;
	private boolean turretSpawned = false;
	private GameLogic gameLogic;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param playerShip
	 */
	public MiniBossShip(float x, float y, GameLogic gameLogic) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyTurretShip, DAMAGE_WHEN_RAMMED, DISABABLE);
		this.gameLogic = gameLogic;
	}
	
	/**
	 * Spawns projectiles from all guns
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastProjectileTime > RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX(), getY()+SIDE_LASER_POSITION_X, SIDE_LASER_WIDTH,
					SIDE_LASER_HEIGHT,SIDE_LASER_DAMAGE ,-SIDE_LASER_ROTATION));
			getParent().addActor( new EnemyLaser(getX()+WIDTH/8, getY()+SIDE_LASER_POSITION_X/2, SIDE_LASER_WIDTH,
					SIDE_LASER_HEIGHT, SIDE_LASER_DAMAGE,-SIDE_LASER_ROTATION));
			
			getParent().addActor( new EnemyLaser(getX()+WIDTH, getY()+SIDE_LASER_POSITION_X, SIDE_LASER_WIDTH,
					SIDE_LASER_HEIGHT, SIDE_LASER_DAMAGE, SIDE_LASER_ROTATION));
			getParent().addActor( new EnemyLaser(getX()+7*WIDTH/8, getY()+SIDE_LASER_POSITION_X/2, SIDE_LASER_WIDTH,
					SIDE_LASER_HEIGHT, SIDE_LASER_DAMAGE, SIDE_LASER_ROTATION));

			getParent().addActor( new EnemyLaser(getX()+WIDTH/4-FRONT_LASER_WIDTH/2, getY(), FRONT_LASER_WIDTH,
					FRONT_LASER_HEIGHT, FRONT_LASER_DAMAGE,0));
			getParent().addActor( new EnemyLaser(getX()+3*WIDTH/4-FRONT_LASER_WIDTH/2, getY(), FRONT_LASER_WIDTH,
					FRONT_LASER_HEIGHT, FRONT_LASER_DAMAGE,0));
			
			lastProjectileTime = TimeUtils.nanoTime();
		}
	
		if(TimeUtils.nanoTime() - lastBombTime > RATEOFBOMB){
			getParent().addActorBefore(this, new TurretShipBomb(getX()+WIDTH/2-TurretShipBomb.WIDTH/2,getY()));
			lastBombTime = TimeUtils.nanoTime();
		}
	}
	
	/**
	 * Ships move pattern
	 */
	@Override
	protected void move(float delta) {
		
		if(!turretSpawned){
			miniTurretShip = new MiniTurretShip(getX(),getY(),gameLogic.playerShip);
			getParent().addActor(miniTurretShip);
			turretSpawned = true;
		}
		
		if(miniTurretShip.isAlive()){
			miniTurretShip.setPosition(getX(), getY());
		}
		
		if(TimeUtils.nanoTime()-lastRandomTime>RANDOMTIME){
			movingRight = randomBoolean();
			movingUp = randomBoolean();
			lastRandomTime =TimeUtils.nanoTime();
			
			int numberOnLeftSide = 0, numberOnRightSide = 0;
			for(EnemyShip enemy: gameLogic.getEnemies()){
				if(LEFT_SIDE.contains(enemy.getBounds())) numberOnLeftSide++;
				else numberOnRightSide++;
			}
			
			if(LEFT_SIDE.contains(getBounds()))numberOnLeftSide -= 2;
			else numberOnRightSide -= 2;
			
			if (numberOnLeftSide > numberOnRightSide) movingRight = false;
			else if (numberOnLeftSide < numberOnRightSide) movingRight = true;
		}
		
		if(getX()<0) movingRight = true;
		else if(getX()>GameScreen.GAME_WITDH-WIDTH) movingRight = false;

		if(getY()<LOWER_Y_CAP) movingUp = true;
		else if(getY()>HIGHER_Y_CAP) movingUp = false;
		
		if(movingRight) setX(getX()+SHIPSPEED);
		else setX(getX()-SHIPSPEED);
		
		if(movingUp) setY(getY()+SHIPSPEED);
		else setY(getY()-SHIPSPEED);
		

	}

	/**
	 * Spawns the ships projectile
	 */
	@Override
	protected void shoot(float delta) {
		spawnProjectile();
	}
	
	/**
	 * Takes only damage after the turret on the ship is dead
	 */
	@Override
	public int hit(int damage) {
		if(miniTurretShip.isAlive()) return 0;
		currentHealth = currentHealth - damage;
		if (currentHealth<=0){
			destroyShip();
			return scoreValue;
		}
		return 0;
	}

	/**
	 * @return true if ship is alive
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	/**
	 *  Removes the ship
	 */
	@Override
	public void destroyShip() {
		isAlive = false;
		remove();
	}
	
	/**
	 * Help function - Used to random ship direction
	 * @return a random boolean
	 */
	private static boolean randomBoolean(){
		return Math.random() < 0.5;
	}
	
//	/**	//TODO: Make this a seperate animation
//	 * Adds a fire in the back of the ship
//	 */
//	@Override
//	public void draw(SpriteBatch batch, float parentAlpha) {
//		super.draw(batch, parentAlpha);
//		currentFrame = onFireAnimation.getKeyFrame(stateTime, true);
//       drawDamagedAnimation(batch, parentAlpha, currentFrame);
//	}
}
