package com.me.space;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class Space {

	/** Our player controlled hero **/

	/** A world has a level through which Bob needs to go through **/

	
	/** The collision boxes **/
	Array<Rectangle> collisionRects = new Array<Rectangle>();

	// Getters -----------
	
	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}



	// --------------------
	public Space() {
		createWorld();
	}

    private void createWorld() {

    }

}