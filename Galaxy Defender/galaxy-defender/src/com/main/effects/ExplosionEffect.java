package effects;

import assets.ImageAssets;

/**
 * Plays a animation designed for missile
 * @author Grupp9
 */
public class ExplosionEffect extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.025f;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ExplosionEffect(float x, float y, float width, float height) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.explosionAnimation);
	}
}
