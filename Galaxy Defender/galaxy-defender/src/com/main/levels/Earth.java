package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Earth spawns as well as win condition
 * 
 */
public class Earth extends Level{

	private static final float spawnTimes[] = {6000000000f, 2000000000f,1000000000f,6000000000f};
	private static final String[][] toSpawn = {{"BASIC:50:760","BASIC:100:760","BASIC:150:760","BASIC:200:760","BASIC:250:760"},
												{"HEAVY:50:760","HEAVY:100:760","BASIC:150:760","BASIC:200:760","STEALTH:250:700"},
												{"BIGLASER:150:760"},
												{"HEAVY:68:760","BIGLASER:105:760","MULTISHOOTER:164:760","BASIC:245:760","STEALTH:2580:700"},
												{"TURRET:300:700"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Earth(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Earth";
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}

	//Unused method
	@Override
	public boolean missionFailed() {return false;}
}