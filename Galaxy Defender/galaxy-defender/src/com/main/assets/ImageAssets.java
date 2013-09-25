package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
	public static TextureRegion space;
	public static TextureRegion moveLeftButton;
	public static TextureRegion moveRightButton;
	public static TextureRegion laser;
	public static TextureRegion playerMissile;
	public static TextureRegion explosionSplashMain;
	public static TextureRegion explosionSplashUp;
	public static TextureRegion topInfoBar;
	public static Array<TextureRegion> explosionAnimation;

	/**
	 * Loads all assets from imageAtlas
	 * TODO: Should not have loose images in assets. All should be a Atlas
	 */
	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		explosionAnimation = new Array<TextureRegion>();
		playerShip = atlas.findRegion("playerShip");
		laser = atlas.findRegion("laser");
		enemyBasicShip = atlas.findRegion("enemyShip");
		enemyScoutShip = atlas.findRegion("scoutShip");
		playerMissile = atlas.findRegion("MissileRed");
		explosionSplashMain = atlas.findRegion("ExplosionSplashMain");
		explosionSplashUp = atlas.findRegion("ExplosionSplashUp");
		topInfoBar = atlas.findRegion("TopInfoBar");
		
		for(int i = 1; i <= 17 ; i++){
			explosionAnimation.add(atlas.findRegion("explosion1 Frames/ExplosionFrame" + i));
		}

		
		moveRightButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Right.png")));
		moveLeftButton = new TextureRegion ( new Texture(Gdx.files.internal("Arrow_Left.png")));
		space = new TextureRegion ( new Texture(Gdx.files.internal("space-1.png")));
	}
	/**
	 * Disposes all atlases from memory
	 */
	public static void dispose() {
		atlas.dispose();
	}
}
