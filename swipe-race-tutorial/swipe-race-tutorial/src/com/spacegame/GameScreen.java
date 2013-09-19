package com.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 * 
 * @author Grupp9
 *
 */
public class GameScreen implements Screen, GestureListener {
	private Stage stage;
	private GameLogic gameLogic;
	
	public static final int MOVMENT_BUTTON_SIZE = 70;

	public static final String LOG = GameScreen.class.getSimpleName();
	
	
	public GameScreen() {
		stage = new Stage();
		gameLogic = new GameLogic();
		stage.addActor(gameLogic);
	}
	
	/**
	 * Called on screen resize
	 * eg. Always on launch
	 */
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}
	/**
	 * Render loop. 
	 * TODO: Should input be handled here?
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			
			if (touchPos.x <= MOVMENT_BUTTON_SIZE && touchPos.y >= MyGame.HEIGHT-MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				Gdx.app.log( GameScreen.LOG, "Pressed x :" + touchPos.x + " y : " + touchPos.y  );
				gameLogic.playerShip.tryMoveLeft();
			}
			if (touchPos.x >= MyGame.WIDTH-MOVMENT_BUTTON_SIZE && touchPos.y >= MyGame.HEIGHT-MOVMENT_BUTTON_SIZE) {		//TODO: Should use buttons instead of areas
				Gdx.app.log( GameScreen.LOG, "Pressed x :" + touchPos.x + " y : " + touchPos.y  );
				gameLogic.playerShip.tryMoveRight();
			}
		}
		
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }
	
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
//		if (velocityX < -100) gameLogic.playerShip.tryMoveLeft();
//		if (velocityX > 100) gameLogic.playerShip.tryMoveRight();
		return false;
	}

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false;}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

}
