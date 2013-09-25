package ships;

import assets.ImageAssets;
import spacegame.GameScreen;

/**
 * 
 * @author Grupp9
 *
 *
 *	A ship that moves in y led as well as x led
 */
public class ScoutShip extends EnemyShip{
	
	public final static int HEIGHT = 40;
	public final static int WIDTH = 40;
	private final static float SHIPSPEED_X = 2.0f;
	private final static float SHIPSPEED_Y = 2.5f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=2;
	
	private boolean movingLeft = true;
	private final float movmentLenght = 50;
	private float currentMovmentLenght;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ScoutShip(float x, float y){
		super(x, y, WIDTH, HEIGHT, HEALTH, SCOREVALUE, ImageAssets.enemyScoutShip);
		currentMovmentLenght = 0;
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
				currentMovmentLenght = 0;
				movingLeft = false;
			}
			setX(getX()-SHIPSPEED_X);
			currentMovmentLenght++;
		}
		else {
			if(getX() > GameScreen.GAME_WITDH-WIDTH || currentMovmentLenght > movmentLenght){
				setRotation(-45);
				currentMovmentLenght = 0;
				movingLeft = true;
			}
			setX(getX()+SHIPSPEED_X);
			currentMovmentLenght++;
		}
	}
}
