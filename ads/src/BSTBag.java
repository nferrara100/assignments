import java.util.Iterator;
import java.util.*;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {
	// Each Bag<E> object is a homogeneous bag whose
	// members are of type E.
	
	private int currentSize = 0;
	
	private class BSTNode<E extends Comparable<E>> {//Add counted element
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
			System.out.println(next + " " + element);
			if (next.equals(element)) {
				return true;
			}
		}
		return false;
	}

	// Return true if and only if this bag is equal to that.
	public boolean equals(Bag<E> that) {//Redo. Has to check no matter order.
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
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {

            int numVisited = 0;
            int currentNumLeft = 0;
            
            BSTNode<E> current = head;
            
            LinkedStack<BSTNode<E>> remaining = new LinkedStack<BSTNode<E>>();

            @Override
            public boolean hasNext() {
                return numVisited < currentSize;
            }

            @Override
            public E next() {
            	if (!hasNext()) {
            		throw new NoSuchElementException();
            	}
            	numVisited++;
            	
            	BSTNode<E> returnNode = current;
            	
            	
            	if(currentNumLeft > 0) {
            		return returnNode.getElement().getElement();
            	}
            	
            	if (current.getLeft() != null) {
            		if(current.getRight() != null) {
            			remaining.push(current.getRight());
            		}
            		current = current.getLeft();
            	}
            	else if (current.getRight() != null) {
            		current = current.getRight();
            	}
            	else if (!remaining.empty()) {
            		current = remaining.pop();
            	}
            	if (current.isRemoved()) {
            		numVisited--;
            		return next();
            	}
         
        		if(returnNode.getElement().getCount() > 1) {
        			currentNumLeft = returnNode.getElement().getCount() - 1;
        		}
        		return returnNode.getElement().getElement();
            }

            @Override
            public void remove() {
            	if (current.getElement().getCount() > 1) {
            		current.getElement().setCount(current.getElement().getCount() - 1);
            	}
            	else {
            		current.remove();
            	}
            }
        };
        return it;
	}
	
}
