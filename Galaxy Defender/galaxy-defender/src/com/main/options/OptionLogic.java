package options;

import screens.GameScreen;

import assets.SoundAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * The logic behind setting and getting options and the texts for option screen
 * @author Grupp9
 *
 */
public class OptionLogic {
	
	private static Preferences options;
	
	/**
	 * Loads options into preferences. Sets default values if no saved options
	 */
	public static void loadOptions(){
		options = Gdx.app.getPreferences("galaxydefender.options");
		if(!options.contains("sound")) options.putBoolean("sound", false);
		if(!options.contains("music")) options.putBoolean("music", false); 
		if(!options.contains("vibrate")) options.putBoolean("vibrate", false); 
		if(!options.contains("layout")) options.putInteger("layout", 1); 
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
		return options.getBoolean("sound");
	}
	
	/**
	 * @return Music on/off
	 */
	public static boolean getMusicOption() {
		return options.getBoolean("music");
	}
	
	/**
	 * @return	Vibrate on/off
	 */
	public static boolean getVibrateOption() {
		return options.getBoolean("vibrate");
	}
	
	/**
	 * @return	Current layout
	 */
	public static int getLayoutOption() {
		return options.getInteger("layout");
	}
	
	/**
	 * Toggle music on/off
	 */
	public static void toggleMusicOption(){
		options.putBoolean("music", !options.getBoolean("music"));
		options.flush();
	}
	
	/**
	 * Toggle sound on/off
	 */
	public static void toggleSoundOption(){
		options.putBoolean("sound", !options.getBoolean("sound"));
		options.flush();
	}
	
	/**
	 * Toggle vibrate on/off
	 */
	public static void toggleVibrateOption(){
		options.putBoolean("vibrate", !options.getBoolean("vibrate"));
		options.flush();
	}
	
	/**
	 * Sets layout to next in order
	 */
	public static void nextlayout(){
	 	int layoutNumber = options.getInteger("layout");
	 	layoutNumber = layoutNumber%2+1; //Have 2 layouts, will always have a number between 1 and 2
	 	options.putInteger("layout", layoutNumber);
	 	options.flush();
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
