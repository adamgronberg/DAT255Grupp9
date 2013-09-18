package com.theinvader360.scene2dtutorial.swiperace;

import java.util.Iterator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameLogic extends Table {
	private InfiniteScrollBg backgroundRoad;
	private Array<EnemyShip> enemyShips;
	private long lastCarTime = 0;
<<<<<<< HEAD:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/TrafficGame.java
	public PlayerCar playerCar;
	private int nrOfCars;
	private int totalNrOfCars;
	
=======
	public final float lane2 = 390;
	public final float lane1 = 240;
	public final float lane0 = 90;
	public PlayerShip playerShip;
>>>>>>> 5618c64a5620bc24e5bd224bfb172ce787db0bc4:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/GameLogic.java
	
	public GameLogic() {
		setBounds(0, 0, 480, 800);
		setClip(true);
		backgroundRoad = new InfiniteScrollBg(getWidth(),getHeight());
		addActor(backgroundRoad);
<<<<<<< HEAD:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/TrafficGame.java
		playerCar = new PlayerCar(this);
		addActor(playerCar);
		enemyCars = new Array<EnemyCar>();	
		
		nrOfCars = 0;
		totalNrOfCars =10;
=======
		playerShip = new PlayerShip(this);
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();		
>>>>>>> 5618c64a5620bc24e5bd224bfb172ce787db0bc4:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/GameLogic.java
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (TimeUtils.nanoTime() - lastCarTime > 3000000000f) spawnCar();
		
		Iterator<EnemyShip> iter = enemyShips.iterator();
		while (iter.hasNext()) {
			EnemyShip enemyShip = iter.next();
			if (enemyShip.getBounds().y + enemyShip.getHeight() <= 0) {
				iter.remove();
				removeActor(enemyShip);
			}
			if (enemyShip.getBounds().overlaps(playerShip.getBounds())) {
                iter.remove();
                if (enemyShip.getX() > playerShip.getX()) {
                    if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(true, true);
                    else enemyShip.crash(true, false);
                }
                else {
                    if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(false, true);
                    else enemyShip.crash(false, false);
                }
			}
		}
	}

	private void spawnCar() {
<<<<<<< HEAD:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/TrafficGame.java
		if(nrOfCars<totalNrOfCars){
			
			int lane = MathUtils.random(0,480-EnemyCar.ENEMY_WIDTH);
			float xPos = lane;
			EnemyCar enemyCar = new EnemyCar(xPos, getHeight());
			enemyCars.add(enemyCar);
			addActor(enemyCar);
			lastCarTime = TimeUtils.nanoTime();
			nrOfCars++;
			
		}
		
=======
		int lane = MathUtils.random(0, 2);
		float xPos = 0;
		if (lane == 0) xPos = lane0;
		if (lane == 1) xPos = lane1;
		if (lane == 2) xPos = lane2;
		EnemyShip enemyShip = new EnemyShip(xPos, getHeight());
		enemyShips.add(enemyShip);
		addActor(enemyShip);
		lastCarTime = TimeUtils.nanoTime();
>>>>>>> 5618c64a5620bc24e5bd224bfb172ce787db0bc4:swipe-race-tutorial/swipe-race-tutorial/src/com/theinvader360/scene2dtutorial/swiperace/GameLogic.java
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
}
