package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Game;

/**
 * Starts the first screen and loads all assets.
 *  
 *  
 * @author Grupp9
 * 
 */
public class MyGame extends Game {
	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	private GameScreen gameScreen;

	@Override
	public void create() {
		Assets.load();
		gameScreen = new GameScreen();	
		setScreen(gameScreen);		//TODO: First screen should be a menu/splashscreen
	}

	@Override
	public void dispose() {
		Assets.dispose();
		gameScreen.dispose();
	}
}
