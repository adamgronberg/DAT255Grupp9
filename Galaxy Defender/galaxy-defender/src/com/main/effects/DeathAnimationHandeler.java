package effects;

import assets.SoundAssets;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import screens.OptionsScreen;
import spacegame.MovableEntity;

public class DeathAnimationHandeler extends MovableEntity {

	private Array<ExplosionEffect> animations;
	private static final int NUMBER_OF_EXPLOSIONS = 11;
	private int currentExposion = 0;
	private static final float TIME_BETWEEN_EXPLOSIONS = 400000000f;
	private float currentTimeBetweenExplosion = 0;
	private float ExplosionSize = 50;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public DeathAnimationHandeler(float width, float height, float x, float y) {
		super(width, height, x, y);
		animations = new Array<ExplosionEffect>();
		for(int i = 0; i < NUMBER_OF_EXPLOSIONS-1; i++){
			Vector2 ranPos = randomPosition();
			animations.add(new ExplosionEffect(getX() + ranPos.x-(ExplosionSize/2), 
					getY() + ranPos.y-(ExplosionSize/2), ExplosionSize, ExplosionSize));
		}
		animations.add(new ExplosionEffect(getX(), 
				getY(), width, height));
		
	}
	
	/**
	 * Updates its linger time
	 */
	@Override
	public void act(float delta){
		if(TimeUtils.nanoTime()-currentTimeBetweenExplosion>TIME_BETWEEN_EXPLOSIONS){
			if(OptionsScreen.getSound()) SoundAssets.bossExplosion.play();
			getParent().addActor(animations.get(currentExposion));
			currentExposion++;
			if(currentExposion == NUMBER_OF_EXPLOSIONS) {
				remove();
			}
			currentTimeBetweenExplosion = TimeUtils.nanoTime();
		}
	}

	/**
	 * Random fire position
	 */
	private Vector2 randomPosition(){
		return new Vector2(MathUtils.random(0, getWidth()), 
				MathUtils.random(0, getHeight()));
	}
}
