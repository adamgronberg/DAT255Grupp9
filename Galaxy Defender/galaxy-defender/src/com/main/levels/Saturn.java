package levels;

import assets.ImageAssets;
import spacegame.GameLogic;

/**
 * Contains Saturn spawns as well as win condition
 * @author Grupp9
 */
public class Saturn extends Level {

	private static final float spawnTimes[] = {5000000000f,5000000000f,500000000f,5000000000f,5000000000f,5000000000f,5000000000f,5000000000f,5000000000f,5000000000f};
	private static final String[][] toSpawn = {{"Belt:5:10:205000000f"},
												{"MultiShooter:225:760","Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760","Kamikaze:150:760", "Kamikaze:300:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Belt:5:10:205000000f"},
												{"MultiShooter:50:760", "Stealth:225:700", "MultiShooter:400:760"},
												{"Belt:5:10:205000000f"},
												{"BigLaser:80:760:Pyramid:3:300000000f:60", "BigLaser:330:760:Pyramid:3:300000000f:60"},
												{"Belt:7:10:205000000f"},
												{"MultiShooter:225:760:Pyramid:3:300000000f:100"},
												{"Belt:10:15:205000000f"}};
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Saturn(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		gameLogic.setBackground(ImageAssets.saturn);
		levelname = "Saturn";
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.noActiveSpawns();
	}

	//// Unused method ////
	@Override public boolean missionFailed() {return false;}
}
