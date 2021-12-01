import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	
	
	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("Rockville");
		town2 = new Town("Takoma");
		town3 = new Town("Olney");
		town4 = new Town("Belstville");
	}

	@AfterEach
	void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		town3 = null;
		town4 = null;
	}

	@Test
	void testCompareTo() {
		assertNotEquals( 0, town1.compareTo(town2) );
	}

	@Test
	void testGetName() {
		
		assertEquals( "Rockville", town1.getName() );
		assertEquals( "Takoma", town2.getName() );
		assertEquals( "Olney", town3.getName() );
		assertEquals( "Belstville", town4.getName() );
		
	}
	
	@Test
	void testToString() {
		
		assertEquals( "Rockville", town1.toString() );
		assertEquals( "Takoma", town2.toString() );
		assertEquals( "Olney", town3.toString() );
		assertEquals( "Belstville", town4.toString() );
	}
	@Test
	void testHashCode() {

		assertEquals( -1159877907, town1.hashCode() );
	}
	@Test
	void testEquals() {
		assertNotEquals( true, town1.equals(town2) );
		assertNotEquals( true, town2.equals(town3) );
		assertNotEquals( true, town3.equals(town4) );
		assertNotEquals( true, town1.equals(town4) );
		
	}
}
