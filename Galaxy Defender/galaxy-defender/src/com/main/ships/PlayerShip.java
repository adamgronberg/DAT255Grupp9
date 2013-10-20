package ships;

import options.OptionLogic;
import com.badlogic.gdx.Gdx;
import assets.ImageAssets;
import screens.GameScreen;
import spacegame.GameLogic;
import spacegame.Sprite;
import weapons.PlayerWeaponLogic;

/**
 * The player ship. Methods for moving ,drawing and more...
 * @author Grupp9
 */
public class PlayerShip extends Sprite {
	
	public static enum Direction {LEFT, RIGHT, NONE;}
	public static final int WITDH = 35;						// The size of the player
	public static final int HEIGHT = 40;					// The size of the player
	private static final float SPEED = 4;					// number of pixels the player moves every act
	private static final float SPAWN_LOCATION_Y = 0.1f; 	//Height where the player moves
	private static final int STARTING_HEALTH  = 100;
	
	private PlayerWeaponLogic weaponHandeler;
	private int maximumHealth;
	private int currentHealth;		
	
	private Direction movmentDirection = Direction.NONE;
	
	/**
	 * Constructor
	 */
	public PlayerShip(GameLogic gameLogic) {
		super(WITDH, HEIGHT, GameScreen.GAME_WITDH/2-WITDH/2, GameScreen.GAME_HEIGHT*SPAWN_LOCATION_Y,ImageAssets.playerShip);
		weaponHandeler = new PlayerWeaponLogic(gameLogic, this);
		maximumHealth = STARTING_HEALTH;
		currentHealth = STARTING_HEALTH;
	}
	
	/**
	 * Constructor
	 * Ship without weapons
	 */
	public PlayerShip() {
		super(WITDH, HEIGHT, GameScreen.GAME_WITDH/2-WITDH/2, GameScreen.GAME_HEIGHT*SPAWN_LOCATION_Y,ImageAssets.playerShip);
		maximumHealth = STARTING_HEALTH;
		currentHealth = STARTING_HEALTH;
	}
	
	/**
	 * @return player current health
	 */
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	/**
	 * Resets player health to its maximum health
	 */
	public void resetHealth(){
		currentHealth = maximumHealth;
	}
	
	/**
	 * Decreases player current health with damage and vibrates
	 * @param damage the damage to decrease player current health with
	 */
	public void decreaseCurrentHealth(int damage){
		currentHealth = currentHealth - damage;
		vibrate(100);
	}
	
	/**
	 * @return player maximum health
	 */
	public int getMaximumHealth(){
		return maximumHealth;
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
		weaponHandeler.spawnPlayerProjectile();
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
	 * Vibrates the android device for the specified number of milliSeconds
	 * @param milliSeconds
	 */
	public void vibrate(int milliSeconds){
		if (OptionLogic.getVibrateOption()) Gdx.input.vibrate(milliSeconds);
	}
	
	/**
	 * @return The player weaponHandeler
	 */
	public PlayerWeaponLogic getWeaponHandeler(){
		return weaponHandeler;
	}
}
