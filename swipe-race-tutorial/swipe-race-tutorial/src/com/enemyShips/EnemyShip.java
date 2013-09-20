package com.enemyships;

import com.spacegame.MovableEntity;


/**
 * 
 * Base class for enemy ships. Enemy ships should extend this class
 * 
 * @author Grupp9
 *	
 *	
 */
public class EnemyShip extends MovableEntity {
	
	
	/**
	 * 
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location
	 * @param width The width of the EnemyShip
	 * @param height The height of the EnemyShip
	 */
	public EnemyShip(float x, float y, float width, float height) {
		super(width, height);
		setPosition(x, y);
	}
	
	/**
	 * Plays On-Death-animation
	 * @param left	If the enemy was hit in the left
	 * @param right If the enemy was hit in the right
	 */
	public void crash(boolean left, boolean right) {
	}
}
//clearActions();
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