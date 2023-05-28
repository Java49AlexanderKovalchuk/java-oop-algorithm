package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_SIZE = 16;
	private LinkedList<T>[] hashTable;
	private int size;
	
	private class HashSetIterator implements Iterator<T> {
		Iterator<T> curItrList;
		Iterator<T> prevItrList;
		int hashTableIndex = 0;
		boolean flNext = false;
		
		HashSetIterator() {
			upDateCurItrList();
		}
		
		@Override
		public boolean hasNext() {
			return curItrList != null;
			
		}
		
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextElement = curItrList.next();
			prevItrList = curItrList;
			upDateCurItrList();
			flNext = true;
			return nextElement;
			
		}
		
		private void upDateCurItrList() {
			if(curItrList == null || !curItrList.hasNext()) {
				//current iterator has no show other linked list
				Iterator<T> it;
				do {
					LinkedList<T> list = getList();
					hashTableIndex++;
					
					if(list == null) {
						curItrList = null;
						return;
					}
					it = list.iterator();
				} while (!it.hasNext());
				curItrList = it;
			}
			
		}
		
		private LinkedList<T> getList() {
			while(hashTableIndex < hashTable.length && hashTable[hashTableIndex] != null) {
				hashTableIndex++;
			}
			
			return hashTableIndex < hashTable.length ? hashTable[hashTableIndex] : null;
		}
					
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			prevItrList.remove();
			size--;
			flNext = false;
			}
		
	}
	
	@SuppressWarnings("unchecked")
	public HashSet(int hashTableSize) {
		hashTable = new LinkedList[hashTableSize]; 
	}
	public HashSet() {
		this(DEFAULT_HASH_TABLE_SIZE); 
	}
	
	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(size >= 0.75 * hashTable.length) {
			recreation();
		}
		int index = getHashTableIndex(obj);
		if(hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if(!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			size++;
			res = true;
		}
		return res;
	}

	private int getHashTableIndex(T obj) {
		return Math.abs(obj.hashCode()) % hashTable.length;
	}
	
	private void recreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for(int i = 0;  i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				for(T obj: hashTable[i]) {
					tmp.add(obj);
				}
			}
		}
		this.hashTable = tmp.hashTable;
	}
	@Override	
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = getHashTableIndex(pattern);
		if(hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if(res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}
	
	@Override
	//FIXME method should be remove after writing iterator 
	public T[] toArray(T[] ar) {
		
		int size = size();
		if(ar.length < size) {
		ar = Arrays.copyOf(ar, size); 
		}
		int index = 0;
			
		for(int i = 0; i < hashTable.length; i++) {
			LinkedList<T> list = hashTable[i];
			if(list != null) {
				for(T element: list) {
				ar[index++] = element;
				} 
			}
		}
		if(ar.length > size) {
			ar[size] = null;
		}
		return ar;
	}

}
