package com.enemyShips;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacegame.Assets;

public class ScoutShip extends EnemyShip{
	
	public final static float HEIGHT = 50;
	public final static float WIDTH = 50;
	private final static float SHIPSPEED = 5.0f;
	
	
	public ScoutShip(float x, float y){
		super(x, y, WIDTH, HEIGHT);
		addAction(moveTo(getX(), -getHeight()-0.1f, SHIPSPEED));	// "-0.1f" Have to move a little further then the screen end
	}
	
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.enemyScoutShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);	
	}
	
}
