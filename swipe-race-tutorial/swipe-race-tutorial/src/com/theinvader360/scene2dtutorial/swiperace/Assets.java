package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
//	public static TextureAtlas atlas;
	public static TextureRegion car;
	public static TextureRegion road;

	public static void load() {
//		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
//		car = atlas.findRegion("car");
//		road = atlas.findRegion("road");
		road=new TextureRegion ( new Texture(Gdx.files.internal("space-1.png")));
		car=new TextureRegion ( new Texture(Gdx.files.internal("playerShip.png")));
	}

	public static void dispose() {
//		atlas.dispose();
	}
}
