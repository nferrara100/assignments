/*
	Example generator for cars headed East.
	Developed for the University of Glasgow
	Advanced Programming exercise spring 2018.
	@author Nicholas Ferrara
*/

import java.util.ArrayList;
import java.util.Arrays;

public class EastGenerator extends CarGenerator
{
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(1, 3, 5));
	private Velocity velocity = new Velocity (1, 0);
	
	
	public EastGenerator (Grid grid)
	{
		super.setGrid(grid);
		super.setLanes(lanes);
		super.setVelocity(velocity);
		
		//Continues the setup in the superclass.
		super.generate();
	}
}
