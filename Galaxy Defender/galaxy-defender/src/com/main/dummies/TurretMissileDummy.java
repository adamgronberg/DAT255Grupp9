package dummies;

import weapons.TargetTypes;

public class TurretMissileDummy extends AreaOfEffectDummy {

	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final int AREA_OF_EFFECT_DAMAGE = 2;
	private static final TargetTypes[] AFFECTED_TARGETS = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE, TargetTypes.ALLY, TargetTypes.PLAYER_PROJECTILE, TargetTypes.ALLY_PROJECTILE, TargetTypes.PLAYER};
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public TurretMissileDummy(float x, float y, float width, float height) {
		super(x, y, width, height, AREA_OF_EFFECT_DAMAGE, FACTION, AFFECTED_TARGETS);
	}

	/**
	 * Gets the affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTED_TARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}
