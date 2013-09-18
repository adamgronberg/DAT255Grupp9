package com.theinvader360.scene2dtutorial.swiperace;

import java.util.Iterator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TrafficGame extends Table {
	private InfiniteScrollBg backgroundRoad;
	private Array<EnemyCar> enemyCars;
	private long lastCarTime = 0;
	public PlayerCar playerCar;
	private int nrOfCars;
	private int totalNrOfCars;
	
	
	public TrafficGame() {
		setBounds(0, 0, 480, 800);
		setClip(true);
		backgroundRoad = new InfiniteScrollBg(getWidth(),getHeight());
		addActor(backgroundRoad);
		playerCar = new PlayerCar(this);
		addActor(playerCar);
		enemyCars = new Array<EnemyCar>();	
		
		nrOfCars = 0;
		totalNrOfCars =10;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (TimeUtils.nanoTime() - lastCarTime > 3000000000f) spawnCar();
		
		Iterator<EnemyCar> iter = enemyCars.iterator();
		while (iter.hasNext()) {
			EnemyCar enemyCar = iter.next();
			if (enemyCar.getBounds().y + enemyCar.getHeight() <= 0) {
				iter.remove();
				removeActor(enemyCar);
			}
			if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
                iter.remove();
                if (enemyCar.getX() > playerCar.getX()) {
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(true, true);
                    else enemyCar.crash(true, false);
                }
                else {
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(false, true);
                    else enemyCar.crash(false, false);
                }
			}
		}
	}

	private void spawnCar() {
		if(nrOfCars<totalNrOfCars){
			
			int lane = MathUtils.random(0,480-EnemyCar.ENEMY_WIDTH);
			float xPos = lane;
			EnemyCar enemyCar = new EnemyCar(xPos, getHeight());
			enemyCars.add(enemyCar);
			addActor(enemyCar);
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
