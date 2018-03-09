import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

public class Grid {
	private Position[][] slots;
	int nSlots;
	int mSlots;
	Set<Car> cars = new HashSet<Car>();
	
	public Grid (int nSlots, int mSlots) {
		slots = new Position[nSlots][mSlots];
    	for(int m = 0; m < mSlots; m++) {
			for(int n = 0; n < nSlots; n++) {
				slots[n][m] = new Position();
			}
		}
		this.nSlots = nSlots;
		this.mSlots = mSlots;
	}
	
	public String print () {
		String newline = System.getProperty("line.separator");
		String margin = "";
		for(int i = 0; i < nSlots; i++) {
			margin += "----";
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
	
	public boolean setCarSlot (Car car) {
		if(car.getN() >= nSlots || car.getM() >= mSlots) {
			removeCar(car);
			return false;
		}
		else {
			Position slot = slots[car.getN()][car.getM()];
			slot.lock.lock();
			removeCar(car);
			slot.setCar(car);
			cars.add(car);
			return true;
		}
	}
	
	private void removeCar (Car car) {
    	for(int m = 0; m < mSlots; m++) {
			for(int n = 0; n < nSlots; n++) {
				if (slots[n][m].getCar() == car) {
					slots[n][m].setCar(null);
					slots[n][m].lock = new ReentrantLock();
					cars.remove(car);
				}
			}
		}
	}
	
	public boolean carsLeft () {
		return !cars.isEmpty();
	}
}
