package levels;

import ships.HeavyShip;
import ships.KamikazeShip;
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
		
		gameLogic.addActor(ks);
		
	}

	@Override
	public void act(float delta) {
		
		
	}

	@Override
	public boolean missionCompleted() {
		return (CollisionDetection.getEnemyShipAmount()==0);
		
		
	}


}
