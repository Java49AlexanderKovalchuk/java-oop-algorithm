package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telran.util.Set;
import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {

	@Override
	protected Integer[] getActual(Integer[] array, int size) {
		//System.out.println("Sorted test");
		return array;
	}
	@Test
	void firstTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(-20, sortedSet.first());
	}
	@Test
	void lastTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(100, sortedSet.last());
	}
	@Test
	void ceilingTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(100, sortedSet.ceiling(100));
		assertEquals(-20, sortedSet.ceiling(-20));
		assertEquals(30, sortedSet.ceiling(30));
		assertEquals(100, sortedSet.ceiling(60));
		assertEquals(-20, sortedSet.ceiling(-22));
		assertEquals(50, sortedSet.ceiling(45));
		assertEquals(7, sortedSet.ceiling(0));
		assertEquals(null, sortedSet.ceiling(105));
		
	}
	@Test
	void floorTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		//TODO test for the method floor
	}
}
