package levels;


import spacegame.GameLogic;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Level extends Actor {
	
	protected String levelname;
	protected float timer;
	protected GameLogic gameLogic;
	

	
	public Level(GameLogic gameLogic){
		super();
		this.gameLogic=gameLogic;	
	}
	
	public String getName(){
		return levelname;
	}
	abstract public void spawn();
	
	abstract public boolean missionCompleted();
	
	

}
