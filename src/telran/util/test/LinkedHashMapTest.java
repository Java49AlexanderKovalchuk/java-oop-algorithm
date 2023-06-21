package telran.util.test;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Collection;
import telran.util.HashMap;
import telran.util.Map.Entry;
import telran.util.Set;

public class LinkedHashMapTest extends MapTest {
@BeforeEach
@Override
void setUp() {
	map = new HashMap<>();
	super.setUp();
}


@Override
protected String[] getKeysActual(String[] keys) {
	
	return keys;
}

@Override
protected Entry<String, Integer>[] getEntriesActual(Entry<String, Integer>[] entries) {
	
	return entries;
}
protected Entry<String, Integer>[] getEntriesExpected(Entry<String, Integer>[] entries) {
	
	return entries;
}
protected String[] getKeysExpected(String[] keys) {
	
	return keys;
}
protected  Integer[] getValuesExpected(Integer[] values) {
	
	return values;
};
protected Integer[] getValuesActual(Integer[] values) {
	
	return values;
}
}