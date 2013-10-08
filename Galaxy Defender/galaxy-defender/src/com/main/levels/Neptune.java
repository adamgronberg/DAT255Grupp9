package levels;

import collisiondetection.CollisionDetection;
import ships.HeavyShip;
import spacegame.GameLogic;

public class Neptune  extends Level{
	
	public Neptune(GameLogic gl){
		
		super(gl);
		levelname="Neptune";
		spawn();
	}

	@Override
	public void spawn() {
		HeavyShip HS=new HeavyShip(200,700);
		gameLogic.addActor(HS);
		
	}

	@Override
	public void act(float delta) {
		
		
	}

	@Override
	public boolean missionCompleted() {
		return (CollisionDetection.getEnemyShipAmount()==0);
		
		
	}

	
}
