package com.spacegame;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.collisiondetection.CollisionDetection;
import com.effects.ExplosionEffect;
import com.ships.BasicShip;
import com.ships.EnemyShip;
import com.ships.HeavyShip;
import com.ships.PlayerShip;
import com.ships.ScoutShip;
import com.spawnlogic.SpawnPattern;
import com.weapons.Projectile;

/**
 * 
 * @author Grupp9
 * TODO: Add levels instead of random spawning
 *
 */
public class GameLogic extends Table {
	
	private GameScreen gameScreen;
	private CollisionDetection collision;
	
	private Array<EnemyShip> enemyShips;
	private Array<Projectile> playerProjectiles;
	private Array<ExplosionEffect> effects; 
	private Array<SpawnPattern> patterns;
	
	
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
		playerShip = new PlayerShip(this);
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();
		playerProjectiles = new Array<Projectile>();
		patterns = new Array<SpawnPattern>();
		effects = new Array<ExplosionEffect>();
		collision = new CollisionDetection(this);
		
	}
	
	
	public Array<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
	}
	
	public Array<EnemyShip> getEnemyShips(){
		return enemyShips;
	}
	
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		Iterator<ExplosionEffect> iterE = effects.iterator();
		while (iterE.hasNext()) {
			ExplosionEffect effect = iterE.next();
			if(effect.isDespawnReady()){					//Removes ship when despawn ready
				removeActor(effect);
				iterE.remove();
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
		
		if(playerIsAlive) {
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
		EnemyShip enemyShip = new BasicShip(xPos, MyGame.HEIGHT+BasicShip.HEIGHT);
		enemyShips.add(enemyShip);
		addActor(enemyShip);
		
		spawnLocation = MathUtils.random(0,MyGame.WIDTH-HeavyShip.WIDTH);
		xPos = spawnLocation;
		enemyShip = new HeavyShip(xPos, MyGame.HEIGHT+HeavyShip.HEIGHT);
		enemyShips.add(enemyShip);
		addActor(enemyShip);
		
		lastEnemyShipTime = TimeUtils.nanoTime();
		nrOfShip++;
	}
	
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
		SpawnPattern spawnPattern = new SpawnPattern(xPos, MyGame.HEIGHT, 5, 200000000f, "ScoutShip", this);
		patterns.add(spawnPattern);
		addActor(spawnPattern);
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
	 * Adds a enemy to gameLogics collision detection
	 * @param enemy
	 */
	public void addEnemyShips(EnemyShip enemy){
		enemyShips.add(enemy);
		addActor(enemy);
	}
	/**
	 * Adds a effect to gameLogics
	 * @param enemy
	 */
	public void addEffect(ExplosionEffect effect){
		effects.add(effect);
		addActor(effect);
	}
	
	
	/**
	 * Adds a projectile to gameLogics collision detection
	 * @param projectile
	 */
	public void addProjectileToCollision(Projectile projectile){
		playerProjectiles.add(projectile);
	}
	
	/**
	 * Toggles shooting on or off
	 */
	public void toggleShooting(){
		shooting = !shooting;
	}
	
	/**
	 * 	Removes all Enemies, projectile, patterns and the player from screen 
	 *  and arrays (Player get removed as actor but won't get uninitialized )
	 */
	public void clearScreen(){
		enemyShips.clear();
		playerProjectiles.clear();
		patterns.clear();
		clear();
	}
	
	/**
	 * Switches player weapon
	 */
	public void switchPlayerWeapon(){
		playerShip.switchWeapon();
	} 
}
