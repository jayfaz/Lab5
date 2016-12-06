package lab5;

import java.util.*;

public class MyArrayList implements MyList {

	private Comparable[] array;
	
	public MyArrayList() {
		array = new Comparable[10];
	}
	
	@Override
	public int size() {
		return array.length;
	}
	
	@Override
	public boolean add(int index, Comparable t) {
		if(array[size()-1] != null) {
			Comparable[] resizeArray;
			resizeArray = new Comparable[size()*2];
			for(int i = 0; i < size(); i++) {
				resizeArray[i] = array[i];
			}
			array = resizeArray;
		}
		if(array[index] == null && index >= 0 || index < size()) {
			array[index] = t;
		}
		
		return false;
	}

	@Override
	public boolean add(Comparable o) {
		if(array[size()-1] != null) {
			Comparable[] resizeArray;
			resizeArray = new Comparable[size()*2];
			for(int i = 0; i < size(); i++) {
				resizeArray[i] = array[i];
			}
			array = resizeArray;
		}
		for(int i = 0; i < size(); i++) {
			if(array[i] == null) {
				array[i] = o;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean clear() {
		array = new Comparable[size()];
		return true;
	}

	@Override
	public boolean contains(Comparable o) {
		for(int i = 0; i < size(); i++) {
			if(array[i] == o) {
				return true;	
			}
		}
		return false;
	}

	@Override
	public Comparable get(int index) {
		if(index < 1 || index > size()) {
			System.out.println("Not a valid index");
			return null;
		} else {
			return array[index -1];
		}
	}

	@Override
	public int indexOf(Comparable o) {
		for(int i = 0; i < size(); i++) {
			if(array[i] == o) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public boolean isEmpty() {
		if(size() < 0) {
			return true;
		}
		return false;
	}

	@Override
	public Comparable remove(int index) {
		if(array[index] != null) {
			array[index] = null;
			for(int i = index; i < size() - 1; i++) {
				array[i] = array[i + 1];
			}
		}
		array[size() -1] = null;
		return array[index];
	}

	@Override
	public Object remove(Comparable o) {
		int index = 0;
		for(int i = 0; size() < 0; i++) {
			if(array[i] == o) {
				index = i;
				array[i] = null;
				
			}
		}
		return array[index];
	}

	@Override
	public boolean set(int index, Comparable element) {
		if(size() > index) {
			array[index] = element;
			return true;
		}
		return false;
	}

	@Override
	public MyList subList(int fromIndex, int toIndex) {
		if(fromIndex < 0 || fromIndex > size() && toIndex < 1 || toIndex > size()) {
			return null;
		}
		MyList currentList = new MyArrayList();
		for(int i = fromIndex; i < toIndex; i++) {
			currentList.add(array[i]);
		}
		return currentList;
	}

	@Override
	public Comparable[] toArray() {
		return array;
	}

	@Override
	public boolean swap(int position1, int position2) {
		Comparable placeHolder;
		if((position1 < size() && position2 < size()) && position1 >= 0 && position2 >= 0) {
			placeHolder = array[position1];
			array[position1] = array[position2];
			array[position2] = placeHolder;
			return true;
		}
		return false;
	}

	@Override
	public boolean shift(int positions) {
		if(positions < size() && positions > 0) {
			Comparable[] array2 = new Comparable[size()];
			for(int i = 0; i < (size()-positions); i++) {
				array2[i + positions] = array[i];
			}
			int counter = 0;
			for(int i = (size()-positions); i < size(); i++) {
				array2[counter] = array[i];
				counter++;
			}
			array = array2;
			return true;
		} else if(positions < 0 && Math.abs(positions) < size()) {
			Comparable[] array2 = new Comparable[size()];
			int counter = 0;
			for(int i = Math.abs(positions); i < size(); i++) {
				array2[counter] = array[i];
			}
			for(int i = 0; i < positions; i++) {
				array2[size() - Math.abs(positions)] = array[i];
			}
			array = array2;
			return true;
		} else {
			return false;
		}
	}
	//http://howtodoinjava.com/algorithm/merge-sort-java-example/
	public Comparable[] mergeSort(Comparable[] list) {
		Comparable[] left = new Comparable[list.length / 2];
		Comparable[] right = new Comparable[list.length - left.length];
		System.arraycopy(list, 0, left, 0, left.length);
		System.arraycopy(list, left.length, right, 0, right.length);
		mergeSort(left);
		mergeSort(right);
		merge(left, right, list);
		return list;
		
	}
	
	private void merge(Comparable[] left, Comparable[] right, Comparable[] result) {
		int counterLeft = 0;
		int counterRight = 0;
		int counter = 0;
		while(counterLeft < left.length && counterRight < right.length) {
			if(left[counterLeft].compareTo(right[counterRight]) > 0) {
				result[counter] = left[counterLeft];
				counterLeft++;
			} else {
				result[counter] = right[counterRight];
				counterRight++;
			}
			counter++;
		}
		System.arraycopy(left, counterLeft, result, counter, left.length - counterLeft);
        System.arraycopy(right, counterRight, result, counter, right.length - counterRight);
	}
	
	private void quickSort(int low, int high) {
	         
	    int i = low;
	    int j = high;
	    // calculate pivot number, I am taking pivot as middle index number
	    Comparable pivot = array[low+(high-low)/2];
	    // Divide into two arrays
	    while (i <= j) {
	            /**
	             * In each iteration, we will identify a number from left side which
	             * is greater then the pivot value, and also we will identify a number
	             * from right side which is less then the pivot value. Once the search
	             * is done, then we exchange both numbers.
	             */
	            while (array[i].compareTo(pivot) < 0) {
	                i++;
	            }
	            while (array[j].compareTo(pivot) > 0) {
	                j--;
	            }
	            if (i <= j) {
	            	Comparable temp = array[i];
	    	        array[i] = array[j];
	    	        array[j] = temp;
	                //move index to next position on both sides
	                i++;
	                j--;
	            }
	        }
	        // call quickSort() method recursively
	        if (low < j)
	            quickSort(low, j);
	        if (i < high)
	            quickSort(i, high);
	    }
	 
	    public static void recursiveQuickSort(int[] array, int startIdx, int endIdx) 
	    { 
	    	int idx = partition(array, startIdx, endIdx); 
	    // Recursively call quicksort with left part of the partitioned array 
	    	if (startIdx < idx - 1) { 
	    		recursiveQuickSort(array, startIdx, idx - 1); 
	    	} 
	    // Recursively call quick sort with right part of the partitioned array 
	    	if (endIdx > idx) { 
	    		recursiveQuickSort(array, idx, endIdx); 
	    	} 
	    }

	    public static int partition(int[] array, int left, int right) { 
	    	int pivot = array[left]; 
	    	// taking first element as pivot while (left <= right) 
	        //searching number which is greater than pivot, bottom up 
	    	while (array[left] < pivot) { 
	    		left++; 
	   		} 
	   		//searching number which is less than pivot, top down 
	   		while (array[right] > pivot) { 
	   			right--; 
    		} 
	   		// swap the values if (left <= right) 
	   		int tmp = array[left]; 
	   		array[left] = array[right];
	   		array[right] = tmp; 
	   		//increment left index and decrement right index 
	   		left++; 
			right--;  
	    	return left; 
	   }
	    
	    public void InsertionSort() {
	         int j;                     // the number of items sorted so far
	         Comparable key;                // the item to be inserted
	         int i;  

	         for (j = 1; j < size(); j++) {
	               key = array[j];
	               for(i = j - 1; i >= 0 && (array[i].compareTo(key) < 0); i--) {
	                     array[ i+1 ] = array[ i ];
	              }
	             array[ i+1 ] = key;    // Put the key in its proper location
	         }
	    }
	
	public void bubbleSort() 
	{
		int n = size();
        Comparable temp = 0;  
        for(int i=0; i < n; i++)
        {
            for(int j=1; j < (n-i); j++)
            {              
            	if(array[j-1].compareTo(array[j]) > 0)
            	{
                //swap the elements!
                  temp = array[j-1];
                  array[j-1] = array[j];
                  array[j] = temp;
                }                   
            }
        }
    }
	
	public void bucketSort() {
		
	}
	
	public void radixSort() {
		
	}
	
	public void heapSort() {
		
	}
	
	public void treeSort() {
		
	}
}
