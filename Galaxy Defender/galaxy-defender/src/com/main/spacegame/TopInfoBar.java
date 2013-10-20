package spacegame;

import screens.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import assets.ImageAssets;

/**
 * 
 * @author Grupp9
 * Bar above screen shoowing Map/Score/Health
 */
public class TopInfoBar extends Table{
	
	public static final float SCORE_PLACEMENT_X = GameScreen.GAME_WITDH*0.75f;
	public static final float PLACEMENT_Y = (float) (GameScreen.GAME_HEIGHT+0.5*GameScreen.INFO_SCREEN_HEIGHT);
	
	public static final float HEALTH_PLACEMENT_X = GameScreen.GAME_WITDH*0.4f;
	public static final float LEVEL_PLACEMENT_X = GameScreen.GAME_WITDH*0.1f;
	
	private TextField scoreField;
	private TextField healthBar;
	private TextField levelName;
	private GameLogic gameLogic;
	
	private Background background;
	
	public TopInfoBar(GameLogic gameLogic){
		this.gameLogic = gameLogic;
		setBounds(0, GameScreen.GAME_HEIGHT, GameScreen.GAME_WITDH, GameScreen.INFO_SCREEN_HEIGHT);
		background = new Background(getX(), getY(), getWidth(), getHeight(), ImageAssets.topInfoBar);
		scoreField = new TextField(SCORE_PLACEMENT_X, PLACEMENT_Y);
		healthBar = new TextField(HEALTH_PLACEMENT_X, PLACEMENT_Y);
		levelName = new TextField(LEVEL_PLACEMENT_X, PLACEMENT_Y);
		addActor(background);
	}
	
	
	/**
	 * Draws all actors on stage
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		background.drawBelow(batch, parentAlpha);
		scoreField.draw(batch, "Score " + gameLogic.getCurrentScore());
		healthBar.draw(batch, "Health " + gameLogic.playerShip.getCurrentHealth() + "%");
		levelName.draw(batch, gameLogic.getLevelName());
		super.draw(batch, parentAlpha);
	}
	
	
}
