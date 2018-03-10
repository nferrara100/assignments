/**
 * @author Nicholas Ferrara
 *
 */
public class Main {

	private static Simulation sim;
	private static final int nSlots = 20;
	private static final int mSlots = 10;
	private static final int maxRefreshes = 2000;
	
	public static void main(String[] args) {
		sim = new Simulation(nSlots, mSlots, maxRefreshes);
	}

}
