package effects;

import assets.ImageAssets;

/**
 * Plays a animation designed fire (plays when a enemy is damaged)
 * @author Grupp9
 */
public class FireAnimation extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.020f;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public FireAnimation(float x, float y, float width, float height) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.fireAnimation);
	}
}