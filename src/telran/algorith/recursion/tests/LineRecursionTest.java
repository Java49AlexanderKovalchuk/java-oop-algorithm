package telran.algorith.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.algorith.recursion.LineRecursion;

class LineRecursionTest {

	@Test
	@Disabled
	void test() {
		f(6);
	}
	
	private void f(int a) {
		if(a > 0 ) {
			System.out.println(a);
			f(a - 1);
		}
	
	}
	@Test
	void factorialTest() 
	{
		assertEquals(6, LineRecursion.factorial(3));
		assertEquals(24, LineRecursion.factorial(4));
		assertEquals(1, LineRecursion.factorial(0));

	}
	@Test
	void powTest() {
		assertEquals(100, LineRecursion.pow(10, 2));
		assertEquals(100, LineRecursion.pow(-10, 2));
		assertEquals(1000, LineRecursion.pow(10, 3));
		assertEquals(-1000, LineRecursion.pow(-10, 3));
		
	}
	@Test
	void sumTest() {
		assertEquals(21, LineRecursion.sum(new int [] {1, 2, 3, 4, 5, 6}));
	}
	@Test
	void reversTest() {
		int array[] = {1, 2, 3, 4, 5, 6};
		int array1[] = {1, 2, 3, 4, 5, 6, 7};
		int expected[] = {6, 5, 4, 3, 2, 1};
		int expected1[] = {7, 6, 5, 4, 3, 2, 1};
		assertArrayEquals(expected, LineRecursion.reverse(array));
		assertArrayEquals(expected1, LineRecursion.reverse(array1));
	}
	
	@Test          // helper function test 
	void multTest() {
		assertEquals(12, LineRecursion.multiply(-2, -6));
		assertEquals(0, LineRecursion.multiply(2, 0));
		assertEquals(2, LineRecursion.multiply(2, 1));
		assertEquals(-16, LineRecursion.multiply(4, -4));
		assertEquals(-16, LineRecursion.multiply(-4, 4)); 
	}

	@Test
	void squareTest() {
		assertEquals(25, LineRecursion.square(-5));
		assertEquals(0, LineRecursion.square(0));
		assertEquals(1, LineRecursion.square(-1));
		assertEquals(49, LineRecursion.square(7));
	}
	
	@Test
	void isSubstringTest() {
		String string = "earth air fire water";
		String sbstr = "fire";
		assertTrue(LineRecursion.isSubstring(string, sbstr));
		sbstr = "firo";
		assertFalse(LineRecursion.isSubstring(string, sbstr));

	}
	
	
}
