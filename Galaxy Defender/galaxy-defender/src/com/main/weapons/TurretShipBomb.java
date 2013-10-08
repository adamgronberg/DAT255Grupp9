package weapons;

import spacegame.GameScreen;
import spacegame.MovableEntity;
import dummies.AreaOfEffectDummy;
import effects.ExplosionEffect;
import assets.ImageAssets;
import assets.SoundAssets;

/**
 * 
 * @author Grupp9
 *
 *	Asteroids
 *  Only has y led moment
 *
 */

public class TurretShipBomb extends Projectile{
	private final static float SPEED=2f;
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int AREA_DAMAGE = 10;
	private static final float AREAEFFECT_H = 85;
	private static final float AREAEFFECT_W = 75;
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = 
		{TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE};
	
	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	*/
	public TurretShipBomb(float x, float y){
		super(x, y, WIDTH, HEIGHT, 5, ImageAssets.playerMissile);
		setRotation(-180);
	}

	
	public void act(float delta) {
		setY(getY()-SPEED);
	}
	
	/**
	 * Adds 
	 * -Explosion Effect
	 * -Explosion Dummy
	 * -Sound Effect
	 */
	@Override
	public void addOnHitEffect(MovableEntity entity) {
		getParent().addActor( new ExplosionEffect(getX()-AREAEFFECT_W/2+WIDTH/2, 
							getY()-AREAEFFECT_H/2 + HEIGHT, AREAEFFECT_W, AREAEFFECT_H));
		getParent().addActor( new AreaOfEffectDummy(getX()-AREAEFFECT_W/2+WIDTH/2, 
							getY()-AREAEFFECT_H/2 + HEIGHT, AREAEFFECT_W, AREAEFFECT_H, AREA_DAMAGE, FACTION, AFFECTEDTARGETS));
		if(GameScreen.getSound()) SoundAssets.missileExplosion.play();
		remove();
	}


	/**
	 * Returnes the affected targets
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
