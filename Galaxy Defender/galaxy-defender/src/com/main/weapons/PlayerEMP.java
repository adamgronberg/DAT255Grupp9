package weapons;

import ships.EnemyShip;
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
	private static final float SPEED = 6f;
	public static final float HEIGHT = 10;
	public static final float WIDTH = 50;
	private static final int DAMAGEONHIT = 0;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY};
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerEMP(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGEONHIT, ImageAssets.playerIonCannon);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()+SPEED);
		super.act(delta);
	}

	/**
	 * Removes as actor
	 */
	@Override
	public void addOnHitEffect(MovableEntity entity) {
		if(entity instanceof EnemyShip){
			EnemyShip enemy = (EnemyShip) entity;
			enemy.disableShip(75);
		}
	}

	/**
	 * Affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}