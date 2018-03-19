import java.util.Iterator;

public class Part2Test {

	public static void main(String[] args) {
		BSTBag<String> bag = new BSTBag<String>();
		
		bag.add("Hello");
		bag.add("World");
		bag.add("AAA");
		
		System.out.println(bag.size());
		
		BSTBag<String> bag2 = new BSTBag<String>();
		
		System.out.println(bag2.equals(bag));
		
		bag2.add("Hello");
		bag2.add("World");
		bag2.add("AAA");
		
		System.out.println(bag2.equals(bag));
		
		System.out.println("Contains AAA: " + bag.contains("AAA"));
		
		System.out.println("Is empty: " + bag.isEmpty());
		
		bag.remove("Hello");
		
		System.out.println("New size: " + bag.size());
		
		Iterator<String> it = bag.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}
