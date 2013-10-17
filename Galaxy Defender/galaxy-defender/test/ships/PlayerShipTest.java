package ships;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerShipTest {

	@Test
	public void testHealth() {
		PlayerShip ps = new PlayerShip();
		
		
		assertTrue(ps.getCurrentHealth()==100);
		
		ps.decreaseCurrentHealth(30);
		assertTrue(ps.getCurrentHealth()==70);
		
		ps.resetHealth();
		assertTrue(ps.getCurrentHealth()==100);
	}

}
