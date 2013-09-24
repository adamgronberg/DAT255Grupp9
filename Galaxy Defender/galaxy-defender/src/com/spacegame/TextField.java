package com.spacegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TextField {
	
	private SpriteBatch batch; 
	private  BitmapFont font;
	
	public TextField(){
		batch=new SpriteBatch();
		font=new BitmapFont();
		
		
	}
	

	    
	    public void draw(String str) {
	    	batch.begin();
	    	batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

	         font.draw(batch, str, 380, 790);
	         batch.end();
	         
	    }

	    

}
