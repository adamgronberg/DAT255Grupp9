package ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	public final static int WIDTH = 30;
	private final static float SHIPSPEED_X = 2.0f;
	private final static float SHIPSPEED_Y = 2.0f;
	private final static int HEALTH=2;
	private final static int SCOREVALUE=2;
	private final static int DAMAGE_WHEN_RAMMED = 10;
	
	private boolean movingLeft;
	private final float movmentLenght = 50;
	private float currentMovmentLenght;

	
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
		super.act(delta);
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
	
	protected void drawDamagedAnimation(SpriteBatch batch, float parentAlpha, TextureRegion currentFrame){
		if(movingLeft){
			batch.draw(currentFrame, getX()+20, getY()-0, 
    			getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
		}
		else{
	    	batch.draw(currentFrame, getX()-0, getY()+20, 
	    			getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
		}
	}
}
