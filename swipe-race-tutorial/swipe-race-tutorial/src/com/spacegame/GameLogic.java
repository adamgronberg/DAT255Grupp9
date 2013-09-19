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
	
	private long lastEnemyShipTime = 0;	//TODO: For release 1 demo
	private int nrOfShip;				//TODO: For release 1 demo
	private int totalNrOfShips;			//TODO: For release 1 demo
	
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
		totalNrOfShips = 10;
		playerShip = new PlayerShip(this);
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
		
		if (TimeUtils.nanoTime() - lastEnemyShipTime > 3000000000f) spawnShip();
		if (TimeUtils.nanoTime() - reloadTime > 300000000f) spawnMissile();
		

		Iterator<EnemyShip> iter = enemyShips.iterator();
		while (iter.hasNext()) {
			EnemyShip enemyShip = iter.next();
			if (enemyShip.getBounds().y + enemyShip.getHeight() <= 0) {				//Check for col with out screen
				iter.remove();
				removeActor(enemyShip);
			}
			else if (enemyShip.getBounds().overlaps(playerShip.getBounds())) {		//check for col with player
				iter.remove();
				if (enemyShip.getX() > playerShip.getX()) {
					if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(true, true);		//Check what direction
					else enemyShip.crash(true, false);
				}
				else {
					if (enemyShip.getY() > playerShip.getY()) enemyShip.crash(false, true);		//check what direction
					else enemyShip.crash(false, false);
                }
				removeActor(enemyShip);
			}	
			else{
				Iterator<Missile> iterM = missiles.iterator();									//check if col with missiles
				while(iterM.hasNext()){
					Missile missile = iterM.next();
					if (enemyShip.getBounds().overlaps(missile.getBounds())) {
						Gdx.app.log( GameScreen.LOG, "Hit!"  );
						iter.remove();
						removeActor(enemyShip);
						iterM.remove();
						removeActor(missile);
						break;
					}
					else if(missile.getBounds().y>MyGame.HEIGHT){
						iterM.remove();
						removeActor(missile);
					}
				}
			}
		}
	}
	/**
	 * Spawns missiles at player front
	 */
	private void spawnMissile() {
		Missile missile = new Missile(playerShip.getX()+PlayerShip.PLAYER_SIZE/2, playerShip.getY()+PlayerShip.PLAYER_SIZE);
		missiles.add(missile);
		addActor(missile);
		reloadTime = TimeUtils.nanoTime();
		
	}
	
	/**
	 * 
	 */
	private void spawnShip() {
		if(nrOfShip<=totalNrOfShips){
			Gdx.app.log( GameScreen.LOG, "" + nrOfShip  );
			int spawnLocation = MathUtils.random(0,MyGame.WIDTH-EnemyShip.ENEMY_WIDTH);
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
