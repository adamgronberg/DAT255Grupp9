package com.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;



/**
 * 
 * @author Grupp9
 *
 * Weapons that have area of effect either after, before or while
 */
public abstract class AreaOfEffectWeapon extends Projectile{

	protected AreaOfEffectDummy areaOfEffectDummy;
	protected int areaDamage;
	
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param onHitDamage
	 * @param areaDamage	The damage inflicted by the area used by the weapon
	 */
	public AreaOfEffectWeapon(float x, float y, float width, float height, int onHitDamage, int areaDamage, TextureRegion texture) {
		super(x, y, width, height, onHitDamage, texture, false);
		this.areaDamage = areaDamage;
	}
	
	/**
	 * @return Area of effect dummy (rectangle with a set damage)
	 */
	public AreaOfEffectDummy getAreaOfEffectDummy(){
		return areaOfEffectDummy;
	}
}
