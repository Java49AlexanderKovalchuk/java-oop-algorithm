package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Map;

abstract class MapTest {
String[] keys = {"lmn", "abc", "ab", "a"};
Integer[] values = {3, 2, 2, 1};
protected Map<String, Integer> map;
	@BeforeEach
	void setUp() {
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}
	@Test
	void getTest() {
		for(int i = 0; i < keys.length; i++) {
			assertEquals(values[i], map.get(keys[i]));
		}
	}
	
	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey("lmn"));
		assertTrue(map.containsKey("a"));
		assertTrue(map.containsKey("abc"));
		assertFalse(map.containsKey("lmno"));
		assertFalse(map.containsKey(""));
		assertThrowsExactly(NullPointerException.class, () -> map.containsKey(null));
	}
	
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue(2));
		assertTrue(map.containsValue(1));
		assertFalse(map.containsValue(50));
		assertThrowsExactly(NullPointerException.class, () -> map.containsValue(null));
	}
	
	
	@Test
	void removeTest() {
		String[] expected = {"lmn", "abc", "ab"};
		Arrays.sort(expected);
		assertEquals(1, map.remove("a"));
		assertEquals(3, map.entrySet().size());
		
		assertArrayEquals(expected, map.keySet().toArray(new String[0]));
	}
}
