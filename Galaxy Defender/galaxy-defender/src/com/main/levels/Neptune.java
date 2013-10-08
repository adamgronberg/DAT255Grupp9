package levels;

import com.badlogic.gdx.utils.TimeUtils;

import collisiondetection.CollisionDetection;
import ships.Asteroid;
import ships.BasicShip;
import ships.BigLaserShip;
import ships.HeavyShip;
import ships.KamikazeShip;
import ships.MultiShooterShip;
import ships.ScoutShip;
import ships.StealthShip;
import ships.TurretShip;
import spacegame.GameLogic;
import spawnlogic.AsteroidBelt;
import spawnlogic.SpawnPattern;

public class Neptune extends Level{
	
	private float spawnTime[] = {6000000000f,6000000000f,6000000000f};
	private int spawnTimers = 0;
	private float lastWaveTime = 0;
	
	private String[][] spawn = {{"BASIC:200:760","HEAVY:300:760","SCOUT:200:760","ASTEROID:150:760","KAMIKAZE:250:760",
								"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760","ASTBELT#5#10#205000000f"},
								{"BASIC:200:760","HEAVY:300:760","SCOUT:100:760","ASTEROID:150:760","KAMIKAZE:250:760",
									"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760", "MULTISHOOTER:325:760"},
									{"BASIC:200:760","HEAVY:300:760","SCOUT:100:760","ASTEROID:150:760","KAMIKAZE:250:760",
										"BIGLASER:350:760","PATTERN#SCOUT#5#275000000f:300:760","ASTBELT#5#10#205000000f"}};
	
	public Neptune(GameLogic gl){
		super(gl);
		levelname="Neptune";
		spawn();
	}

	@Override
	public void spawn() {
		HeavyShip HS=new HeavyShip(200,700);
		gameLogic.addActor(HS);
		
	}

	@Override
	public void act(float delta) {
		if(spawn.length != spawnTime.length){
			System.out.println("Error! The number of spawn times are not the same as number of spawnes!");
			return;
		}
		
		if (TimeUtils.nanoTime() - lastWaveTime > spawnTime[spawnTimers]){
			
			for(String s: spawn[spawnTimers]){
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
					gameLogic.addActor(new TurretShip(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2])));
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
					gameLogic.addActor(new SpawnPattern(Float.parseFloat(spawnInfo[1]), Float.parseFloat(spawnInfo[2]), 
							Integer.parseInt(detailedSpawn[2]), Float.parseFloat(detailedSpawn[3]), detailedSpawn[1], gameLogic));
				}
			}
			lastWaveTime = TimeUtils.nanoTime();
			spawnTimers++;
			if (spawnTimers == spawnTime.length){
				spawnTimers = 0;
			}
		}
		
	}

	@Override
	public boolean missionCompleted() {
		return (CollisionDetection.getEnemyShipAmount()==0);
	}

	
}
