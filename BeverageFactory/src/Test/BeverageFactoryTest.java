package Test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.beverageFactory.BeverageFactory;

public class BeverageFactoryTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	BeverageFactory beverageFactory = new BeverageFactory();

	@Test
	public void testCalculateOrderValue() {
		
		
		String order1 = "[\"chai,-sugar\",\"chai\",\"coffee,-milk\"]";
		assertTrue(beverageFactory.calculateOrderValue(order1) == 11.5);
		
		String order2 =  "coffee";
		assertTrue(beverageFactory.calculateOrderValue(order2) == 5);
		
		String order3 =  "chai,-sugar";
		assertTrue(beverageFactory.calculateOrderValue(order3) == 3.5);
		
		String order4 =  "[\"coffee\",\"chai\"]";
		assertTrue(beverageFactory.calculateOrderValue(order4) == 9);
		
		
	}
	
	@Test
	public void testCalculateOrderValueExceptionScenario1() throws Exception {
		String order =  "Juice";
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Invalid order");
		beverageFactory.calculateOrderValue(order);
	}


	@Test
	public void testCalculateOrderValueExceptionScenario2() throws Exception {
		String order =  "";
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Invalid order");
		beverageFactory.calculateOrderValue(order);
	}
	

	@Test
	public void testCalculateOrderValueExceptionScenario3() throws Exception {
		String order =  "coffee,-coffee";
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Invalid order");
		beverageFactory.calculateOrderValue(order);
	}
	

	@Test
	public void testCalculateOrderValueExceptionScenario4() throws Exception {
		String order =  "chai,-soda";
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Invalid order");
		beverageFactory.calculateOrderValue(order);
	}
}
