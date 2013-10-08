package levels;

import collisiondetection.CollisionDetection;
import ships.Asteroid;
import ships.KamikazeShip;
import spacegame.GameLogic;

public class Saturn extends Level{

	public Saturn(GameLogic gl){
	super(gl);
	levelname="Saturn";
	spawn();
	
}

@Override
public void spawn() {
	Asteroid ast=new Asteroid(300,700);
	
	gameLogic.addActor(ast);
	
}

@Override
public void act(float delta) {
	
	
}

@Override
public boolean missionCompleted() {
	return false;
	
	
}

}
