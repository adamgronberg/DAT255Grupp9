package com.spawnlogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.ships.BasicShip;
import com.ships.EnemyShip;
import com.ships.ScoutShip;
import com.spacegame.GameLogic;
import com.spacegame.GameScreen;

/**
 * 
 * @author Grupp9
 *
 * Contains different spawning patterns
 *
 */
public class SpawnPattern extends Actor{

	private int totalNumberOfEnemies;
	private int currentNumberOfEnemies = 0;
	private float timePassedSinceSpawn = 0;
	private float spawnX;
	private float spawnY;
	private float timeBetweenEnemy;
	private GameLogic gameLogic;
	private String enemyType;
	
	
	/**
	 * Constructor
	 * @param spawnX x-led the enemies should spawn
	 * @param spawnY y-led the enemies should spawn
	 * @param totalNumberOfEnemies Total number of enemies to spawn
	 * @param timeBetweenEnemy time in nanoseconds between spawns
	 * @param enemyType The Class name of the ship you want to spawn
	 * @param gameLogic The stage that uses the spawn pattern
	 */
	public SpawnPattern(float spawnX, float spawnY, 
			int totalNumberOfEnemies, float timeBetweenEnemy, 
			String enemyType, GameLogic gameLogic){
		this.totalNumberOfEnemies = totalNumberOfEnemies;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.gameLogic = gameLogic;
		this.timeBetweenEnemy = timeBetweenEnemy;
		this.enemyType = enemyType;
		Gdx.app.log( GameScreen.LOG, "SpawnPattern created!");
	}
	
	
	/**
	 * Called when act is called in its stage
	 * Spawns 'totalNumberOfEnemies' on the same 
	 * spot with a time difference of 'timeBetweenEnemy'
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(TimeUtils.nanoTime() - timePassedSinceSpawn > timeBetweenEnemy){
			EnemyShip enemyShip;
			if(enemyType.equals("ScoutShip")) enemyShip = new ScoutShip(spawnX, spawnY);
			else enemyShip = new BasicShip(spawnX, spawnY);
			
			Gdx.app.log( GameScreen.LOG, "Spawn: " + currentNumberOfEnemies);
			
			gameLogic.addEnemyShips(enemyShip);
			currentNumberOfEnemies++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		if(currentNumberOfEnemies >= totalNumberOfEnemies){
			Gdx.app.log( GameScreen.LOG, "Spawn patter complete! Total number of ships: " + currentNumberOfEnemies);
			remove();
		}
	}
	
}
