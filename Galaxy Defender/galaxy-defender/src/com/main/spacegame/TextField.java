package spacegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * 
 * @author Grupp9
 * Displays a text field on the screen
 */
public class TextField {
	 
	private  BitmapFont font;
	private float xPos;
	private float yPos;
	
	/**
	 * Constructor
	 * @param xPos
	 * @param yPos
	 */
	public TextField(float xPos, float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		font=new BitmapFont();		
	}

	/**
	 * Draws the text on the screen
	 * @param batch
	 * @param str
	 */
	public void draw(SpriteBatch batch, String str) {
	   	batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    font.draw(batch, str,xPos, yPos);
	}
}
