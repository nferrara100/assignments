/**
 * @author Nicholas Ferrara
 *
 */

import java.util.concurrent.locks.*;

public class Slot {
	
	private Car car;
	private Lock carLock = new ReentrantLock();
	private Condition carCondition = carLock.newCondition();
	private boolean reserved = false;
	
	Car getCar() {
		return car;
	}
	
	boolean setCar (Car car) {
		try {
			//System.out.println("Locking " + car);
			carLock.lock();
			if (car != null) {
				while(this.car != null) {
					//System.out.println("Not null " + car);
					carCondition.await();
				}
			}
			//System.out.println("Awake " + car);
			this.car = car;
			carCondition.signalAll();
		}
		catch(InterruptedException ex){
			ex.printStackTrace();
		}finally {
			//System.out.println("Unlocking " + car);
			carLock.unlock();
		}
		return true;
	}
	
	boolean reserve () {
		if (reserved == false && car == null) {
			reserved = true;
			return true;
		}
		return false;
	}
}
