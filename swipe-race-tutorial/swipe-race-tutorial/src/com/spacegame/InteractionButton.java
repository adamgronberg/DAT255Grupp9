package com.spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Grupp9
 * Draws a texture on screen
 * TODO: Could merge this with "Background.java" does more or less the same thing
 */
public class InteractionButton extends Actor {
	
	private TextureRegion texture;
	
	/**
	 * Constructor
	 * @param x			
	 * @param y
	 * @param width
	 * @param height	
	 * @param texture	What texture to draw
	 */
	public InteractionButton(int x, int y, float width, float height, TextureRegion texture) {
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
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}
}
