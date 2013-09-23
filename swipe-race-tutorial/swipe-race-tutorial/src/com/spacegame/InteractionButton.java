package com.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * 
 * @author Grupp9
 * Draws a texture on screen
 */
public class InteractionButton extends MovableEntity {
	
	private TextureRegion texture;
	
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
		super( width, height, x, y);
		this.texture = texture;

	}
	
	/**
	 * Draws the texture
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(Color.WHITE);
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}
	
	
	/**
	 * Returns true if coordinates is within button
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPressed(float x, float y){
		return bounds.contains(x, y) ? true : false;
	}
	
	
	
}
