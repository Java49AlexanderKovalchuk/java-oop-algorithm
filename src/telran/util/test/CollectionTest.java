package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
//TODO move tests of interface Collection methods (5 methods) from ListTest to here
	protected Integer[] numbers = {10, -20, 7, 50, 100, 30};
	protected Collection<Integer> collection; 
	
	private static final int BIG_LENGTH = 100000;
	
	@BeforeEach
	void setUp() {
		collection = getCollection();
		for( int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}
	abstract protected Collection<Integer> getCollection();
	 
	@Test
	void testAdd() {
		assertTrue(collection.add(numbers[0]));
		assertEquals(numbers.length + 1, collection.size());
	}
	
	@Test
	void testRemove() {           //{10, -20, 7, 50, 100, 30};
		Integer obj1 = 10;
		Integer obj2 = 50;
		Integer obj3 = 30;
		Integer obj4 = 700;
		Integer[] expectedNo10_50_30 = {-20, 7, 100};
		assertTrue(collection.remove(obj1));
		assertTrue(collection.remove(obj2));
		assertTrue(collection.remove(obj3));
		assertFalse(collection.remove(obj4));
		assertEquals(3, collection.size()); 
		runTest(expectedNo10_50_30);
		
	}
	
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		int size = collection.size();
		for(int i = 0; i < size; i++) {
			assertEquals(numbers[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] = collection.toArray(new Integer[0]);
		assertArrayEquals(numbers, actualArray);
	}
	
	@Test
	void testRemoveIfAll() {     //{10, -20, 7, 50, 100, 30};
		collection.removeIf(a -> a == 101);
		Integer[] expected = {10, -20, 7, 50, 100, 30};
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
		assertFalse(collection.removeIf(a -> a == 1000));
		collection.removeIf(a -> a == 10);
		Integer[] expected_No10 = { -20, 7, 50, 100, 30};
		assertArrayEquals(expected_No10, collection.toArray(new Integer[0]));
		
		collection.removeIf(a -> a > 40);
		Integer[]expectedNo10_No50_No100 = {-20, 7, 30};
		assertArrayEquals(expectedNo10_No50_No100, collection.toArray(new Integer[0]));
		assertEquals(3, collection.size());
		collection.removeIf(a -> ((int)a & 1) == 0 );
		Integer[]expectedOnlyOdd = {7};
		assertArrayEquals(expectedOnlyOdd, collection.toArray(new Integer[0]));
		
		assertTrue(collection.removeIf(a -> true));
		assertEquals(0, collection.size());
		
	}
	
	@Test
	void testRemoveIfPredicate() {
		Integer[] expected = {10, -20, 50, 100, 30};
		assertFalse(collection.removeIf(a -> a % 2 != 0 && a >= 10));
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		runTest(expected);
	}
	
	//instead method get(i) we are using here the public method toArray(array) 
	protected void runTest(Integer[] expected) {
		//int size = collection.size();
		Integer[] actual = new Integer[expected.length];
		actual = collection.toArray(new Integer[0]);
		assertArrayEquals(expected, actual);
	}
}
