package com.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.spacegame.Assets;
import com.spacegame.GameLogic;
import com.spacegame.MovableEntity;
import com.spacegame.MyGame;
import com.weapons.PlayerLaser;
import com.weapons.PlayerMissile;
import com.weapons.Projectile;

/**
 * 
 * @author Grupp9
 *
 * The player ship. Methods for moving ,drawing and shooting
 */
public class PlayerShip extends MovableEntity {
	
	public static enum Direction {LEFT, RIGHT, NONE;}
	private static enum AvailableWeapons {MISSILE, LASER;} 	//Implemented weapons
	public static final int PLAYER_SIZE = 50;				// The size of the player
	private static final float SPEED = 5;					// number of pixels the player moves every act
	private static final float PLAYER_SPAWNLOCATION = 0.1f; //Height where the player moves
	
	private float lastMissileTime = 0;      	//For testing
	
	private GameLogic gameLogic;
	
	private Direction movmentDirection = Direction.NONE;
	private AvailableWeapons currentWeapon = AvailableWeapons.LASER;
	
	
	/**
	 * Constructor
	 * 
	 * Sets player starting variables
	 * @param gameLogic
	 */
	public PlayerShip(GameLogic gameLogic) {
		super(PLAYER_SIZE, PLAYER_SIZE, MyGame.WIDTH/2, MyGame.HEIGHT*PLAYER_SPAWNLOCATION);
		this.gameLogic = gameLogic;
	}

	
	/**
	 * Called when "draw" is called in its stage
	 */
	public void drawAbove(SpriteBatch batch, float parentAlpha) {
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
	
	/**
	 * Spawns projectiles in front of the player
	 * Current weapons:
	 * -Laser
	 * -Missiles
	 */
	public void spawnPlayerProjectile() {
		Projectile projectile;
		if (currentWeapon == AvailableWeapons.MISSILE && TimeUtils.nanoTime() - lastMissileTime > PlayerMissile.RATEOFFIRE) {
			projectile = new PlayerMissile(getX()+PlayerShip.PLAYER_SIZE/2-PlayerMissile.WIDTH/2, getY());
			gameLogic.addProjectileToCollision(projectile);
			gameLogic.addActor(projectile);
			lastMissileTime = TimeUtils.nanoTime();
		}
		else if(currentWeapon == AvailableWeapons.LASER && TimeUtils.nanoTime() - lastMissileTime > PlayerLaser.RATEOFFIRE) {
			projectile = new PlayerLaser(getX()+PlayerShip.PLAYER_SIZE/2, 
										 getY()+PlayerShip.PLAYER_SIZE);
			gameLogic.addProjectileToCollision(projectile);
			gameLogic.addActor(projectile);
			lastMissileTime = TimeUtils.nanoTime();
		}
	}
	
	/**
	 * Swaps players weapon
	 */
	public void switchWeapon(){
		if(currentWeapon == AvailableWeapons.LASER) { 
			currentWeapon = AvailableWeapons.MISSILE;
		}
		else if(currentWeapon == AvailableWeapons.MISSILE) { 
			currentWeapon = AvailableWeapons.LASER;
		}
	}
}
