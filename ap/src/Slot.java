/*
	Stores car classes in the Grid and prevents double booking.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.concurrent.locks.*;

public class Slot
{
	private Car car;
	private Lock carLock = new ReentrantLock();
	private Condition carCondition = carLock.newCondition();
	private boolean reserved = false;
	
	
	//Holds the Slot until it is filled. Only works without generators.
	//Returns false if already reserved.
	boolean reserve ()
	{
		if (reserved == false && car == null)
		{
			reserved = true;
			return true;
		}
		return false;
	}
	
	
	public Car getCar()
	{
		return car;
	}
	
	
	//Places a new car in the Slot. Pauses the Thread if already occupied.
	public boolean setCar (Car car)
	{
		try
		{
			//Prevents multiple Threads from continuing at once.
			carLock.lock();
			if (car != null)
			{
				//Pauses until the Slot is free.
				while(this.car != null)
				{
					carCondition.await();
				}
			}
			
			//Occupies the Slot.
			this.car = car;
			
			//Awakens other Threads waiting in the queue.
			carCondition.signalAll();
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			carLock.unlock();
		}
		return true;
	}
}
