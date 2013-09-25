package spacegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TextField {
	 
	private  BitmapFont font;
	private float xPos;
	private float yPos;
	
	public TextField(float xPos, float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		font=new BitmapFont();		
	}

	public void draw(SpriteBatch batch, String str) {
	   	batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    font.draw(batch, str,xPos, yPos);
	}
}
