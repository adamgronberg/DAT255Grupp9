package dummies;

import spacegame.MovableEntity;
import weapons.HasFaction;
import weapons.TargetTypes;

/**
 * Used on weapons to add a area of effect dummy
 * A rectangle with area
 * @author Grupp9
 */
public class AreaOfEffectDummy extends MovableEntity implements HasFaction{
	
	private TargetTypes faction = TargetTypes.PLAYER;
	private TargetTypes[] affectedTargets = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	
	protected int areaDamage;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param areaDamage
	 * @param faction
	 * @param affectedTargets
	 */
	public AreaOfEffectDummy(float x, float y, float width, float height, int areaDamage, 
			TargetTypes faction, TargetTypes[] affectedTargets) {
		super(width, height, x, y);
		this.areaDamage = areaDamage;
		this.faction = faction;
		this.affectedTargets = affectedTargets;
	}
	
	/**
	 * @return Area damage of the dummy
	 */
	public int getAreaDamage(){
		return areaDamage;
	}
	
	/**
	 * 
	 * @param enemy
	 */
	public void targetEffect(MovableEntity entity){}
	
	/**
	 * Gets the affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return affectedTargets;
	}
	
	/**
	 * Gets the faction of the dummy
	 */
	@Override
	public TargetTypes getFaction() {
		return faction;
	}
}
