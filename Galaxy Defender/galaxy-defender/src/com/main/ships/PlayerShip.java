package ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import assets.ImageAssets;
import spacegame.GameScreen;
import spacegame.Sprite;
import weapons.PlayerLaser;
import weapons.PlayerMissile;

/**
 * 
 * @author Grupp9
 *
 * The player ship. Methods for moving ,drawing and shooting
 */
public class PlayerShip extends Sprite {
	
	public static enum Direction {LEFT, RIGHT, NONE;}
	private static enum AvailableWeapons {MISSILE, LASER;} 	//Implemented weapons
	private static final int WITDH = 35;						// The size of the player
	private static final int HEIGHT = 40;					// The size of the player
	private static final float SPEED = 4;					// number of pixels the player moves every act
	private static final float SPAWN_LOCATION_Y = 0.1f; //Height where the player moves
	private static final int STARTING_HEALTH  = 100;
	
	private int maximumHealth;
	private int currentHealth;
	
	private float lastLaserTime = 0;      		//For testing
	private float lastMissileTime = 0;      	//For testing
	private boolean missileReady = true;		//For testing
	
	private Direction movmentDirection = Direction.NONE;
	private AvailableWeapons currentWeapon = AvailableWeapons.LASER;
	
	
	/**
	 * Constructor
	 */
	public PlayerShip() {
		super(WITDH, HEIGHT, GameScreen.GAME_WITDH/2-WITDH/2, GameScreen.GAME_HEIGHT*SPAWN_LOCATION_Y,ImageAssets.playerShip);
		maximumHealth = STARTING_HEALTH;
	}
	
	/**
	 * @return player current health
	 */
	public int getCurrnetHealth(){
		return currentHealth;
	}
	
	/**
	 * Resets player health to its maximum health
	 */
	public void resetHealth(){
		currentHealth = maximumHealth;
	}
	
	/**
	 * Decreases player current health with damage
	 * @param damage the damage to decrease player current health with
	 */
	public void decreaseCurrentHealth(int damage){
		currentHealth = currentHealth - damage;
	}
	
	/**
	 * @return player maximum health
	 */
	public int getMaximumHealth(){
		return maximumHealth;
	}

	/**
	 * Makes sure Player is always above everything else on screen 
	 * (Looks weird when weapons come from above it)
	 */
	public void drawAbove(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(ImageAssets.playerShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
		
	/**
	 * Tries to move the player to the Right. 
	 * If the player is out of screen he will be moved back
	 */
	public void moveRight() {
		movmentDirection = Direction.RIGHT;
	}
	
	/**
	 * Tries to move the player to the left. 
	 * If the player is out of screen he will be moved back
	 */
	public void moveLeft() {
		movmentDirection = Direction.LEFT;
	}
	
	/**
	 * Stops the player from moving
	 */
	public void stay(){
		movmentDirection = Direction.NONE;
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		if (GameScreen.optionAutoShoot) spawnPlayerProjectile();
		switch(movmentDirection){
			case LEFT:
				setX(getX()-SPEED);
				break;
			case RIGHT:
				setX(getX()+SPEED);
				break;
			case NONE:
				break;
			
		}
		if(TimeUtils.nanoTime() - lastMissileTime > PlayerMissile.RATEOFFIRE) missileReady = true;
		if(getX()<0) setX(0);
		else if(getX()>GameScreen.GAME_WITDH-WITDH) setX(GameScreen.GAME_WITDH-WITDH);	
	}
	
	/**
	 * @return	What X direction the player is moving in
	 */
	public Direction getMovmentDirection(){
		return movmentDirection;
	}
	
	/**
	 * Spawns player projectiles
	 */
	public void spawnPlayerProjectile() {
		if(currentWeapon == AvailableWeapons.LASER && TimeUtils.nanoTime() - lastLaserTime > PlayerLaser.RATEOFFIRE) {
			getParent().addActor( new PlayerLaser(getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH/2, getY()+PlayerShip.HEIGHT));
			lastLaserTime = TimeUtils.nanoTime();
		}
	}
	
	public void shootMissle(){
		if (missileReady) {
			getParent().addActor(new PlayerMissile(getX()+PlayerShip.WITDH/2-PlayerMissile.WIDTH/2, getY()));
			lastMissileTime = TimeUtils.nanoTime();
			missileReady = false;
		}
	}
	
	public boolean getMissileReady(){
		return missileReady;
	}
	
	/**
	 * Swaps players weapon
	 */
	public void switchWeapon(){
		if(currentWeapon == AvailableWeapons.LASER) { 
			currentWeapon = AvailableWeapons.MISSILE;
		}
		else if(currentWeapon == AvailableWeapons.MISSILE) { 
			currentWeapon = AvailableWeapons.LASER;
		}
	}
}
