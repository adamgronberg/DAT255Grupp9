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
	
	/**
	 * 
	 * @param x x-led Spawn location
	 * @param y y-led Spawn location
	 * @param width The width of the EnemyShip
	 * @param height The height of the EnemyShip
	 */
	public EnemyShip(float x, float y, float width, float height, int health, int scoreValue, TextureRegion texture) {
		super(width, height, x, y, texture);
		this.health = health;
		this.scoreValue=scoreValue;
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
}
