/*
	Organizes the various Slot classes which 
	store what places are occupied. Also handles
	graphical output.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;

public class Grid
{
	private Slot[][] slots;
	private int nSlots;
	private int mSlots;
	private Set<Car> cars = new HashSet<Car>();
	
	
	public Grid (int nSlots, int mSlots)
	{
		slots = new Slot[nSlots][mSlots];
		
    	for(int m = 0; m < mSlots; m++)
    	{
			for(int n = 0; n < nSlots; n++)
			{
				slots[n][m] = new Slot();
			}
		}
    	
		this.nSlots = nSlots;
		this.mSlots = mSlots;
	}
	
	
	//Prints the screen into the terminal. Recursively continues until the
	//simulation is over.
	public void refresh (int remainingRefreshes, int refreshInterval, Simulation sim)
	{
		System.out.println(print());
		
		//Terminates if there are no cars let or the maximum amount of
		//refreshes is exceeded.
		if (carsLeft() && remainingRefreshes > 0)
		{
			try
			{
				//Pauses before printing the next frame.
				Thread.sleep(refreshInterval);
				refresh(remainingRefreshes - 1, refreshInterval, sim);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			//Calls the callback method for custom cleanup depending on the
			//particular of simulation.
			sim.completed();
		}
	}
	
	
	//Renders the grid in textual form.
	public String print ()
	{
		String newline = System.getProperty("line.separator");
		String margin = "";
		
		//The top line on the grid.
		for(int i = 0; i < nSlots; i++)
		{
			margin += "--";
		}
		String returnString = margin + newline;
		
		//Renders each slot.
		for(int m = 0; m < mSlots; m++)
		{
			for(int n = 0; n < nSlots; n++)
			{
				//Returns blank if no car, and renders the icon if there is one.
				char carChar = (slots[n][m].getCar() == null)?' ':
					(slots[n][m].getCar().getVelocity().getM() != 0)?'o':'-';
				returnString += carChar + "|";
			}
			returnString += newline;
		}
		
		//Adds the final bottom line.
		returnString += margin + newline;
		return returnString;
	}
	
	//Moves the car passed as a parameter.
	public boolean updateCarSlot (Car car)
	{
		boolean returnVal;
		
		//Kills the car if it has left the grid.
		if(!withinBounds(car.getN(), car.getM()))
		{
			cars.remove(car);
			returnVal = false;
		}
		else
		{
			cars.add(car);
			
			//Returns only once the slot is free.
			returnVal = slots[car.getN()][car.getM()].setCar(car);
		}
		
		//Removes the car's old placeholder. If it just started the old
		//placeholder wouldn't exist. New cars can't move in until the
		//slot is cleared.
		if(withinBounds(car.getPrevN(), car.getPrevM()))
		{
			slots[car.getPrevN()][car.getPrevM()].setCar(null);
		}
		
		return returnVal;
	}
	
	
	//Used to avoid duplicate starting positions when not using a generator.
	public boolean reserve (int nSlot, int mSlot)
	{
		return slots[nSlot][mSlot].reserve();
	}
	
	
	//Returns true if the given parameters are valid coordinates.
	public boolean withinBounds (int n, int m)
	{
		return n < nSlots && n >= 0 && m < mSlots && m >= 0;
	}
	
	
	//Gets the maximum opposite index of the slot. Used to start a car
	//on any side.
	//If @param isN == false then index == m.
	public int ceilIndex (int index, boolean isN)
	{
		if(index >= 0)
		{
			return 0;
		}
		else if (isN)
		{
			return nSlots - 1;
		}
		else
		{
			return mSlots - 1;
		}
	}
	
	
	public boolean carsLeft ()
	{
		return !cars.isEmpty();
	}
	
	
	public int getNSlots ()
	{
		return nSlots;
	}
	
	
	public int getMSlots ()
	{
		return mSlots;
	}
}
