package spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import assets.*;
import input.InputControl;


/**
 * 
 * @author Grupp9
 *
 * Draws everything on GameScreen and handles input
 * Tells all actors to draw and act
 */
public class GameScreen implements Screen{
	private Stage stage;
	private GameLogic gameLogic;
	private InputControl inputController;
	private TopInfoBar topInfoBar;
	
	public static boolean sound = false; 			//TODO: Temp. disables/enables sound
	public static boolean optionAutoShoot = true; 	//TODO: Temp. disables/enables shoot
	
	public static enum ControlLayout {LAYOUT1, LAYOUT2}
	public ControlLayout currentLayout = ControlLayout.LAYOUT2;
	
	public static final float GAME_HEIGHT= ((float) MyGame.HEIGHT)*0.95f;
	public static final float GAME_WITDH= ((float) MyGame.WIDTH);
	public static final float INFO_SCREEN_HEIGHT=((float) MyGame.HEIGHT)*0.05f;
	public static final int MOVMENT_BUTTON_SIZE = 60;
	public static final String LOG = GameScreen.class.getSimpleName();
	
	private InteractionButton moveLeftButton;
	private InteractionButton moveRightButton;
	private InteractionButton shootMissileButton;
	private InteractionButton shootEMPButton;

	/**
	 * Constructor
	 * Adds gameLogic as actor, starting the game
	 */
	public GameScreen(MyGame myGame) {
		moveLeftButton = new InteractionButton(0, 0, GameScreen.MOVMENT_BUTTON_SIZE,
				GameScreen.MOVMENT_BUTTON_SIZE, ImageAssets.moveLeftButton);

		moveRightButton = new InteractionButton(GameScreen.MOVMENT_BUTTON_SIZE, 0, 
				GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, ImageAssets.moveRightButton);
			
		shootMissileButton = new InteractionButton(GAME_WITDH - GameScreen.MOVMENT_BUTTON_SIZE, 0, 
				GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, ImageAssets.missileButton);
		
		shootEMPButton = new InteractionButton(GAME_WITDH - GameScreen.MOVMENT_BUTTON_SIZE*2, 0, 
				GameScreen.MOVMENT_BUTTON_SIZE, GameScreen.MOVMENT_BUTTON_SIZE, ImageAssets.emptyButton);
		
		gameLogic = new GameLogic();
		inputController = new InputControl(gameLogic, this);
		topInfoBar = new TopInfoBar(this);
		
		stage = new Stage();
		stage.addActor(gameLogic);
		stage.addActor(topInfoBar);
		stage.addActor(shootMissileButton);
		stage.addActor(shootEMPButton);
		stage.addActor(moveLeftButton);
		stage.addActor(moveRightButton);		
		if(sound) SoundAssets.spaceMusic.play();
	}
	
	/**
	 * Called on screen resize
	 * Always on launch
	 */
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}
	
	/**
	 * Converts input from mouse/touch to correct coordinate system
	 * @return corrected x,y coordinates (y is not corrected)
	 */
	public Vector3 unProjectCoordinates(float x, float y){
		Vector3 vector = new Vector3();
		vector.set(x, y, 0);
		stage.getCamera().unproject(vector);
		return vector;
	}
	
	/**
	 * Returns the current layout
	 * @return
	 */
	public ControlLayout getCurrentLayout(){
		return currentLayout;
	}
	
	/**
	 * Change android layout
	 */
	public void changeOptionControlLayout(){
		switch(currentLayout){
			case LAYOUT1:
				currentLayout = ControlLayout.LAYOUT2;
				moveRightButton.setX(GameScreen.MOVMENT_BUTTON_SIZE);
				shootMissileButton.setX(GAME_WITDH - GameScreen.MOVMENT_BUTTON_SIZE);
				break;
			case LAYOUT2:
				currentLayout = ControlLayout.LAYOUT1;
				moveRightButton.setX(GAME_WITDH - GameScreen.MOVMENT_BUTTON_SIZE);
				shootMissileButton.setX(GAME_WITDH/2 - GameScreen.MOVMENT_BUTTON_SIZE/2);
				break;
		}
	}	
	
	/**
	 * @return Move left button
	 */
	public InteractionButton getMoveLeftButton(){
		return moveLeftButton;
	}
	
	/**
	 * @return Move right button
	 */
	public InteractionButton getMoveRightButton(){
		return moveRightButton;
	}
	
	/**
	 * @return Shoot missile button
	 */
	public InteractionButton getShootMissileButton(){
		return shootMissileButton;
	}
	
	/**
	 * @return Shoot missile button
	 */
	public InteractionButton getShootEMPButton(){
		return shootEMPButton;
	}
	
	/**
	 * Render loop. 
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(gameLogic.playerShip.getMissileReady() == true){
			shootMissileButton.setVisible(true);
		} 
		else shootMissileButton.setVisible(false);
		
		if(gameLogic.playerShip.getEMPReady() == true){
			shootEMPButton.setVisible(true);
		} 
		else shootEMPButton.setVisible(false);
		
		stage.act(delta);				//Tells all actors to act
		stage.draw();					//Tells all actors to draw
		}
	
	/**
	 * Shows screen and adds input control 
	 * Called when app is resumed on android
	 */
	@Override
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputController);
		multiplexer.addProcessor(new GestureDetector(inputController));
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	/**
	 * @return Current GameLogic score
	 */
	public int getGameLogicScore(){
		return gameLogic.getScore();
	}
	
	/**
	 * @return Current GameLogic health
	 */
	public int getGameLogicHealth(){
		return gameLogic.getPlayerHealth();
	}
	
	/**
	 * Hides screen and removes input control
	 * Called when game pauses(Home button on android)
	 */	
	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}

	public static void toggleOptionAutoShoot() {
		optionAutoShoot = !optionAutoShoot;
		
	}	
}
