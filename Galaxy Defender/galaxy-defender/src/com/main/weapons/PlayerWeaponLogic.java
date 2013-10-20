package weapons;

import ships.PlayerShip;
import spacegame.GameLogic;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Handles player weapons
 * @author Grupp9
 */
public class PlayerWeaponLogic {
	
	private static final int STARTING_LASER_COST = 20, STARTING_EMP_COST = 10, STARTING_MISSILE_COST = 10;
	private int currentUpgradeCostLaser = STARTING_LASER_COST, currentUpgradeCostEMP = STARTING_EMP_COST, currentUpgradeCostMissile = STARTING_MISSILE_COST;
	
	private static boolean ifAutoShooting = true; //TODO: For testing
	private static enum Lasers {SINGLE, DUAL, TRIPPLE, QUAD;} 	//Implemented weapons
	private Lasers currentLaser = Lasers.SINGLE;
	
	private float currentUpgradedDisableTime = 0;
	private float currentUpgradedMissileArea = 0;
	
	public static final float EMP_INCREASE_PER_UPGRADE = 500000000f;
	public static final float MISSILE_INCREASE_PER_UPGRADE = 50f;
	
	private float lastLaserTime = 0;
	private float lastMissileTime = 0;      	
	private float lastEMPTime = 0;      		
	private boolean missileReady = true;		
	private boolean EMPReady = true;
	private PlayerShip playerShip;
	private GameLogic gameLogic;
	
	/**
	 * Constructor
	 * @param playerShip The player ship
	 * @param gameLogic	
	 */
	public PlayerWeaponLogic(GameLogic gameLogic, PlayerShip playerShip) {
		this.playerShip = playerShip;
		this.gameLogic = gameLogic;
	}

	/**
	 * Spawns player projectiles
	 */
	public void spawnPlayerProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > PlayerMissile.RATEOFFIRE) missileReady = true;
		if(TimeUtils.nanoTime() - lastEMPTime > PlayerEMP.RATEOFFIRE) EMPReady = true;
		if(TimeUtils.nanoTime() - lastLaserTime > PlayerLaser.RATEOFFIRE) {
			if (ifAutoShooting){ 
				switch(currentLaser){
					case SINGLE:
						playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
						break;
					case DUAL:
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX(), playerShip.getY()+PlayerShip.HEIGHT));
						playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
						break;
					case TRIPPLE:
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX()-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
						playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
						break;
					default:
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH*2, playerShip.getY()+PlayerShip.HEIGHT));
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2+PlayerLaser.WIDTH*2, playerShip.getY()+PlayerShip.HEIGHT));
						playerShip.getParent().addActor(new PlayerLaser(playerShip.getX()-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
						playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
						break;		
				}
				lastLaserTime = TimeUtils.nanoTime();
			}
		}
	}
	
	/**
	 * Shoots a missile if the cooldown is ready
	 */
	public void shootMissle(){
		if (missileReady) {
			playerShip.getParent().addActorBefore(playerShip, new PlayerMissile(playerShip.getX()+PlayerShip.WITDH/2-PlayerMissile.WIDTH/2, playerShip.getY(), currentUpgradedMissileArea));
			lastMissileTime = TimeUtils.nanoTime();
			missileReady = false;
		}
	}
	
	/**
	 * Shoots a EMP if the cooldown is ready
	 */
	public void shootEMP(){
		if (EMPReady) {
			playerShip.getParent().addActorBefore(playerShip,new PlayerEMP(playerShip.getX()+PlayerShip.WITDH/2-PlayerEMP.WIDTH/2, playerShip.getY(), currentUpgradedDisableTime));
			lastEMPTime = TimeUtils.nanoTime();
			EMPReady = false;
		}
	}
	
	/**
	 * @return True if missile is ready
	 */
	public boolean getMissileReady(){
		return missileReady;
	}
	
	/**
	 * @return True if EMP is ready
	 */
	public boolean getEMPReady(){
		return EMPReady;
	}
	
	/**
	 * Increases the disable time for EMP wave
	 * @param nanoTime	The time to increase with
	 */
	public void increaseEMPDisableTime(){
		if(gameLogic.getCurrentScore() - currentUpgradeCostEMP >= 0){
			gameLogic.decreaseCurrentScore(currentUpgradeCostEMP);
			currentUpgradeCostEMP = 2*currentUpgradeCostEMP;
			currentUpgradedDisableTime = currentUpgradedDisableTime + EMP_INCREASE_PER_UPGRADE;
		}
	}
	
	/**
	 * Increases the disable time for EMP wave
	 * @param nanoTime	The time to increase with
	 */
	public void increaseMissileBlastArea(){
		if(gameLogic.getCurrentScore() - currentUpgradeCostMissile >= 0){
			gameLogic.decreaseCurrentScore(currentUpgradeCostMissile);
			currentUpgradeCostMissile = 2*currentUpgradeCostMissile;
			currentUpgradedMissileArea = currentUpgradedMissileArea + MISSILE_INCREASE_PER_UPGRADE;
		}
	}
	
	/**
	 * Upgrades the laser to the next level
	 */
	public void upgradeLaser(){
		if(gameLogic.getCurrentScore() - currentUpgradeCostLaser >= 0){

			switch(currentLaser){
				case SINGLE:
					currentLaser = Lasers.DUAL;
					break;
				case DUAL:
					currentLaser = Lasers.TRIPPLE;
					break;
				case TRIPPLE:
					currentLaser = Lasers.QUAD;
					break;
				default:
					return;
			}		
			gameLogic.decreaseCurrentScore(currentUpgradeCostLaser);
			currentUpgradeCostLaser = 2*currentUpgradeCostLaser+2*currentUpgradeCostLaser;
		}
	}
	
	/**
	 * Resets the upgrade cost for weapons
	 */
	public void resetUpgradeCosts(){
		currentUpgradeCostLaser = STARTING_LASER_COST;
		currentUpgradeCostEMP = STARTING_EMP_COST;
		currentUpgradeCostMissile = STARTING_MISSILE_COST;		
	}
	
	/**
	 * @return	the current emp cost
	 */
	public int getEmpUpgradeCost(){
		return currentUpgradeCostEMP;
	}
	
	/**
	 * @return	the current laser cost
	 */
	public int getLaserUpgradeCost(){
		return currentUpgradeCostLaser;
	}
	
	/**
	 * @return	the current missile cost
	 */
	public int getMissileUpgradeCost(){
		return currentUpgradeCostMissile;
	}
	
	/**
	 * Disable/enable autoshoot
	 */
	public static void toggleOptionAutoShoot() {
		ifAutoShooting = !ifAutoShooting;	
	}
	
	/**
	 * @return	If the ship is auto shooting or not
	 */
	public static boolean getIfAutoShooting(){
		return ifAutoShooting;
	}
}
