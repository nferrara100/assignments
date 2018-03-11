/*
	The thread that stores movement data.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

public class Car extends Thread
{
	private int nSlot;
	private int mSlot;
	private Grid grid;
	private Velocity velocity;
	private int travelTime = 0;
	
	
	public Car (Grid grid, Velocity velocity, int nSlot, int mSlot)
	{
		this.grid = grid;
		this.velocity = velocity;
		this.nSlot = nSlot;
		this.mSlot = mSlot;
	}
	
	
	//Called when the thread is started. Loops moving the car until it
	//leaves the grid.
	public void run ()
	{
		try
		{
			while(true)
			{
				//Returns false if the car is no longer in the grid.
				if(!grid.updateCarSlot(this))
				{
					break;
				}
				
				//Times out for the given interval.
				Thread.sleep(velocity.getSpeed());
				travelTime += velocity.getSpeed();
				
				//Actually moves the car.
				nSlot += velocity.getN();
				mSlot += velocity.getM();
			}
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public int getN ()
	{
		return nSlot;
	}
	
	
	public int getM ()
	{
		return mSlot;
	}
	
	
	//Gets nSlot before the last update
	public int getPrevN ()
	{
		return nSlot - velocity.getN();
	}
	
	
	//Gets mSlot before the last update
	public int getPrevM ()
	{
		return mSlot - velocity.getM();
	}
	
	
	public Velocity getVelocity ()
	{
		return velocity;
	}
	
	
	public int getTravelTime ()
	{
		return travelTime;
	}
}