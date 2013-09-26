package weapons;

import assets.*;

/**
 * 
 * @author Grupp9
 * 
 * Player standard green laser
 *
 */
public class PlayerLaser extends Projectile {
	
	public static final float RATEOFFIRE = 300000000f; //In nanoseconds
	private static final float SPEED = 6f;
	public static final float HEIGHT = 10;
	public static final float WIDTH = 5;
	private static final int DAMAGEONHIT = 1;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerLaser(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGEONHIT, ImageAssets.laser);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()+SPEED);
		super.act(delta);
	}

	/**
	 * Removes as actor
	 */
	@Override
	public void addOnHitEffect() {
		remove();
	}

	/**
	 * Affected targets
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
