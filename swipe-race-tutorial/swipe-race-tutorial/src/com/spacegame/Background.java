package com.spacegame;



/**
 * 
 * @author Grupp9
 * 
 * TODO: Could use this class to add the different map backgrounds
 */
public class Background extends Sprite {
	
	/**
	 * Constructor
	 * @param width 	Width of the screen should be here 
	 * @param height	Height of the screen should be here
	 */
	public Background(float width, float height) {
		super(width, height, 0, 0, Assets.space);
		//addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(width, 0)))); 
		//TODO: Could have a scrolling background instead of static?
	}
	
}
