//This class is used to store information about the wine in the application
public class Wine {
	private String name;
	private double price;
	private int quantity;
	
	public Wine (String theName, double thePrice, int theQuantity) {
		name = theName;
		price = thePrice;
		quantity = theQuantity;
	}
	
	public String getName () {
		return name;
	}
	
	public int getPriceInt () {
		return (int) Math.round(100 * price);
	}
	
	public int getQuantity () {
		return quantity;
	}
}
