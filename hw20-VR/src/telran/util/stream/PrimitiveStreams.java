package telran.util.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import telran.util.ArrayList;
import telran.util.*;

public class PrimitiveStreams {
	static public int[] randomUnique(int nNumbers, int minInclusive,
			int maxExclusive) {
		if(!(nNumbers > 0)) {
			throw new IllegalArgumentException("nNumbers has to be positive");
		}
		if (maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}
	static public int[] shuffle(int[] array) {
		//TODO 
		//returns new array with shuffled numbers
		//Implementation hints: two stream pipes
		//first stream pipe fills telran.util.ArrayList<Integer> with array's numbers in the
		// random order (apply the same approach
		// as in the randomUnique method with forEach instead of toArray for
		// adding numbers to the ArrayList
		// 
		//second stream pipe creates array of int's from telran.util.ArrayList as we have done at class
		Collection<Integer> list = new ArrayList<>();
		new Random().ints(0, array.length)
		.distinct().limit(array.length).forEach(ind -> list.add(array[ind]));
		return 	list.stream().mapToInt(n -> n).toArray();
	}
	// Ludmila
	public static int[] shuffleLuda (int[] array) {
		return new Random().ints(0, array.length).distinct()
				.limit(array.length).map(ind -> array[ind])				
				.toArray();
	}
	// V.R.
	static public int[] shuffleOLd(int[] array) {
		Collection<Integer> uniqueRandoms = new HashSet<>(); 
		int [] indexes = getRandomIndexes(0, array.length, array.length, uniqueRandoms);
		int [] result = new int[array.length];
		for(int i=0; i<array.length; i++) {
			result[i] = array[indexes[i]];
		}
		return result;
	}
	static private int[] getRandomIndexes(int min, int max, int amount, Collection<Integer> setInt) {
		int [] result = new int[amount];
		int ind = 0;
		while(setInt.size() < amount) {
			int tmp = (int)Math.floor(Math.random() * (max - min) + min);
			if(setInt.add(tmp)) {
				result[ind++] = tmp;
			}
		}
		return result;
	}
	// V.R.
	static public int getPredicateSumStream(Collection<Integer> collection,
												Predicate<Integer> predicate) {
		return collection.stream()
				.filter(predicate)
				.mapToInt(n -> n)
				.sum();
	}

	static public int getPredicateSumRegular(Collection<Integer> collection,
											Predicate<Integer> predicate) {
		Integer result = 0; 
	    for(Integer val: collection) {
		    if(predicate.test(val)) {
		    	result += val;
	        }
	    }
		return result;
	}
}
