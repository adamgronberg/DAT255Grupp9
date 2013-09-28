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
	public static TextureRegion missileButton;
	public static Array<TextureRegion> explosionAnimation;
	public static Array<TextureRegion> enemyLaserAnimation;
	public static Array<TextureRegion> playerLaserAnimation;
	public static Array<TextureRegion> fireAnimation;

	/**
	 * Loads all assets from imageAtlas
	 * TODO: Should not have loose images in assets. All should be a Atlas
	 */
	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		
		explosionAnimation = new Array<TextureRegion>();
		enemyLaserAnimation = new Array<TextureRegion>();
		playerLaserAnimation = new Array<TextureRegion>();
		fireAnimation = new Array<TextureRegion>();
		
		playerShip = atlas.findRegion("Ships/PlayerShip");
		playerLaser = atlas.findRegion("weapons/greenLaser");
		enemyLaser = atlas.findRegion("weapons/redLaser");
		enemyBasicShip = atlas.findRegion("Ships/Ship3");
		enemyScoutShip = atlas.findRegion("Ships/Ship9");
		enemyHeavyShip = atlas.findRegion("Ships/Ship17");
		playerMissile = atlas.findRegion("weapons/Missile1");
		topInfoBar = atlas.findRegion("gui/TopInfoBar");
		space = atlas.findRegion("backgrounds/space1");
		moveRightButton = atlas.findRegion("gui/move_right");
		moveLeftButton = atlas.findRegion("gui/move_left");
		missileButton = atlas.findRegion("gui/missileButton");
		
		for(int i = 1; i <= 2 ; i++){
			enemyLaserAnimation.add(atlas.findRegion("weapons/redLaserHit" + i));
		}
		
		for(int i = 1; i <= 2 ; i++){
			playerLaserAnimation.add(atlas.findRegion("weapons/greenLaserHit" + i));
		}

		for(int i = 1; i <= 13 ; i++){
			fireAnimation.add(atlas.findRegion("fire animation/fire" + i));
		}
		
		for(int i = 1; i <= 17 ; i++){
			explosionAnimation.add(atlas.findRegion("explosion1 Frames/ExplosionFrame" + i));
		}
	}
	/**
	 * Disposes all atlases from memory
	 */
	public static void dispose() {
		atlas.dispose();
	}
}
