package com.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Grupp9
 * Draws a texture on screen
 */
public class InteractionButton extends Actor {
	
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
		this.texture = texture;
		setWidth(width);
		setHeight(height);
		setPosition(x, y);
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
	 * Moves the button 
	 * @param x
	 * @param y
	 */
	public void moveButton(float x, float y){
		setPosition(x, y);
	}
	
	
}
