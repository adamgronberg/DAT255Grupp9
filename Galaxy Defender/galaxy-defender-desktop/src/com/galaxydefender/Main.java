package com.galaxydefender;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import spacegame.MyGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GalaxyDefender";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 800;
		
		new LwjglApplication(new MyGame(MyGame.ApplicationType.DESKTOP), cfg);
	}
}
