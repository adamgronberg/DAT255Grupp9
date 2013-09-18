package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyShip extends Actor {
	private Rectangle bounds = new Rectangle();
	public final static int ENEMY_HEIGHT=80;
	public final static int ENEMY_WIDTH=80;
	
<<<<<<< HEAD:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/EnemyShip.java
	public EnemyShip(float x, float y) {
=======
<<<<<<< HEAD:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/EnemyCar.java
	public EnemyCar(float x, float y) {
>>>>>>> adb2fab79c3d02aa47c903c93551b79c201af47f:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/EnemyShip.java
		setWidth(ENEMY_WIDTH);
		setHeight(ENEMY_HEIGHT);
=======
	public EnemyShip(float x, float y) {
		setWidth(80);
		setHeight(80);
>>>>>>> 5618c64a5620bc24e5bd224bfb172ce787db0bc4:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/EnemyShip.java
		setPosition(x, y - getHeight()/2);

		int rnd = MathUtils.random(0, 3);
		if (rnd == 0) setColor(Color.RED);
		if (rnd == 1) setColor(Color.GREEN);
		if (rnd == 2) setColor(Color.WHITE);
		if (rnd == 3) setColor(Color.BLUE);
		
		addAction(moveTo(x, -getHeight(), MathUtils.random(4.0f, 6.0f)));
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.playerShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	public void crash(boolean front, boolean above) {
		clearActions();
		addAction(fadeOut(1f));
		if (front && above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(200, 200, 1.5f)), removeActor()));
		if (front && !above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(200, -200, 1.5f)), removeActor()));
		if (!front && above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
		if (!front && !above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(-200, -200, 1.5f)), removeActor()));
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
}