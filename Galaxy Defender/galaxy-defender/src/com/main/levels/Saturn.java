package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Saturn spawns as well as win condition
 * 
 */
public class Saturn extends Level{

	private static final float spawnTimes[] = {6000000000f,6000000000f,6000000000f};
	private static final String[][] toSpawn = {{"ASTBELT#5#10#205000000f"},
												{"ASTBELT#5#10#205000000f"},
												{"ASTBELT#5#10#205000000f"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Saturn(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Saturn";
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}

}
