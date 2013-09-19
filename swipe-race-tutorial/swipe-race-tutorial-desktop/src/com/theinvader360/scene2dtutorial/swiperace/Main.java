package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spacegame.MyGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "swipe-race-tutorial";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 800;
		
		new LwjglApplication(new MyGame(), cfg);
	}
}
