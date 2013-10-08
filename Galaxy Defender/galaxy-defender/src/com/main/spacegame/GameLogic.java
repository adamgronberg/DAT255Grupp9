package spacegame;

import levels.*;
import assets.ImageAssets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import ships.PlayerShip;
import weapons.*;
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
	private Background backgroundSpace;
	private GameScreen gameScreen;
	public PlayerShip playerShip;
	private Level level;
	private int nextLevel=0;
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn enemies randomly (Release 1 demo)
	 */
	public GameLogic(GameScreen gameScreen) {
		setBounds(0, 0, GameScreen.GAME_WITDH, GameScreen.GAME_HEIGHT);
		setClip(true);
		this.gameScreen = gameScreen;
		level = new Neptune(this);
		addActor(level);
		playerShip = new PlayerShip();
		addActor(playerShip);
		backgroundSpace = new Background(getX(), getY(),getWidth(), getHeight(), ImageAssets.space);
		addActor(backgroundSpace);
	}
	
	/**
	 *  Handles object spawning and collision
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if (playerShip.getCurrentHealth()<1) {	//For testing
			currentScore=0;
			clear();
			addActor(playerShip);
			playerShip.resetHealth();
			//gameScreen.victory(); lägg i mission completed
		}
		

		//if (TimeUtils.nanoTime() - lastEnemyShipTime > 9000000000f) spawnShip();			//For testing
		//if (TimeUtils.nanoTime() - lastAstroidSpawn > 10000000000f) spawnAstroids();			//For testing
		//if (TimeUtils.nanoTime() - lastSpawnPatternTime > 7000000000f) spawnPattern();		//For testing
		OutOfBoundsDetection.checkOutOfBounds(getChildren());
		CollisionDetection.checkCollisions(this);
		if(level.missionCompleted()){
			gameScreen.victory();
			level.remove();
			level=nextLevel(nextLevel);
			nextLevel++;
			addActor(level);
		}
		
	}
	/**Switches level
	 * 
	 * @param nextlevel
	 * @return
	 */
	
	private Level nextLevel(int nextlevel) {
		switch(nextlevel){
		case 0:
			return new Uranus(this);
		case 1:
			return new Saturn(this);
		default:
			return new Neptune(this);
		}
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
		return playerShip.getCurrentHealth();
	}
/**
 * 
 * @return the name of the Level
 */
	public String getLevelName(){
		return level.getName();
	}
	
	/**
	 * @return allPlayerShotts
	 */
	public Array<Projectile> getPlayerShotts(){
		Array<Projectile> playerProjectile = new Array<Projectile>();
		SnapshotArray<Actor> toSearch = getChildren();
		for(Actor actor: toSearch){
			if(actor instanceof Projectile){
				
				Projectile projectile = (Projectile)actor;
				if(projectile.getFaction()==TargetTypes.PLAYER){
					playerProjectile.add(projectile);
				}
			}
		}
		return  playerProjectile;
	}
}
