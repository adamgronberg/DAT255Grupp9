package spawnlogic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import ships.Asteroid;
import spacegame.GameScreen;
import spacegame.MovableEntity;

public class AsteroidBelt extends MovableEntity{

	private int numberOfRows;
	private int asteroidsPerRow;
	private int currentRow = 0;
	private float timePassedSinceSpawn = 0;
	private float timeBetween;
	
	/**
	 * 
	 * @param spawnX
	 * @param spawnY
	 * @param totalNumber
	 * @param timeBetween
	 */
	public AsteroidBelt(int numberOfRows, int asteroidsPerRow, float timeBetween){
		super(1, 1, 0, GameScreen.GAME_HEIGHT);
		this.numberOfRows = numberOfRows;
		this.asteroidsPerRow = asteroidsPerRow;
		this.timeBetween = timeBetween;
	}
	
	/**
	 * 
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
