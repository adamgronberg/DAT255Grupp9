package ships;

import java.util.Iterator;

import spacegame.GameLogic;
import spacegame.GameScreen;
import spacegame.MovableEntity;
import weapons.Projectile;
import assets.ImageAssets;
import com.badlogic.gdx.utils.Array;

public class EscapingShip extends EnemyShip {
	private final static float SHIPSPEED=2.1f;
	private final static int HEALTH=7;
	private final static int SCOREVALUE=40;
	
	public final static int HEIGHT=45;
	public final static int WIDTH=35;
	private static final int DAMAGE_WHEN_RAMMED = 0;

	private static final int BOUNCING_CONST = 30;
	
	private static final boolean DISABABLE = true;
	
	protected boolean movingLeft;
	protected boolean bouncing;
	protected int bouncingCounter;
	protected float timeBeforeTurn;
	private GameLogic gl;
	private boolean isAlive = true;
	
	/**
	 * Constructor
	 * @param x	x-led Spawn location
	 * @param y y-led Spawn location
	 * @param gl gamelogic needed for its ability to dodge hostile projectile
	 */
	public EscapingShip( float x, float y, GameLogic gl) {
		super(WIDTH, HEIGHT, x, y, HEALTH, SCOREVALUE, ImageAssets.escapingShip, DAMAGE_WHEN_RAMMED, DISABABLE);
		movingLeft=true;
		bouncing=false;
		this.gl=gl;
		
	}

	@Override
	protected void move(float delta) {
		
		changeDirectionCheck();
		if(movingLeft){
			setX(getX()-SHIPSPEED*currentHealth/HEALTH);
		}
		else{
			setX(getX()+SHIPSPEED*currentHealth/HEALTH);
		}
	}
	
	protected void changeDirectionCheck(){
		
		if(!bouncing){
			avoidLaser();
		}else{
			bouncingCounter--;
			if(bouncingCounter<1){
				bouncing=false;
			}
		}
		if(isOutOfBoundsX(this)){
			movingLeft= !movingLeft;
			bouncing=true;
			bouncingCounter = BOUNCING_CONST;
		}
	}

	/**
	 * Checks if any movableobject is out of bounds
	 * @param actors
	 */
	protected boolean isOutOfBoundsX(MovableEntity movableObj){
		if (getX() < 0 || getX() +WIDTH > GameScreen.GAME_WITDH){
			return true;
		}
		return false;
	}
	
	/**
	 * check how to best avoid player projectiles
	 */
	protected void avoidLaser(){
		long misileCheck = 0;
		Array<Projectile> playerProjectile = gl.getPlayerShotts();
		
		if(!isSideEmpty(playerProjectile)){
			for(Projectile proj: playerProjectile){
				misileCheck += Math.pow(proj.getY(),4)*(1/((getX()+WIDTH/2)-proj.getX()));
			}
			
			if(misileCheck>0){
				movingLeft=false;
			}
			else{
				movingLeft=true;
			}
		}
	}
	
	/**
	 * check if one side is safe and then move ship in 
	 * @return if one side is free from player projectiles
	 */
	protected boolean isSideEmpty( Array<Projectile> playerProjectile ){
		boolean leftSideEmpty = true;
		boolean rightSideEmpty = true;
		Projectile projectile;
		
		Iterator<Projectile> it = playerProjectile.iterator();
		while(it.hasNext()&&(leftSideEmpty||rightSideEmpty)){
			projectile = it.next();
			if(dangerous(projectile)){
				return false;
			}
			if(projectile.getX()<GameScreen.GAME_WITDH/2){
				leftSideEmpty=false;
			}else{
				rightSideEmpty=false;
			}
		}
		
		
		
		if((!leftSideEmpty)&&(!rightSideEmpty)){
			return false;
		}else if(rightSideEmpty){
			movingLeft = false;
		}else{
			movingLeft = true;
		}
		
		//System.out.println("re:  "+rightSideEmpty+"   ml: "+movingLeft+ "  le: "+ leftSideEmpty);
		
		return true;
		
	}
	
	/**
	 * 
	 * @param projectile
	 * @return if projectile should be considered as a great threat
	 */
	protected boolean dangerous(Projectile projectile){
		
		return distToShip(projectile)<70;
	}
	
	/**
	 * 
	 * @param projectile 
	 * @return distance between projectile and the ship
	 */
	protected float distToShip(Projectile projectile){
		return (float) Math.sqrt( Math.pow(projectile.getX()-this.getX()+EscapingShip.WIDTH/2, 2) + Math.pow(projectile.getY()-this.getY()+ EscapingShip.HEIGHT/2, 2));
	}

	/**
	 * @return true if ship is alive
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	/**
	 *  Removes the ship
	 */
	@Override
	public void destroyShip() {
		isAlive = false;
		remove();
	}
	
	@Override protected void shoot(float delta) {}
}
