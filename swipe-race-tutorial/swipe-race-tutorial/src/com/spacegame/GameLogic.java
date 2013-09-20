package com.spacegame;

import java.util.Iterator;

import spawnlogic.SpawnPattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.enemyShips.BasicShip;
import com.enemyShips.EnemyShip;
import com.enemyShips.ScoutShip;

/**
 * 
 * @author Grupp9
 *
 */
public class GameLogic extends Table {
	
	private InfiniteScrollBg backgroundSpace;
	private InteractionButton moveLeftButton;
	private InteractionButton moveRightButton;
	
	private Array<EnemyShip> enemyShips;
	private Array<Missile> missiles;
	private Array<SpawnPattern> patterns;
	
	private long lastEnemyShipTime = 0;		//For testing
	private float lastSpawnPatternTime = 0; //For testing
	private float lastMissileTime = 0;      //For testing
	private int nrOfShip;					//For testing
	private boolean playerIsAlive = true;   //For testing
	
	public PlayerShip playerShip;

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn set number of enemies randomly (Release 1 demo)
	 */
	public GameLogic() {
		setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
		setClip(true);
		backgroundSpace = new InfiniteScrollBg(getWidth(), getHeight());
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
		missiles = new Array<Missile>();
		patterns = new Array<SpawnPattern>();

	}
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (playerIsAlive){
			if (TimeUtils.nanoTime() - lastEnemyShipTime > 2000000000f) spawnShip();
			if (TimeUtils.nanoTime() - lastMissileTime > 300000000f) spawnMissile();
			if (TimeUtils.nanoTime() - lastSpawnPatternTime > 5000000000f) spawnPattern();
		}
		
		Iterator<Missile> iterM;
		Iterator<EnemyShip> iter = enemyShips.iterator();
		while (iter.hasNext()) {
			EnemyShip enemyShip = iter.next();
			iterM = missiles.iterator();
			if (checkOutOfBoundsY(enemyShip)) {				
				iter.remove();
				removeActor(enemyShip);
			}
			else if (collisionControl(enemyShip, playerShip)) {		
				iter.remove();
				removeActor(enemyShip);
				removeActor(playerShip);
				playerIsAlive = false;
				break;
			}										
			else {
				while(iterM.hasNext()){
					Missile missile = iterM.next();
					if (collisionControl(enemyShip, missile)) {
						Gdx.app.log( GameScreen.LOG, "Hit!"  );
						iter.remove();
						removeActor(enemyShip);
						iterM.remove();
						removeActor(missile);
						break;
					}
				}
			}
		}
		iterM = missiles.iterator();
		while(iterM.hasNext()){
			Missile missile = iterM.next();
			if (checkOutOfBoundsY(missile)){
				iterM.remove();
				removeActor(missile);
			}
		}
	}
	
	/**
	 * Checks if a obj is out of bounds on y-led
	 * @param movableObj
	 * @return
	 */
	private boolean checkOutOfBoundsY(MovableEntity movableObj){
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
	 * Spawns missiles in front of the player
	 */
	private void spawnMissile() {
		Missile missile = new Missile(playerShip.getX()+PlayerShip.PLAYER_SIZE/2, 
				playerShip.getY()+PlayerShip.PLAYER_SIZE);
		missiles.add(missile);
		addActor(missile);
		lastMissileTime = TimeUtils.nanoTime();	
	}
	
	/**
	 * Temporary spawn
	 * Spawns a BasicShip and a ScoutShip on a random x coordinate above the screen
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
	
	private void spawnPattern(){		
		int spawnLocation = MathUtils.random(0,MyGame.WIDTH-ScoutShip.WIDTH);
		float xPos = spawnLocation;
		SpawnPattern spawnPattern = new SpawnPattern(xPos, MyGame.HEIGHT, 5, 200000000f, "ScoutShip", this);
		patterns.add(spawnPattern);
		addActor(spawnPattern);
		
		lastSpawnPatternTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
	
	public void addEnemyShips(EnemyShip enemy){
		enemyShips.add(enemy);
		addActor(enemy);
	}
	
	
//	if (enemyShip.getX() > playerShip.getX()) {
//	if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(true, true);		//Check what direction
//	else enemyShip.crash(true, false);
//}
//else {
//	if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(false, true);		//check what direction
//	else enemyShip.crash(false, false);
//}
}