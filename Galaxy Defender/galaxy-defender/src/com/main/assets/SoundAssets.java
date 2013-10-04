package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * 
 * @author Grupp9
 * Loads all sound assets on start
 */
public class SoundAssets {
	public static Music spaceMusic;
	public static Sound missileExplosion;
	
	/**
	 * Loads sounds
	 */
	public static void load(){
		missileExplosion = Gdx.audio.newSound(Gdx.files.internal("missileExplosion.wav"));
		spaceMusic = Gdx.audio.newMusic(Gdx.files.internal("space_music.mp3"));
		spaceMusic.setLooping(true);
	}
	
	/**
	 * Disposes sound
	 */
	public static void dispose(){
		spaceMusic.dispose();
		missileExplosion.dispose();
	}
}
