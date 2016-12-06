package lab5;

public class Driver {

	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.add(2);
		list.add(6);
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(1);
		list.add(8);
		list.mergeSort(list.getList());
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
