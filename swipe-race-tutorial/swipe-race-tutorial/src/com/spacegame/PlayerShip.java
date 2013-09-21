package com.spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author Grupp9
 *
 * The player ship. Methods for moving and drawing
 */
public class PlayerShip extends MovableEntity {
	
	public static enum Direction {LEFT, RIGHT, NONE;}
	public static final int PLAYER_SIZE = 50;			// The size of the player
	private static final float SPEED = 5;				// number of pixels the player moves every act
	private static final float PLAYER_SPAWNLOCATION = 0.1f; //Height where the player moves
	private Direction movmentDirection = Direction.NONE;
	
	
	/**
	 * Constructor
	 * 
	 * Sets player starting variables
	 * @param gameLogic
	 */
	public PlayerShip() {
		super(PLAYER_SIZE, PLAYER_SIZE);
		setPosition(MyGame.WIDTH/2, MyGame.HEIGHT*PLAYER_SPAWNLOCATION);	
	}

	
	/**
	 * Called when "draw" is called in its stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
		
	/**
	 * Tries to move the player to the Right. 
	 * If the player is out of screen he will be moved back
	 */
	public void moveRight() {
		movmentDirection = Direction.RIGHT;
	}
	
	/**
	 * Tries to move the player to the left. 
	 * If the player is out of screen he will be moved back
	 */
	public void moveLeft() {
		movmentDirection = Direction.LEFT;
	}
	
	/**
	 * Stops the player from moving
	 */
	public void stay(){
		movmentDirection = Direction.NONE;
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		
		switch(movmentDirection){
			case LEFT:
				setX(getX()-SPEED);
				break;
			case RIGHT:
				setX(getX()+SPEED);
				break;
			case NONE:
				break;
			
		}

		if(getX()<0) setX(0);
		else if(getX()>MyGame.WIDTH-PLAYER_SIZE) setX(MyGame.WIDTH-PLAYER_SIZE);
		
		super.act(delta);
	}
	
	/**
	 * @return	What X direction the player is moving in
	 */
	public Direction getMovmentDirection(){
		return movmentDirection;
	}

}
