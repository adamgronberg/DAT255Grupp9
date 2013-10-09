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
	
	public final static int HEIGHT = 35;
	public final static int WIDTH = 35;
	private final static float SHIPSPEED_X = 1.5f;
	private final static float SHIPSPEED_Y = 2f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=2;
	private final static int DAMAGE_WHEN_RAMMED = 10;
	
	private boolean movingLeft;
	private final float movmentLenght = 50;
	private float currentMovmentLenght;
	private static final boolean DISABABLE = true;

	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ScoutShip(float x, float y){
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyScoutShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);
		currentMovmentLenght = 0;
		movingLeft = true;
	}

	/**
	 * Ship move function
	 */
	@Override
	protected void move(float delta) {
		if(movingLeft){
			if(getX() < 0 || currentMovmentLenght > movmentLenght){
				movingLeft = false;
				currentMovmentLenght = 0;
			}
			setX(getX()-SHIPSPEED_X);
		}
		else {
			if(getX() > GameScreen.GAME_WITDH-WIDTH || currentMovmentLenght > movmentLenght){
				movingLeft = true;
				currentMovmentLenght = 0;
			}
			setX(getX()+SHIPSPEED_X);
		}
		currentMovmentLenght++;
		setY(getY()-SHIPSPEED_Y);
	}

	@Override protected void shoot(float delta) {}

	@Override
	public void upgrade(int boast) {
		// TODO Auto-generated method stub
		
	}
}
