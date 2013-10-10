package levels;


import ships.EscapingShip;
import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Uranus spawns as well as win condition
 * 
 */
public class Uranus extends Level{
	
	private static final float spawnTimes[] = {6000000000f,5000000000f,6000000000f,300000000f,300000000f,5000000000f,5000000000f, 3000000000f};
	private static final String[][] toSpawn = {
												{"VPATTERN#BASIC#9#150000000f#50:225:760"},
												{"HEAVY:10:760","BASIC:85:760","HEAVY:225:760","BASIC:300:760","HEAVY:440:760","KAMIKAZE:50:760","KAMIKAZE:400:760"},
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760"},
												{"BASIC:125:760", "BASIC:225:760", "BASIC:325:760"},
												{"BASIC:75:760", "BASIC:175:760", "BASIC:275:760", "BASIC:375:760"},
												{"KAMIKAZE:10:760","KAMIKAZE:225:760","KAMIKAZE:440:760"},
												{"PATTERN#SCOUT#5#275000000f:80:760", "PATTERN#SCOUT#5#275000000f:225:760","PATTERN#SCOUT#5#275000000f:370:760"},
												{"VPATTERN#BASIC#3#300000000f#100:225:760"}};
	EscapingShip escapingShip;
	/**
	 * Constructor
	 * @param gameLogic The game logic
	 */
	public Uranus(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		escapingShip = new EscapingShip(200,700, gameLogic);
		gameLogic.addActor(escapingShip);
		levelname="Uranus";		
	}
	
	/**
	 * Called when act on parent is called
	 * Stops spawning when done
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if(levelSpawnDone && escapingShip.isAlive()){
			spawnSynch =0;
			levelSpawnDone = false;
		}
	}

	/**
	 * TODO: Should have a mission complete condition
	 */
	@Override
	public boolean missionCompleted() {
		
		return levelSpawnDone && gameLogic.activeSpawns();
		
	}

	//Unused method
	@Override
	public boolean missionFailed() {return false;}
}
