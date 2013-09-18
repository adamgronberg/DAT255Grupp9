package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScreen implements Screen, GestureListener {
	private Stage stage;
	private Button button;
	private ButtonStyle buttonStyle;
	private GameLogic gameLogic;

	public static final String LOG = GameScreen.class.getSimpleName();
	
	
	public GameScreen() {
		stage = new Stage();
//		buttonStyle.checked.draw(batch, 0, 0, 15,15);
//		button = new Button();
		gameLogic = new GameLogic();
		stage.addActor(gameLogic);
	}
	
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			
			//bucket.x = touchPos.x - 64 / 2;
			if (touchPos.x <= 90 && touchPos.y >= 710) {
				Gdx.app.log( GameScreen.LOG, "Pressed x :" + touchPos.x + " y : " + touchPos.y  );
				gameLogic.playerShip.tryMoveLeft();
			}
			if (touchPos.x >= 400 && touchPos.y >= 710) {
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
		if (velocityX < -100) gameLogic.playerShip.tryMoveLeft();
		if (velocityX > 100) gameLogic.playerShip.tryMoveRight();
		return false;
	}

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	@Override public boolean touchDown(float x, float y, int pointer, int button) {
		//Gdx.app.log( GameScreen.LOG, "Pressed x :" + x + " y : " + y  );
		
		return false;
	}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

}
