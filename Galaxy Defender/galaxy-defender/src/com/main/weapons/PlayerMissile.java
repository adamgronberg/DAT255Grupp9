package weapons;

import spacegame.GameScreen;
import dummies.PlayerMissileExplosionDummy;
import assets.ImageAssets;
import assets.SoundAssets;
import effects.ExplosionEffect;

/**
 * 
 * @author Grupp9
 *
 * Player missile. Have a small blast radius on impact and a sweet on hit effect aswell as sound
 *
 */
public class PlayerMissile extends Projectile{

	private static final float SPEED = 5f;
	private static final int DAMAGE = 1;
	private static final float AREAEFFECT_H = 80;
	private static final float AREAEFFECT_W = 60;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTEDTARGETS = 
		{TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	
	public static final float RATEOFFIRE = 800000000f; //In nanoseconds
	public static final float HEIGHT = 30;
	public static final float WIDTH = 20;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerMissile(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGE, ImageAssets.playerMissile);
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
	 * Adds 
	 * -Explosion Effect
	 * -Explosion Dummy
	 * -Sound Effect
	 */
	@Override
	public void addOnHitEffect() {
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