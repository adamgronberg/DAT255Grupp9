package ships;


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
	protected int scoreValue;
	protected int health;
	public static enum EnemyTypes {HEAVY, BASIC, SCOUT};
	public int damageOnHit;
	
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
	public EnemyShip(float x, float y, float width, float height, int health, int scoreValue, TextureRegion texture, int damageOnHit) {
		super(width, height, x, y, texture);
		this.health = health;
		this.scoreValue=scoreValue;
		this.damageOnHit = damageOnHit;
	}
	
	/**
	 * Removes health from ship and removes as actor when dead.
	 */
	public int hit(int damage) {
		health = health - damage;
		if (health<=0){
			remove();
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
	 * @return The damage of the enemy
	 */
	public int getOnHitDamage() {
		return damageOnHit;
	}
	
	/**
	 *  removes the ship
	 */
	public void addOnHitEffect() {
		remove();
	}
}
