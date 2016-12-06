package lab5;

public class Driver {

	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.add(1);
		list.add(20);
		list.add(14);
		list.add(12);
		list.add(45);
		list.add(78);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
