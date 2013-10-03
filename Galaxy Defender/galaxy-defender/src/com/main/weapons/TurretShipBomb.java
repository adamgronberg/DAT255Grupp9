package weapons;

import spacegame.GameScreen;
import spacegame.MovableEntity;

import com.badlogic.gdx.math.MathUtils;

import dummies.PlayerMissileExplosionDummy;
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
	private final static float BOMBSPEED=2f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=1;
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int DAMAGE_WHEN_RAMMED = 15;
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
		setY(getY()-BOMBSPEED);
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
		getParent().addActor( new PlayerMissileExplosionDummy(getX()-AREAEFFECT_W/2+WIDTH/2, 
							getY()-AREAEFFECT_H/2 + HEIGHT, AREAEFFECT_W, AREAEFFECT_H));
		if(GameScreen.sound) SoundAssets.missileExplosion.play();
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
