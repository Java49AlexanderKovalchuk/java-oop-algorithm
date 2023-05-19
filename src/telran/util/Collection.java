package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
boolean add(T obj);
int size();
boolean remove(T pattern);  
default T[] toArray(T[] array) {
		int size = size();
		int index = 0;
		if(array.length < size) {
		array = Arrays.copyOf(array, size); 
		}
//	    Iterator<T> itr = iterator();	
//		while(itr.hasNext()) {
//			array[index++] = itr.next();
//		}
		// other way
		for(T element: this) {
			array[index++] = element;
		}
		
		if(array.length > size) {
			array[size] = null;
		}
		return array;
	}
boolean removeIf(Predicate<T> predicate);
boolean contains(T pattern);
default boolean isEqual(T object, T pattern) {
	return pattern == null  ? object == pattern : pattern.equals(object);
}

}
 