package spacegame;

import assets.ImageAssets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.TimeUtils;
import ships.BasicShip;
import ships.EnemyShip.EnemyTypes;
import ships.HeavyShip;
import ships.PlayerShip;
import ships.ScoutShip;
import spawnlogic.SpawnPattern;
import collisiondetection.CollisionDetection;
import collisiondetection.OutOfBoundsDetection;





/**
 * 
 * @author Grupp9
 * TODO: Add levels instead of random spawning
 *
 */
public class GameLogic extends Table {
	
	private GameScreen gameScreen;
	
	private int currentScore=0;
	private long lastEnemyShipTime = 0;			//For testing
	private float lastSpawnPatternTime = 0;		//For testing
				
	private boolean playerIsAlive = true;  		//For testing
	private boolean shooting = false;
	
	private Background backgroundSpace;
	
	public PlayerShip playerShip;
	public static final int startLife=100;
	public int life;
	
	

	
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
		backgroundSpace = new Background(getX(), getY(),getWidth(), getHeight(), ImageAssets.space);
		addActor(backgroundSpace);
		life = startLife;
	}
	

	
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		if (!playerIsAlive) {	//For testing
			currentScore=0;
			clear();
			addActor(playerShip);
			playerIsAlive = true;
			life=startLife;
		}
		
		super.act(delta);
		
		SnapshotArray<Actor> actors = getChildren();
		for(Actor actor: actors){
			if (actor instanceof MovableEntity){
				MovableEntity entity = (MovableEntity)actor;
				OutOfBoundsDetection.isOutOfBoundsY(entity);
			}
		}
		
		
		if (playerIsAlive){
			if (TimeUtils.nanoTime() - lastEnemyShipTime > 2000000000f) spawnShip();			//For testing
			if (TimeUtils.nanoTime() - lastSpawnPatternTime > 5000000000f) spawnPattern();		//For testing
			if (gameScreen.getOptionAutoShoot() || shooting) playerShip.spawnPlayerProjectile();//For testing TODO:Should prob be in playerShip act
		}
		
		if(playerIsAlive) {											//For testing
			CollisionDetection.checkCollisions(this);
		}
	}
	
	
	/**
	 * Spawns a BasicShip and a ScoutShip on a random x coordinate above the screen
	 * For testing
	 */
	private void spawnShip() {
		int xPos = (int) MathUtils.random(0,GameScreen.GAME_WITDH-BasicShip.WIDTH);
		addActor(new BasicShip(xPos, GameScreen.GAME_HEIGHT+BasicShip.HEIGHT));
		
		xPos = (int) MathUtils.random(0,GameScreen.GAME_WITDH-HeavyShip.WIDTH);
		addActor(new HeavyShip(xPos, GameScreen.GAME_HEIGHT+HeavyShip.HEIGHT));
		
		lastEnemyShipTime = TimeUtils.nanoTime();
	}
	
	/**
	 * Calls when a player are in a collision
	 * @param damage the damage of the collision
	 */
	public void playerCollision(int damage){
		decHealth(damage);
		if(life<=0){
			playerIsAlive = !playerIsAlive;
		}
	}
	
	/**
	 * Sets up a SpawnPattern for scoutShips
	 * For testing	TODO: Should prob be in some sort of "levelDesign" class
	 */
	private void spawnPattern(){		
		float spawnLocation = MathUtils.random(0,GameScreen.GAME_WITDH-ScoutShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new SpawnPattern(xPos, GameScreen.GAME_HEIGHT, 5, 200000000f, EnemyTypes.SCOUT));
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
	/**
	 * add the score to the total score
	 */
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
	/**
	 * 
	 * @return the current health
	 */
	public int getHealth(){
		return life;
	}
	/**
	 * 
	 * @param damage from projectile or enemyship
	 */
	private void decHealth(int damage){
		life=life-damage;
		if(life<0){
			life=0;
		}
	}
	/**
	 * 
	 * @param extraLife
	 */
//	private void incHealth(int extraLife){
//		life=life+extraLife;
//	}
}
