package telran.util.test;

import telran.util.Set;
import telran.util.LinkedHashSet;
public class LinkedHashSetTest extends SetTest {

	@Override
	protected <T> Set<T> getSet() {
		
		return new LinkedHashSet<>();
	}
	@Override
	protected Integer[] getExpected(Integer[] expected) {
		return expected;
	}
	@Override
	protected Integer[] getActual(Integer[] actual, int size) {
		return actual;
	}
}
