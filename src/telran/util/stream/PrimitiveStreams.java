package telran.util.stream;

import java.util.Random;

import telran.util.ArrayList;
import telran.util.List;

public class PrimitiveStreams {
	
	static public int[] randomUnique(int nNumbers, int minInclusive, int maxExclusive) {
		if(maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}
	
	static public int[] shuffle(int[] array) {
		int[] res = new int[array.length];
		int [] index = {0};
		
		new Random().ints(0, array.length).distinct().limit(array.length)
				.forEach(i -> res[index[0]++] = array[i]);
		return res;
	}

}
