package weapons;

import dummies.EMPDummy;
import spacegame.MovableEntity;
import assets.*;

/**
 * 
 * @author Grupp9
 * 
 * EMP shoots a wave that disables all enemies on contact of a set amount of time
 *
 */
public class PlayerEMP extends Projectile {
	
	public static final float RATEOFFIRE = 2000000000f; //In nanoseconds
	public static final float HEIGHT = 40;
	public static final float WIDTH = 50;
	private static final float DISABLE_TIME = 500000000f;
	private static final float SPEED = 6f;
	private static final int DAMAGEONHIT = 0;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	private float disableTimeUpgrade;
	
	private int distanceTraveled;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerEMP(float x, float y, float disableTimeUpgrade){
		super(x, y, WIDTH, HEIGHT, DAMAGEONHIT, ImageAssets.playerIonCannon);
		distanceTraveled = 0;
		this.disableTimeUpgrade = disableTimeUpgrade;
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()+SPEED);
		distanceTraveled = distanceTraveled + (int)SPEED;
		if(distanceTraveled>HEIGHT){
			getParent().addActor(new EMPDummy(getX(), getY(), WIDTH, HEIGHT, DAMAGEONHIT, FACTION, 
					AFFECTEDTARGETS, DISABLE_TIME+disableTimeUpgrade));
			distanceTraveled = 0;
		}
	}

	/**
	 * Removes as actor
	 */
	@Override
	public void addOnHitEffect(MovableEntity entity) {}

	/**
	 * Affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}
	
	/**
	 * The faction the EMP came from
	 */
	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}