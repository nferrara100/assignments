import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class Simulation {
	
	private Grid grid;
	private int numCars;
	private ArrayList<Car> cars;
	
	public Simulation (int nSlots, int mSlots, final int maxRefreshes) {	

		grid = new Grid (nSlots, mSlots);
		numCars = ThreadLocalRandom.current().nextInt(1, nSlots + mSlots - 2);
		cars = new ArrayList<Car>();
		
		
		for(int i = 0; i < numCars; i++) {
			int speed = ThreadLocalRandom.current().nextInt(750, 1500);
			int nSlot;
			int mSlot;
			Velocity velocity;
			
			do {
				if(ThreadLocalRandom.current().nextInt(2) == 1) {
					nSlot = ThreadLocalRandom.current().nextInt(1, grid.nSlots);
					mSlot = 0;
					velocity = new Velocity(0, 1, speed);
				}
				else {
					mSlot = ThreadLocalRandom.current().nextInt(1, grid.mSlots);
					nSlot = 0;
					velocity = new Velocity(1, 0, speed);
				}
			} while (!grid.reserve(nSlot, mSlot));
			
			cars.add(new Car(grid, velocity, nSlot, mSlot));
			cars.get(i).start();
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
