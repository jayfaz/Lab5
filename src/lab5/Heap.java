package lab5;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Heap implements MyHeap {

	private Node root;
	private int size;

	/*
	 * Creates a node and sets the value
	 */
	public Node makeHeap(Comparable value) {
		this.root = new Node(value);
		size++;
		return root;
	}

	public boolean isEmpty() { // checks if root is empty
		return root == null;
	}

	public boolean insert(Comparable value) {
		Node currentNode = findLastParent();
		Node n = new Node(value, null, null, currentNode);
		if (currentNode.getLeftChild() == null) {
			currentNode.setLeftChild(n);
		} else {
			currentNode.setRightChild(n);
		}
		shiftUp(currentNode);
		size++;
		return true;
	}

	public boolean deleteMin() {
		return delete(root);
	}

	public boolean decreaseKey(Node key, Comparable updateValue) {
		if(updateValue.compareTo(key.getData()) < 0) {
			key.setData(updateValue);
			shiftUp(key);
			return true;
		}
		return false;
	}
	
	public boolean delete(Node del) {
		if(root == null) {
			return false;
		}
		Node last = findLastNode();
		swapData(del, last);
		
		if(size % 2 == 0) {//if even
			last.getParent().setLeftChild(null);
		} else {
			last.getParent().setRightChild(null);
		}
		shiftDown(del);
		size--;
		return true;
	}

	public boolean union(MyHeap heap) {
		ArrayList<Node> list = new ArrayList<>();
		while (!(heap.isEmpty())) {
			insert(heap.findMin());
			heap.deleteMin();
		}
		return true;
	}

	public Comparable findMin() {
		return root.getData();
	}

	public void shiftUp(Node n) {
		Node currentNode = n;
		while(currentNode != root && currentNode.getData().compareTo(currentNode.getParent().getData()) <= 0) {
			swapData(currentNode, currentNode.getParent());
			currentNode = currentNode.getParent();
		}
	}

	private void shiftDown(Node n) {
		Node currentNode = n;
		boolean conShift = true;
		while (conShift) {
			if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) { 	
				conShift = false;
			} else if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null) {													
				Node child;
				if (currentNode.getLeftChild().getData().compareTo(currentNode.getRightChild().getData()) <= 0)
					child = currentNode.getLeftChild();
				else
					child = currentNode.getRightChild();
				if (currentNode.getData().compareTo(child.getData()) > 0) {
					swapData(currentNode, child);
					currentNode = child;
				} else {
					conShift = false;
				}
			} else {
				Node child;
				if (currentNode.getLeftChild() != null)
					child = currentNode.getLeftChild();
				else
					child = currentNode.getRightChild();

				if (currentNode.getData().compareTo(child.getData()) > 0) {
					swapData(currentNode, child);
					currentNode = child;
				} else {
					conShift = false;
				}
			}
		}

	}

	private void swapData(Node a, Node b) {
		Comparable temp = a.getData();
		a.setData(b.getData());
		b.setData(temp);
	}

	public boolean isNodeFull(Node n) {
		return (n.getLeftChild() != null && n.getRightChild() != null);
	}

	public String toBinary(int n) {
		String tempString = "";
		if (n > 0) {
			tempString = toBinary(n / 2) + n % 2;
			return tempString;
		}
		return tempString;
	}
	
	public Node findLastNode() {
		String str = toBinary(size);
		str = str.substring(1);
		
		Node currentNode = root;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '0') {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}
		return currentNode;
	}
	
	public Node findLastParent() {
		String str = toBinary(size + 1);
		str = str.substring(1, str.length() - 1);
		
		Node currentNode = root;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '0') {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}
		return currentNode;
	}

	public int getSize() {
		return size;
	}

	public Node getRoot() {
		return root;
	}

	public void print() {
		Queue<Node> queue = new LinkedList<Node>();
		if (root == null)
			return;
		queue.clear();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.print(node.getData() + " ");
			if (node.getLeftChild() != null)
				queue.add(node.getLeftChild());
			if (node.getRightChild() != null)
				queue.add(node.getRightChild());
		}
		System.out.println();

	}
	
	public static MyArrayList heapSort(MyArrayList list) {
		MyArrayList data = new MyArrayList();
		Heap heap = new Heap();
		for(int i = 0; i < list.size(); i++) {
			heap.insert(list.get(i));
		}
		for(int j = 0; j < list.size() && !(heap.isEmpty()); j++) {
			data.add(heap.findMin());
			heap.deleteMin();
		}
		return data;
	}
}
