package ships;

import assets.ImageAssets;

/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *  Only has y led moment
 *
 */
public class BasicShip extends EnemyShip {
	private final static float SHIPSPEED=2f;
	private final static int HEALTH=2;
	private final static int SCOREVALUE=1;
	
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int DAMAGE_WHEN_RAMMED = 15;

	
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public BasicShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBasicShip, DAMAGE_WHEN_RAMMED);
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
	/**
	 *  removes the ship
	 */
	public void addOnHitEffect() {
		remove();
	}
}
