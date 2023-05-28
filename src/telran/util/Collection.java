 package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.params.converter.DefaultArgumentConverter;

public interface Collection<T> extends Iterable<T> {
boolean add(T obj);
int size();
boolean remove(T pattern);  
default T[] toArray(T[] array) {
	int size = size();
	if(array.length < size) {
	array = Arrays.copyOf(array, size); 
	}
	int index = 0;
	//	Iterator<T> itr = iterator();	
	//	while(itr.hasNext()) {
	//  array[index++] = itr.next();
	//	}
	// other way
	for(T element: this) {
		array[index++] = element;
	}
	if(array.length > size) {
		array[size] = null;
	}
	return array;
}
default boolean removeIf(Predicate<T> predicate) {
	int oldSize = size();
	Iterator<T> it = iterator();
	while(it.hasNext()) {
		T obj = it.next();
		if(predicate.test(obj)) {
			it.remove();
		}
	}
	return oldSize > size();
}
boolean contains(T pattern); 
default boolean isEqual(T object, T pattern) {
	return pattern == null  ? object == pattern : pattern.equals(object);
	}
default void clear() {
	removeIf(element -> true);
}

}
 