package ships;

import assets.ImageAssets;
import spacegame.GameScreen;

/**
 * 
 * @author Grupp9
 *
 *	A ship that moves in y led as well as x led
 */
public class ScoutShip extends EnemyShip{
	
	public final static int HEIGHT = 40;
	public final static int WIDTH = 35;
	private final static float SHIPSPEED_X = 2.0f;
	private final static float SHIPSPEED_Y = 2.5f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=2;
	
	private boolean movingLeft;
	private final float movmentLenght = 50;
	private float currentMovmentLenght;
	private static final int DAMAGE_WHEN_RAMMED = 10;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ScoutShip(float x, float y){
		super(x, y, WIDTH, HEIGHT, HEALTH, SCOREVALUE, ImageAssets.enemyScoutShip, DAMAGE_WHEN_RAMMED);
		currentMovmentLenght = 0;
		movingLeft = true;
		setRotation(-45);
	}
		
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-SHIPSPEED_Y);
		if(movingLeft){
			if(getX() < 0 || currentMovmentLenght > movmentLenght){
				setRotation(45);
				movingLeft = false;
				currentMovmentLenght = 0;
			}
			setX(getX()-SHIPSPEED_X);
		}
		else {
			if(getX() > GameScreen.GAME_WITDH-WIDTH || currentMovmentLenght > movmentLenght){
				setRotation(-45);
				movingLeft = true;
				currentMovmentLenght = 0;
			}
			setX(getX()+SHIPSPEED_X);
		}
		currentMovmentLenght++;
	}
	/**
	 *  removes the ship
	 */
	public void addOnHitEffect() {
		remove();
	}
}
