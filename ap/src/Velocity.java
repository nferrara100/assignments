/*
	Stores rate of movement information.
	Designed for Car but can be used for any mathematical grid.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.concurrent.ThreadLocalRandom;

public class Velocity implements Cloneable
{
	private int nSlots = 0;
	private int mSlots = 0;
	private int speed = 750;
	private int speedVariation = 400;

	
	//All constructors will either simply include parameters or
	//resort to defaults.
	
	public Velocity (int nSlots, int mSlots, int speed, int speedVariation)
	{
		this(nSlots, mSlots, speed);
		this.speedVariation = speedVariation;
	}
	
	
	public Velocity (int nSlots, int mSlots, int speed)
	{
		this(nSlots, mSlots);
		this.speed = speed;
	}
	
	
	public Velocity (int nSlots, int mSlots)
	{
		this.nSlots = nSlots;
		this.mSlots = mSlots;
	}
	
	
	//Facilitates cloning
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    
    //Adds additional random speed to speed. Separated from constructor
    //so it can be called after cloning so each instance is different.
	public void randomizeSpeed ()
	{
		speed = speed + ThreadLocalRandom.current().nextInt(0, speedVariation);
	}
    
    
	public int getN()
	{
		return nSlots;
	}

	
	public int getM()
	{
		return mSlots;
	}

	
	public int getSpeed()
	{
		return speed;
	}
}
