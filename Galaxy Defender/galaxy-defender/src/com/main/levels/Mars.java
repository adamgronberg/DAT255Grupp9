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
public class Mars extends Level {	//Basic:225:760:Pyramid:6:300000000f:50

	private static final float spawnTimes[] = {6000000000f, 6000000000f, 6000000000f, 9000000000f};
	private static final String[][] toSpawn = {{"Heavy:100:760","Heavy:200:760","Heavy:300:760"},
												{"MultiShooter:150:760","MultiShooter:250:760","Scout:100:750:Vertical:2:275000000f","Scout:350:750:Vertical:2:275000000f"},
												{"BigLaser:50:760","BigLaser:350:760","Scout:200:750:Vertical:2:275000000f","Scout:250:750:Vertical:3:275000000f","Scout:300:750:Vertical:2:275000000f"},
												{"Basic:225:760:Pyramid:6:300000000f:50"}};
	
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
		return levelSpawnDone && gameLogic.noActiveSpawns();
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