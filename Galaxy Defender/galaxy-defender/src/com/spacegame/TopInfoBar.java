package com.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TopInfoBar extends Table{
	
	private Background background;
	
	public TopInfoBar(){
		setBounds(0, GameScreen.GAME_HEIGHT, GameScreen.GAME_WITDH, GameScreen.INFO_SCREEN_HEIGHT);
		background = new Background(getX(), getY(), getWidth(), getHeight(), ImageAssets.topInfoBar);
		addActor(background);
	}
	
	
	/**
	 * Draws all actors on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		background.drawBelow(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}
	
	
}
