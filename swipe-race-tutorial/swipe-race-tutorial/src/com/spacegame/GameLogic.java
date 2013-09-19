package com.spacegame;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
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
	
	private InfiniteScrollBg backgroundSpace;
	private InteractionButton moveLeftButton;
	private InteractionButton moveRightButton;
	
	private Array<EnemyShip> enemyShips;
	private Array<Missile> missiles;
	
	private long lastEnemyShipTime = 0;	//For release 1 demo
	private int nrOfShip;				//For release 1 demo
	private boolean playerIsAlive = true;
	
	private float reloadTime = 0;
	
	public PlayerShip playerShip;

	
	/**
	 * Constructor
	 * TODO: Should set up unique levels and not spawn set number of enemys randomly (Release 1 demo)
	 */
	public GameLogic() {
		setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
		setClip(true);
		backgroundSpace = new InfiniteScrollBg(getWidth(), getHeight());
		moveLeftButton = new InteractionButton(0, 0, GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, Assets.moveLeftButton);
		moveRightButton = new InteractionButton(MyGame.WIDTH - GameScreen.MOVMENT_BUTTON_SIZE, 0, 
				GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, Assets.moveRightButton);
		addActor(backgroundSpace);
		addActor(moveLeftButton);
		addActor(moveRightButton);
		
		
		nrOfShip = 1;
		playerShip = new PlayerShip();
		addActor(playerShip);
		enemyShips = new Array<EnemyShip>();
		missiles = new Array<Missile>();
	}
	/**
	 *  
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		
		
		if (TimeUtils.nanoTime() - lastEnemyShipTime > 2000000000f) spawnShip();
		if (TimeUtils.nanoTime() - reloadTime > 300000000f && playerIsAlive) spawnMissile();
		
		Iterator<Missile> iterM;
		Iterator<EnemyShip> iter = enemyShips.iterator();
		while (iter.hasNext()) {
			EnemyShip enemyShip = iter.next();
			iterM = missiles.iterator();
			if (checkOutOfBoundsY(enemyShip)) {				//Check for col with out screen
				iter.remove();
				removeActor(enemyShip);
			}
			else if (collisionControl(enemyShip, playerShip)) {		//check for col with player
				iter.remove();
				removeActor(enemyShip);
				removeActor(playerShip);
				playerIsAlive = false;
				break;
			}										//check if col with missiles
			else {
				while(iterM.hasNext()){
					Missile missile = iterM.next();
					if (collisionControl(enemyShip, missile)) {
						Gdx.app.log( GameScreen.LOG, "Hit!"  );
						iter.remove();
						removeActor(enemyShip);
						iterM.remove();
						removeActor(missile);
						break;
					}
				}
			}
		}
		iterM = missiles.iterator();
		while(iterM.hasNext()){
			Missile missile = iterM.next();
			if (checkOutOfBoundsY(missile)){
				iterM.remove();
				removeActor(missile);
			}
		}
	}
	
	private boolean checkOutOfBoundsY(MovableEntity movableObj){
		if (movableObj.bounds.y < -movableObj.getBounds().getHeight() || movableObj.bounds.y >= 
				MyGame.HEIGHT+movableObj.getBounds().getHeight()){
			return true; 
		}
		return false;
	}
	
	private boolean collisionControl(MovableEntity movableObj1, MovableEntity movableObj2){
		return movableObj1.getBounds().overlaps(movableObj2.getBounds());
	}
	
	/**
	 * Spawns missiles at player front
	 */
	private void spawnMissile() {
		Missile missile = new Missile(playerShip.getX()+PlayerShip.PLAYER_SIZE/2, 
				playerShip.getY()+PlayerShip.PLAYER_SIZE);
		missiles.add(missile);
		addActor(missile);
		reloadTime = TimeUtils.nanoTime();	
	}
	
	/**
	 * 
	 */
	private void spawnShip() {
		Gdx.app.log( GameScreen.LOG, "" + nrOfShip  );
		int spawnLocation = MathUtils.random(0,MyGame.WIDTH-EnemyShip.ENEMY_WIDTH);
		float xPos = spawnLocation;
		EnemyShip enemyShip = new EnemyShip(xPos, MyGame.HEIGHT+EnemyShip.ENEMY_HEIGHT);
		enemyShips.add(enemyShip);
		addActor(enemyShip);
		lastEnemyShipTime = TimeUtils.nanoTime();
		nrOfShip++;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
	
	
//	if (enemyShip.getX() > playerShip.getX()) {
//	if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(true, true);		//Check what direction
//	else enemyShip.crash(true, false);
//}
//else {
//	if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(false, true);		//check what direction
//	else enemyShip.crash(false, false);
//}
}
