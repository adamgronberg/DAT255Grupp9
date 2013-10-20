package effects;

import assets.ImageAssets;

/**
 * Plays a animation designed for laser on hit effect
 * @author Grupp9
 */
public class PlayerLaserEffect extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.020f;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public PlayerLaserEffect(float x, float y, float width, float height) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.playerLaserAnimation);
	}
}
