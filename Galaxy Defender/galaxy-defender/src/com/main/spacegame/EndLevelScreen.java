package spacegame;

import highscore.HighscoreHandler;
import highscore.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Screen that shows when a level ha either completed or the player lost
 * @author Grupp9
 *
 */
public class EndLevelScreen implements Screen, InputProcessor{
	
	private String text="Enter Your Name: ";
	private String name="";
	private Stage stage;
	private Skin skin;
	private TextButton continueButton,laserButton,missileButton,empButton;
	private GameLogic gameLogic;
	private Label endLevelLabel,nextLevelLabel;
	private Label highScore;
	private MyGame myGame;
	private Table table,table2;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;
	private int level;
	private String[] endLevelTexts = {"GAME OVER","You saved Neptunus!","You saved Uranus!","You saved Saturn!","You saved Jupiter!","You saved Mars!","You saved our solar system! Well done!"};
	private String[] nextLevelTexts = {"","The mission on Uranus is to destroy the escaping enemy ship","Survive all asteroid-attacks on Saturn","Destroy the giant enemy ship to save Jupiter","In order to save Mars you have to make sure that\nnot a single enemy ship passes your position"
										,"Destroy the mothership in order to save Earth",""};
	
	/**
	 * Constructor
	 * @param myGame
	 * @param level
	 */
	public EndLevelScreen(MyGame myGame, int level){
		this.myGame = myGame;
		this.level = level;	
	}
	
	/**
	 * Render loop. 
	 */
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		stage.act();
		stage.draw();	
	}

	/**
	 * Called on screen resize
	 * Always on launch
	 */
	@Override
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	/**
	 * Shows screen and adds input control 
	 * Called when app is resumed on android
	 */
	@Override public void show() {
			
		stage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Keys.BACK) {
	                myGame.switchScreen(MyGame.ScreenType.MENU);
	            }
	            return super.keyDown(keyCode);
	        }
	    };
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table2 = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);		
		table.setBackground(menuBackground);
		table2.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT/2);
		
		highScore=new Label(text,skin);
		endLevelLabel = new Label(endLevelTexts[level],skin);
		nextLevelLabel = new Label(nextLevelTexts[level],skin);
		table.add(endLevelLabel).spaceBottom(50).row();
		table.add(nextLevelLabel).spaceBottom(50).row();
		
		if((level!=0) && (level!=6)){
			
			laserButton = new TextButton("Upgrade Laser:\n" + gameLogic.playerShip.getWeaponHandeler().getLaserUpgradeCost(),skin);
			laserButton.addListener(new ClickListener() {	       
		        public void clicked(InputEvent event,float x,float y )
		        {
		        	gameLogic.playerShip.getWeaponHandeler().upgradeLaser();
		        	laserButton.setText("Upgrade Laser:\n"+gameLogic.playerShip.getWeaponHandeler().getLaserUpgradeCost());
		        }
		    } );
			
			missileButton = new TextButton("Upgrade Missile:\n"+gameLogic.playerShip.getWeaponHandeler().getMissileUpgradeCost(),skin);
			missileButton.addListener(new ClickListener() {	       
		        public void clicked(InputEvent event,float x,float y )
		        {
		        	gameLogic.playerShip.getWeaponHandeler().increaseMissileBlastArea();
			        missileButton.setText("Upgrade Missile:\n" + gameLogic.playerShip.getWeaponHandeler().getMissileUpgradeCost());
		        }
		    } );
			
			empButton = new TextButton("Upgrade EMP:\n"+ gameLogic.playerShip.getWeaponHandeler().getEmpUpgradeCost(),skin);
			empButton.addListener(new ClickListener() {	       
		        public void clicked(InputEvent event,float x,float y )
		        {
		        	gameLogic.playerShip.getWeaponHandeler().increaseEMPDisableTime();
		        	empButton.setText("Upgrade EMP:\n" + gameLogic.playerShip.getWeaponHandeler().getEmpUpgradeCost());
		        }
		    } );
				
			table2.add(laserButton).width(130).height(50).spaceRight(30);
			table2.add(missileButton).width(130).height(50).spaceRight(30);
			table2.add(empButton).width(130).height(50);
		}
		
		continueButton = new TextButton("Continue", skin);
		continueButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.GAME);
	        	if(level==0 ||level==6){
		        	HighscoreHandler highscoreHandler = HighscoreHandler.getInstance();
		        	if(name.length()!=0){
						highscoreHandler.addPlayerToHighscore(new User(gameLogic.getCurrentScore(), name));
					}else  {
						highscoreHandler.addPlayerToHighscore(new User(gameLogic.getCurrentScore(), "Hero"));
					}
		        	myGame.resetGame();
					myGame.switchScreen(MyGame.ScreenType.HIGHSCORE);	               
	        	}	        	
	        }
	    } );
		
		
		if(level==0 || level==6){
			Gdx.input.setOnscreenKeyboardVisible(true);	
			table.add(highScore).spaceBottom(100).row();
			highScore.setText(text);
		}
		table.add(continueButton).width(130).height(50);
		
		stage.addActor(table);
		stage.addActor(table2);
				
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
	}

	/**
	 * Disables input from this screen
	 * Called when screen is not visible
	 */
	@Override public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	/**
	 * Player writes name and after highscorelist is shown
	 */
	@Override
	public boolean keyTyped (char character) {
		if (character == '\n') {
			Gdx.input.setOnscreenKeyboardVisible(false);
		} else if(Character.isLetterOrDigit(character)&& name.length()<13) {
			name += character;
			text += character;
			highScore.setText(text);
		}
		return false;
	}
	
	/**
	 * @param playerShip
	 */
	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}
	
	/**
	 * 
	 * @param cost the amount to reduce the score with
	 */
	protected void reducePlayerScore(int cost){
		myGame.reducePlayerScore(cost);
	}
	
	/**
	 * Used for deleting errors when writing highscore name and saving highscore in desktopversion
	 * by pressing the right arrow key
	 * 
	 **/
	@Override	
	public boolean keyDown(int keycode) {
		
		if(keycode==Keys.DEL && name.length()!=0){
			name = name.substring(0,name.length()-1);
			text = text.substring(0,text.length()-1);
			highScore.setText(text);
		}
		return false;
	}
	

	////////////// Unused methods  /////////////////
	@Override public boolean keyUp(int keycode) {return false;}
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {	return false;}
	@Override public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
	@Override public boolean mouseMoved(int screenX, int screenY) {return false;}
	@Override public boolean scrolled(int amount) {return false;}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}
}
