package lab5;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.add(2);
		list.add(6);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(100);
		list.add(8);
		list.add(12);
		list.add(18);
		list.add(74);
		list.add(2);
		list.add(6);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(100);
		list.add(8);
		list.add(12);
		list.add(18);
		list.add(74);
		list = Heap.heapSort(list);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
