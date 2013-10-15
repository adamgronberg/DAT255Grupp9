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
public class MiniBossBomb extends Projectile{

	public static final int HEIGHT=30;
	public static final int WIDTH=30;
	private static final float BOMBSPEED=8f;
	private static final float AREAEFFECT_H = 120;
	private static final float AREAEFFECT_W = 120;
	private static final int AREA_DAMAGE = 20;
	private static final int DAMAGE_ON_HIT = 10;
	private static final float EXPLODE_POINT = 50;
	private static final TargetTypes FACTION = TargetTypes.ENEMY;
	private static final TargetTypes[] AFFECTEDTARGETS = 
		{TargetTypes.PLAYER, TargetTypes.PLAYER_PROJECTILE, TargetTypes.ALLY, TargetTypes.ENEMY_PROJECTILE, TargetTypes.ALLY_PROJECTILE};

	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	*/
	public MiniBossBomb(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGE_ON_HIT, ImageAssets.mine);
		setRotation(0);
	}

	/**
	 * Bombs act method
	 */
	public void act(float delta) {
		setRotation(getRotation()+10f);
		setY(getY()-BOMBSPEED);
		checkIfDown();
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
	 * Checks if the bomb hit the "ground", should if so explode
	 */
	public void checkIfDown(){
		if(getY()<= EXPLODE_POINT){
			addOnHitEffect(null);
		}
	}

	/**
	 * Returns the affected targets
	 */
	@Override
	public TargetTypes[] getFactionTypes() {
		return AFFECTEDTARGETS;
	}

	/**
	 * Returns the Faction
	 */
	@Override
	public TargetTypes getFaction() {
		return FACTION;
	}

}
