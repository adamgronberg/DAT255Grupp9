package spacegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Blasse
 * Movable objects with a texture
 */
public class Sprite extends MovableEntity {

	protected TextureRegion texture;
	
	/**
	 * Draws the sprite
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param texture
	 */
	public Sprite(float width, float height, float x, float y, TextureRegion texture) {
		super(width, height, x, y);
		this.texture = texture;
	}
	
	/**
	 * Draws the explosion
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(texture, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}

}
