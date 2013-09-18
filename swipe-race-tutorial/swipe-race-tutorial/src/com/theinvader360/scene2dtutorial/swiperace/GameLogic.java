package com.theinvader360.scene2dtutorial.swiperace;

import java.util.Iterator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
/**
 * 
 * @author Grupp9
 *
 */
public class GameLogic extends Table {
	private InfiniteScrollBg backgroundRoad;
	private Array<EnemyShip> enemyShips;
	private long lastEnemyShipTime = 0;	//TODO: For release 1 demo
	private int nrOfShip;				//TODO: For release 1 demo
	private int totalNrOfShips;			//TODO: For release 1 demo
	public PlayerShip playerShip;

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn set number of enemys randomly (Release 1 demo)
	 */
	public GameLogic() {
		setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
		setClip(true);
		backgroundRoad = new InfiniteScrollBg(getWidth(), getHeight());
		addActor(backgroundRoad);		
		nrOfShip = 0;
		totalNrOfShips =10;
		playerShip = new PlayerShip(this);
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();		
	}
	/**
	 * Called 
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (TimeUtils.nanoTime() - lastEnemyShipTime > 3000000000f) spawnShip();
		
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
	
	private void spawnShip() {
		if(nrOfShip<totalNrOfShips){
			
			int spawnLocation = MathUtils.random(0,480-EnemyShip.ENEMY_WIDTH);
			float xPos = spawnLocation;
			EnemyShip enemyShip = new EnemyShip(xPos, getHeight() + EnemyShip.ENEMY_HEIGHT);
			enemyShips.add(enemyShip);
			addActor(enemyShip);
			lastEnemyShipTime = TimeUtils.nanoTime();
			nrOfShip++;
			
		}
		}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
}
