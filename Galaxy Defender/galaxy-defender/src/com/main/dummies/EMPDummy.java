package dummies;

import ships.EnemyShip;
import spacegame.MovableEntity;
import weapons.TargetTypes;

/**
 * Dummy for EMP contains the disable time
 * @author Grupp9
 */
public class EMPDummy extends AreaOfEffectDummy{

	private float disableTime;
	
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
	public EMPDummy(float x, float y, float width, float height,
			int areaDamage, TargetTypes faction, TargetTypes[] affectedTargets, float disableTime) {
		super(x, y, width, height, areaDamage, faction, affectedTargets);
		this.disableTime = disableTime;
	}

	/**
	 * The targets affected by the emp
	 * @param enemy
	 */
	@Override
	public void targetEffect(MovableEntity entity){
		if(entity instanceof EnemyShip){
			EnemyShip enemy = (EnemyShip)entity;
			enemy.disableShip(disableTime);
		}
	}
}
