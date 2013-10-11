package ships;

import assets.ImageAssets;

/**
 * Enemy ship that attempts to ram player ship
 * @author Group 9
 */
public class KamikazeShip extends EnemyShip {
	
	public final static int HEIGHT = 45;
	public final static int WIDTH = 35;
	private final static float SHIPSPEED_X = 0.8f;
	private final static float SHIPSPEED_Y = 3f;
	private final static int HEALTH=2;
	private final static int SCOREVALUE=15;
	private final static int DAMAGE_WHEN_RAMMED = 40;
	private static final boolean DISABABLE = true;
	
	private PlayerShip playerShip;
	
	/**
	 * Constructor
	 * @param x x-led spawn location
	 * @param y y-led spawn location
	 * @param playerShip reference to player ship needed for targeting
	 */
	public KamikazeShip(float x, float y, PlayerShip playerShip){
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyKamikazeShip, 
				DAMAGE_WHEN_RAMMED, DISABABLE);		
		this.playerShip = playerShip;
	}
	
	/**
	 * Moves ship towards playerShip position 
	 */
	@Override
	protected void move(float delta) {
		if(playerShip.getX()>getX()){
			setX(getX()+SHIPSPEED_X);
		}else if(playerShip.getX()<getX()){
			setX(getX()-SHIPSPEED_X);
		}
		setY(getY()-SHIPSPEED_Y);	
	}

	@Override protected void shoot(float delta) {}
}
