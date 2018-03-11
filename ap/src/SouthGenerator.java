/*
	Example generator for cars headed South.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.ArrayList;
import java.util.Arrays;

public class SouthGenerator extends CarGenerator
{
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(3, 4, 7));
	
	
	public SouthGenerator (Grid grid, int speed, int speedVariation, int numCars)
	{
		super.setGrid(grid);
		super.setLanes(lanes);
		super.setVelocity(new Velocity (0, 1, speed, speedVariation));
		super.setNumCars(numCars);
		
		//Continues the setup in the superclass.
		super.generate();
	}
}
