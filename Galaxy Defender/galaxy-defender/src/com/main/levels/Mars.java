package levels;

import assets.ImageAssets;

import com.badlogic.gdx.scenes.scene2d.Actor;

import ships.EnemyShip;
import spacegame.GameLogic;

/**
 * Contains Mars spawns as well as win condition
 * @author Grupp9
 */
public class Mars extends Level {

	private static final float spawnTimes[] = {6000000000f, 3000000000f, 3000000000f, 3000000000f, 3000000000f,3000000000f};
	private static final String[][] toSpawn = {{"Heavy:225:760:Pyramid:5:300000000f:50"},
												{"MultiShooter:150:760","MultiShooter:250:760","Scout:100:750:Vertical:2:275000000f","Scout:350:750:Vertical:2:275000000f"},
												{"BigLaser:70:760","BigLaser:340:760","Scout:150:750:Vertical:5:275000000f","Scout:250:750:Vertical:10:275000000f","Scout:350:750:Vertical:5:275000000f"},
												{"Basic:225:760:Pyramid:6:300000000f:50"},
												{"Heavy:100:760","Heavy:200:760","Heavy:300:760"},
												{"Basic:225:760:Pyramid:9:300000000f:50"}};
	/**
	 * Constructor
	 * @param gl The game logic
	 */
	public Mars(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		gameLogic.setBackground(ImageAssets.mars);
		levelname = "Mars";
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