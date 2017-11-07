
public class CustomerAccount {
	private int balance;
	private String name;
	private static final double returnSurcharge = 0.2;
	
	public CustomerAccount (String theName, int theBalance) {
		name = theName;
		balance = theBalance;
	}
	
	public int getBalance () {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public double sale (int numBottles, int costBottle) {
		double transactionTotal = numBottles*costBottle;
		balance += transactionTotal;
		return transactionTotal;
	}
	
	public double refund (int numBottles, int costBottle) {
		double transactionTotal = numBottles*costBottle;
		balance -= transactionTotal;
		return transactionTotal;
	}

}

