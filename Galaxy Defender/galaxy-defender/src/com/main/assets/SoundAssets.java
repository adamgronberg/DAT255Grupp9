package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Loads all sound assets
 * @author Grupp9
 */
public class SoundAssets {
	public static Music spaceMusic;
	public static Sound missileExplosion;
	public static Sound bossExplosion;
	
	/**
	 * Loads sounds
	 */
	public static void load(){
		missileExplosion = Gdx.audio.newSound(Gdx.files.internal("missileExplosion.wav"));
		bossExplosion = Gdx.audio.newSound(Gdx.files.internal("bossExplosion.mp3"));
		spaceMusic = Gdx.audio.newMusic(Gdx.files.internal("space_music.mp3"));
		spaceMusic.setLooping(true);
	}
	
	/**
	 * Disposes sound
	 */
	public static void dispose(){
		spaceMusic.dispose();
		bossExplosion.dispose();
		missileExplosion.dispose();
	}
}
