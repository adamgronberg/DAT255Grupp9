package ships;

import com.badlogic.gdx.math.MathUtils;

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
	private final static int SCOREVALUE=0;
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

	@Override
	protected void move(float delta) {
		setY(getY()-SHIPSPEED);
	}

	@Override protected void shoot(float delta) {}

	@Override
	public void uppgrade(int boast) {
		// TODO Auto-generated method stub
		
	}

}
