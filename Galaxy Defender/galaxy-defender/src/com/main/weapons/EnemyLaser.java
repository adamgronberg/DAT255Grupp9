package weapons;

import spacegame.MovableEntity;
import effects.EnemyLaserEffect;
import assets.ImageAssets;
import com.badlogic.gdx.math.MathUtils;

/**
 * Enemy standard green laser
 * @author Grupp9
 */

public class EnemyLaser extends Projectile {
	
	public static final float HEIGHT = 25;
	public static final float WIDTH = 3;
	private static final int DAMAGE_ON_HIT = 15;
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = {TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};
	private static final float AREAEFFECT_H = 20;
	private static final float AREAEFFECT_W = 20;
	
	private float speedX, speedY;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */	
	public EnemyLaser(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGE_ON_HIT, ImageAssets.enemyLaser);
		speedX = 0;
		speedY = 6;
	}
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param damageOnHit
	 * @param degree
	 */
	public EnemyLaser(float x, float y,float width, float height, int damageOnHit, float degree){
		super(x, y, width, height, damageOnHit, ImageAssets.enemyLaser);
		setRotation(degree);
		speedX = 6*MathUtils.sinDeg(degree);
		speedY = 6*MathUtils.cosDeg(degree);
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		setY(getY()-speedY);
		setX(getX()+speedX);
	}
	
	/**
	 * Removes the laser
	 */
	@Override
	public void addOnHitEffect(MovableEntity entity) {
		getParent().addActor( new EnemyLaserEffect(getX()-AREAEFFECT_W/2+WIDTH/2, 
				getY()-AREAEFFECT_H/2, AREAEFFECT_W, AREAEFFECT_H, false));
		remove();
	}

	/**
	 * Different target the projectile affects
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	/**
	 * The different target types
	 */
	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}
