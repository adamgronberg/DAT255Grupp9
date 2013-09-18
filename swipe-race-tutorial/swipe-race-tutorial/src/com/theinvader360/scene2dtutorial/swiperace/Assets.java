package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Handles game textures
 * @author Grupp9
 * 
 * TODO: Read up on atlas and how they are generated (More memory efficient) and implement
 * 
 */
public class Assets {
//	public static TextureAtlas atlas;
	public static TextureRegion playerShip;
	public static TextureRegion space;

	public static void load() {
//		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
//		car = atlas.findRegion("car");
//		road = atlas.findRegion("road");
		space=new TextureRegion ( new Texture(Gdx.files.internal("space-1.png")));
		playerShip=new TextureRegion ( new Texture(Gdx.files.internal("playerShip.png")));
	}

	public static void dispose() {
//		atlas.dispose();
	}
}
