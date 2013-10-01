/**package ships;

import static org.junit.Assert.*;
import org.junit.Test;



public class EnemyShipTest{


	
	@Test
	public void xPosTest() {

		EnemyShip eShip = new HeavyShip(0,0);
		assertTrue(0==eShip.getBounds().x);
			
	}
	
	
	@Test
	public void hitTest(){
		EnemyShip eShip1= new HeavyShip(0,0);
		EnemyShip eShip2= new HeavyShip(0,0);
		
		eShip1.hit(2);
		assertTrue(eShip1.health==0);
		
		eShip2.hit(1);
		assertTrue(eShip2.health==1);
	}
	
	@Test
	public void actTest(){
		EnemyShip hShip= new HeavyShip(0,0);
		EnemyShip sShip= new ScoutShip(0,0);
		EnemyShip bShip= new BasicShip(0,0);
		
		hShip.act(1);
		sShip.act(1);
		bShip.act(1);
		
		assertFalse(hShip.getBounds().y==0);
		assertFalse(sShip.getBounds().y>=0);
		assertFalse(sShip.getBounds().x==0);
		assertFalse(bShip.getBounds().y==0);	
	}
	
	
	
	
	

}
*/
