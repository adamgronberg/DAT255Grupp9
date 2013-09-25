package ships;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import spacegame.Sprite;


/**
 * 
 * Base class for enemy ships. Enemy ships should extend this class
 * 
 * @author Grupp9
 *	
 *	
 */
public abstract class EnemyShip extends Sprite{
	protected int scoreValue;
	protected int health;
	
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

	


//clearActions();						Example of how to use animations. Should be done in a separate class
//addAction(fadeOut(1f));
//if (front && above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(200, 200, 1.5f)), removeActor()));
//if (front && !above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(200, -200, 1.5f)), removeActor()));
//if (!front && above) addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
//if (!front && !above) addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(-200, -200, 1.5f)), removeActor()));


//int rnd = MathUtils.random(0, 3);
//if (rnd == 0) setColor(Color.RED);
//if (rnd == 1) setColor(Color.GREEN);
//if (rnd == 2) setColor(Color.WHITE);
//if (rnd == 3) setColor(Color.YELLOW);