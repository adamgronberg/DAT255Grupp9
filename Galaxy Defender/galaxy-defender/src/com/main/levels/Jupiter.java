package levels;

import assets.ImageAssets;
import ships.MiniBossShip;
import spacegame.GameLogic;

/**
 * 
 * @author Grupp9
 *
 * Contains Jupiter spawns as well as win condition
 * 
 */
public class Jupiter extends Level{

	private static final float spawnTimes[] = {12000000000f, 12000000000f};
	private static final String[][] toSpawn = 	{{"Circling:50:760:400:false:Vertical:5:600000000f", 
													"Circling:390:760:400:true:Vertical:5:600000000f"},
												{"Circling:85:760:600:false:Vertical:5:600000000f",
													"Circling:355:760:600:true:Vertical:5:600000000f"}};
	private MiniBossShip miniBossShip;
	
	/**
	 * Constructor
	 * @param gameLogic	The game logic
	 */
	public Jupiter(GameLogic gameLogic){
		super(gameLogic, toSpawn, spawnTimes);
		gameLogic.setBackground(ImageAssets.jupiter);
		miniBossShip = new MiniBossShip(200,650, gameLogic);
		gameLogic.addActor(miniBossShip);
		levelname = "Jupiter";
	}
	
	/**
	 * Called when act on parent is called
	 * Stops spawning when done
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if(levelSpawnDone && miniBossShip.isAlive()){
			spawnSynch = 0;
			levelSpawnDone = false;
		}
	}
	
	/**
	 * Survive all asteroid belts
	 */
	@Override
	public boolean missionCompleted() {
		return !miniBossShip.isAlive();
	}

	////// Unused method //////
	@Override public boolean missionFailed() {return false;}
}