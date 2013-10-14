package levels;

import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Neptune spawns as well as win condition
 */
public class Neptune extends Level {
	
	                                 
	private static final float spawnTimes[] = {5000000000f, 3000000000f,5000000000f, 3000000000f,2500000000f,3000000000f,2000000000f};
	private static final String[][] toSpawn = 
		{{"Scout:350:750:Vertical:2:275000000f","Scout:450:760:Vertical:2:275000000f"},
		{"Basic:50:760:Pyramid:3:150000000f:50","Basic:350:760:Pyramid:3:150000000f:50", "Heavy:150:740","Heavy:260:740"},
		{"Scout:50:750:Vertical:2:275000000f","Scout:160:760:Vertical:2:275000000f"},
		{"Heavy:300:760:Pyramid:3:150000000f:50"}, 
		{"Heavy:75:760:Pyramid:3:150000000f:50"},  
		{"Heavy:200:760:Pyramid:3:150000000f:50"}, 
		{"Heavy:225:760:Pyramid:3:150000000f:50"}};
									

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
		return levelSpawnDone && gameLogic.noActiveSpawns();
	}

	/////// Unused method ///////
	@Override public boolean missionFailed() {return false;}
}
