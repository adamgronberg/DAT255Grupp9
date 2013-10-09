package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Neptune spawns as well as win condition
 */
public class Neptune extends Level{
	
	private static final float spawnTimes[] = {5000000000f,5000000000f,5000000000f};
	private static final String[][] toSpawn = {{"BASIC:200:760","HEAVY:300:760","KAMIKAZE:250:760",
								"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760"},
								{"BASIC:200:760","HEAVY:300:760","KAMIKAZE:250:760",
									"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760", "MULTISHOOTER:325:760"},
									{"BASIC:200:760","HEAVY:300:760","STEALTH:150:760","KAMIKAZE:250:760",
										"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Neptune(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Neptune";
	}
	
	/**
	 * Defeat as many enemies as you can
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}

	
}
