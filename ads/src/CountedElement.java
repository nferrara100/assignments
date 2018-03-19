public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count) {
		element = e;
		this.count = count;
	}
	
	public CountedElement(E e) {
		this(e, 1);
	}
	
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
	
	public String toString () {
		return "(" + element.toString() + ", " + count + ")";
	}
	
	public int compareTo(CountedElement<E> sC1) {
		int compared = element.compareTo(sC1.getElement());
		if(compared == 0) {
			compared = count - sC1.getCount();
		}
		return compared;
	}
}
