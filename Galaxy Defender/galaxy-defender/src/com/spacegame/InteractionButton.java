package com.spacegame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * 
 * @author Grupp9
 * Draws a texture on screen
 */
public class InteractionButton extends Sprite {
	
	
	/**
	 * Constructor
	 * (bottom left corner is 0,0)
	 * @param x			
	 * @param y
	 * @param width
	 * @param height	
	 * @param texture	What texture to draw
	 */
	public InteractionButton(float x, float y, float width, float height, TextureRegion texture) {
		super( width, height, x, y, texture);
		this.texture = texture;
	}
	
	/**
	 * Returns true if coordinates is within button
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPressed(float x, float y){
		return getBounds().contains(x, y);
	}
	
	
	
}
