import java.util.*;

public class Statistics {
	ArrayList<CarGenerator> generators;
	
	public Statistics (ArrayList<CarGenerator> generators) {
		this.generators = generators;
	}
	
	public String tabulate () {
		String newline = System.getProperty("line.separator");
		String returnString = "------ Final Statistics (Seconds) ------" + newline;
		returnString += String.format("%-20s %-20s %-20s %-20s %-20s %n",
				"Generator ID", "Maximum Travel Time", "Mininum Travel Time", "Mean Travel Time", "Variance in Travel Time");
		
		for (int i = 0; i < generators.size(); i++) {
			CarGenerator car = generators.get(i);
			returnString += String.format("%-20s %-20.1f %-20.1f %-20.1f %-20.1f%n",
					"Generator " + (i + 1), cast(car.maxTime()), cast(car.minTime()),
					cast(car.meanTime()), cast(car.timeVariance()));
		}
		return returnString;
	}
	
	private float cast (int milli) {
		return (float) milli / 1000;
	}
}
