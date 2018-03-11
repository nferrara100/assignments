/*
	Example generator for cars headed North.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.ArrayList;
import java.util.Arrays;

public class NorthGenerator extends CarGenerator
{
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(1, 5, 6));
	
	
	public NorthGenerator (Grid grid, int speed)
	{
		super.setGrid(grid);
		super.setLanes(lanes);
		super.setVelocity(new Velocity (0, -1, speed));
		
		//Continues the setup in the superclass.
		super.generate();
	}
}
