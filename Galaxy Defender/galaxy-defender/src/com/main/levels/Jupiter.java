package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Jupiter spawns as well as win condition
 * 
 */
public class Jupiter extends Level{

	private static final float spawnTimes[] = {6000000000f, 5000000000f};
	private static final String[][] toSpawn = {{"MULTISHOOTER:300:760"}, {"TURRET:300:760"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Jupiter(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Jupiter";
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}

}