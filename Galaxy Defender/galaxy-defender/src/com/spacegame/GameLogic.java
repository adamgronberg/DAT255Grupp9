package com.spacegame;


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
	
	private int currentScore=0;
	private long lastEnemyShipTime = 0;			//For testing
	private float lastSpawnPatternTime = 0;		//For testing
				
	private boolean playerIsAlive = true;  		//For testing
	private int currentRespawnTime = 0;			//For testing
	private static final int respawnTime = 50;	//For testing
	private boolean shooting = false;
	
	private Background backgroundSpace;
	
	public PlayerShip playerShip;
	
	

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn enemies randomly (Release 1 demo)
	 */
	public GameLogic(GameScreen gameScreen) {
		
		this.gameScreen = gameScreen;
		setBounds(0, 0, GameScreen.GAME_WITDH, GameScreen.GAME_HEIGHT);
		setClip(true);
		playerShip = new PlayerShip();
		addActor(playerShip);
		collision = new CollisionDetection(this);
		backgroundSpace = new Background(getX(), getY(),getWidth(), getHeight(), ImageAssets.space);
		addActor(backgroundSpace);
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
			currentScore=0;
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
	
		
		int spawnLocation = (int) MathUtils.random(0,GameScreen.GAME_WITDH-BasicShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new BasicShip(xPos, GameScreen.GAME_HEIGHT+BasicShip.HEIGHT));
		
		spawnLocation = (int) MathUtils.random(0,GameScreen.GAME_WITDH-HeavyShip.WIDTH);
		xPos = spawnLocation;
		addActor(new HeavyShip(xPos, GameScreen.GAME_HEIGHT+HeavyShip.HEIGHT));
		
		lastEnemyShipTime = TimeUtils.nanoTime();
		
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
		int spawnLocation = (int) MathUtils.random(0,GameScreen.GAME_WITDH-ScoutShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new SpawnPattern(xPos, GameScreen.GAME_HEIGHT, 5, 200000000f, "ScoutShip", this));
		lastSpawnPatternTime = TimeUtils.nanoTime();
	}
	/**
	 * Draws all actors on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		
		backgroundSpace.drawBelow(batch, parentAlpha);
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
	
	public void addScore(int score){
		currentScore=currentScore+score;
		System.out.println("CurrentScore: "+currentScore);

	}
	
	/**
	 * 
	 * @return the current score
	 */
	public int getScore(){
		return currentScore;
	}
}
