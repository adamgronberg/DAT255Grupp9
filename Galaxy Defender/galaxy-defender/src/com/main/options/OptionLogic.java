package options;

import screens.GameScreen;

import assets.SoundAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * The logic behind setting and getting options and the texts for option screen
 * @author Grupp9
 */
public class OptionLogic {
	
	private static Preferences data;
	private static String SOUND = "soundOption", MUSIC = "musicOption", VIBRATE = "vibrateOption", LAYOUT = "layoutOption";
	
	/**
	 * Loads options into preferences. Sets default values if no saved options
	 */
	public static void loadOptions(){
		data = Gdx.app.getPreferences("galaxydefender.data");
		if(!data.contains(SOUND)) data.putBoolean(SOUND, false);
		if(!data.contains(MUSIC)) data.putBoolean(MUSIC, false); 
		if(!data.contains(VIBRATE)) data.putBoolean(VIBRATE, false); 
		if(!data.contains(LAYOUT)) data.putInteger(LAYOUT, 1); 
	}
	
	/**
	 * Returns the current control button layout as an Enum
	 */
	public static GameScreen.ControlLayout getLayout(){
		switch(OptionLogic.getLayoutOption()){
			case 2:
				return GameScreen.ControlLayout.LAYOUT2;
			default:
				return GameScreen.ControlLayout.LAYOUT1;
		}
	}
	
	/**
	 * Turnes on and off music
	 */
	public static void musicOnOff(){
		if(getMusicOption()) SoundAssets.spaceMusic.play();
		else SoundAssets.spaceMusic.stop();
	}
	
	/**
	 * @return	Sound on/off
	 */
	public static boolean getSoundOption() {
		return data.getBoolean(SOUND);
	}
	
	/**
	 * @return Music on/off
	 */
	public static boolean getMusicOption() {
		return data.getBoolean(MUSIC);
	}
	
	/**
	 * @return	Vibrate on/off
	 */
	public static boolean getVibrateOption() {
		return data.getBoolean(VIBRATE);
	}
	
	/**
	 * @return	Current layout
	 */
	public static int getLayoutOption() {
		return data.getInteger(LAYOUT);
	}
	
	/**
	 * Toggle music on/off
	 */
	public static void toggleMusicOption(){
		data.putBoolean(MUSIC, !data.getBoolean(MUSIC));
		data.flush();
	}
	
	/**
	 * Toggle sound on/off
	 */
	public static void toggleSoundOption(){
		data.putBoolean(SOUND, !data.getBoolean(SOUND));
		data.flush();
	}
	
	/**
	 * Toggle vibrate on/off
	 */
	public static void toggleVibrateOption(){
		data.putBoolean(VIBRATE, !data.getBoolean(VIBRATE));
		data.flush();
	}
	
	/**
	 * Sets layout to next in order
	 */
	public static void nextlayout(){
	 	int layoutNumber = data.getInteger(LAYOUT);
	 	layoutNumber = layoutNumber%2+1; //Have 2 layouts, will always have a number between 1 and 2
	 	data.putInteger(LAYOUT, layoutNumber);
	 	data.flush();
	}
	
	/**
	 * Returns text for soundButton
	 */
	public static String getSoundText(){
		if(OptionLogic.getSoundOption()) return "Sound: set Off";
		else return "Sound: set On";
	}
	
	/**
	 * Returns text for vibrateButton
	 */
	public static String getVibrateText(){
		if(OptionLogic.getVibrateOption()) return "Vibration: set Off";
		else return "Vibration: set On";
	}
	
	/**
	 * Returns text for musicButton
	 */
	public static String getMusicText(){
		if(OptionLogic.getMusicOption()) return "Music: set Off";
		else return "Music: set On";
	}
	
	/**
	 * Returns text for layoutButton
	 */
	public static String getLayoutText(){
		switch(OptionLogic.getLayoutOption()){
			case 2:
				return "Control Layout 2";
			default:
				return "Control Layout 1";
		}
	}
}
