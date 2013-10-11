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
	private static final String[][] toSpawn = { //
												{"Basic:225:760:Pyramid:9:150000000f:50"},
												{"Heavy:10:760","Basic:85:760","Heavy:225:760","Basic:300:760","Heavy:440:760","Kamikaze:50:760","Kamikaze:400:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Basic:125:760", "Basic:225:760", "Basic:325:760"},
												{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
												{"Kamikaze:10:760","Kamikaze:225:760","Kamikaze:440:760"},
												{"Scout:80:760:Vertical:5:275000000f", "Scout:225:760:Vertical:5:275000000f","Scout:370:760:Vertical:5:275000000f"},
												{"Basic:225:760:Pyramid:3:300000000f:50"}};
																		
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
