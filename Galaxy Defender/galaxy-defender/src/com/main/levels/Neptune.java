package levels;

import ships.BossShip;
import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Neptune spawns as well as win condition
 */
public class Neptune extends Level{

	                                          //ANvänds inte, 1-2        2-3
	private static final float spawnTimes[] = {5000000000f, 3000000000f,5000000000f, 3000000000f,2500000000f,3000000000f,2000000000f};
	private static final String[][] toSpawn = {{"PATTERN#SCOUT#2#275000000f:350:750","PATTERN#SCOUT#2#275000000f:450:760"},
								{"VPATTERN#BASIC#3#150000000f#50:50:760","VPATTERN#BASIC#3#150000000f#50:350:760", "HEAVY:150:740","HEAVY:260:740"},
								{"PATTERN#SCOUT#2#275000000f:50:750","PATTERN#SCOUT#2#275000000f:160:760"},
								{"VPATTERN#HEAVY#3#550000000f#50:300:760"}, // '.'
								{"VPATTERN#HEAVY#3#550000000f#50:75:760"},  //'.'
								{"VPATTERN#BASIC#5#150000000f#50:200:760"}, // V
								{"VPATTERN#BASIC#9#150000000f#50:225:760"},};// V
									

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

	/////// Unused method ///////
	@Override public boolean missionFailed() {return false;}
}
