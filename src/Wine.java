
public class Wine {
	String name;
	double price;
	int quantity;
	
	public Wine (String theName, double thePrice, int theQuantity) {
		name = theName;
		price = thePrice;
		quantity = theQuantity;
	}
	
	public String getName () {
		return name;
	}
	
	public double getPrice () {
		return price;
	}
	
	public int getQuantity () {
		return quantity;
	}
}
