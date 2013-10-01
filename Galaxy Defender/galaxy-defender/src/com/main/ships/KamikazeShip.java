package ships;

import spacegame.GameScreen;
import assets.ImageAssets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class KamikazeShip extends EnemyShip {
	
	public final static int HEIGHT = 45;
	public final static int WIDTH = 45;
	private final static float SHIPSPEED_X = 0.8f;
	private final static float SHIPSPEED_Y = 3f;
	private final static int HEALTH=2;
	private final static int SCOREVALUE=15;
	private final static int DAMAGE_WHEN_RAMMED = 40;
	
	private PlayerShip playerShip;

	public KamikazeShip(float x, float y, PlayerShip playerShip){
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.enemyKamikazeShip, DAMAGE_WHEN_RAMMED);		
		this.playerShip = playerShip;
	}
	
	@Override
	public void act(float delta){		
		super.act(delta);
		if(playerShip.getX()>getX()){
			setX(getX()+SHIPSPEED_X);
		}else if(playerShip.getX()<getX()){
			setX(getX()-SHIPSPEED_X);
		}
		setY(getY()-SHIPSPEED_Y);
	}

}
