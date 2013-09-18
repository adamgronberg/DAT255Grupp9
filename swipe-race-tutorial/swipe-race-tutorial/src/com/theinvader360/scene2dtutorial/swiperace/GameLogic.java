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
	private int nrOfCars;
	private int totalNrOfCars;
	public PlayerShip playerShip;

	public GameLogic() {
		setBounds(0, 0, 480, 800);
		setClip(true);
		backgroundRoad = new InfiniteScrollBg(getWidth(),getHeight());
		addActor(backgroundRoad);		
		nrOfCars = 0;
		totalNrOfCars =10;
		playerShip = new PlayerShip(this);
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();		
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
		if(nrOfCars<totalNrOfCars){
			
			int lane = MathUtils.random(0,480-EnemyShip.ENEMY_WIDTH);
			float xPos = lane;
			EnemyShip enemyShip = new EnemyShip(xPos, getHeight());
			enemyShips.add(enemyShip);
			addActor(enemyShip);
			lastCarTime = TimeUtils.nanoTime();
			nrOfCars++;
			
		}
		}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
}
