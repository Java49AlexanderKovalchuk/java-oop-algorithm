package telran.util;

import java.util.Arrays;

import telran.util.*;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16; 
	private T[] array;
	private int size;
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];   
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if(size == array.length) {
			reallocate();
		}
		array[size] = obj; 
		size++;
		return true;
	}

	private void reallocate() {
		 array = Arrays.copyOf(array, array.length * 2);		
	}
	
		
	@Override
	public void add(int index, T obj) {
		if(size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}
	

	@Override
	public T remove(int index) {
		T res = array[index];
		
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return res;
	} 

	@Override
	public T get(int index) {
		T res = array[index];		
		return res;

	}
		
	@Override
	public int size() {
		
		return size ;
	}
	
	@Override
	public boolean remove(T pattern) {
		// TODO 
		//my first solution:
		//boolean resRet = false;
//		int index = 0;
//		while(index < size && resRet == false) {
//			if(isEqual(array[index], pattern)) {
//				resRet = true;
//				System.arraycopy(array, index + 1, array, index, size - index - 1);
//				size--;
//			}
//			index++;
//		}		
//		return resRet;
		//second solution:
		boolean res = true;
		int index = indexOf(pattern);
		if(index < 0) {
			res = false;
		}
		else remove(index);
		return res;
	}
	
	@Override
	public T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int index = 0;
		while(index < size && res == - 1) {
			 if(isEqual(array[index], pattern)) {
				 res = index;
			 }
			 index++;
		}
		return res;
	}
	
	private boolean isEqual(T object, T pattern) { 		
		return pattern == null ? object == pattern : pattern.equals(object);
	}
	
	@Override
	public int lastIndexOf(T pattern) {
		//TODO
		int res = -1;
		int index = size - 1;
		while(index >=  0 && res == -1) {
			if(isEqual(array[index], pattern)) {
				res = index;
			}
			index--;
		}
		return res;
	}
	
}
