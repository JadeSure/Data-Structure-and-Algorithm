import java.util.Arrays;

public class MyArray <E>{
	private Object[] elements;

	
	public MyArray(int length) {
		elements = new Object[length];
	}
	
	public MyArray() {
		elements = new Object [0];
	}

	public int size() {
		return elements.length;
	}

	public void add(E element) {
		Object[] newArray = new Object[elements.length + 1];
		for (int i = 0; i < elements.length; i++) {
			newArray[i] = elements[i];
		}
		newArray[elements.length] = element;
		elements = newArray;
	}

	public String show() {
		return Arrays.toString(elements);
	}

	public void delete(int index) {
		// index smaller than two edges
		if (index < 0 || index > elements.length - 1) {
			return;
		}

		// because of deleting array, new array index should be minus 1
		Object[] newArray = new Object[elements.length - 1];
		for (int i = 0; i < newArray.length; i++) {
			// before deleted index, all elements equal to each other
			if (i < index) {
				newArray[i] = elements[i];
				// after deleted index, the old index of element bigger one than new array
			} else {
				newArray[i] = elements[i + 1];
			}
		}
		elements = newArray;
	}

	public Object get(int index) {
		return elements[index];
	}

	// could be deleted
	// insert a value to a specific locaiton
	public void insert(int index, E element) {
		int extendIndex = elements.length;
		if (index > elements.length-1) {
			extendIndex = index+1;
		}
		
		Object[] newArr = new Object[extendIndex];
		if (index < 0) {
			return;
		}

		for (int i = 0; i < elements.length; i++) {
				newArr[i] = elements[i];
		}
		newArr[index] = element;
		elements = newArr;

	}

	public void set(int index, E element) {
		if (index < 0 || index > elements.length - 1) {
			return;
		}
		elements[index] = element;
	}

	// sort method by linear way
	public <T> int search(T target) {
		for (int i = 0; i < elements.length; i++) {
			
			if (target.equals(elements[i])) {
				return i;
			}
		}
		// did not find equal value
		return -1;
	}
}
