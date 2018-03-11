import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
	
	private int nSlots = 20;
	private int mSlots = 10;
	private int maxRefreshes = 2000;
	private int refreshInterval = 20;
	private Grid grid;
	private ArrayList<Car> cars;
	ArrayList<CarGenerator> generators;
	private static Simulation sim;
	private Statistics stats;
	
	public static void main (String[] args) {	
		sim = new Simulation();
		sim.start();
	}
	
	public void start () {
		grid = new Grid (nSlots, mSlots);
		generators = new ArrayList();
		
		ArrayList<Integer> eastFast = new ArrayList<Integer>(Arrays.asList(1, 3, 5));
		generators.add(new CarGenerator(grid, eastFast));
		
		generators.add(new WestGenerator(grid, 700));
		
		ArrayList<Integer> northFast = new ArrayList<Integer>(Arrays.asList(1, 5, 6));
		generators.add(new CarGenerator(grid, northFast, new Velocity (0, -1, 900)));
		
		ArrayList<Integer> southSlow = new ArrayList<Integer>(Arrays.asList(3, 4, 7));
		generators.add(new CarGenerator(grid, southSlow, new Velocity (0, 1, 1200, 800), 5));
		
		ArrayList<Integer> southFast = new ArrayList<Integer>(Arrays.asList(2, 8, 9));
		generators.add(new CarGenerator(grid, southFast, new Velocity (0, 1, 600)));
	
		refresh(maxRefreshes);
	}
	
	private void refresh (int remainingRefreshes) {
		System.out.println(grid.print());
		
		if (grid.carsLeft() && remainingRefreshes > 0) {
			try {
				Thread.sleep(refreshInterval);
				refresh(remainingRefreshes - 1);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		else {
			stats = new Statistics(generators);
			System.out.println(stats.tabulate());
		}
	}
	
}
