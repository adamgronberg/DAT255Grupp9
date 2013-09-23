package com.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.collisiondetection.CollisionDetection;
import com.collisiondetection.OutOfBoundsDetection;
import com.ships.BasicShip;
import com.ships.HeavyShip;
import com.ships.PlayerShip;
import com.ships.ScoutShip;
import com.spawnlogic.SpawnPattern;


/**
 * 
 * @author Grupp9
 * TODO: Add levels instead of random spawning
 *
 */
public class GameLogic extends Table {
	
	private GameScreen gameScreen;
	private CollisionDetection collision;
	
	
	private long lastEnemyShipTime = 0;			//For testing
	private float lastSpawnPatternTime = 0;		//For testing
	private int nrOfShip= 1;					//For testing
	private boolean playerIsAlive = true;  		//For testing
	private int currentRespawnTime = 0;			//For testing
	private static final int respawnTime = 50;	//For testing
	private boolean shooting = false;
	
	public PlayerShip playerShip;

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn enemies randomly (Release 1 demo)
	 */
	public GameLogic(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
		setClip(true);
		playerShip = new PlayerShip();
		addActor(playerShip);
		collision = new CollisionDetection(this);
		
	}
	

	
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		SnapshotArray<Actor> actors = getChildren();
		for(Actor actor: actors){
			if (actor instanceof MovableEntity){
				MovableEntity entity = (MovableEntity)actor;
				OutOfBoundsDetection.isOutOfBoundsY(entity);
			}
		}
		

		if (!playerIsAlive && currentRespawnTime > respawnTime) {	//For testing
			addActor(playerShip);
			currentRespawnTime = 0;
			playerIsAlive = true;
		}
		
		if (playerIsAlive){
			if (TimeUtils.nanoTime() - lastEnemyShipTime > 2000000000f) spawnShip();			//For testing
			if (TimeUtils.nanoTime() - lastSpawnPatternTime > 5000000000f) spawnPattern();		//For testing
			if (gameScreen.getOptionAutoShoot() || shooting) playerShip.spawnPlayerProjectile();//For testing TODO:Should prob be in playerShip act
		}
		else currentRespawnTime++;									//For testing
		
		if(playerIsAlive) {											//For testing
			collision.checkCollisions();
		}
	}
	
	
	/**
	 * Spawns a BasicShip and a ScoutShip on a random x coordinate above the screen
	 * For testing
	 */
	private void spawnShip() {
		Gdx.app.log( GameScreen.LOG, "" + nrOfShip  );
		
		int spawnLocation = MathUtils.random(0,MyGame.WIDTH-BasicShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new BasicShip(xPos, MyGame.HEIGHT+BasicShip.HEIGHT));
		
		spawnLocation = MathUtils.random(0,MyGame.WIDTH-HeavyShip.WIDTH);
		xPos = spawnLocation;
		addActor(new HeavyShip(xPos, MyGame.HEIGHT+HeavyShip.HEIGHT));
		
		lastEnemyShipTime = TimeUtils.nanoTime();
		nrOfShip++;
	}
	
	/**
	 * Changes the player state
	 */
	public void changePlayerAlive(){
		playerIsAlive = !playerIsAlive;
	}
	
	/**
	 * Sets up a SpawnPattern for scoutShips
	 * For testing	TODO: Should prob be in some sort of "levelDesign" class
	 */
	private void spawnPattern(){		
		int spawnLocation = MathUtils.random(0,MyGame.WIDTH-ScoutShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new SpawnPattern(xPos, MyGame.HEIGHT, 5, 200000000f, "ScoutShip", this));
		lastSpawnPatternTime = TimeUtils.nanoTime();
	}
	/**
	 * Draws all actors on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
		if(playerIsAlive) playerShip.drawAbove(batch, parentAlpha);
	}
	
	/**
	 * Toggles shooting on or off
	 */
	public void toggleShooting(){
		shooting = !shooting;
	}
	
	
	/**
	 * Switches player weapon
	 */
	public void switchPlayerWeapon(){
		playerShip.switchWeapon();
	} 
}
