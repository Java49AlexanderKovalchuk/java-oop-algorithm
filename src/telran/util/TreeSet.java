package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements Set<T> {
	private static class Node<T> { 
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		Node(T obj) {
			this.obj = obj;
		}
	
	}
	
	
		private Node<T> root;
		private Comparator<T> comp;
		private int size;
		public TreeSet(Comparator<T> comp) {
			this.comp = comp;
		}
		public TreeSet() {
			this((Comparator<T>)Comparator.naturalOrder());

		}
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;
		TreeSetIterator() {
			current = root == null ? null : getLeast(root);
		}
		@Override
		public boolean hasNext() {
			 
			return current != null;
		}
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return res;
		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException(); 
			}
			removeNode(prev);
			flNext = false;
		}
	}
	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj); 
		boolean res = false;
		if(size == 0) {
			root = node;
			res = true;
		} else {
			Node<T> parent = getParent(obj);
			if(parent != null) {
				res = true;
				node.parent = parent;
				if(comp.compare(obj, parent.obj) > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if(res) {
			size++;
		}
		return res;
	}
	
	private Node<T> getCurrent(Node<T> current) {
		
		return current.right != null ? getLeast(current.right) : getGreaterParent(current);
	}
 
	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}
				
	private Node<T> getLeast(Node<T> node) {
		Node<T> current = node;
		while(current.left != null) {
			current = current.left;  
		}
		return current;
	}
	
	private Node<T> getNodeParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while(current != null && (compRes = comp.compare(obj, current.obj)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}
	private Node<T> getNode(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if(node != null && comp.compare(obj, node.obj) == 0) {
			res = node;
		}
		return res;
	
	}
	private Node<T> getParent(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if(node != null && comp.compare(obj, node.obj) != 0) {
			res = node;
		}
		return res;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if(node != null) {
			removeNode(node);
		}
		return false;
	}
	
	private void removeNode(Node<T> removeNode) {
		if(isJunction(removeNode)) {
			removeJunction(removeNode);
		} else if (removeNode == root) {
			removeLeafRoot();
		} else {
			removeNonJunction(removeNode);
		}
		size--;
	}

	private void removeNonJunction(Node<T> removeNode) {
		Node<T> child = removeNode.left == null ? removeNode.right : removeNode.left;
		Node<T> parent = removeNode.parent;
		if(parent.right == removeNode) {
			parent.right = child;
		} else {
			parent.left = child;
		}
		if(child != null) {
			child.parent = parent;
		}
	}
	private void removeLeafRoot() {
		root = root.left == null ? root.right : root.left;
		if(root != null) {
			root.parent = null; 
		} 
	}
	private void removeJunction(Node<T> removeNode) {
		Node<T> nodeSubstitute = getMostFromLeft(removeNode.right);	
		removeNode.obj = nodeSubstitute.obj;
		removeNonJunction(nodeSubstitute);
	}
	
	private Node<T> getMostFromLeft(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
//	private void removeNode(Node<T> node) {
//		// TODO Auto-generated method stub
//		if(isJunction(node)) {
//			removeJunction(node);
//		} else {
//			removeNonJunction(node);
//		}
//		size--;
//	}
//	
//	private void removeNonJunction(Node<T> node) { 
//		// TODO Auto-generated method stub
//		Node<T> parent = node.parent;
//		if(parent == null) {
//			removeRoot();
//		}
//		Node<T> child = (node.left == null) ? node.right : node.left;
//		if(child == null) {
//			if(parent.left == null) {
//				parent.right = null;
//			} else {
//				parent.left = null;
//			}
//		}
//		child.parent = parent;
//		node.parent = null;
//		//node.left = node.right = null;
//	}
//
//	private void removeRoot() {
//		if (root.left != null && root.right != null) {
//			removeJunction(root);
//		} else {
//			if (root.left == null) {
//				root = root.right;
//			} else {
//				root = root.left;
//			}
//		}
//		
//	}
//
//	private void removeJunction(Node<T> node) {
//		// TODO Auto-generated method stub
//		Node<T> nodeSubstitute = node.left;
//		while(nodeSubstitute != null) {
//			nodeSubstitute = nodeSubstitute.right;
//		}
//		node.obj = nodeSubstitute.obj;
//		removeNonJunction(nodeSubstitute);
//		
//	}
//
	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}

	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
	
		return new TreeSetIterator();
	}
	
}
