package com.spacegame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Missile extends MovableEntity{
	public static final float MISSILEHEIGHT = 10;
	public static final float MISSILEWIDTH = 5;
	
	
	public Missile(float x, float y){
		super(MISSILEWIDTH, MISSILEHEIGHT);
		setPosition(x, y);
		setColor(Color.GREEN);
		addAction(moveTo(x, MyGame.HEIGHT + MISSILEHEIGHT, 1f));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerLaser, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
}
