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

	private static final float spawnTimes[] = {6000000000f,3500000000f,6000000000f,4000000000f,6000000000f};
	private static final String[][] toSpawn = {{"ASTBELT#5#10#205000000f"},
												{"MULTISHOOTER:300:760", "STEALTH:200:700", "STEALTH:50:700"},
												{"ASTBELT#5#10#205000000f"},
												{"MULTISHOOTER:200:760", "STEALTH:100:700", "STEALTH:300:700"},
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
