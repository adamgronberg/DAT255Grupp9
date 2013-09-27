package effects;

import assets.ImageAssets;

/**
 * 
 * @author Grupp9
 * Plays a animation designed for missile
 */
public class EnemyLaserEffect extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.015f;
	
	public EnemyLaserEffect(float x, float y, float width, float height) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.enemyLaserAnimation);
	}
}
