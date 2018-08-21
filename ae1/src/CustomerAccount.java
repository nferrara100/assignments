//This class stores data about a given customer's relationship with the company

public class CustomerAccount {
	private int balance;
	private String name;
	private static final double returnSurcharge = 0.2;
	
	public CustomerAccount (String theName, double theBalance) {
		name = theName;
		balance = (int) Math.round(theBalance*100);
		//Is converted as a best practice
	}
	
	public String getBalance () {
		String result = "£" + String.format("%.02f", (float) Math.abs(balance) / 100);
		//This outputs a String formatted as money
		if (balance < 0) {
			result = "CR " + result;
		}
		//Since Math.abs prevents negative numbers this denotes them instead.
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public String sale (int numBottles, int costBottle) {
		double transactionTotal = numBottles*costBottle;
		balance += transactionTotal;
		return "£" + String.format("%.02f", (float) transactionTotal / 100);
		//Returns the result as a String ready for use
	}
	
	public String refund (int numBottles, int costBottle) {
		double transactionTotal = numBottles * costBottle * (1-returnSurcharge);
		balance -= transactionTotal;
		return "£" + String.format("%.02f", (float) transactionTotal / 100);
	}

}

