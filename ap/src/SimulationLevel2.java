/*
	The main class of the application.
	Simulates a large intersection via the terminal
	complete with cars going in ever direction that
	cannot occupy the same space.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;

public class SimulationLevel2
{
	private int nSlots = 20;
	private int mSlots = 10;
	private int maxRefreshes = 2000;
	private int refreshInterval = 20;
	private ArrayList<CarGenerator> generators;
	private static SimulationLevel2 sim;
	private Statistics stats;
	private Grid grid;
	
	
	public static void main (String[] args)
	{	
		sim = new SimulationLevel2();
		sim.start();
	}
	
	
	//Creates a grid and populates it.
	public void start ()
	{
		grid = new Grid (nSlots, mSlots);
		generators = new ArrayList<CarGenerator>();
		
		generators.add(new EastGenerator(grid));
		generators.add(new WestGenerator(grid, 700));
		generators.add(new NorthGenerator(grid, 900));
		generators.add(new SouthGenerator(grid, 1200, 800, 5));
		generators.add(new SouthGeneratorFast(grid));
	
		refresh(maxRefreshes);
	}
	
	
	//Prints the screen into the terminal.
	private void refresh (int remainingRefreshes)
	{
		System.out.println(grid.print());
		
		//Terminates if there are no cars let or the maximum amount of
		//refreshes is exceeded.
		if (grid.carsLeft() && remainingRefreshes > 0)
		{
			try
			{
				//Pauses before printing the next frame.
				Thread.sleep(refreshInterval);
				refresh(remainingRefreshes - 1);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			//Prints the final statistics once the application finishes.
			stats = new Statistics(generators);
			System.out.println(stats.tabulate());
		}
	}
	
}
