package telran.algorithm.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static telran.algorithm.InitialAlgorithms.*;

class AlgorithmTests {

	int N_NUMBERS = 10000000;
	short[] array;
	
	void setUpBigArray() {
		 
		array = new short[N_NUMBERS];
			for(int i = 0; i < N_NUMBERS; i++) {
				array[i] = (short) (Math.random() * Short.MAX_VALUE);
		 }
	 }
	
	private void runTest() {
		for(int i = 1; i < N_NUMBERS; i++) {
			assertTrue(array[i - 1] <= array[i]);
		}
		
	}
	
	@Test
	@Disabled
	void bubbleSortTest() {
		setUpBigArray();
		bubbleSort(array);
		runTest();
	}
	
	@Test
	void testSortShortPositive() {
		setUpBigArray();
		sortShortPositive(array);
		runTest();
	}

	@Test	
	void isSum2Test() {
		short[] array = {30000, 1, 5, 2, 10000, 0, 500,0};
		short[] array1 = {30000, 1, 5, 2, 10000, 0, 500,0, Short.MAX_VALUE};
		assertTrue(isSum2(array, (short)30000));
		assertTrue(isSum2(array, (short)7));
		assertFalse(isSum2(array, (short)30003));
		assertFalse(isSum2(array, (short)8));
		assertTrue(isSum2(array1, Short.MIN_VALUE));
		
	}
 
	@Test
	void getMaxPositiveWithNegativeTest() {
		short[] array = {1, 1, 1, -1, 20, 100,200, 100 -100, -100, -20, -40, 80};
		short[] array1 = {-40, 1, -40, -6, 2, 3, 40};
		short[] array2 = {40, 1, 2, 3, 40, -30};
		assertEquals(100,
			getMaxPositiveWithNegativeReflect(array));
		assertEquals(40,
			getMaxPositiveWithNegativeReflect(array1));
		assertEquals(-1,
			getMaxPositiveWithNegativeReflect(array2));
	}

	@Test
	@Disabled
	void maxValueComplexityNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityN());
	}
	
	@Test
	void maxValueComplexityLogNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityLogN());
	}

	@Test
	void binarySearchTest() {
		Integer[]arr = {-10, 0, 2, 17};
		assertEquals(1, binarySearch(arr,  0, Comparator.naturalOrder()));
		assertEquals(3, binarySearch(arr, 17, Comparator.naturalOrder()));
		assertEquals(1, binarySearch(arr, 0, Comparator.naturalOrder()));
		assertEquals(-1, binarySearch(arr, -300, Comparator.naturalOrder()));
	}
	
	@Test
		void binarySearchUpdatedTest() {
		Integer [] arr2 = {-4, -1, 1, 3, 5};
		assertEquals(2, binarySearchUpdated(arr2, 1, Comparator.naturalOrder()));
		//assertEquals(4, binarySearchUpdated(arr2, 5, Comparator.naturalOrder()));
//		assertEquals(-1, binarySearchUpdated(arr2, 0, Comparator.naturalOrder()));
//		//assertEquals(-1, binarySearchUpdated(arr2, -5, Comparator.naturalOrder()));
//		assertEquals(-5, binarySearchUpdated(arr2, 4, Comparator.naturalOrder()));
//		assertEquals(-6, binarySearchUpdated(arr2, 6, Comparator.naturalOrder()));
//		Integer [] arr3 = {-4, 1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 5, 6};
//		assertEquals(8, binarySearchUpdated(arr3, 5, Comparator.naturalOrder()));
//		Integer [] arr4 = {1, 3, 3, 3, 5};
//		assertEquals(1, binarySearchUpdated(arr4, 3, Comparator.naturalOrder()));
//		Integer [] arr5 = {2, 2,2,2,5,5,5,5,5,7,8,9, 10, 11, 12, 13,13, 15};
//		assertEquals(0, binarySearchUpdated(arr5, 2, Comparator.naturalOrder()));
	}
	
		

	private Long getMaxValueComplexityN() {
		long res = 1;
		while (res > 0) {
			res++;			
		}
		return res - 1;
	}
	
	private Long getMaxValueComplexityLogN() {
		long res = 1;
		while (res > 0) {
			res *= 2;			
		}
		return res - 1;
	}

}
