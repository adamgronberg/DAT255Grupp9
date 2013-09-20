package com.enemyShips;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacegame.Assets;

/**
 * 
 * @author Grupp9
 *
 *	The Basic enemy
 *	50x50 big and moderate speed. Don't move in y-led. Moves to bottom in X-led
 *	Does not shoot
 *
 */
public class BasicShip extends EnemyShip {
	private final static float SHIPSPEED = 5.0f;
	public final static int HEIGHT=50;
	public final static int WIDTH=50;
	
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 */
	public BasicShip(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		addAction(moveTo(x, -getHeight()-0.1f, SHIPSPEED));	// "-0.1f" Have to move a little further then the screen end
	}
	
	/**
	 * Called when draw is called on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.enemyBasicShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}

}
