package weapons;

import ships.PlayerShip;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 * Handles player weapons
 */
public class PlayerWeaponHandeler {
	
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
	
	/**
	 * Constructor
	 * @param playerShip The player ship
	 */
	public PlayerWeaponHandeler(PlayerShip playerShip) {
		this.playerShip = playerShip;
	}

	/**
	 * Spawns player projectiles
	 */
	public void spawnPlayerProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > PlayerMissile.RATEOFFIRE) missileReady = true;
		if(TimeUtils.nanoTime() - lastEMPTime > PlayerEMP.RATEOFFIRE) EMPReady = true;
		if(TimeUtils.nanoTime() - lastLaserTime > PlayerLaser.RATEOFFIRE) {
			switch(currentLaser){
				case SINGLE:
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
					break;
				case DUAL:
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX(), playerShip.getY()+PlayerShip.HEIGHT));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
					break;
				case TRIPPLE:
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
					break;
				default:
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2-PlayerLaser.WIDTH*2, playerShip.getY()+PlayerShip.HEIGHT));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH/2+PlayerLaser.WIDTH*2, playerShip.getY()+PlayerShip.HEIGHT));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
					playerShip.getParent().addActorBefore(playerShip, new PlayerLaser(playerShip.getX()+PlayerShip.WITDH-PlayerLaser.WIDTH/2, playerShip.getY()+PlayerShip.HEIGHT-PlayerLaser.HEIGHT*2));
					break;
			}
			lastLaserTime = TimeUtils.nanoTime();
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
		currentUpgradedDisableTime = currentUpgradedDisableTime + EMP_INCREASE_PER_UPGRADE;
	}
	
	/**
	 * Increases the disable time for EMP wave
	 * @param nanoTime	The time to increase with
	 */
	public void increaseMissileBlastArea(){
		currentUpgradedMissileArea = currentUpgradedMissileArea + MISSILE_INCREASE_PER_UPGRADE;
	}
	
	/**
	 * Upgrades the laser to the next level
	 */
	public void upgradeLaser(){
		switch(currentLaser){
			case SINGLE:
				currentLaser = Lasers.DUAL;
				break;
			case DUAL:
				currentLaser = Lasers.TRIPPLE;
				break;
			default:
				currentLaser = Lasers.QUAD;
				break;
		}
	}
}
