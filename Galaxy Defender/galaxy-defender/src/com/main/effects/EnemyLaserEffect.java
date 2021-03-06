package effects;

import assets.ImageAssets;

/**
 * Plays a animation designed for enemy laser
 * @author Grupp9
 */
public class EnemyLaserEffect extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.015f;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public EnemyLaserEffect(float x, float y, float width, float height, boolean loop) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.enemyLaserAnimation);
		loopFrames = loop;
	}
}
