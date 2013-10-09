package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Uranus spawns as well as win condition
 * 
 */
public class Uranus extends Level{
	
	private static final float spawnTimes[] = {6000000000f};
	private static final String[][] toSpawn = {{"ESCAPING:200:700"}};
	
	/**
	 * Constructor
	 * @param gameLogic The game logic
	 */
	public Uranus(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname="Uranus";		
	}

	/**
	 * TODO: Should have a mission complete condition
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}
}
