package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import telran.util.*;


class ArrayListTest {

private static final int BIG_LENGTH = 100000;
List<Integer> list;
Integer[] numbers = {10, -20, 7, 50, 100, 30};

@BeforeEach  
void setUp() {
	list = new ArrayList<>(1);
	for( int i = 0; i < numbers.length; i++) {
		list.add(numbers[i]);
	}
}
	@Test
	void testAdd() {
		assertTrue(list.add(numbers[0]));
		assertEquals(numbers.length + 1, list.size());
	}
	
	@Test
	void testAddIndex() {
		Integer[] expected0_500 = {500, 10, -20, 7, 50, 100, 30};
		Integer[] expected0_500_3_700 = {500, 10, -20, 700, 7, 50, 100, 30};
		Integer[] expected0_500_3_700_8_300 = {500, 10, -20, 700, 7, 50, 100, 30, 300};
		list.add(0, 500); 
		runTest(expected0_500);
		list.add(3, 700);
		runTest(expected0_500_3_700);
		list.add(8, 300);
		runTest(expected0_500_3_700_8_300);
	}
	
	@Test
	void testRemoveIndex() {
		 Integer[] expectedNo10 = {-20, 7, 50, 100, 30};
		 Integer[] expectedNo10_50 = {-20, 7, 100, 30};
		 Integer[] expectedNo10_50_30 = {-20, 7, 100};
		 assertEquals(10, list.remove(0));
		 runTest(expectedNo10);
		 assertEquals(50, list.remove(2));
		 runTest(expectedNo10_50);
		 assertEquals(30, list.remove(3));
		 runTest(expectedNo10_50_30);
	}
	
	@Test
	void testGetIndex() {
		assertEquals(10, list.get(0));
	}
	
	@Test
	void testIndexOf() {
		list.add(3, 10); 
		assertEquals(0, list.indexOf(10));
		assertEquals(-1, list.indexOf(null));
	}
	
	@Test
	void testlastIndexof() {
		list.add(3, 10);  //{10, -20, 7, 10, 50, 100, 30};
		assertEquals(3, list.lastIndexOf(10));
		assertEquals(-1, list.lastIndexOf(80));
		assertEquals(-1, list.lastIndexOf(null));
		assertEquals(6, list.lastIndexOf(30));
	}
	
	@Test
	void testRemove() {           //{10, -20, 7, 50, 100, 30};
		Integer obj1 = 10;
		Integer obj2 = 50;
		Integer obj3 = 30;
		Integer obj4 = 700;
		Integer[] expectedNo10_50_30 = {-20, 7, 100};
		assertTrue(list.remove(obj1));
		assertTrue(list.remove(obj2));
		assertTrue(list.remove(obj3));
		assertFalse(list.remove(obj4));
		assertEquals(3, list.size());
		runTest(expectedNo10_50_30);
		
	}
	
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = list.toArray(bigArray);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			assertEquals(numbers[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] = list.toArray(new Integer[0]);
		assertArrayEquals(numbers, actualArray);
	}
	
	@Test
	void testSort() {
		Integer expected[] = {-20, 7, 10, 30, 50, 100 };
		list.sort();
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	
	@Test
	void testSortPersons() {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person(123, 25, "Vasya");
		Person p2 = new Person(124, 20, "Asaf");
		Person p3 = new Person(120, 50, "Arkady");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		Person expected[] = {p3, p1, p2};
		persons.sort();
		assertArrayEquals(expected, persons.toArray(new Person[0]));
	}
	
	@Test
	void testSortPersonsByAge() {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person(123, 25, "Vasya");
		Person p2 = new Person(124, 20, "Asaf");
		Person p3 = new Person(120, 50, "Arkady");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		Person expected[] = {p3, p1, p2};
		persons.sort(new PersonsAgeComparator());
		assertArrayEquals(expected, persons.toArray(new Person[0]));
	}
	
	@Test
	void testSortBubble() {
		Integer expected[] = {-20, 7, 10, 30, 50, 100 };
		list.sort(new ComparatorInteger());
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		
	}
	
	@Test
	void testSortEvenOdd() {
		Integer n = 17;
		list.add(n);
		list.sort(new EvenOddComparator());
		Integer expected[] = {-20, 10, 30, 50, 100, 17, 7};
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	private void runTest(Integer[] expected) {
		int size = list.size();
			Integer[] actual = new Integer[expected.length];
		for(int i = 0; i < size; i++) {
			actual[i] = list.get(i); 
		}
		assertArrayEquals(expected, actual);
	}
	
}
