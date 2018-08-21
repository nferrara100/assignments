/*
	Developed for the University of Glasgow
	Algorithms and Data Structures exercise spring 2018.
	Student ID: 2352426
	@author Nicholas Ferrara
*/

import java.util.Iterator;
import java.util.*;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {
	// Each Bag<E> object is a homogeneous bag whose
	// members are of type E.
	
	private int currentSize = 0;
	
	private class BSTNode<E extends Comparable<E>> {
		private CountedElement<E> element;
		private BSTNode<E> left;
		private BSTNode<E> right;
		private boolean removed = false;
		
		public BSTNode (E e) {
			element = new CountedElement<E>(e);
		}
		
		public CountedElement<E> getElement () {
			return element;
		}
		
		public void setLeft (BSTNode<E> node) {
			left = node;
		}
		
		public BSTNode<E> getLeft () {
			return left;
		}
		
		public BSTNode<E> getRight () {
			return right;
		}
		
		public void setRight (BSTNode<E> node) {
			right = node;
		}
		
		public boolean isRemoved () {
			return removed;
		}
		
		public void remove () {
			removed = true;
		}
	}
	
	private BSTNode<E> head = null;
	
	// ////////// Accessors ////////////
	
	// Return true if and only if this bag is empty.
	public boolean isEmpty() {
		if (currentSize  <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	// Return the size of this set.
	public int size() {
		return currentSize;
	}

	// Return true if and only if element is a member of this bag.
	public boolean contains(E element) {
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			E next = it.next();
			if (next.equals(element)) {
				return true;
			}
		}
		return false;
	}

	// Return true if and only if this bag is equal to that.
	public boolean equals(Bag<E> that) {
		Iterator<E> it = iterator();
		Iterator<E> thatIterator = that.iterator();
		while (it.hasNext()) {
			if(!thatIterator.hasNext()) {
				return false;
			}
			if (it.next() != thatIterator.next()) {
				return false;
			}
		}
		if (!thatIterator.hasNext()) {
			return true;
		}
		else {
			return false;
		}
	}

	// //////////Transformers ////////////

	// Make this bag empty.
	public void clear() {
		head = null;
		currentSize = 0;
	}

	// Add element to bag.
	// increment the number of element items in the bag
	public void add(E element) {
		currentSize++;
		BSTNode<E> current = head;
		if(head == null) {
			head = new BSTNode<E>(element);
		}
		else {
			while(true) {
				if(current.getElement().getElement().compareTo(element) <= -1) {
					if (current.getLeft() != null) {
						current = current.getLeft();
					}
					else {
						current.setLeft(new BSTNode<E>(element));
						return;
					}
				}
				else if(current.getElement().getElement().compareTo(element) >= 1) {
					if (current.getRight() != null) {
						current = current.getRight();
					}
					else {
						current.setRight(new BSTNode<E>(element));
						return;
					}
				}
				else {
					current.getElement().setCount(current.getElement().getCount() + 1);
					return;
				}
			}
		}
	}

	// Remove it from this set.
	// Do nothing if no item in bag pertaining to element
	// otherwise decrement number of element items (lazy deletion)
	public void remove(E element) {
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			E next = it.next();
			if (next == element) {
				it.remove();
				currentSize--;
				return;
			}
		}
	}

	// ////////// Iterator ////////////

	// Return an iterator that will visit all members of this
	// bag, in no particular order
	
	private class InOrderIterator implements Iterator {
		private LinkedStack<BSTNode<E>> track;
		private BSTNode<E> current;
		
		private InOrderIterator () {
			track = new LinkedStack<BSTNode<E>>();
			
			for (BSTNode<E> curr = head; curr != null; curr = curr.getLeft()) {
				for (int i = 0; i < curr.getElement().getCount(); i++) {
					track.push(curr);
				}
			}
		}
		
		public boolean hasNext() {
			return (! track.empty());
		}
		
		public E next () {
        	if (!hasNext()) {
        		return null;
        	}
        	BSTNode<E> place = track.pop();
        	for (BSTNode<E> curr = place.getRight(); curr != null; curr = curr.getLeft()) {
        			track.push(curr);
        	}
        	if(place.isRemoved()) {
        		if(hasNext()) {
        			return next();
        		}
        		else {
        			return null;
        		}
        	}
			return place.getElement().getElement();
		}
		
        public void remove() {
        	currentSize--;
        	if(current == null) {
        		current = (BSTNode<E>) track.pop();
        	}
        	System.out.println(current.getElement().getElement() + " " + current.getElement().getCount());
        	if (current.getElement().getCount() > 1) {
        		current.getElement().setCount(current.getElement().getCount() - 1);
        	}
        	else {
        		current.remove();
        	}
        	System.out.println(current.getElement().getElement() + " " + current.getElement().getCount() + " " + current.isRemoved());
        }
	}
		
	public Iterator<E> iterator() {
		return new InOrderIterator();
	}	
}
