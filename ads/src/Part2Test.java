import java.util.Iterator;

public class Part2Test {

	public static void main(String[] args) {
		BSTBag<String> bag = new BSTBag<String>();
		System.out.println(bag.isEmpty());
		
		bag.add("Hello");
		bag.add("World");
		bag.add("AAA");
		
		System.out.println(bag.size());
		
		BSTBag<String> bag2 = new BSTBag<String>();
		
		System.out.println(bag2.equals(bag));
		
		bag2.add("World");
		bag2.add("Hello");
		bag2.add("AAA");
		
		System.out.println(bag2.equals(bag));
		
		System.out.println("Contains AAA: " + bag.contains("AAA"));
		
		System.out.println("Is empty: " + bag.isEmpty());
		
		bag.remove("Hello");
		
		System.out.println("New size: " + bag.size());
		
		bag.add("Same");
		bag.add("Same");
		bag.add("Nick");
		bag.remove("Nick");
		
		Iterator<String> it = bag.iterator();
		
		bag.add("Dev");
		
		System.out.println(bag2.contains("Nick"));
		System.out.println(bag.contains("Nick"));
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		
		
	}
	
	
//	
//	Iterator<E> it = iterator();
//	while (it.hasNext()) {
//		E searchEl = it.next();
//		Iterator<E> thatIterator = that.iterator();
//		boolean elEquals = false;
//		while(thatIterator.hasNext()) {
//			if (thatIterator.next() == searchEl) {
//				elEquals = true;//num
//			}
//		}
//		if (elEquals != true) {
//			return false;
//		}
//	}
//	if (!thatIterator.hasNext()) {
//		return true;
//	}
//	else {
//		return false;
//	}
	
	
	
	
	
	
//	
//	LinkedStack<BSTNode<E>> saved = new LinkedStack<BSTNode<E>>();
//	
//	//Clean out removed elements
//	for (int i = 0; i < currentSize - elementsChecked; i++) {
//		BSTNode<E> nodeToCheck = track.pop();
//		if (!nodeToCheck.isRemoved()) {
//			saved.push(track.pop());
//		}
//	}
//	for (int i = 0; i < currentSize - elementsChecked; i++) {
//		track.push(saved.pop());
//	}
//	return (! track.empty());
	
}
