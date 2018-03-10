import java.util.*;

public class Grid {
	
	private Slot[][] slots;
	int nSlots;
	int mSlots;
	Set<Car> cars = new HashSet<Car>();
	
	public Grid (int nSlots, int mSlots) {
		slots = new Slot[nSlots][mSlots];
		
    	for(int m = 0; m < mSlots; m++) {
			for(int n = 0; n < nSlots; n++) {
				slots[n][m] = new Slot();
			}
		}
    	
		this.nSlots = nSlots;
		this.mSlots = mSlots;
	}
	
	public String print () {
		String newline = System.getProperty("line.separator");
		String margin = "";
		for(int i = 0; i < nSlots; i++) {
			margin += "--";
		}
		String returnString = margin + newline;
		
		for(int m = 0; m < mSlots; m++) {
			for(int n = 0; n < nSlots; n++) {
				char carChar = (slots[n][m].getCar() == null)?' ':(slots[n][m].getCar().getIsN())?'o':'-';
				returnString += carChar + "|";
			}
			returnString += newline;
		}
		
		returnString += margin + newline + newline;
		return returnString;
	}
	
	public boolean updateCarSlot (Car car) {
		if(car.getN() >= nSlots || car.getM() >= mSlots) {
			//System.out.println(car.getN()+ " " + nSlots + " " + car.getM()+ " " + mSlots);
			removeCar(car);
			cars.remove(car);
			return false;
		}
		else {
			cars.add(car);
			boolean success = slots[car.getN()][car.getM()].setCar(car);
			removeCar(car);
			return success;
		}
	}
	
	private void removeCar (Car car) {
		if(car.getSetupComplete()) {
			slots[car.getPrevN()][car.getPrevM()].setCar(null);
		}
	}
	
	public boolean carsLeft () {
		return !cars.isEmpty();
	}
}
