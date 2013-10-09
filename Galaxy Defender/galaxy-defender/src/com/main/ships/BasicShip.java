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
	private static final boolean DISABABLE = true;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public BasicShip(float x, float y) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyBasicShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);
	}

	/**
	 * Ship move function
	 */
	@Override
	protected void move(float delta) {
		setY(getY()-SHIPSPEED);
		
	}

	@Override
	protected void shoot(float delta) {}

	@Override
	public void upgrade(int boast) {
		
		
	}
}
