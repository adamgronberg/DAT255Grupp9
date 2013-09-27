package weapons;

import assets.ImageAssets;

/**
 * 
 * @author Grupp9
 * 
 * Enemy standard green laser
 *
 */

public class EnemyLaser extends Projectile {
	
	public static final float RATEOFFIRE = 2500000000f; //In nanoseconds
	private static final float SPEED = 6f;
	public static final float HEIGHT = 10;
	public static final float WIDTH = 3;
	private static final int DAMAGE_ON_HIT = 1;
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public EnemyLaser(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGE_ON_HIT, ImageAssets.enemyLaser);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-SPEED);
		super.act(delta);
	}
	
	/**
	 * Removes the laser
	 */
	@Override
	public void addOnHitEffect() {
		remove();
	}

	/**
	 * Different target the projectile affects
	 */
	@Override
	public TargetTypes[] getTargetTypes() {
		return AFFECTEDTARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}
