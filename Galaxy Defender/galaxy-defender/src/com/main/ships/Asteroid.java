package ships;

import com.badlogic.gdx.math.MathUtils;

import spacegame.GameScreen;
import assets.ImageAssets;

/**
 * 
 * @author Grupp9
 *
 *	Asteroids
 *  Only has y led moment
 *
 */

public class Asteroid extends EnemyShip {
	private final static float SHIPSPEED=2f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=1;
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int DAMAGE_WHEN_RAMMED = 15;
	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	*/
	public Asteroid(float x, float y){
		super(MathUtils.random(20,45),MathUtils.random(30,50), x, y, HEALTH, SCOREVALUE, ImageAssets.asteroids.get(MathUtils.random(0,11)), DAMAGE_WHEN_RAMMED);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		setY(getY()-SHIPSPEED);
	}

}
