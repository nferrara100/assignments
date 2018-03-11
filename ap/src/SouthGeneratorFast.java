/*
	Example generator for cars headed South.
	Customized to reduce constructor arguments.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SouthGeneratorFast extends CarGenerator
{
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(2, 8, 9));
	
	
	public SouthGeneratorFast(Grid grid)
	{
		super.setVelocity(new Velocity (0, 1, 400, 100));
		super.setNumCars(ThreadLocalRandom.current().nextInt(2, 4));
		super.setGrid(grid);
		super.setLanes(lanes);
		
		//Continues the setup in the superclass.
		super.generate();
	}
}
