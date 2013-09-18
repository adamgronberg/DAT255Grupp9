package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerShip extends Actor {
	private GameLogic gameLogic;
	private Rectangle bounds = new Rectangle();
	private int lane;
	
	public PlayerShip(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		setWidth(80);
		setHeight(80);
		lane = 1;
		setPosition(getWidth()/2, getHeight()/20);
		setColor(Color.YELLOW);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void tryMoveRight() {
		if ((getActions().size == 0) && (lane != 2)) moveToLane(lane+1);
	}

	public void tryMoveLeft() {
		if ((getActions().size == 0) && (lane != 0)) moveToLane(lane-1);
	}
	
	private void moveToLane(int lane) {
		this.lane = lane;
		
		switch (lane) {
			case 0:
				addAction(moveTo(0, 0 , 0.5f));
				break;
			case 1:
				addAction(moveTo(MyGame.WIDTH/2-80, 0 , 0.5f));
				break;
			case 2:
				addAction(moveTo(MyGame.WIDTH-80, 0 , 0.5f));
				break;
		}
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
