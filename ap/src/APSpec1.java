/*
	The main class of the application.
	Simulates a large intersection via the terminal
	complete with cars going in ever direction that
	cannot occupy the same space. This particular
	simulation uses only the basics and does not
	enable much customization.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class APSpec1 extends Simulation
{
	private int nSlots = 20;
	private int mSlots = 10;
	private int maxRefreshes = 2000;
	private int refreshInterval = 20;
	private static APSpec1 sim;
	private Grid grid;
	private int numCars;
	private ArrayList<Car> cars;
	
	
	public static void main (String[] args)
	{	
		sim = new APSpec1();
		sim.start();
	}
	
	
	//Creates a grid and populates it.
	public void start ()
	{
		grid = new Grid (nSlots, mSlots);
		
		//Reduced by 1 because one the corner Slot overlaps.
		numCars = ThreadLocalRandom.current().nextInt(1, nSlots + mSlots - 1);
		cars = new ArrayList<Car>();
		
		//Generates all the cars specified by numCars
		for(int i = 0; i < numCars; i++)
		{
			//Randomizes the speed of the car.
			int speed = ThreadLocalRandom.current().nextInt(750, 1500);
			int nSlot;
			int mSlot;
			Velocity velocity;
			
			//Finds a place for the car in the grid. If the random location is
			//occupied it repeats until an empty space is found.
			do
			{
				//Randomly switches which axis is varied. The other is 0
				//because it is the starting axis.
				if(ThreadLocalRandom.current().nextInt(2) == 1)
				{
					nSlot = ThreadLocalRandom.current().nextInt(1, grid.getNSlots());
					mSlot = 0;
					velocity = new Velocity(0, 1, speed);
				}
				else
				{
					mSlot = ThreadLocalRandom.current().nextInt(1, grid.getMSlots());
					nSlot = 0;
					velocity = new Velocity(1, 0, speed);
				}
			}
			while (!grid.reserve(nSlot, mSlot));
			
			//Starts the car.
			cars.add(new Car(grid, velocity, nSlot, mSlot));
			cars.get(i).start();
		}
	
		//Starts printing the simulation.
		grid.refresh(maxRefreshes, refreshInterval, this);
	}
	
	
	//Callback for when the simulation ends.
	public void completed ()
	{
		System.out.println("------ Simulation Ended ------");
	}	
}
