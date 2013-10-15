package levels;

import assets.ImageAssets;
import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Earth spawns as well as win condition
 * 
 */
public class Earth extends Level{	

	private static final float spawnTimes[]  = {6000000000f,200000000f,200000000f,200000000f,200000000f,200000000f,200000000f,5000000000f,5000000000f,4000000000f,5000000000f};
	private static final String[][] toSpawn = { {"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760","Heavy:10:760","Heavy:420:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"BigLaser:225:760:Pyramid:3:300000000f:200"},
												{"MultiShooter:150:760","MultiShooter:300:760"},
												{"Stealth:0:700","Stealth:440:700"},
												{"Boss:-100:650"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Earth(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		gameLogic.setBackground(ImageAssets.earth);
		levelname = "Earth";		
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.noActiveSpawns();
	}

	////// Unused method //////
	@Override public boolean missionFailed() {return false;}
}