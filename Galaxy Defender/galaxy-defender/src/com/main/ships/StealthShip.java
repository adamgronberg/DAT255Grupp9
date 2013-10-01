package ships;

import com.badlogic.gdx.math.MathUtils;

import spacegame.GameScreen;
import weapons.EnemyLaser;
import assets.ImageAssets;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * 
 * @author Grupp9
 *
 *	StealthShip
 *  Disappears from the screen and comes back
 *
 */

public class StealthShip extends EnemyShip {
	private final static float SHIPSPEED=1f;
	private final static int HEALTH=1;
	private final static int SCOREVALUE=1;
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int DAMAGE_WHEN_RAMMED = 15;
	private boolean visible = true;
	private long currentTime;
	private long lastMissileTime=0;
	/**
	 * Constructor
	* @param x	x-led Spawn location
	* @param y y-led Spawn location
	*/
	public StealthShip(float x, float y){
		super(WIDTH,HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyStealthShip, DAMAGE_WHEN_RAMMED);
		currentTime = TimeUtils.nanoTime();
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its position.
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		if(visible){
			spawnProjectile();
			if(TimeUtils.nanoTime()-currentTime > 3000000000f){
				setVisible(false);
				setY(getY()-SHIPSPEED);
				visible = false;
				currentTime = TimeUtils.nanoTime();
			}
			
		}
		else if(!visible){
			if(getY()-SHIPSPEED<GameScreen.GAME_HEIGHT*0.2){
				destroyShip();
			}
			else{
				setY(getY()-SHIPSPEED);
			}
			setX(GameScreen.GAME_WITDH-WIDTH+50);
			if(TimeUtils.nanoTime()-currentTime > 2000000000f){
				setX(MathUtils.random(0,GameScreen.GAME_WITDH-WIDTH));
				setVisible(true);
				visible=true;
				currentTime=TimeUtils.nanoTime();
			}

		}
	}
	
	/**
	 * Spawns projectiles in front of the enemies
	 */
	public void spawnProjectile() {
		if(TimeUtils.nanoTime() - lastMissileTime > EnemyLaser.RATEOFFIRE) {
			getParent().addActor( new EnemyLaser(getX()+WIDTH/2-EnemyLaser.WIDTH/2, getY()));
			lastMissileTime = TimeUtils.nanoTime();
		}
	}
}