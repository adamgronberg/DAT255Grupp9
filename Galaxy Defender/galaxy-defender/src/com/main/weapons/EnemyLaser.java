package weapons;

import spacegame.MovableEntity;
import effects.EnemyLaserEffect;
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
	public static final float HEIGHT = 25;
	public static final float WIDTH = 3;
	private static final int DAMAGE_ON_HIT = 15;
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};
	private static final float AREAEFFECT_H = 20;
	private static final float AREAEFFECT_W = 20;
	
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
	public void addOnHitEffect(MovableEntity entity) {
		getParent().addActor( new EnemyLaserEffect(getX()-AREAEFFECT_W/2+WIDTH/2, 
				getY()-AREAEFFECT_H/2, AREAEFFECT_W, AREAEFFECT_H));
		remove();
	}

	/**
	 * Different target the projectile affects
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
