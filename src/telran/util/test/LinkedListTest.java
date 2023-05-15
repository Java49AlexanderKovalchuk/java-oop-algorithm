package telran.util.test;

import telran.util.LinkedList;
import telran.util.List;


class LinkedListTest extends ListTest {

	@Override
	protected <T> List<T> getList() {
		return new LinkedList<T>();
	}
		
} 
