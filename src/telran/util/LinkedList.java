package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		Node(T obj) {
			this.obj = obj;
		}
	}
	
	@Override
	public boolean add(T obj) {
		add(size, obj);
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = true;
		int index = indexOf(pattern);
		if(index < 0) {
			res = false;
		}
		else remove(index);
		return res;		
	}

	@Override
	public T[] toArray(T[] ar) {
		if(ar.length < size) {
			ar = Arrays.copyOf(ar, size); 
			}
		Node<T> current = head;
		int index = 0;
		while(current != null) {
		
			ar[index++] = current.obj;
			current = current.next;
		}
		if(ar.length > size) {
			ar[size] = null;
		}
		return ar;
	}
	
	@Override
	public void add(int index, T obj) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = new Node<>(obj);
		addNode(index, node); 
		
	}

	@Override
	public T remove(int index) {
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		T res = getNode(index).obj;
		removeNode(index);
		return res;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return getNode(index).obj;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int index = 0;
		while(index < size && res == - 1) {
			 if(isEqual(getNode(index).obj, pattern)) {
				 res = index;
			 }
			 index++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int index = size - 1;
		while(index >=  0 && res == -1) {
			if(isEqual(getNode(index).obj, pattern)) {
				res = index;
			}
			index--;
		}
		return res;
	}

	@Override
	public void sort() {
		// no implement

	}

	@Override
	public void sort(Comparator<T> comp) {
		// no implement

	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int res = -1;
		int index = 0;
		while(index < size && res == - 1) {
			 if(predicate.test(getNode(index).obj)) {
				 res = index;
			 }
			 index++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int res = -1;
		int index = size - 1;
		while(index >= 0 && res == -1) {
			if(predicate.test(getNode(index).obj)) {
				res = index;
			}
			index--;
		}
		return res;		
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		int oldSize = size;
		for(int i = size - 1; i >= 0; i--) {
			if(predicate.test(getNode(i).obj)) {
				remove(i);				
			}
		}
		return oldSize > size;		
	}
	
	private void addNode(int index, Node<T> node) {
		if(head == null) {
			head = tail = node;
		} else {
			if(index == 0) {
				addNodeHead(node);
			} else if (index == size) {
				addNodeTail(node);
			} else {
				addNodeMiddle(index, node);
			}
		}
		size++;
	}
	private void addNodeHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;
	}
	private void addNodeTail(Node<T> node) {
		 node.prev = tail;
		 tail.next = node;
		 tail = node;
	}
	private void addNodeMiddle(int index, Node<T> node) {
		Node<T> nodeA = getNode(index);
		Node<T> nodeBefore = nodeA.prev;
		node.prev = nodeBefore;
		node.next = nodeA;
		nodeBefore.next = node;
		nodeA.prev = node; 
	
	}

	private Node<T> getNode(int index) {
		
		return index > size / 2 ? getNodeFromRight(index) : getNodeFromLeft(index); 

}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private void removeNodeHead() {
		head = getNode(1); 
	}
	
	private void removeNodeTail() {
		tail = getNode(size - 2);
	}
	
	private void removeNodeMiddle(int index) {
		getNode(index - 1).next = getNode(index + 1);
		getNode(index + 1).prev = getNode(index - 1);
	}
	
	private void removeNode(int index) {
		if(head == tail) {
			head = tail = null;
		} else {
			if(index == 0) {
				removeNodeHead();
			} else if(index == size - 1) {
				removeNodeTail();
			} else {
				removeNodeMiddle(index);
			}
		}
		size--;
	}
	
	private boolean isEqual(T object, T pattern) { 		
		return pattern == null ? object == pattern : pattern.equals(object);
	}

}
