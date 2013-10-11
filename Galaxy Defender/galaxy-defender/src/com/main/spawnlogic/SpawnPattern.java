package spawnlogic;

import spacegame.MovableEntity;

public abstract class SpawnPattern extends MovableEntity{

	protected float timeBetween;
	protected float timePassedSinceSpawn = 0;
	
	public SpawnPattern(float x, float y, float timeBetween) {
		super(0, 0, x, y);
		this.timeBetween = timeBetween;
	}
}
