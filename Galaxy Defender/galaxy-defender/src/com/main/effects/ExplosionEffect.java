package effects;

import assets.ImageAssets;


/**
 * 
 * @author Grupp9
 * Plays a animation designed for missile
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
