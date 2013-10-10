package levels;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ships.EnemyShip;
import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Mars spawns as well as win condition
 * 
 */
public class Mars extends Level{

	private static final float spawnTimes[] = {6000000000f, 6000000000f, 6000000000f, 9000000000f};
	private static final String[][] toSpawn = {{"HEAVY:100:760","HEAVY:200:760","HEAVY:300:760"},
												{"MULTISHOOTER:150:760","MULTISHOOTER:250:760","PATTERN#SCOUT#2#275000000f:100:750","PATTERN#SCOUT#2#275000000f:350:750"},
												{"BIGLASER:50:760","BIGLASER:350:760","PATTERN#SCOUT#2#150000000f#:200:750","vPATTERN#SCOUT#3#150000000f#:250:750","PATTERN#SCOUT#2#150000000f#:300:750"},
												{"VPATTERN#BASIC#6#150000000f#50:225:760"}};
	
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Mars(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		levelname = "Mars";
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	/**
	 * Do not let any enemy pass you.
	 */
	@Override
	public boolean missionCompleted() {
		return levelSpawnDone && gameLogic.activeSpawns();
	}
	
	/**
	 * Mission failed if any enemy escaped
	 */
	@Override
	public boolean missionFailed() {
		
		for(Actor actor: gameLogic.getChildren()){
			if(actor instanceof EnemyShip){
				if(actor.getY()<0){
					return true;
				}
			}
		}
		return false;
	}

}