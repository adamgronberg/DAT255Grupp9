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

	private static final float spawnTimes[] = {6000000000f,5000000000f,700000000f,5000000000f,6000000000f,3000000000f, 5000000000f};
	private static final String[][] toSpawn = {{"ASTBELT#5#10#205000000f"},
												{"MULTISHOOTER:300:760","BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760", "KAMIKAZE:225:760"},
												{"BASIC:125:760", "BASIC:225:760", "BASIC:325:760"},
												{"ASTBELT#5#10#205000000f"},
												{"MULTISHOOTER:200:760", "STEALTH:100:700", "KAMIKAZE:50:760", "KAMIKAZE:50:760", "BASIC:50:760"},
												{"ASTBELT#5#10#205000000f"},
												{"VPATTERN#BIGLASER#3#300000000f#60:80:760", "VPATTERN#BIGLASER#3#300000000f#60:310:760"}};
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

	//Unused method
	@Override
	public boolean missionFailed() {return false;}
}
