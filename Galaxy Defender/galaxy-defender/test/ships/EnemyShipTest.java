package ships;





import static org.junit.Assert.*;
import org.junit.Test;



import com.badlogic.gdx.scenes.scene2d.ui.Table;


/*Kommentera bort 	"onFireAnimation = new Animation(TIMEPERFRAME, ImageAssets.fireAnimation) i EnemyShip.java"
 Innan testning
 Glöm ej avkommentering innan merging
*/

public class EnemyShipTest {




	@Test
	public void xPosTest() {
			
		EnemyShip eShip = new HeavyShip(0,0);
		Table table = new Table();
		table.addActor(eShip);
		assertTrue(0==eShip.getBounds().x);
			
	}
	
	
	@Test
	public void hitTest(){
		EnemyShip eShip1= new BasicShip(0,0);
		EnemyShip eShip2= new BasicShip(0,0);
		
		
		eShip1.hit(2);
		assertTrue(eShip1.currentHealth==0);
		
		eShip2.hit(1);
		assertTrue(eShip2.currentHealth==1);
	}
	
	@Test
	public void actTest(){
		
		
		
		Table table=new Table();
		EnemyShip hShip= new HeavyShip(0,0);
		EnemyShip sShip= new ScoutShip(0,0);
		EnemyShip bShip= new BasicShip(0,0);
		
		
		table.addActor(hShip);
		table.addActor(sShip);
		table.addActor(bShip);
		
		assertTrue(table.getChildren().size==3);
		
		hShip.act(1);
		sShip.act(1);
		bShip.act(1);
		System.out.println(""+table.getChildren().size);
		
		assertTrue(table.getChildren().size>=3);
		assertFalse(hShip.getBounds().y==0);
		assertFalse(sShip.getBounds().y>=0);
		assertFalse(sShip.getBounds().x==0);
		assertFalse(bShip.getBounds().y==0);	
		
	}
	
	@Test
	public void destroyedTest(){
		EnemyShip hShip= new HeavyShip(0,0);
		Table table=new Table();
		table.addActor(hShip);
		assertTrue(table.getChildren().size==1);
		hShip.destroyShip();
		assertTrue(table.getChildren().size==0);
		
		
	}
	
	
	
	

}

