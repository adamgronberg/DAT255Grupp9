package com.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Handles game textures
 * @author Grupp9
 * 
 * TODO: Read up on atlas and how they are generated (More memory efficient) and implement better
 * 
 */
public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion playerShip;
	public static TextureRegion enemyBasicShip;
	public static TextureRegion enemyScoutShip;
	public static TextureRegion space;
	public static TextureRegion moveLeftButton;
	public static TextureRegion moveRightButton;
	public static TextureRegion playerLaser;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		playerShip = atlas.findRegion("playerShip");
		playerLaser = atlas.findRegion("laser");
		enemyBasicShip = atlas.findRegion("enemyShip");
		enemyScoutShip = atlas.findRegion("scoutShip");

		moveRightButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Right.png")));
		moveLeftButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Left.png")));
		space = new TextureRegion ( new Texture(Gdx.files.internal("space-1.png")));

	}

	public static void dispose() {
		atlas.dispose();
	}
}
