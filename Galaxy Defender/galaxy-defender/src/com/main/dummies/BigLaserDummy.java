package dummies;

import weapons.TargetTypes;

public class BigLaserDummy extends AreaOfEffectDummy{
	
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};

	public BigLaserDummy(float x, float y, float width, float height,
			int areaDamage) {
		super(x, y, width, height, areaDamage);
	}

	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}

}
