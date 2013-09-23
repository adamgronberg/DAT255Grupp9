package com.ships;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacegame.Sprite;


/**
 * 
 * Base class for enemy ships. Enemy ships should extend this class
 * 
 * @author Grupp9
 *	
 *	
 */
public abstract class EnemyShip extends Sprite{
	
	protected int health;
	
	/**
	 * 
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location
	 * @param width The width of the EnemyShip
	 * @param height The height of the EnemyShip
	 */
	public EnemyShip(float x, float y, float width, float height, int health, TextureRegion texture) {
		super(width, height, x, y, texture);
		this.health = health;
	}
	
	/**
	 * Removes health from ship
	 * Sets despawnReady = true when below 0 health
	 */
	public void hit(int damage) {
		health = health - damage;
		if (health<=0) remove();
	}
}



//clearActions();						Example of how to use animations. Should be done in a separate class
//addAction(fadeOut(1f));
//if (front && above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(200, 200, 1.5f)), removeActor()));
//if (front && !above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(200, -200, 1.5f)), removeActor()));
//if (!front && above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
//if (!front && !above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(-200, -200, 1.5f)), removeActor()));


//int rnd = MathUtils.random(0, 3);
//if (rnd == 0) setColor(Color.RED);
//if (rnd == 1) setColor(Color.GREEN);
//if (rnd == 2) setColor(Color.WHITE);
//if (rnd == 3) setColor(Color.YELLOW);