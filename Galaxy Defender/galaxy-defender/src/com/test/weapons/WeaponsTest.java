/**package weapons;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import assets.ImageAssets;

public class WeaponsTest extends Projectile{


	
	public WeaponsTest(){
		super(0f, 0f, 0f, 0f, 1, ImageAssets.laser);
	}



	
	
	

	
	@Test
	public void actTest(){
		Projectile pro= new PlayerLaser(0,0);
		Projectile pro2= new PlayerMissile(0,0);
		
		pro.act(1);
		pro2.act(1);
		
		assertFalse(pro.getBounds().y==0);
		assertFalse(pro2.getBounds().y<=0);	
		assertTrue(pro.getBounds().x==0);
		assertTrue(pro2.getBounds().x==0);	
	}
	
	@Test
	public void wTest(){
		Projectile pro= new MockProjectile(0f,0f,0f,-5,1, ImageAssets.laser);
		Projectile pro2= new PlayerMissile(0,0);
		
		pro.act(1);
		pro2.act(1);
		
		assertFalse(pro.getBounds().y!=0);
		assertFalse(pro2.getBounds().y<=0);	
	}


	@Override
	public void addOnHitEffect() {
		// TODO Auto-generated method stub	
	}


	@Override
	public TargetTypes[] getTargetTypes() {
		// TODO Auto-generated method stub
		return null;
	}


}
*/