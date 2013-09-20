package com.enemyShips;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacegame.Assets;
import com.spacegame.MyGame;

/**
 * 
 * @author Grupp9
 *
 */
public class ScoutShip extends EnemyShip{
	
	public final static int HEIGHT = 40;
	public final static int WIDTH = 40;
	private final static float SHIPSPEED_X = 2.0f;
	private final static float SHIPSPEED_Y = 3.0f;
	private boolean movingLeft = true;
	private final float movmentLenght = 50;
	private float currentMovmentLenght;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ScoutShip(float x, float y){
		super(x, y, WIDTH, HEIGHT);
		setRotation(-45);
		currentMovmentLenght = 0;
	}
	
	
	/**
	 * 
	 */
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
		setY(getY()-SHIPSPEED_Y);
		
		if(movingLeft){
			if(getX() < 0 || currentMovmentLenght > movmentLenght){
				currentMovmentLenght = 0;
				setRotation(45);
				movingLeft = false;
			}
			setX(getX()-SHIPSPEED_X);
			currentMovmentLenght++;
		}
		else {
			if(getX() > MyGame.WIDTH-WIDTH || currentMovmentLenght > movmentLenght){
				currentMovmentLenght = 0;
				setRotation(-45);
				movingLeft = true;
			}
			setX(getX()+SHIPSPEED_X);
			currentMovmentLenght++;
		}
		super.act(delta);
	}
	
}
