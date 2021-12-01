import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {
	Town source, destination;
	Road road1;
	Road road2;
	Road road3;
	Road road4;
	
	Road road5;
	
	@BeforeEach
	void setUp() throws Exception {
		road1 = new Road(new Town("Takoma"), new Town("Beltsville"), "road_1");
		road2 = new Road(new Town("Rockville"), new Town("Beltsville"), "road_2");
		road3 = new Road(new Town("Geithersburg"), new Town("Takoma"), "road_3");
		road4 = new Road(new Town("Geithersburg"), new Town("Rockville"), "road_4");
		
		road5 = new Road(new Town("Olney"), new Town("SilverSpring"),4, "road_5");
	}

	@AfterEach
	void tearDown() throws Exception {
		source = null;
		destination = null;
		road1 = null;
		road2 = null;
		road3 = null;
		road4 = null;
	}

	
	
	@Test
	void testCompareTo() {
		assertNotEquals(0, road1.compareTo(road2) );
		
	}


	@Test
	void testContains() {
		
		assertEquals(true, road1.contains(new Town("Beltsville")));
		assertEquals(true, road2.contains(new Town("Rockville")));
		assertEquals(true, road3.contains(new Town("Geithersburg")));
		assertEquals(true, road4.contains(new Town("Rockville")));
		
	}
	

	@Test
	void testEquals() {
		assertEquals(false, road1.equals(road4));
	}
	
	@Test
	void testGetDestination() {
		
		assertEquals("Beltsville", road1.getDestination().getName());
		assertEquals("Beltsville", road2.getDestination().getName());
		assertEquals("Takoma", road3.getDestination().getName());
		assertEquals("Rockville", road4.getDestination().getName());
	}
	
	@Test
	void testGetName() {
		assertEquals("road_1", road1.getName());
		assertEquals("road_2", road2.getName());
		assertEquals("road_3", road3.getName());
		assertEquals("road_4", road4.getName());
	}
	
	@Test
	void testGetSource() {
		assertEquals("Takoma", road1.getSource().getName());
		assertEquals("Rockville", road2.getSource().getName());
		assertEquals("Geithersburg", road3.getSource().getName());
		assertEquals("Geithersburg", road4.getSource().getName());
	}
	
	@Test
	void testGetWeight() {
		assertEquals( 4, road5.getWeight());
	}
	
	@Test
	void testToString() {
		assertEquals("road_5, 4; Olney; SilverSpring", road5.toString());

	}

}
