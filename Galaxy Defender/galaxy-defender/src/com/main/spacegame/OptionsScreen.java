package spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class OptionsScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	private TextButton mainMenuButton;
	private MyGame myGame;
	private Table table;
	private TextureAtlas atlas;
	private TextureRegionDrawable menuBackground;

	public OptionsScreen(MyGame myGame){
		this.myGame = myGame;
	}
	
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();	
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
		Gdx.input.setInputProcessor(stage);
	}

	@Override public void show() {
		stage = new Stage();
		atlas = new TextureAtlas("uiskin.atlas");
		menuBackground = new TextureRegionDrawable(assets.ImageAssets.mainMenu);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		table = new Table(skin);
		table.setBounds(0, 0,MyGame.WIDTH,MyGame.HEIGHT);
		table.setBackground(menuBackground);
		
		mainMenuButton = new TextButton("Main Menu", skin);
		mainMenuButton.addListener(new ClickListener() {	       
	        public void clicked(InputEvent event,float x,float y )
	        {
	        	myGame.switchScreen(MyGame.ScreenType.MENU);
	        }
	    } );
		
		mainMenuButton.pad(15, 10, 15, 10);	
	
		table.add(mainMenuButton).spaceBottom(50).row();
		
		stage.addActor(table);	
	}

	@Override public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override public void pause() {}

	@Override public void resume() {}

	@Override public void dispose() {}

}
