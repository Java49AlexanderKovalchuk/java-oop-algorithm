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
	
	static public int[] shuffleArray(int[] ar) {
		List <Integer> list = new ArrayList<>(); 
		new Random().ints(0, ar.length).distinct().limit(ar.length)
				.forEach(n -> list.add(ar[n]));
		return list.stream().mapToInt(n -> n).toArray();
	}

}
