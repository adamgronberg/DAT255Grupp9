package levels;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.TimeUtils;

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
	
	private static final float spawnTimes[] = {6000000000f,5000000000f,6000000000f,3000000000f,5000000000f};
	private static final String[][] toSpawn = {
												{"BASIC:225:710","BASIC:175:720","BASIC:275:720","BASIC:125:730","BASIC:325:730","BASIC:75:740","BASIC:375:740","BASIC:25:750", "BASIC:425:750"},
												{"BASIC:120:760","HEAVY:300:760","KAMIKAZE:350:760","BASIC:160:710","HEAVY:10:720","KAMIKAZE:450:730"},
												{"BASIC:240:760","HEAVY:20:760","KAMIKAZE:330:760"},
												{"BASIC:200:760","HEAVY:300:760","KAMIKAZE:250:760", "PATTERN#SCOUT#5#275000000f:300:760","PATTERN#SCOUT#5#275000000f:300:760"},
												{"BASIC:200:760","HEAVY:300:760","KAMIKAZE:250:760"}};
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
		if(!levelSpawnDone){
			spawn();
		}
		else if(levelSpawnDone && escapingShip.isAlive()){
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
}
