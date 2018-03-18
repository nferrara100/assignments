public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count) {
		//constructor - to complete
		element = e;
		this.count = count;
	}
	
	public CountedElement(E e) {
		//constructor - to complete
		this(e, 1);
	}
	
	//add getters and setters
	public void setElement(E element) {
		this.element = element;
	}
	
	public E getElement () {
		return element;
	}
	
	public void setCount (int count) {
		this.count = count;
	}
	
	public int getCount () {
		return count;
	}
	
	//add toString() method
	
	public String toString () {
		return "(" + element.toString() + ", " + count + ")"; //stub to be improved
	}
	
	public int compareTo(CountedElement<E> sC1) {
		return element.compareTo(sC1.getElement());
		
	}

}
