package lab4_heaps;

import lab5.Heap;

public class Driver {
	public static void main(String[] args) {
		Heap h = new Heap();
		h.makeHeap(3);
		h.insert(2);
		h.insert(1);
		h.insert(5);
		h.insert(7);
		h.insert(40);
		h.insert(20);
		h.deleteMin();
		h.print();
	}
}
