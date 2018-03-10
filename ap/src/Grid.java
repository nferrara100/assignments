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
				char carChar = (slots[n][m].getCar() == null)?' ':(slots[n][m].getCar().getVelocity().getM() != 0)?'o':'-';
				returnString += carChar + "|";
			}
			returnString += newline;
		}
		
		returnString += margin + newline + newline;
		return returnString;
	}
	
	public boolean updateCarSlot (Car car) {
		boolean returnVal;
		
		if(!withinBounds(car.getN(), car.getM())) {
			cars.remove(car);
			returnVal = false;
		}
		else {
			cars.add(car);
			returnVal = slots[car.getN()][car.getM()].setCar(car);
		}
		
		if(withinBounds(car.getPrevN(), car.getPrevM())) {
			//System.out.println(car.getPrevN() + " " + car.getPrevM());
			slots[car.getPrevN()][car.getPrevM()].setCar(null);
		}
		
		return returnVal;
	}
	
	public boolean withinBounds (int n, int m) {
		return n < nSlots && n >= 0 && m < mSlots && m >= 0;
	}
	
	public boolean carsLeft () {
		return !cars.isEmpty();
	}
	
	public int getNSlots () {
		return nSlots;
	}
	
	public int getMSlots () {
		return mSlots;
	}
	
	public boolean reserve (int nSlot, int mSlot) {
		return slots[nSlot][mSlot].reserve();
	}
}
