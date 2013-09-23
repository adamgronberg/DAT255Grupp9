package com.weapons;

import com.badlogic.gdx.math.Rectangle;


/**
 * 
 * @author Grupp9
 *
 * Used on weapons to add a area of effect dummy
 * A rectangle with area damage
 */
public class AreaOfEffectDummy extends Rectangle{


	private static final long serialVersionUID = 1L;
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
		set(x, y, width, height);
		this.areaDamage = areaDamage;
	}
	
	/**
	 * @return Area damage of the dummy
	 */
	public int getAreaDamage(){
		return areaDamage;
	}
	
	/**
	 * @return bounds of the rectangle
	 */
	public Rectangle getBounds(){
		return this;
	}
	
	
}
