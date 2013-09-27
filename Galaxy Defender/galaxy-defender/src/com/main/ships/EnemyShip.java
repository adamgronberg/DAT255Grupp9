package ships;

import assets.ImageAssets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import spacegame.Sprite;

/**
 * 
 * 
 * @author Grupp9
 *	
 * Base class for enemy ships. Enemy ships should extend this class
 */
public abstract class EnemyShip extends Sprite{
	private int scoreValue;
	private int health;
	private int damageWhenRammed;
	private int startingHealth;
	public static enum EnemyTypes {HEAVY, BASIC, SCOUT};

	
	private static final float TIMEPERFRAME = 0.045f;
	private Animation animation; 
	private float stateTime;
	private TextureRegion currentFrame;
	
	/**
	 * 
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location
	 * @param width The width of the EnemyShip
	 * @param height The height of the EnemyShip
	 * @param health The total health of the enemy
	 * @param scorevalue The value of killing the enemy
	 * @param texture The image of the enemy
	 * @param damageOnHit The damage per hit
	 */
	public EnemyShip(float x, float y, float width, float height, int health, int scoreValue, TextureRegion texture, int damageWhenRammed) {
		super(width, height, x, y, texture);
		this.health = health;
		this.scoreValue = scoreValue;
		this.damageWhenRammed = damageWhenRammed;
		
		startingHealth = health;
		animation = new Animation(TIMEPERFRAME, ImageAssets.fireAnimation);
		stateTime = 0f;
	}
	
	/**
	 * Adds a fire depending on how damaged the target is
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		currentFrame = animation.getKeyFrame(stateTime, true);
        if(health<startingHealth){
        	drawDamagedAnimation(batch, parentAlpha, currentFrame);
        }
	}
	
	protected void drawDamagedAnimation(SpriteBatch batch, float parentAlpha, TextureRegion currentFrame){
    	batch.draw(currentFrame, getX()+getWidth()*0.5f, getY()+getHeight()*0.5f, 
    			getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
    	if(((float)health/(float)startingHealth) < 0.4f){
    		batch.draw(currentFrame, getX()+getWidth()*0.2f, getY()+getHeight()*0.2f, 
    				getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
    	}
	}
	
	/**
	 * Updates the animation when damaged
	 */
	@Override
	public void act(float delta){
		stateTime += delta;
	}
	
	/**
	 * Removes health from ship and removes as actor when dead.
	 */
	public int hit(int damage) {
		health = health - damage;
		if (health<=0){
			destroyShip();
			return scoreValue;
		}
		return 0;
	}
	
	/**
	 * @return scoreValue The ScoreValue of the ship
	 */
	public int getScoreValue(){
		return scoreValue;
	}
	
	/**
	 * @return The damage of the enemy when rammed
	 */
	public int getDamageWhenRammed() {
		return damageWhenRammed;
	}
	
	/**
	 *  removes the ship
	 */
	public void destroyShip() {
		remove();
	}
}
