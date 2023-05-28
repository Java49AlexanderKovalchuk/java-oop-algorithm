package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {

	protected Integer[] numbers = {10, -20, 7, 50, 100, 30};
	protected Collection<Integer> collection; 
	
	protected static final int BIG_LENGTH = 100000;
	
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
	void testRemovePattern() {           //{10, -20, 7, 50, 100, 30};
		Integer[] expectedNo10 = {-20, 7, 50, 100, 30};
		Integer[] expectedNo10_50 = {-20, 7, 100, 30};
		Integer[] expectedNo10_50_30 = {-20, 7, 100};
		assertTrue(collection.remove(numbers[0]));
		runTest(expectedNo10);
		Integer ObjToRemove = 50;
		assertTrue(collection.remove(ObjToRemove));
		runTest(expectedNo10_50);
		assertTrue(collection.remove((Integer)30));
		runTest(expectedNo10_50_30);
		assertFalse(collection.remove((Integer)50));
		
		
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
	void testIterator() {
		
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		it1.next();
		while(it2.hasNext()) {
			it2.next();
		}
		assertEquals(numbers[1], it1.next());
		assertThrowsExactly(NoSuchElementException.class, 
				() -> it2.next());
	}
	
	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = {-20, 7, 50, 100, 30};
		Integer[] expectedLast = {-20, 7, 50, 100};

		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		runTest(expectedFirst);
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		while(it.hasNext()) {
			it.next();
		}
		it.remove();
		runTest(expectedLast);
		
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
	
	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertTrue(collection.contains(numbers[3]));
		assertTrue(collection.contains(numbers[numbers.length - 1]));
		assertFalse(collection.contains(1000000));
	}
	@Test
	void clearFunctional() {
		collection.clear();
		assertEquals(0, collection.size());
	}
	protected void runTest(Integer[] expected) {
		Integer[]actual = collection.toArray(new Integer[0]);
		assertArrayEquals(expected, actual);
	}
	
}
