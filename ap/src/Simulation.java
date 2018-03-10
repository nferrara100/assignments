import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
	
	private Grid grid;
	private int numCars;
	private Car[] cars;
	
	public Simulation (int nSlots, int mSlots, final int maxRefreshes) {	

		grid = new Grid (nSlots, mSlots);
		numCars = ThreadLocalRandom.current().nextInt(1, nSlots + mSlots - 2);
		cars = new Car[numCars];
		
		for(int i = 0; i < numCars; i++) {
			cars[i] = new Car(grid);
			cars[i].start();
		}
		
		refresh(maxRefreshes);
	}
	
	private void refresh (int remainingRefreshes) {
		System.out.println(grid.print());
		
		if (grid.carsLeft() && remainingRefreshes > 0) {
			try {
				Thread.sleep(20);
				refresh(remainingRefreshes - 1);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		else {
			System.out.println("------ Simulation Ended ------");
		}
	}
	
}
