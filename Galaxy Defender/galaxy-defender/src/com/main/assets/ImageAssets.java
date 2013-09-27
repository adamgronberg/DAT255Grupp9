package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * @author Grupp9
 * Contains all image assets and animations
 */
public class ImageAssets {
	public static TextureAtlas atlas;
	public static TextureRegion playerShip;
	public static TextureRegion enemyBasicShip;
	public static TextureRegion enemyScoutShip;
	public static TextureRegion enemyHeavyShip;
	public static TextureRegion space;
	public static TextureRegion moveLeftButton;
	public static TextureRegion moveRightButton;
	public static TextureRegion playerLaser;
	public static TextureRegion enemyLaser;
	public static TextureRegion playerMissile;
	public static TextureRegion topInfoBar;
	public static Array<TextureRegion> explosionAnimation;

	/**
	 * Loads all assets from imageAtlas
	 * TODO: Should not have loose images in assets. All should be a Atlas
	 */
	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		
		explosionAnimation = new Array<TextureRegion>();
		playerShip = atlas.findRegion("Ships/PlayerShip");
		playerLaser = atlas.findRegion("weapons/greenLaser");
		enemyLaser = atlas.findRegion("weapons/redLaser");
		enemyBasicShip = atlas.findRegion("Ships/Ship1");
		enemyScoutShip = atlas.findRegion("Ships/Ship2");
		enemyHeavyShip = atlas.findRegion("Ships/Ship25");
		playerMissile = atlas.findRegion("weapons/Missile1");
		topInfoBar = atlas.findRegion("TopInfoBar");
		space = atlas.findRegion("space1");
		moveRightButton = atlas.findRegion("Arrow_Right");
		moveLeftButton = atlas.findRegion("Arrow_Left");
		
		for(int i = 1; i <= 17 ; i++){
			explosionAnimation.add(atlas.findRegion("explosion1 Frames/ExplosionFrame" + i));
		}

		
//		moveRightButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Right.png")));
//		moveLeftButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Left.png")));
//		space = new TextureRegion ( new Texture(Gdx.files.internal("space-1.png")));
	}
	/**
	 * Disposes all atlases from memory
	 */
	public static void dispose() {
		atlas.dispose();
	}
}
