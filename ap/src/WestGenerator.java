import java.util.ArrayList;
import java.util.Arrays;

public class WestGenerator extends CarGenerator {
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(2, 4, 6, 8));
	private Velocity velocity;
	
	public WestGenerator (Grid grid, int speed) {
		super.setGrid(grid);
		Velocity velocity = new Velocity (-1, 0, speed);
		super.setVelocity(velocity);
		super.setLanes(lanes);
	}
}
