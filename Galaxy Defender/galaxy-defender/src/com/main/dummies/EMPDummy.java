package dummies;

import ships.EnemyShip;
import spacegame.MovableEntity;
import weapons.TargetTypes;

/**
 * 
 * @author Grupp9
 * DUmmy for EMP
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
	 * 
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
