package com.spacegame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Missile extends MovableEntity{

	public Missile(float x, float y){
		setWidth(5);
		setHeight(10);
		setPosition(x, y);
		setColor(Color.GREEN);
		addAction(moveTo(x, MyGame.HEIGHT, 1f));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerLaser, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
}
