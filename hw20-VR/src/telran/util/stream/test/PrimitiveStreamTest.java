package telran.util.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.HashSet;
import telran.util.*;

import static telran.util.stream.PrimitiveStreams.*;

class PrimitiveStreamTest {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 2_000_000_000;
	private static final int N_NUMBERS = 1000;
	private static final int N_RUNS = 100;

	@Test
	void uniqueRandomTest() {
		int[] arrayPrev = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
		runUniqueTest(arrayPrev);
		for (int i = 0; i < N_RUNS; i++) {
			int[] arrayNext = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(arrayNext);
			runArrayNotEqualTest(arrayPrev, arrayNext);
			arrayPrev = arrayNext;

		}
	}

	private void runArrayNotEqualTest(int[] arrayPrev, int[] arrayNext) {

		int index = 0;
		if (arrayPrev.length == arrayNext.length) {
			while (index < arrayPrev.length && arrayPrev[index] == arrayNext[index]) {
				index++;
			}
		}
		assertTrue(index < arrayPrev.length);

	}

	private void runUniqueTest(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : array) {
			set.add(num);
		}
		assertEquals(array.length, set.size());

	}
	@Test
	void shuffleTest() {
		for (int i = 0; i < N_RUNS; i++) {
			int[] inputArray = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(inputArray);
			int [] shuffledArray = shuffle(inputArray);
			runArrayNotEqualTest(inputArray, shuffledArray);
			runArraysHaveSameContent(inputArray, shuffledArray);
		}
	}
	// V.R.
	@Test
	void shuffleOldTest() {
		for (int i = 0; i < N_RUNS; i++) {
			int[] inputArray = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(inputArray);
			int [] shuffledArray = shuffleOLd(inputArray);
			runArrayNotEqualTest(inputArray, shuffledArray);
			runArraysHaveSameContent(inputArray, shuffledArray);
		}
	}
	private void runArraysHaveSameContent(int[] first, int[] second) {
		assertTrue(first.length == second.length);
		HashSet<Integer> set = new HashSet<>();
		for(int num: first) {			
			set.add(num);
		}		
		for(int num: second) {			
			assertTrue(set.contains(num));
		}		
	}
	// V.R.
	@Test
	void predicateSumTest() {
		Integer [] inpArray = { 3, 5, 2, -11, -12, 21, 20};
		Collection<Integer> col = new LinkedList<>();
		for(Integer el: inpArray) {
			col.add(el);
		}
		assertEquals(18, getPredicateSumStream(col, p -> p % 2 != 0));
		assertEquals(18, getPredicateSumStream(col, p -> p % 2 != 0));
		assertEquals(10, getPredicateSumStream(col, p -> p % 2 == 0));
		assertEquals(10, getPredicateSumStream(col, p -> p % 2 == 0));
	}

}
