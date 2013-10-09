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
	public static TextureRegion escapingShip;
	public static TextureRegion enemyKamikazeShip;
	public static TextureRegion enemyMultiShooterShip;
	public static TextureRegion enemyBigLaserShip;
	public static TextureRegion enemyStealthShip;
	public static TextureRegion enemyTurretShip;
	public static TextureRegion space;
	public static TextureRegion mainMenu;
	public static TextureRegion moveLeftButton;
	public static TextureRegion moveRightButton;
	public static TextureRegion emptyButton;
	public static TextureRegion playerLaser;
	public static TextureRegion enemyLaser;
	public static TextureRegion playerMissile;
	public static TextureRegion playerIonCannon;
	public static TextureRegion topInfoBar;
	public static TextureRegion missileButton;
	public static Array<TextureRegion> asteroids;
	public static Array<TextureRegion> explosionAnimation;
	public static Array<TextureRegion> enemyLaserAnimation;
	public static Array<TextureRegion> playerLaserAnimation;
	public static Array<TextureRegion> fireAnimation;

	/**
	 * Loads all assets from imageAtlas
	 */
	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		
		explosionAnimation = new Array<TextureRegion>();
		enemyLaserAnimation = new Array<TextureRegion>();
		playerLaserAnimation = new Array<TextureRegion>();
		fireAnimation = new Array<TextureRegion>();
		
		
		playerShip = atlas.findRegion("Ships/PlayerShip");
		playerLaser = atlas.findRegion("weapons/greenLaser");
		playerIonCannon = atlas.findRegion("weapons/blueMegaLaser");
		enemyLaser = atlas.findRegion("weapons/redLaser");
		escapingShip = atlas.findRegion("Ships/Ship8");
		enemyBasicShip = atlas.findRegion("Ships/Ship3");
		enemyScoutShip = atlas.findRegion("Ships/Ship9");
		enemyHeavyShip = atlas.findRegion("Ships/Ship17");
		enemyKamikazeShip = atlas.findRegion("Ships/Ship15");
		enemyMultiShooterShip = atlas.findRegion("Ships/Ship12");
		enemyBigLaserShip = atlas.findRegion("Ships/Ship14");
		enemyStealthShip = atlas.findRegion("Ships/Ship10");
		enemyTurretShip = atlas.findRegion("Ships/Ship23");
		playerMissile = atlas.findRegion("weapons/Missile1");
		topInfoBar = atlas.findRegion("gui/TopInfoBar");
		space = atlas.findRegion("backgrounds/space1");
		mainMenu = atlas.findRegion("backgrounds/MainMenu");
		moveRightButton = atlas.findRegion("gui/move_right");
		moveLeftButton = atlas.findRegion("gui/move_left");
		missileButton = atlas.findRegion("gui/missileButton");
		emptyButton = atlas.findRegion("gui/EMPButton");
		

		asteroids = new Array<TextureRegion>(); 
		for(int i = 1; i <= 12; i++){
			asteroids.add(atlas.findRegion("Ships/Asteroid" + i));
		}
		
		for(int i = 1; i <= 2; i++){
			enemyLaserAnimation.add(atlas.findRegion("weapons/redLaserHit" + i));
		}
		
		for(int i = 1; i <= 2; i++){
			playerLaserAnimation.add(atlas.findRegion("weapons/greenLaserHit" + i));
		}

		for(int i = 1; i <= 13; i++){
			fireAnimation.add(atlas.findRegion("fire animation/fire" + i));
		}
		
		for(int i = 1; i <= 17; i++){
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
