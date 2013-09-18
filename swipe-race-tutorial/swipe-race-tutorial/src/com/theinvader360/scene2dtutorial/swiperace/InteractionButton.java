package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InteractionButton extends Actor {
	
	private TextureRegion texture;
	

	public InteractionButton(int x, int y, float width, float height, TextureRegion texture) {
		this.texture = texture;
		setWidth(width);
		setHeight(height);
		setPosition(x, y);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}
}
