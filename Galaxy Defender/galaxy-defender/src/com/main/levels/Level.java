package levels;

import java.util.LinkedList;

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
		
		LinkedList<String> fifo = new LinkedList<String>();
		
		for(String info: spawnInfo){
			fifo.add(info);
		}

		Object newObject = null;
		
		if(fifo.peek().matches("Basic|Scout|Heavy|BigLaser|Asteroid|MultiShooter")){
			try {
				newObject = Class.forName("ships." + fifo.pop() + "Ship" ).getDeclaredConstructors()[0].newInstance(
						Float.parseFloat(fifo.pop()), Float.parseFloat(fifo.pop()));
			} catch (Exception e) {e.printStackTrace();}
		}
		
		else if(fifo.peek().matches("Stealth|MiniBoss|Kamikaze")){
			try {
				newObject = Class.forName("ships." + fifo.pop() + "Ship" ).getDeclaredConstructors()[0].newInstance(
						Float.parseFloat(fifo.pop()), Float.parseFloat(fifo.pop()), gameLogic.playerShip);
			} catch (Exception e) {e.printStackTrace();}
		}
		
		else if(fifo.peek().matches("Escaping")){
			try {
				newObject = Class.forName("ships." + fifo.pop() + "Ship" ).getDeclaredConstructors()[0].newInstance(
						Float.parseFloat(fifo.pop()), Float.parseFloat(fifo.pop()), gameLogic);
			} catch (Exception e) {e.printStackTrace();}
		}
		
		else if(fifo.peek().matches("Belt")){
			fifo.pop();
			newObject = new AsteroidBelt(Integer.parseInt(fifo.pop()), Integer.parseInt(fifo.pop()), 
					Float.parseFloat(fifo.pop()));
		}
		
		else if(fifo.peek().equals("Circling")){
			fifo.pop();
			newObject = new CirclingShip(Float.parseFloat(fifo.pop()), Float.parseFloat(fifo.pop()), 
									Float.parseFloat(fifo.pop()), fifo.pop().equals("true"));
		}
		
		if(!fifo.isEmpty()){

			if(fifo.peek().matches("Vertical")){
				fifo.pop();
				newObject = new VerticalPattern(Integer.parseInt(fifo.pop()), 
						Float.parseFloat(fifo.pop()), (EnemyShip)newObject);
			}
			
			else if(fifo.peek().matches("Pyramid")){
				fifo.pop();
				newObject = new PyramidPattern(Integer.parseInt(fifo.pop()), 
						Float.parseFloat(fifo.pop()), Integer.parseInt(fifo.pop()), (EnemyShip)newObject);
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
