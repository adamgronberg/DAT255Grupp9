package com.spacegame;

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
	
	/**
	 * Load assets and creates and adds the GameScreen
	 */
	@Override
	public void create() {
		Assets.load();
		gameScreen = new GameScreen(this);	
		setScreen(gameScreen);		//TODO: First screen should be a menu/splashscreen
	}
	
	/**
	 * Disposes all assets and closes game
	 */
	@Override
	public void dispose() {
		Assets.dispose();
		gameScreen.dispose();
	}
	
}
