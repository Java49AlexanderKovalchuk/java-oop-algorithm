package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

 import java.util.Arrays;

class ArrayListTest {

	@Test
	void testAdd() {
		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<String> strings = new ArrayList<>();
		numbers.add(5);
		numbers.add(10);
		strings.add("ABC");
		assertEquals(2, numbers.size());
		assertEquals(1, strings.size());
	}
	
	@Test
	void testAddIndex() {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		assertEquals(3, numbers.size());
		numbers.add(0, 100);
		
		assertEquals(4, numbers.size());
		numbers.add(2, 1000);
		assertEquals(5, numbers.size());
		assertEquals(100, numbers.get(0));
		assertEquals(1000, numbers.get(2));
		
				
	}
	 
}
