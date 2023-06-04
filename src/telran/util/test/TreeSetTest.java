package telran.util.test;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.*;

public class TreeSetTest extends SortedSetTest {

	@Override 
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
		//return new TreeSet<>((Comparator<T>) Comparator.naturalOrder());
	}
//	@Override
//	@Test
//	void clearPerformance( ) {
//		
//	}

}
