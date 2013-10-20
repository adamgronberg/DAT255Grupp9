package spawnlogic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import screens.GameScreen;
import ships.Asteroid;

/**
 * 
 * @author Grupp9
 *
 *	Spawning pattern for a asteroid belt
 */
public class AsteroidBelt extends SpawnPattern{

	private int numberOfRows;
	private int asteroidsPerRow;
	private int currentRow = 0;
	
	/**
	 * 
	 * @param spawnX
	 * @param spawnY
	 * @param totalNumber
	 * @param timeBetween
	 */
	public AsteroidBelt(int numberOfRows, int asteroidsPerRow, float timeBetween){
		super(0, GameScreen.GAME_HEIGHT, timeBetween);
		this.numberOfRows = numberOfRows;
		this.asteroidsPerRow = asteroidsPerRow;
	}
	
	/**
	 * Called when act in parent is called
	 */
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(TimeUtils.nanoTime() - timePassedSinceSpawn > timeBetween){
			for (int i = 0; i <=asteroidsPerRow; i++){
				float posX =  MathUtils.random(0,GameScreen.GAME_WITDH-Asteroid.WIDTH);
				Asteroid asteroid = new Asteroid(posX, getY());
				getParent().addActor(asteroid);
			}
						
			currentRow++;
			timePassedSinceSpawn = TimeUtils.nanoTime();
		}
		if(currentRow >= numberOfRows){
			remove();
		}
	}

}
