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
import ships.EnemyShip;
import spawnlogic.SpawnPattern;
import collisiondetection.CollisionDetection;

/**
 * 
 * @author Grupp9
 * Handles the game logic.
 */
public class GameLogic extends Table {
	
	private int currentScore=0;
	private Background backgroundSpace;
	private GameScreen gameScreen;
	public PlayerShip playerShip;
	private Level level;

	/**
	 * Constructor
	 * @param gameScreen
	 */
	public GameLogic(GameScreen gameScreen) {
		setBounds(0, 0, GameScreen.GAME_WITDH, GameScreen.GAME_HEIGHT);
		setClip(true);
		this.gameScreen = gameScreen;
		playerShip = new PlayerShip();
		level = new Neptune(this);	
		backgroundSpace = new Background(getX(), getY(),getWidth(), getHeight(), ImageAssets.space);
		
		addActor(level);
		addActor(playerShip);
		addActor(backgroundSpace);

	}
	
	/**
	 * checks collision detection and controls if the player is dead
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if (playerShip.getCurrentHealth()<1) {	//For testing
			gameScreen.defeat(); //TODO switch places defeat and reset add another score variable
			resetGame();
		}
		
		CollisionDetection.checkCollisions(this);
		
		if(level.missionCompleted()){
			startNextLevel();
			gameScreen.victory();
		}
		
		else if(level.missionFailed()){
			clear();
			playerShip.stay();
			level = new Neptune(this);	//TODO:Should end game not start a new one
			addActor(level);
			addActor(playerShip);
			gameScreen.defeat();
			currentScore=0;
		}
	}
	
	/**
	 * Switches level
	 * @param level the current level
	 * @return the next level
	 */
	private Level nextLevel(Level level) {
		if(level.getName().equals("Neptune")) return new Uranus(this);
		else if(level.getName().equals("Uranus")) return new Saturn(this);
		else if(level.getName().equals("Saturn")) return new Jupiter(this);
		else if(level.getName().equals("Jupiter")) return new Mars(this);
		else if(level.getName().equals("Mars")) return new Earth(this);
		else return new Neptune(this);
	}
	
	/**
	 * Draws all actors on stage
	 * makes sure background is below everything and that playerShip is above
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		backgroundSpace.drawBelow(batch, parentAlpha);
		super.draw(batch, parentAlpha);
		playerShip.drawAbove(batch, parentAlpha);
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
	 * @return the current health
	 */
	public int getPlayerHealth(){
		return playerShip.getCurrentHealth();
	}
	
	/**
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

	/** 
	 * @return True if no enemies on the map 
	 */ 
	public boolean noActiveSpawns(){ 
		Array<MovableEntity> spawns  = new Array<MovableEntity>(); 
		SnapshotArray<Actor> toSearch = getChildren(); 
		for(Actor actor: toSearch){ 
			if(actor instanceof EnemyShip || actor instanceof SpawnPattern){ 
				MovableEntity entity = (MovableEntity)actor; 
				spawns.add(entity); 
			} 
		} 
		return spawns.size == 0;
	}
	
	/**
	 * Resets everything and recreates first level
	 */
	public void resetGame(){
		playerShip.stay();
		currentScore=0;
		clear();
		level = new Neptune(this);
		addActor(level);
		addActor(playerShip);
		playerShip.resetHealth();
	}
	
	/**
	 * Starts the next level
	 */
	public void startNextLevel(){
		playerShip.stay();
		clear();
		level=nextLevel(level);
		addActor(level);
		addActor(playerShip);
	}
}
