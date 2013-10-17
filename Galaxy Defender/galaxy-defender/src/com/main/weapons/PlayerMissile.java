package weapons;

import spacegame.MovableEntity;
import dummies.AreaOfEffectDummy;
import assets.ImageAssets;
import assets.SoundAssets;
import effects.ExplosionEffect;
import spacegame.OptionsScreen;

/**
 * 
 * @author Grupp9
 *
 * Player missile. Have a small blast radius on impact and a sweet on hit effect as well as sound
 *
 */
public class PlayerMissile extends Projectile{


	private static final float SPEED = 5f;
	private static final int DAMAGE = 1;
	private static final int AREA_OF_EFFECT_DAMAGE = 2;
	private static final float AREAEFFECT_H = 85;
	private static final float AREAEFFECT_W = 75;
	private static final TargetTypes FACTION = TargetTypes.PLAYER;
	private static final TargetTypes[] AFFECTED_TARGETS = 
		{TargetTypes.ENEMY, TargetTypes.ENEMY_PROJECTILE};
	
	public static float RATEOFFIRE = 2000000000f; //In nanoseconds
	public static final float HEIGHT = 25;
	public static final float WIDTH = 10;
	private float upgradedAreaEffectH;
	private float upgradedAreaEffectW;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerMissile(float x, float y, float areaUpgrade){
		super(x, y, WIDTH, HEIGHT, DAMAGE, ImageAssets.playerMissile);
		upgradedAreaEffectH = AREAEFFECT_H + areaUpgrade;
		upgradedAreaEffectW = AREAEFFECT_W + areaUpgrade;
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
	public void addOnHitEffect(MovableEntity entity) {
		getParent().addActor( new ExplosionEffect(getX()-upgradedAreaEffectW/2+WIDTH/2, 
							getY()-upgradedAreaEffectH/2 + HEIGHT, upgradedAreaEffectW, upgradedAreaEffectH));
		getParent().addActor( new AreaOfEffectDummy(getX()-upgradedAreaEffectW/2+WIDTH/2, 
							getY()-upgradedAreaEffectH/2 + HEIGHT, upgradedAreaEffectW, upgradedAreaEffectH, AREA_OF_EFFECT_DAMAGE, 
							FACTION, AFFECTED_TARGETS));
		if(OptionsScreen.getSound()) SoundAssets.missileExplosion.play();
		remove();
	}
	
	/**
	 * Returnes the affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTED_TARGETS;
	}

	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}
}