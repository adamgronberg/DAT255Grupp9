package levels;

import spacegame.GameLogic;
import ships.BossShip;

/**
 * 
 * @author Grupp9
 *
 * Contains Earth spawns as well as win condition
 * 
 */
public class Earth extends Level{

	private static final float spawnTimes[]  = {6000000000f,200000000f,200000000f,200000000f,200000000f,200000000f,200000000f,5000000000f,5000000000f,4000000000f,5000000000f};
	private static final String[][] toSpawn = {
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760"},
												{"BASIC:125:760", "BASIC:225:760", "BASIC:325:760"},
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760","HEAVY:10:760","HEAVY:420:760"},
												{"BASIC:125:760", "BASIC:225:760", "BASIC:325:760"},
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760"},
												{"BASIC:125:760", "BASIC:225:760", "BASIC:325:760"},
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760"},
												{"VPATTERN#BIGLASER#3#300000000f#200:225:760"},
												{"MULTISHOOTER:150:760","MULTISHOOTER:300:760"},
												{"STEALTH:0:700","STEALTH:440:700"},
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