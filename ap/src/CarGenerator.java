/*
	Provides a facility to mass produce varied car objects
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018
	@author Nicholas Ferrara
*/

import java.util.*;

public abstract class CarGenerator
{
	private ArrayList<Car> cars = new ArrayList<Car>();
	private Grid grid;
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
	private Velocity velocity = new Velocity (0, 1);
	private int numCars = 2;
	
	
	//Used by subclasses
	public CarGenerator () {}
	
	
	//Called by subclasses after construction complete
	public void generate ()
	{
		//Since the class places cars on a grid the gird must be given by the 
		//subclass's constructor.
		if(grid == null)
		{
			throw new NullPointerException("Specify the generator's grid");
		}
		
		//Since lanes are shuffled the modulus of cars / numCars will always
		//be placed in different lanes.
		Collections.shuffle(lanes);
		
		//Generates all the cars specified by numCars
		for(int i = 0, x = 0; i < numCars; i++, x++)
		{
			//If there is a car in every lane the first lane gets another
			if (i == lanes.size() && lanes.size() > 0)
			{
				x = 0;
			}
			
			//A separate velocity object is needed so the speed of each can be
			//randomized.
			Velocity carVelocity;
			try
			{
				carVelocity = (Velocity) velocity.clone();
			}
			catch (CloneNotSupportedException ex)
			{
				ex.printStackTrace();
				carVelocity = velocity;
			}
			carVelocity.randomizeSpeed();
			
			//Sets the starting position
			int mSlot;
			int nSlot;
			if (velocity.getN() == 0)
			{
				//Places the car on the correct side of the grid
				mSlot = grid.ceilIndex(velocity.getM(), false);
				nSlot = lanes.get(x);
			}
			else
			{
				nSlot = grid.ceilIndex(velocity.getN(), true);
				mSlot = lanes.get(x);
			}
			
			//Starts the car
			cars.add(new Car(grid, carVelocity, nSlot, mSlot));
			cars.get(i).start();
		}
	}
	
	
	//Gets the cumulative amount of driving time by all cars.
	public int totalTime ()
	{
		int totalTime = 0;
		for (Car car : cars)
		{
			totalTime += car.getTravelTime();
		}
		return totalTime;
	}
	
	
	//Gets the mean driving time of all cars.
	public int meanTime ()
	{
		return totalTime() / cars.size();
	}
	
	
	//Gets the shortest driving time by a car.
	public int minTime ()
	{
		int minTime = cars.get(0).getTravelTime();
		for (Car car : cars)
		{
			if (car.getTravelTime() < minTime)
			{
				minTime = car.getTravelTime();
			}
		}
		return minTime;
	}
	
	
	//Gets the longest driving time by a car.
	public int maxTime ()
	{
		int maxTime = 0;
		for (Car car : cars)
		{
			if (car.getTravelTime() > maxTime)
			{
				maxTime = car.getTravelTime();
			}
		}
		return maxTime;
	}
	
	
	//Gets the difference between the longest and shortest driving times.
	public int timeVariance ()
	{
		return maxTime() - minTime();
	}
	
	
	public void setGrid (Grid grid)
	{
		this.grid = grid;
	}
	
	
	public void setLanes (ArrayList<Integer> lanes)
	{
		this.lanes = lanes;
	}

	
	public void setVelocity(Velocity velocity)
	{
		this.velocity = velocity;
	}

	
	public void setNumCars(int numCars)
	{
		this.numCars = numCars;
	}
}
