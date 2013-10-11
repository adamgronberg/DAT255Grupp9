package levels;

import ships.CirclingShip;
import ships.EnemyShip;
import spacegame.GameLogic;
import spawnlogic.AsteroidBelt;
import spawnlogic.PyramidPattern;
import spawnlogic.VerticalPattern;
import collisiondetection.OutOfBoundsDetection;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 * Abstract level, contains general spawning logic 
 */
public abstract class Level extends Actor {
	
	protected float spawnTimes[];
	protected String[][] toSpawn;
	protected String levelname;
	protected float timer;
	protected GameLogic gameLogic;
	protected int spawnSynch = 0;
	protected float lastWaveTime = 0;
	protected boolean levelSpawnDone = false;

	/**
	 * Constructor
	 * @param gameLogic	The game logic
	 * @param toSpawn	The array containing spawn info for level
	 * @param spawnTimes	The time between each spawn wave
	 */
	public Level(GameLogic gameLogic, String[][] toSpawn, float[] spawnTimes){
		super();
		this.gameLogic = gameLogic;
		this.toSpawn = toSpawn;
		this.spawnTimes = spawnTimes;
	}
	
	/**
	 * Returns level name
	 */
	public String getName(){
		return levelname;
	}
	
	/**
	 * Spawns the enemies on the level
	 */
	public void spawn(){
		if (TimeUtils.nanoTime() - lastWaveTime > spawnTimes[spawnSynch]){
			for(String s: toSpawn[spawnSynch]){
				decideSpawn(s);
			}
			lastWaveTime = TimeUtils.nanoTime();
			spawnSynch++;
			if (spawnSynch == spawnTimes.length) levelSpawnDone = true;
		}
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

		OutOfBoundsDetection.checkOutOfBounds(gameLogic.getChildren());
	}
	
	/**
	 * Decides what to spawn
	 * @param s The string containing spawn information
	 */
	private void decideSpawn(String s) {
		String[] spawnInfo = s.split(":");
		
		Object newObject = null;
		
		if(spawnInfo[0].matches("Basic|Scout|Heavy|BigLaser|Asteroid|MultiShooter")){
			try {
				newObject = Class.forName("ships." + spawnInfo[0] + "Ship" ).getDeclaredConstructors()[0].newInstance(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]));
			} catch (Exception e) {e.printStackTrace();}
		}
		
		else if(spawnInfo[0].matches("Stealth|Turret|Kamikaze|")){
			try {
				newObject = Class.forName("ships." + spawnInfo[0] + "Ship" ).getDeclaredConstructors()[0].newInstance(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), gameLogic.playerShip);
			} catch (Exception e) {e.printStackTrace();}
		}
		
		else if(spawnInfo[0].matches("Belt")){
			newObject = new AsteroidBelt(Integer.parseInt(spawnInfo[1]), Integer.parseInt(spawnInfo[2]), Float.parseFloat(spawnInfo[3]));
		}
		
		else if(spawnInfo[0].equals("Circling")){
			boolean circleClockWise = false;
			if(spawnInfo[4].equals("true")) circleClockWise = true;
			newObject = new CirclingShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), 
									Float.parseFloat(spawnInfo[3]), circleClockWise);
		}
		
		if(spawnInfo.length>=4){
			
			if(spawnInfo[3].matches("Vertical")){
				newObject = new VerticalPattern(Integer.parseInt(spawnInfo[4]), Float.parseFloat(spawnInfo[5]), (EnemyShip)newObject);
			}
			else if(spawnInfo[3].matches("Pyramid")){
				newObject = new PyramidPattern(Integer.parseInt(spawnInfo[4]), Float.parseFloat(spawnInfo[5]), Integer.parseInt(spawnInfo[6]), (EnemyShip)newObject);
			}
		}	
		
		if(newObject == null){
			System.out.println("Error in spawnSynch: " + spawnSynch);
			return;
		}
		
		gameLogic.addActor((Actor) newObject);
	}
	
	/**
	 * @return If the mission is done or not
	 */
	abstract public boolean missionCompleted();
	
	/**
	 * @return If the mission failed
	 */
	abstract public boolean missionFailed();
}
