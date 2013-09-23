package com.weapons;

import com.effects.AreaEffect;
import com.spacegame.Assets;

/**
 * 
 * @author Grupp9
 *
 * Player missile. Have a small blast radius on impact and a sweet on hit effect
 *
 */
public class PlayerMissile extends AreaOfEffectWeapon{
	
	public static final float RATEOFFIRE = 800000000f; //In nanoseconds
	private static final float SPEED = 5f;
	private static final int DAMAGE = 1;
	private static final int EXPLODEDAMAGE = 1;
	private static final int LINGERTIME = 15;
	
	public static final float HEIGHT = 30;
	public static final float WIDTH = 20;
	
	public static final float EXPLOSION_H = 80;
	public static final float EXPLOSION_W = 60;
	
	/**
	 * Constructor
	 * @param x x-led Spawn
	 * @param y y-led Spawn
	 */
	public PlayerMissile(float x, float y){
		super(x, y, WIDTH, HEIGHT, DAMAGE, EXPLODEDAMAGE, Assets.playerMissile);
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
	 * Returners a effect to play and adds a AreaOfEffectDummy at the tip of the missile 
	 */
	@Override
	public AreaEffect addOnHitEffect() {
		AreaEffect effect = new AreaEffect(getX()-EXPLOSION_W/2+WIDTH/2, getY()-EXPLOSION_H/2 + HEIGHT, EXPLOSION_W, 
												EXPLOSION_H, LINGERTIME, Assets.explosionSplashMain);
		areaOfEffectDummy = new AreaOfEffectDummy(getX()-EXPLOSION_W/2+WIDTH/2, getY()-EXPLOSION_H/2 + HEIGHT, 
												EXPLOSION_W, EXPLOSION_H, areaDamage);
		return effect;
	}
}