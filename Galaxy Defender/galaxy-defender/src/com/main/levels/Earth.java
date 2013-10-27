package levels;

import assets.ImageAssets;
import spacegame.GameLogic;

/**
 * Contains Earth spawns as well as win condition
 * @author Grupp9
 */
public class Earth extends Level{	

	private static final float spawnTimes[]  = {6000000000f,600000000f,600000000f,600000000f,600000000f,600000000f,600000000f,2000000000f,2000000000f,2000000000f,10000000000f,10000000000f,10000000000f,10000000000f,10000000000f,10000000000f,10000000000f};
	private static final String[][] toSpawn = { {"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760",
												"BigLaser:225:760:Pyramid:3:300000000f:150"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760","Heavy:30:760","Heavy:400:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760",
												"Circling:85:760:600:false:Vertical:5:600000000f", "Circling:355:760:600:true:Vertical:5:600000000f"},
												{"Scout:180:750:Vertical:10:400000000f","Scout:300:760:Vertical:10:400000000f"},
												{"Circling:50:760:400:false:Vertical:5:600000000f", "Circling:390:760:400:true:Vertical:5:600000000f"},
												{"MultiShooter:150:760","MultiShooter:300:760", "Scout:50:750:Vertical:2:275000000f","Scout:160:760:Vertical:2:275000000f"},
												{"Boss:-100:650"},
												{"BigLaser:225:760:Pyramid:3:300000000f:200","Scout:90:750:Vertical:10:275000000f","Scout:390:760:Vertical:10:275000000f"},
												{"MultiShooter:225:760:Pyramid:3:300000000f:100","Scout:180:750:Vertical:10:275000000f","Scout:300:760:Vertical:10:275000000f"},
												{"BigLaser:225:760:Pyramid:3:300000000f:200","Scout:90:750:Vertical:10:275000000f","Scout:390:760:Vertical:10:275000000f"},
												{"MultiShooter:225:760:Pyramid:3:300000000f:100","Scout:180:750:Vertical:10:275000000f","Scout:300:760:Vertical:10:275000000f"},
												{"BigLaser:225:760:Pyramid:3:300000000f:200","Scout:90:750:Vertical:10:275000000f","Scout:390:760:Vertical:10:275000000f"},
												{"MultiShooter:225:760:Pyramid:3:300000000f:100","Scout:180:750:Vertical:10:275000000f","Scout:300:760:Vertical:10:275000000f"}};
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
	 * Destroy the boss and kill all his minions!!!!
	 */
	@Override
	public boolean missionCompleted() {
		if(levelSpawnDone && gameLogic.noActiveSpawns()){
			if(gameLogic.getNumberOfAnimations() == 0){
				return true;
			}
		}
		return false;
	}

	//// Unused method ////
	@Override public boolean missionFailed() {return false;}
}