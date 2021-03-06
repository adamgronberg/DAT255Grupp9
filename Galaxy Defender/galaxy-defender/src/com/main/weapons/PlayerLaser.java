package weapons;

import spacegame.MovableEntity;
import effects.PlayerLaserEffect;
import assets.*;

/**
 * Player standard green laser
 * @author Grupp9
 */
public class PlayerLaser extends Projectile {
	
	public static final float RATEOFFIRE = 400000000f; //In nanoseconds
	private static final float SPEED = 6f;
	public static final float HEIGHT = 10;
	public static final float WIDTH = 3;
	private static final int DAMAGEONHIT = 1;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	private static final float AREAEFFECT_H = 8;
	private static final float AREAEFFECT_W = 8;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerLaser(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGEONHIT, ImageAssets.playerLaser);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()+SPEED);
	}

	/**
	 * Removes as actor
	 */
	@Override
	public void addOnHitEffect(MovableEntity entity) {
		getParent().addActor( new PlayerLaserEffect(getX()-AREAEFFECT_W/2+WIDTH/2, 
				getY()-AREAEFFECT_H/2, AREAEFFECT_W, AREAEFFECT_H));
		remove();
	}

	/**
	 * Affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	/**
	 * The faction of the laser
	 */
	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}
