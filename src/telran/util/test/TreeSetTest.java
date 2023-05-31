package telran.util.test;
import java.util.Comparator;

import telran.util.*;

public class TreeSetTest extends SetTest {

	@Override 
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
		//return new TreeSet<>((Comparator<T>) Comparator.naturalOrder());
		
	}

}
