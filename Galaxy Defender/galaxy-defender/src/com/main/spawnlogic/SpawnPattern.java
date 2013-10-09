package spawnlogic;

import spacegame.MovableEntity;

public abstract class SpawnPattern extends MovableEntity{

	protected float timeBetween;
	protected float timePassedSinceSpawn = 0;
	
	public SpawnPattern(float width, float height, float x, float y, float timeBetween) {
		super(width, height, x, y);
		this.timeBetween = timeBetween;
	}

}
