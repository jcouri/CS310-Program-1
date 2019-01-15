package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;




public class ArrayLinearList<E> implements LinearListADT<E>{
	private E list[];
	private int maxSize, arraySize, first, last;
	
	
	public ArrayLinearList(int size) {
		maxSize = size;
		first = last = arraySize = 0;
		list = (E[]) new Object [maxSize];
	}
	
	public ArrayLinearList() {
		this (DEFAULT_MAX_CAPACITY);
	}
	
	public boolean addFirst(E obj) {                 
		if (isFull() == false){
			if (isEmpty() == false){
				if (first == 0){
					first = maxSize - 1;
				}
				else{
					first--;
				}
			}
			list [first] = obj;
			arraySize++;
			return true;
		}
		return false;
	}

	public boolean addLast(E obj) {                  
		if (isFull() == false){
			if (isEmpty() == false){
				if (last == maxSize - 1){
					last = 0;
				}
				else{
					last++;
				}
			}
			list [last] = obj;
			arraySize++;
			return true;
		}
		return false;
	}

	public E removeFirst() {
		if (isEmpty() == false) {
			E obj = list[first];
			if (arraySize != 1) {
				if (first == maxSize - 1){
					first = 0;
				}
				else{
					first++;
				}
			}
			arraySize--;
			return obj;
		}
		return null;
	}

	public E removeLast() {
		if (isEmpty() == false) {
			E obj = list[last];
			if (arraySize != 1) {
				if (last == 0){
					last = maxSize - 1;
				}
				else{
					last--;
				}
			}
			arraySize--;
			return obj;
		}
		return null;
	}

	public E remove(E obj) {
		if (contains(obj)) {
			int i = first;
			for (E tmp: this) {
				if (obj.getClass().getName().equals(tmp.getClass().getName())) {
					if(((Comparable<E>)obj).compareTo(tmp) == 0){
						break;
					}
				}
				if (i == maxSize - 1){
					i = 0;
				}
				else{
					i++;
				} 
			}
			while (i != last) {
				if (i == maxSize - 1){
					list[i] = list[0];
				}
				else{
					list[i] = list[i+1];
				}
				if (i == maxSize - 1){
					i = 0;
				}
				else{
					i++;
				}
			}
			if (arraySize != 1) {
				if (last == 0){
					last = maxSize - 1;
				}
				else{
					last--;
				}
			}
			arraySize--;
			return obj;
		}
		return null;
	}

	public E peekFirst() {
		if (isEmpty()) {
			return null;
		}
		E obj = list[first];
		return obj;
	}
	
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		E obj = list[last];
		return obj;
	}

	public boolean contains(E obj) {
		return find (obj) != null;
	}

	public E find(E obj) {
		boolean k = false;
		for (E tmp: this) {
			if (obj.getClass().getName().equals(tmp.getClass().getName())) {
				if(((Comparable<E>)obj).compareTo(tmp) == 0) {
					k = true;
				}
			}	
			if (k){
				return tmp;
			}
		}
		return null;
	}

	public void clear() {                       
		list = (E[]) new Object [maxSize];	
		first = 0;
		last = 0;
		arraySize = 0;
	}

	public boolean isEmpty() {                
		if (arraySize == 0){
			return true;
		}
		return false;
	}

	public boolean isFull() {                
		if (arraySize == maxSize){
			return true;
		}
		return false;
	}

	public int size() {                       
		return arraySize;
	}

	public Iterator<E> iterator() {
		class IteratorHelper implements Iterator<E> {
			private int count, index;
			public IteratorHelper() {
				index = first;
				count = 0;
			}
			public boolean hasNext() {
				return count != arraySize;
			}
			public E next() {
				if(!hasNext()) throw new NoSuchElementException();
				E tmp = list[index++];
				if (index == maxSize) index = 0;
				count++;
				return tmp;
			}
			public void remove() {
				
			}
		}
		return new IteratorHelper();
		
	}

}

