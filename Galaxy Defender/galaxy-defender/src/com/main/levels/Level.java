package levels;

import ships.Asteroid;
import ships.BasicShip;
import ships.BigLaserShip;
import ships.BossShip;
import ships.EscapingShip;
import ships.HeavyShip;
import ships.KamikazeShip;
import ships.MultiShooterShip;
import ships.ScoutShip;
import ships.StealthShip;
import ships.TurretShip;
import spacegame.GameLogic;
import spawnlogic.AsteroidBelt;
import spawnlogic.VPattern;
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
	private void decideSpawn(String s){
		String[] spawnInfo = s.split(":");
		
		if(spawnInfo[0].equals("BASIC")){
			gameLogic.addActor(new BasicShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("HEAVY")){
			gameLogic.addActor(new HeavyShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("SCOUT")){
			gameLogic.addActor(new ScoutShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("ASTEROID")){
			gameLogic.addActor(new Asteroid(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("BIGLASER")){
			gameLogic.addActor(new BigLaserShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("STEALTH")){
			gameLogic.addActor(new StealthShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), gameLogic.playerShip));
		}
		
		else if(spawnInfo[0].equals("TURRET")){
			gameLogic.addActor(new TurretShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), gameLogic.playerShip));
		}
		
		else if(spawnInfo[0].equals("ESCAPING")){
			gameLogic.addActor(new EscapingShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), gameLogic));
		}
		
		else if(spawnInfo[0].equals("MULTISHOOTER")){
			gameLogic.addActor(new MultiShooterShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
		}
		
		else if(spawnInfo[0].equals("KAMIKAZE")){
			gameLogic.addActor(new KamikazeShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), gameLogic.playerShip));
		}
		
		else if(spawnInfo[0].startsWith("ASTBELT")){
			String[] detailedSpawn = spawnInfo[0].split("#");
			gameLogic.addActor(new AsteroidBelt(Integer.parseInt(detailedSpawn[1]), Integer.parseInt(detailedSpawn[2]), Float.parseFloat(detailedSpawn[3])));
		}
		
		else if(spawnInfo[0].startsWith("PATTERN")){
			String[] detailedSpawn = spawnInfo[0].split("#");
			gameLogic.addActor(new VerticalPattern(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), 
					Integer.parseInt(detailedSpawn[2]), Float.parseFloat(detailedSpawn[3]), detailedSpawn[1], gameLogic));
		}
		else if(spawnInfo[0].startsWith("VPATTERN")){
			String[] detailedSpawn = spawnInfo[0].split("#");
			gameLogic.addActor(new VPattern(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), 
					Integer.parseInt(detailedSpawn[2]), Float.parseFloat(detailedSpawn[3]), detailedSpawn[1], gameLogic, Integer.parseInt(detailedSpawn[4])));
		}
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
