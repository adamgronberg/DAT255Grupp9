package ships;

import assets.ImageAssets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import effects.ExplosionEffect;

import spacegame.Sprite;

/**
 * @author Grupp9
 *	
 * Base class for enemy ships. Enemy ships should extend this class
 */
public abstract class EnemyShip extends Sprite implements Cloneable{
	public static enum EnemyTypes {HEAVY, BASIC, SCOUT, ASTEROID, TURRET};
	protected int scoreValue;
	protected int currentHealth;
	private int damageWhenRammed;
	private int maximumHealth;
	private static final float TIMEPERFRAME = 0.045f;
	private float stateTime;
	private Animation onFireAnimation; 
	private TextureRegion currentFrame;
	private float disabledCurrentTime;
	private float disabledDuration;
	private boolean disabable;
	private boolean firePosInitialised = false;
	private Vector2 firePos1, firePos2, firePos3;
	private static boolean freeze = false;
	
	/**
	 * 
	 * @param width	enemy width
	 * @param height	enemy height
	 * @param x	Spawn location
	 * @param y	Spawn location
	 * @param health	The total health of the enemy
	 * @param scoreValue	The value of killing the enemy
	 * @param texture	The image of the enemy
	 * @param damageWhenRammed	Damage on ram
	 */
	public EnemyShip(float width, float height,float x, float y, int health, 
			int scoreValue, TextureRegion texture, int damageWhenRammed, boolean disabable) {
		super(width, height, x, y, texture);
		this.currentHealth = health;
		this.scoreValue = scoreValue;
		this.disabable = disabable;
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
		disabledCurrentTime = TimeUtils.nanoTime();
		this.disabledDuration = disabledDuration;
	}
	
	/**
	 * @return 
	 */
	public boolean disabled(){
		if(!disabable) return false;
		if (TimeUtils.nanoTime() - disabledCurrentTime > disabledDuration) return false;
		else return true;
	}
	
	/**
	 * Draws damaged animation
	 * @param batch
	 * @param parentAlpha
	 * @param currentFrame
	 */
	protected void drawDamagedAnimation(SpriteBatch batch, float parentAlpha, TextureRegion currentFrame){
		if(!firePosInitialised){
			firePos1 = randomPosition(); 
			firePos2 = randomPosition(); 
			firePos3 = randomPosition();
			firePosInitialised = true;
		}
		
    	batch.draw(currentFrame, getX()+firePos1.x-15/2, getY()+firePos1.y-25/2, 
    			getOriginX(), getOriginY(), 15, 25, 1, 1, getRotation());
    	
    	if(((float)currentHealth/(float)maximumHealth) < 0.6f){
    		batch.draw(currentFrame, getX()+firePos2.x-20/2, getY()+firePos2.y-35/2, 
    				getOriginX(), getOriginY(), 20, 35, 1, 1, getRotation());
    	}
    	if(((float)currentHealth/(float)maximumHealth) < 0.3f){
    		batch.draw(currentFrame, getX()+firePos3.x/2-25/2, getY()+firePos3.y-40/2, 
    				getOriginX(), getOriginY(), 25, 40, 1, 1, getRotation());
    	}
	}
	
	/**
	 * Updates ship actions
	 */
	@Override
	public void act(float delta){
		if(freeze) return;
		if (!disabled()){
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
		if(getParent() != null){
			getParent().addActor(new ExplosionEffect(getX()+getWidth()/2-(getWidth()/2)/2, getY(), getWidth()/2, getWidth()/2));
		}
		remove();
	}
	
	/**
	 * Returns ships current hit points
	 */
	public int getHealth(){
		return currentHealth;
	}
	
	/**
	 * Clones the object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * Random fire position
	 */
	private Vector2 randomPosition(){
		return new Vector2(MathUtils.random(getWidth()/6f, getWidth()-getWidth()/6f), MathUtils.random(getHeight()/6f, getHeight()-getHeight()/6));
	}
	
	/**
	 * Stops all ship logic
	 */
	public static void Freeze(){
		freeze = true;
	}
	
	/**
	 * Starts ship logic
	 */
	public static void unFreeze(){
		freeze = false;
	}
}
