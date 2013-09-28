package spacegame;

import assets.ImageAssets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	
	private int currentScore=0;
	private long lastEnemyShipTime = 0;			//For testing
	private float lastSpawnPatternTime = 0;		//For testing
	
	private Background backgroundSpace;

	public PlayerShip playerShip;
	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn enemies randomly (Release 1 demo)
	 */
	public GameLogic() {
		setBounds(0, 0, GameScreen.GAME_WITDH, GameScreen.GAME_HEIGHT);
		setClip(true);
		playerShip = new PlayerShip();
		addActor(playerShip);
		backgroundSpace = new Background(getX(), getY(),getWidth(), getHeight(), ImageAssets.space);
		addActor(backgroundSpace);
		playerShip.resetHealth();
	}
	
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if (playerShip.getCurrnetHealth()<1) {	//For testing
			currentScore=0;
			clear();
			addActor(playerShip);
			playerShip.resetHealth();
		}
		
		if (TimeUtils.nanoTime() - lastEnemyShipTime > 3000000000f) spawnShip();			//For testing
		if (TimeUtils.nanoTime() - lastSpawnPatternTime > 7000000000f) spawnPattern();		//For testing
		OutOfBoundsDetection.checkOutOfBounds(getChildren());
		CollisionDetection.checkCollisions(this);
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
	 * Sets up a SpawnPattern for scoutShips
	 * For testing	TODO: Should prob be in some sort of "levelDesign" class
	 */
	private void spawnPattern(){		
		float spawnLocation = MathUtils.random(0,GameScreen.GAME_WITDH-ScoutShip.WIDTH);
		float xPos = spawnLocation;
		addActor(new SpawnPattern(xPos, GameScreen.GAME_HEIGHT, 5, 275000000f, EnemyTypes.SCOUT));
		lastSpawnPatternTime = TimeUtils.nanoTime();
	}
	/**
	 * Draws all actors on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		backgroundSpace.drawBelow(batch, parentAlpha);
		super.draw(batch, parentAlpha);
		playerShip.drawAbove(batch, parentAlpha);
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
	}
	
	/**
	 * @return the current score
	 */
	public int getScore(){
		return currentScore;
	}
	
	/**
	 * 
	 * @return the current health
	 */
	public int getPlayerHealth(){
		return playerShip.getCurrnetHealth();
	}
}
