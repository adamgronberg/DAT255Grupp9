package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * 
 * @author Grupp9
 *
 * The player ship. 
 */
public class PlayerShip extends Actor {
	
	private Rectangle bounds = new Rectangle();			// The position of the player
	public static final int PLAYER_SIZE = 50;			// The size of the player
	public static final float PLAYER_MOVMENTSPEED = 5;
	public static final float PLAYER_SPAWNLOCATION = 0.1f;
	
	/**
	 * Constructor
	 * 
	 * Sets player starting variables
	 * @param gameLogic
	 */
	public PlayerShip(GameLogic gameLogic) {
		setWidth(PLAYER_SIZE);
		setHeight(PLAYER_SIZE);
		setPosition(MyGame.WIDTH/2, MyGame.HEIGHT*PLAYER_SPAWNLOCATION);	//Height where the player moves
		//setColor(Color.YELLOW);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
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
	 * Called when the player has moved
	 */
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Tries to move the player to the Right. 
	 * If the player is out of screen he will be moved back
	 */
	public void tryMoveRight() {
		addAction(moveTo(getX()+PLAYER_MOVMENTSPEED, MyGame.HEIGHT*PLAYER_SPAWNLOCATION , 0));
		if (getX()+PlayerShip.PLAYER_SIZE>=MyGame.WIDTH){
			addAction(moveTo(MyGame.WIDTH-PlayerShip.PLAYER_SIZE, MyGame.HEIGHT*PLAYER_SPAWNLOCATION , 0));
		}
	}
	
	/**
	 * Tries to move the player to the left. 
	 * If the player is out of screen he will be moved back
	 */
	public void tryMoveLeft() {
		addAction(moveTo(getX()-PLAYER_MOVMENTSPEED, MyGame.HEIGHT*PLAYER_SPAWNLOCATION , 0));
		if (getX()<=0){
			addAction(moveTo(0, MyGame.HEIGHT*PLAYER_SPAWNLOCATION , 0));
		}
	}
	
	/**
	 * Use this in collision ect
	 * @return The rectangle that contains the position of the player.
	 */
	public Rectangle getBounds() {
		return bounds;
	}
}
