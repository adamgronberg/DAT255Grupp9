package levels;

import com.badlogic.gdx.math.MathUtils;

import ships.*;
import spacegame.GameLogic;
import collisiondetection.CollisionDetection;

public class Uranus extends Level{
	
public Uranus(GameLogic gl){
		
		super(gl);
		levelname="Uranus";
		spawn();
		
	}

	@Override
	public void spawn() {
		KamikazeShip ks=new KamikazeShip(200,700, gameLogic.playerShip);
		EscapingEnemyShip ees = new EscapingEnemyShip(200,700, gameLogic);
		for(int i=0; i<5; i++){
			StealthShip ss = new StealthShip(MathUtils.random(0,400),700,gameLogic.playerShip);
			gameLogic.addActor(ss);
		}
		for(int i=0; i<10; i++){
			ks = new KamikazeShip(MathUtils.random(0,400),700,gameLogic.playerShip);
			gameLogic.addActor(ks);
		}
		gameLogic.addActor(ks);
		gameLogic.addActor(ees);
		
	}

	@Override
	public void act(float delta) {
		
		
	}

	@Override
	public boolean missionCompleted() {
		return (CollisionDetection.getEnemyShipAmount()==0);
		
		
	}


}
