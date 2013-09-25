package effects;

import assets.ImageAssets;

public class ExplosionEffect extends AnimatedAreaEffect{

	private static final float TIMEPERFRAME = 0.025f;
	
	public ExplosionEffect(float x, float y, float width, float height) {
		super(x, y, width, height, TIMEPERFRAME, ImageAssets.explosionAnimation);
	}
}
