/*
	Complies statistical data about car journeys.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;

public class Statistics
{
	private ArrayList<CarGenerator> generators;
	private final int MILLI_IN_SEC = 1000;
	
	
	public Statistics (ArrayList<CarGenerator> generators)
	{
		this.generators = generators;
	}
	
	
	//Returns a full statistical readout of car travel times.
	public String tabulate ()
	{
		String newline = System.getProperty("line.separator");
		String returnString = newline + "------ Final Statistics (Seconds) ------" + newline + newline;
		
		//Title row
		returnString += String.format("%-20s %-20s %-20s %-20s %-20s %n",
				"Generator ID", "Maximum Travel Time", "Mininum Travel Time", "Mean Travel Time", "Variance in Travel Time");
		
		//Prints a new row for each generator
		for (int i = 0; i < generators.size(); i++)
		{
			CarGenerator car = generators.get(i);
			returnString += String.format("%-20s %-20.1f %-20.1f %-20.1f %-20.1f%n",
					"Generator " + (i + 1), cast(car.maxTime()), cast(car.minTime()),
					cast(car.meanTime()), cast(car.timeVariance()));
		}
		return returnString;
	}
	
	
	//Turns milliseconds into seconds
	private float cast (int milli)
	{
		return (float) milli / MILLI_IN_SEC;
	}
}
