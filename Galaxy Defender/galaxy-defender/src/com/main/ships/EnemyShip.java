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
	public static enum EnemyTypes {HEAVY, BASIC, SCOUT, ASTEROID};
	private int scoreValue;
	protected int currentHealth;
	private int damageWhenRammed;
	private int maximumHealth;
	private static final float TIMEPERFRAME = 0.045f;
	private float stateTime;
	private Animation onFireAnimation; 
	private TextureRegion currentFrame;
	private float disabledCurrentTime;
	private float disabledDuration;
	
	/**
	 * Constructor
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location
	 * @param width The width of the EnemyShip
	 * @param height The height of the EnemyShip
	 * @param health The total health of the enemy
	 * @param scorevalue The value of killing the enemy
	 * @param texture The image of the enemy
	 * @param damageOnHit The damage per hit
	 */
	public EnemyShip(float width, float height,float x, float y, int health, int scoreValue, TextureRegion texture, int damageWhenRammed) {
		super(width, height, x, y, texture);
		this.currentHealth = health;
		this.scoreValue = scoreValue;
		this.damageWhenRammed = damageWhenRammed;

		maximumHealth = health;
		onFireAnimation = new Animation(TIMEPERFRAME, ImageAssets.fireAnimation);
		stateTime = 0f;
	}
	
	/**
	 * Ship move function
	 * @param delta
	 */
	protected abstract void move(float delta);
	
	/**
	 * Ship shoot function
	 * @param delta
	 */
	protected abstract void shoot(float delta);
	
	/**
	 * Adds a fire depending on how damaged the target is
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		currentFrame = onFireAnimation.getKeyFrame(stateTime, true);
        if(currentHealth<maximumHealth){
        	drawDamagedAnimation(batch, parentAlpha, currentFrame);
        }
	}
	
	/**
	 * Disables a ship for a duration
	 * @param disabledDuration
	 */
	public void disableShip(float disabledDuration){
		disabledCurrentTime = 0;
		this.disabledDuration = disabledDuration;
	}
	
	/**
	 * Call this function if the target should be able to be disabled
	 * @return
	 */
	protected boolean disabable(){
		disabledCurrentTime++;
		return disabled();
	}
	
	/**
	 * returns if the ship is disabled or not
	 * @return
	 */
	public boolean disabled(){
		if (disabledCurrentTime >= disabledDuration) return false;
		else return true;
	}
	
	/**
	 * Draws damaged animation
	 * @param batch
	 * @param parentAlpha
	 * @param currentFrame
	 */
	protected void drawDamagedAnimation(SpriteBatch batch, float parentAlpha, TextureRegion currentFrame){
    	batch.draw(currentFrame, getX()+getWidth()*0.5f, getY()+getHeight()*0.5f, 
    			getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
    	if(((float)currentHealth/(float)maximumHealth) < 0.4f){
    		batch.draw(currentFrame, getX()+getWidth()*0.2f, getY()+getHeight()*0.2f, 
    				getWidth()/2, getHeight()/2, 15, 30, 1, 1, getRotation());
    	}
	}
	
	/**
	 * Updates ship actions
	 */
	@Override
	public void act(float delta){
		if (!disabable()){
			move(delta);
			shoot(delta);
		}
		stateTime += delta;
	}

	/**
	 * Removes health from ship and removes as actor when dead.
	 */
	public int hit(int damage) {
		currentHealth = currentHealth - damage;
		if (currentHealth<=0){
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
	 *  Removes the ship
	 */
	public void destroyShip() {
		remove();
	}
	
	/**
	 * Returns ships current hit points
	 */
	public int getHealth(){
		return currentHealth;
	}
	
	/**
	 * boast the ships combat ability 
	 * 
	 * @param boast determine the strength of the boast  
	 */
	public void uppgrade(int boast){
		maximumHealth += boast;
		currentHealth += boast;
	}
}
