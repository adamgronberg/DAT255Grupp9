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
	private final static float SHIPSPEED=2.5f;
	private final static int HEALTH=2;
	private final static int SCOREVALUE=1;
	
	public final static int HEIGHT=40;
	public final static int WIDTH=30;
	private static final int DAMAGE_WHEN_RAMMED = 15;

	
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public BasicShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT, HEALTH, SCOREVALUE, ImageAssets.enemyBasicShip, DAMAGE_WHEN_RAMMED);
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
