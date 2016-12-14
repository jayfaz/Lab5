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
		if(index < 0 || index >= size()) {
			System.out.println("Not a valid index");
			return -1;
		} else {
			return array[index];
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
	//only works with a full array
	public Comparable[] mergeSort(Comparable[] list) {
		if(list.length <= 1) {
			return list;
		}
		Comparable[] left = new Comparable[list.length/2];
		Comparable[] right = new Comparable[list.length - left.length];
		left = Arrays.copyOfRange(list, 0, list.length/2);
		right = Arrays.copyOfRange(list, list.length/2, list.length);
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
			if(left[counterLeft] != null && right[counterRight] != null) {
				if(left[counterLeft].compareTo(right[counterRight]) < 0) {
					result[counter] = left[counterLeft];
					counterLeft++;
				} else {
					result[counter] = right[counterRight];
					counterRight++;
				}
				counter++;
			} 
		}
		System.arraycopy(left, counterLeft, result, counter, left.length - counterLeft);
        System.arraycopy(right, counterRight, result, counter, right.length - counterRight);
	}    
	
	public void quickSort(int minIndex, int maxIndex) {//minIndex should be 0, and maxIndex should be list.length -1
	    int i = minIndex;
	    int j = maxIndex;
	    Comparable pivot = array[minIndex+(maxIndex-minIndex)/2]; //middle index as pivot
	    while(i <= j) {
	    	while(array[i].compareTo(pivot) < 0) {
	    		i++;
	    	}
	        while(array[j].compareTo(pivot) > 0) {
	            j--;
	        }
	        if(i <= j) {
	        	Comparable temp = array[i];
	    	    array[i] = array[j];
	    	    array[j] = temp;
	            i++;
	            j--;
	        }
	    }
	    if(minIndex < j)
	        quickSort(minIndex, j);
	    if(i < maxIndex)
	        quickSort(i, maxIndex);
	    }
	 
	    public static void recursiveQuickSort(Comparable[] array, int minIndex, int maxIndex) { 
	    	int idx = partition(array, minIndex, maxIndex);
	    	if (minIndex < idx - 1) { 
	    		recursiveQuickSort(array, minIndex, idx - 1); 
	    	} 
	    	if (maxIndex > idx) { 
	    		recursiveQuickSort(array, idx, maxIndex); 
	    	} 
	    }
	    

	   public static int partition(Comparable[] array, int left, int right) { 
	    	Comparable pivot = array[left]; // taking first element as pivot 
	    	while (left <= right) {
	    		while (array[left].compareTo(pivot) < 0) { 
	    			left++; 
	    		} 
	    		while (array[right].compareTo(pivot) > 0) { 
	    			right--; 
	    		} 
	    		if (left <= right) { 
	    			Comparable tmp = array[left]; 
	    			array[left] = array[right];
	    			array[right] = tmp;
	    			left++; 
	    			right--; 
	    		}
	    	}
	    	return left; 
	   }
	    
	    public void InsertionSort() { // descending sort 
	         int j;                     //# of items sorted
	         Comparable key;                //data to be sorted
	         int i;  

	         for (j = 1; j < size(); j++) {
	               key = array[j];
	               for(i = j - 1; i >= 0 && (array[i].compareTo(key) < 0); i--) {
	                     array[i+1] = array[i];
	              }
	             array[i+1] = key;
	         }
	    }
	
	public void bubbleSort() 
	{
		int n = size();
        Comparable temp = 0;  
        for(int i = 0; i < n; i++)
        {
            for(int j=1; j < (n-i); j++)
            {              
            	if(array[j-1].compareTo(array[j]) > 0)
            	{
                //swap the elements
                  temp = array[j-1];
                  array[j-1] = array[j];
                  array[j] = temp;
                }                   
            }
        }
    }
	
	public void bucketSort(int maxVal) {
		int [] bucket = new int[maxVal+1]; 
	    for (int i = 0; i < bucket.length; i++) {
	         bucket[i]=0;
	    }
	    for (int i = 0; i < array.length; i++) {
	         bucket[(int) array[i]]++;
	    }
	    int outPos=0;
	    for (int i = 0; i < bucket.length; i++) {
	    	for (int j = 0; j < bucket[i]; j++) {
	    		array[outPos++]=i;
	    	}
	    }
	}
	
	public void radixSort() {
		Comparable m = array[0];
		int exp = 1;
		int n = size();
        int[] b = new int[10];
        for (int i = 1; i < n; i++)
            if (array[i].compareTo(m) > 0) {
            	m = array[i];
            }
        while ((int)m / exp > 0)
        {
            int[] bucket = new int[10];
 
            for (int i = 0; i < n; i++)
                bucket[((int)array[i] / exp) % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = n - 1; i >= 0; i--)
                b[--bucket[((int)array[i] / exp) % 10]] = (int)array[i];
            for (int i = 0; i < n; i++)
                array[i] = b[i];
            exp *= 10;        
        }
    } 
	public Comparable[] getList() {
		return array;
	}
	
	public void heapSort() {
		
	}
	
	public void treeSort() {
		
	}
}
