package lab5;

public interface MyList<T> {
	
	boolean add(int index,Comparable t);
		
	boolean add(Comparable o);
	
	boolean clear();
	
	boolean contains(Comparable o);
	
	T get(int index);
	
	int indexOf(Comparable o);
	
	boolean isEmpty();
	
	T remove(int index);
	
	T remove(Comparable o);
	
	boolean set(int index, Comparable element);
	
	int size();
	
	MyList subList(int fromIndex, int toIndex);
	
	T[] toArray();
	
	boolean swap(int position1, int position2);
	
	boolean shift(int positions);
}
