package weapons;

import effects.PlayerLaserEffect;
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
	public static final float WIDTH = 3;
	private static final int DAMAGEONHIT = 1;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	private static final float AREAEFFECT_H = 8;
	private static final float AREAEFFECT_W = 8;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerLaser(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGEONHIT, ImageAssets.playerLaser);
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
		getParent().addActor( new PlayerLaserEffect(getX()-AREAEFFECT_W/2+WIDTH/2, 
				getY()-AREAEFFECT_H/2, AREAEFFECT_W, AREAEFFECT_H));
		remove();
	}

	/**
	 * Affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}
