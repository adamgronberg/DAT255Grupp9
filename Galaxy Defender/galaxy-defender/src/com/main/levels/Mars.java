package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Mars spawns as well as win condition
 * 
 */
public class Mars extends Level{

	private static final float spawnTimes[] = {6000000000f};
	private static final String[][] toSpawn = {{"KAMIKAZE:100:700"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Mars(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Mars";
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}

}