package com.spacegame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


/**
 * 
 * @author Grupp9
 *
 */
public class EnemyShip extends MovableEntity {
	public final static int ENEMY_HEIGHT=50;
	public final static int ENEMY_WIDTH=50;
	private final static float ENEMYSPEED = 4.0f;
	
	
	/**
	 * Constructor
	 * 
	 * @param x Enemy spawn location x-led
	 * @param y Enemy spawn location y-led
	 */
	public EnemyShip(float x, float y) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT);

		setPosition(x, y);
		setRotation(180);

		int rnd = MathUtils.random(0, 3);
		if (rnd == 0) setColor(Color.RED);
		if (rnd == 1) setColor(Color.GREEN);
		if (rnd == 2) setColor(Color.WHITE);
		if (rnd == 3) setColor(Color.YELLOW);
		
		addAction(moveTo(x, -getHeight()-0.1f, ENEMYSPEED));	// Have to move a little further then the screen end
	}
	


	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	/**
	 * Plays On-Death-animation
	 * @param front	If the enemy was hit in the left
	 * @param above If the enemy was hit in the right
	 */
	public void crash(boolean front, boolean above) {
	}
	
}
//clearActions();
//addAction(fadeOut(1f));
//if (front && above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(200, 200, 1.5f)), removeActor()));
//if (front && !above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(200, -200, 1.5f)), removeActor()));
//if (!front && above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
//if (!front && !above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(-200, -200, 1.5f)), removeActor()));
