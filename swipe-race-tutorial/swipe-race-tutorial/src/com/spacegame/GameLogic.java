package com.spacegame;

import java.util.Iterator;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.enemyship.BasicShip;
import com.enemyship.EnemyShip;
import com.enemyship.ScoutShip;
import com.playerweapons.ExplosionDummy;
import com.playerweapons.PlayerLaser;
import com.playerweapons.PlayerMissile;
import com.playerweapons.Projectile;
import com.spawnlogic.SpawnPattern;

/**
 * 
 * @author Grupp9
 * TODO: Move out stuff from this class, handles to much
 *
 */
public class GameLogic extends Table {
	
	private GameScreen gameScreen;
	
	private Background backgroundSpace;
	private InteractionButton moveLeftButton;
	private InteractionButton moveRightButton;
	
	private Array<EnemyShip> enemyShips;
	private Array<Projectile> playerProjectiles;
	private Array<SpawnPattern> patterns;
	
	
	private long lastEnemyShipTime = 0;			//For testing
	private float lastSpawnPatternTime = 0;		//For testing
	private float lastMissileTime = 0;      	//For testing
	private int nrOfShip;						//For testing
	private boolean playerIsAlive = true;  		//For testing
	private boolean useLaser = true;			//For testing
	private boolean useMissiles = false;		//For testing
	private int currentRespawnTime = 0;			//For testing
	private static final int respawnTime = 50;	//For testing
	private boolean shooting = false;
	
	public PlayerShip playerShip;

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn set number of enemies randomly (Release 1 demo)
	 */
	public GameLogic(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
		setClip(true);
		backgroundSpace = new Background(getWidth(), getHeight());
		moveLeftButton = new InteractionButton(0, 0, GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, Assets.moveLeftButton);
		moveRightButton = new InteractionButton(MyGame.WIDTH - GameScreen.MOVMENT_BUTTON_SIZE, 0, 
				GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, Assets.moveRightButton);
		addActor(backgroundSpace);
		addActor(moveLeftButton);
		addActor(moveRightButton);
		
		
		nrOfShip = 1;
		playerShip = new PlayerShip();
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();
		playerProjectiles = new Array<Projectile>();
		patterns = new Array<SpawnPattern>();
	}
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (!playerIsAlive && currentRespawnTime > respawnTime) {							//For testing
			addActor(playerShip);
			currentRespawnTime = 0;
			playerIsAlive = true;
		}
		
		if (playerIsAlive){
			if (TimeUtils.nanoTime() - lastEnemyShipTime > 2000000000f) spawnShip();			//For testing
			if (TimeUtils.nanoTime() - lastSpawnPatternTime > 5000000000f) spawnPattern();	//For testing
			if (gameScreen.getOptionAutoShoot() || shooting) spawnPlayerProjectile();			//For testing
		}
		else currentRespawnTime++;															//For testing
		
		///////////////////////////// For testing
		if(gameScreen.getOptionControlLayout()) {				
			moveRightButton.moveButton(MyGame.WIDTH - GameScreen.MOVMENT_BUTTON_SIZE, moveRightButton.getY());
		}
		else{
			moveRightButton.moveButton(GameScreen.MOVMENT_BUTTON_SIZE, moveRightButton.getY());
		}
		/////////////////////////////
		
		if(playerIsAlive) {
			Iterator<Projectile> iterM;
			Iterator<EnemyShip> iter = enemyShips.iterator();
			while (iter.hasNext()) {
				EnemyShip enemyShip = iter.next();
				iterM = playerProjectiles.iterator();
				if (isOutOfBoundsY(enemyShip)) {				
					iter.remove();
					removeActor(enemyShip);
				}
				else if(collisionControl(enemyShip, playerShip)) {
					clearScreen();
					playerIsAlive = false;
					break;
				}									
				else {
					while(iterM.hasNext()){
						Projectile projectile = iterM.next();
						if(projectile.isDespawnReady()){
							removeActor(projectile);
							iterM.remove();
						}
						if (collisionControl(enemyShip, projectile)) {
							Gdx.app.log( GameScreen.LOG, "Hit!"  );
							if(!(projectile.getClass() == ExplosionDummy.class)){
								removeActor(projectile);
								iterM.remove();
							}
							removeActor(enemyShip);
							iter.remove();
							projectile = projectile.explode(this);
							if(projectile != null) playerProjectiles.add(projectile);
							break;
						}
					}
				}
			}
			iterM = playerProjectiles.iterator();
			while(iterM.hasNext()){
				Projectile missile = iterM.next();
				if (isOutOfBoundsY(missile)){
					iterM.remove();
					removeActor(missile);
				}
			}
		}
	}
	
	/**
	 * Checks if a obj is out of bounds on y-led
	 * @param movableObj
	 * @return	returns true if out of bounds
	 */
	private boolean isOutOfBoundsY(MovableEntity movableObj){
		if (movableObj.bounds.y < -movableObj.getBounds().getHeight() || movableObj.bounds.y >= 
				MyGame.HEIGHT+movableObj.getBounds().getHeight()){
			return true; 
		}
		return false;
	}
	
	/**
	 * Tests if obj1 and obj2 collided
	 * @param movableObj1
	 * @param movableObj2
	 * @return true if the objects collided
	 */
	private boolean collisionControl(MovableEntity movableObj1, MovableEntity movableObj2){
		return movableObj1.getBounds().overlaps(movableObj2.getBounds());
	}
	
	/**
	 * Spawns projectiles in front of the player
	 * Current weapons:
	 * -Laser
	 * -Missiles
	 */
	public void spawnPlayerProjectile() {
		Projectile projectile;
		if (useMissiles && TimeUtils.nanoTime() - lastMissileTime > PlayerMissile.RATEOFFIRE) {
			projectile = new PlayerMissile(playerShip.getX()+PlayerShip.PLAYER_SIZE/2, 
										   playerShip.getY()+PlayerShip.PLAYER_SIZE);
			playerProjectiles.add(projectile);
			addActor(projectile);
			lastMissileTime = TimeUtils.nanoTime();
		}
		else if(useLaser && TimeUtils.nanoTime() - lastMissileTime > PlayerLaser.RATEOFFIRE) {
			projectile = new PlayerLaser(playerShip.getX()+PlayerShip.PLAYER_SIZE/2, 
										 playerShip.getY()+PlayerShip.PLAYER_SIZE);
			playerProjectiles.add(projectile);
			addActor(projectile);
			lastMissileTime = TimeUtils.nanoTime();
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
		
		spawnLocation = MathUtils.random(0,MyGame.WIDTH-ScoutShip.WIDTH);
		xPos = spawnLocation;
		enemyShip = new ScoutShip(xPos, MyGame.HEIGHT+ScoutShip.HEIGHT);
		enemyShips.add(enemyShip);
		addActor(enemyShip);
		
		lastEnemyShipTime = TimeUtils.nanoTime();
		nrOfShip++;
	}
	
	/**
	 * Sets up a SpawnPattern for scoutShips
	 * For testing
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
	 * Swaps players weapon
	 */
	public void switchWeapon(){
		if(useLaser) { 
			useMissiles = true;
			useLaser = false;
		}
		else if(useMissiles) { 
			useMissiles = false;
			useLaser = true;
		}
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
	private void clearScreen(){
		for(EnemyShip enemy: enemyShips){
			removeActor(enemy);
		}
		enemyShips.clear();
		for(Projectile porjectile: playerProjectiles){
			removeActor(porjectile);
		}
		playerProjectiles.clear();
		for(SpawnPattern pattern: patterns){
			removeActor(pattern);
		}
		patterns.clear();
		
		removeActor(playerShip);		
	}
}
