package com.effects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacegame.Sprite;

public class AreaEffect extends Sprite{

	private float lingerTime;
	private float lastLingerTime = 0;
	
	/**
	 * Constructor
	 * @param x 			x-led spawn
	 * @param y				y-led spawn
	 * @param width			Width of explosion	
	 * @param height		Height of explosion
	 * @param lingerTime	The time the explosion lingers (20 ~ 1 second)
	 */
	public AreaEffect(float x, float y, float width, float height, float lingerTime, TextureRegion texture) {
		super( width, height, x, y, texture);
		this.lingerTime = lingerTime;
		this.texture = texture;
	}
	
	/**
	 * Called when "act" is called in its stage
	 * Updates its linger time
	 */
	@Override
	public void act(float delta){
		if (lastLingerTime >= lingerTime) remove();
		lastLingerTime++;
		super.act(delta);
	}	
}
