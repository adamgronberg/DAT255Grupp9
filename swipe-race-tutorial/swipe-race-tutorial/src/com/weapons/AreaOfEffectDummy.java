package com.weapons;

import com.spacegame.MovableEntity;


/**
 * 
 * @author Grupp9
 *
 * Used on weapons to add a area of effect dummy
 * A rectangle with area damage
 */
public class AreaOfEffectDummy extends MovableEntity{

	private int areaDamage;
	

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param areaDamage
	 */
	public AreaOfEffectDummy(float x, float y, float width, float height, int areaDamage) {
		super(width, height, x, y);
		this.areaDamage = areaDamage;
	}
	
	/**
	 * @return Area damage of the dummy
	 */
	public int getAreaDamage(){
		return areaDamage;
	}
	
	
}
