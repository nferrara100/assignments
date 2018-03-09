import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class Simulation {
	private int nSlots;
	private int mSlots;
	private Grid grid;
	private int numCars;
	private Car[] cars;
	private Thread[] carThreads;
	
	public Simulation (int nSlots, int mSlots) {
		this.nSlots = nSlots;
		this.mSlots = mSlots;		
		start();
	}
	
	private void start () {
		grid = new Grid (nSlots, mSlots);
		
		numCars = ThreadLocalRandom.current().nextInt(1, nSlots + mSlots - 2);
		cars = new Car[numCars];
		carThreads = new Thread[numCars];
		
		for(int i = 0; i < numCars; i++) {
			cars[i] = new Car(grid);
		}
		
		for(int i = 0; i < numCars; i++) {
			carThreads[i] = new Thread(cars[i]);
			carThreads[i].start();
		}
		
		refresh(50);//Might need to redo this.
	}
	
	private void refresh (int iterationsLeft) {
		System.out.println(grid.print());
		
		if (iterationsLeft > 0) {
			try {
				Thread.sleep(20);
				refresh(iterationsLeft -1);
			}
			catch (InterruptedException inEx) {
				System.out.print("Simulation Interupted");
			}
		}
		else {
			System.out.println();
			System.out.println("------ Simulation Ended ------");
		}
	}
	
}
