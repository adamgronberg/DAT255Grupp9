package com.spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Grupp9
 * 
 * TODO: Could use this class to add the different map backgrounds
 */
public class Background extends Actor {
	
	/**
	 * Constructor
	 * @param width 	Width of the screen should be here 
	 * @param height	Height of the screen should be here
	 */
	public Background(float width, float height) {
		setWidth(width);
		setHeight(height);
		setPosition(0, 0);
		//addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(width, 0)))); 
		//TODO: Could have a scrolling background in endless mode?
	}
	
	/**
	 * Draws the background
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.space, getX(), getY(), getWidth(), getHeight());
	}
}
