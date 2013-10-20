package spawnlogic;

import spacegame.MovableEntity;

/**
 * Super class for patterns
 * @author Grupp9
 *
 */
public abstract class SpawnPattern extends MovableEntity{

	protected float timeBetween;
	protected float timePassedSinceSpawn = 0;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param timeBetween
	 */
	public SpawnPattern(float x, float y, float timeBetween) {
		super(0, 0, x, y);
		this.timeBetween = timeBetween;
	}
}
