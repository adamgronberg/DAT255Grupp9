package levels;

import assets.ImageAssets;
import ships.EnemyShip;
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

	
	private static final float spawnTimes[] = {3000000000f,3000000000f,3000000000f,300000000f,300000000f,3000000000f,3000000000f, 3000000000f};
	
	private static final String[][] toSpawn = { {"Kamikaze:100:760:Pyramid:3:150000000f:50","Kamikaze:430:760:Pyramid:3:150000000f:50"},
		{"Basic:85:760","Basic:300:760","Kamikaze:50:760","Kamikaze:400:760","Stealth:440:700"},
		{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
		{"Basic:125:760", "Basic:225:760", "Basic:325:760","Stealth:440:700"},
		{"Basic:75:760", "Basic:175:760", "Basic:275:760", "Basic:375:760"},
		{"Kamikaze:10:760","Kamikaze:225:760","Kamikaze:440:760","Stealth:0:700","Stealth:440:700"},
		{"Scout:80:760:Vertical:5:275000000f", "Scout:225:760:Vertical:5:275000000f","Scout:370:760:Vertical:5:275000000f"},
		{"Basic:225:760:Pyramid:3:300000000f:50","Stealth:440:700"} };

	EscapingShip escapingShip;
	
	/**
	 * Constructor
	 * @param gameLogic The game logic
	 */
	public Uranus(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		gameLogic.setBackground(ImageAssets.uranus);
		escapingShip = new EscapingShip(200,650, gameLogic);
		gameLogic.addActor(escapingShip);
		levelname="Uranus";	
		EnemyShip.turnOffScore();
	
	}
	
	/**
	 * Called when act on parent is called
	 * Stops spawning when done
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if(levelSpawnDone && escapingShip.isAlive()){
			spawnSynch = 0;
			levelSpawnDone = false;
		}
	}

	/**
	 * Mission ends when the escaping ship has died
	 */
	@Override
	public boolean missionCompleted() {
		
		 if(!escapingShip.isAlive()){
		EnemyShip.turnOnScore();
		gameLogic.addScore(200);
		return true;
		 }
		 return false;
		
	}

	/**
	 * If you don't win the level within the timelimit of 3 min you fail
	 */
	@Override public boolean missionFailed() {
			return false;
	}
}
