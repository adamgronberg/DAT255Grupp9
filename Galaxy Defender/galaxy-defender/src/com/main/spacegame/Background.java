package spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



/**
 * 
 * @author Grupp9
 * 
 * TODO: Could use this class to add the different map backgrounds
 */
public class Background extends MovableEntity {
	
	private TextureRegion texture;
	
	/**
	 * Constructor
	 * @param width 	Width of the screen should be here 
	 * @param height	Height of the screen should be here
	 */
	public Background(float x, float y,float width, float height,TextureRegion texture) {
		super(width, height, x, y);
		this.texture = texture;
		//addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(width, 0)))); 
		//TODO: Could have a scrolling background instead of static?
	}
	
	/**
	 * Draws the explosion
	 */
	public void drawBelow(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(texture, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	/**
	 * Sets background-image
	 * 
	 * @param texture
	 */
	public void setTextureRegion(TextureRegion texture) {
		this.texture = texture;
	}
	
}
