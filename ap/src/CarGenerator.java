import java.util.*;

public class CarGenerator {
	
	private ArrayList<Car> cars = new ArrayList<Car>();
	
	private Grid grid;
	private ArrayList<Integer> lanes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
	private Velocity velocity = new Velocity (0, 1);
	private int density = 2;
	
	public CarGenerator (Grid grid, ArrayList<Integer> lanes, Velocity velocity, int density) {
		setGrid(grid);
		setLanes(lanes);
		setVelocity(velocity);
		setDensity(density);
		generate();
	}
	
	public void generate () {
		Collections.shuffle(lanes);
		for(int i = 0, x = 0; i < density; i++, x++) {
			System.out.println("i: " + i);
			System.out.println("size: " + lanes.size());
			if (i == lanes.size() && lanes.size() > 0) {
				x = 0;
			}
			
			Velocity carVelocity;
			try {
				carVelocity = (Velocity) velocity.clone();
			} catch (CloneNotSupportedException ex) {
				ex.printStackTrace();
				carVelocity = velocity;
			}
			carVelocity.randomizeSpeed();
			
			int mSlot;
			int nSlot;
			
			if (velocity.getN() == 0) {
				mSlot = grid.ceilIndex(velocity.getM(), false);
				nSlot = lanes.get(x);
			}
			else {
				nSlot = grid.ceilIndex(velocity.getN(), true);
				mSlot = lanes.get(x);
			}
			
			cars.add(new Car(grid, carVelocity, nSlot, mSlot));
			cars.get(i).start();
		}
	}
	
	public CarGenerator (Grid grid, ArrayList<Integer> lanes, Velocity velocity) {
		this(grid, lanes, velocity, 3);
	}
	
	public CarGenerator (Grid grid, ArrayList<Integer> lanes) {
		this(grid, lanes, new Velocity (1, 0, 600));
	}
	
	public CarGenerator () {
		if(grid == null) {
			throw new NullPointerException("Specify the generator's grid");
		}
		generate();
	}
	
	public int totalTime () {
		int totalTime = 0;
		for (Car car : cars) {
			totalTime += car.getTravelTime();
		}
		return totalTime;
	}
	
	public int meanTime () {
		return totalTime() / cars.size();
	}
	
	public int minTime () {
		int minTime = cars.get(0).getTravelTime();
		for (Car car : cars) {
			if (car.getTravelTime() < minTime) {
				minTime = car.getTravelTime();
			}
		}
		return minTime;
	}
	
	public int maxTime () {
		int maxTime = 0;
		for (Car car : cars) {
			if (car.getTravelTime() > maxTime) {
				maxTime = car.getTravelTime();
			}
		}
		return maxTime;
	}
	
	public int timeVariance () {
		return maxTime() - minTime();
	}
	
	public void setLanes (ArrayList<Integer> lanes) {
		this.lanes = lanes;
	}
	
	public void setGrid (Grid grid) {
		this.grid = grid;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public void setDensity(int density) {
		this.density = density;
	}
}
