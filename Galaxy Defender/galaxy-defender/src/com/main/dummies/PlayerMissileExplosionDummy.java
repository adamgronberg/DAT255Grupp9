package dummies;

import weapons.TargetTypes;

/**
 * 
 * @author Grupp9
 * The dummy created for PlayerMissile
 *
 */
public class PlayerMissileExplosionDummy extends AreaOfEffectDummy{

	private static final int AREA_OF_EFFECT_DAMAGE = 1;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY, TargetTypes.PROJECTILE};
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public PlayerMissileExplosionDummy(float x, float y, float width, float height) {
		super(x, y, width, height, AREA_OF_EFFECT_DAMAGE);
	}

	/**
	 * Gets the affected targets
	 */
	@Override
	public TargetTypes[] getTargetTypes() {
		return AFFECTEDTARGETS;
	}

}
