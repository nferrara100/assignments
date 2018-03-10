/**
 * @author Nicholas Ferrara
 *
 */

import java.util.concurrent.locks.*;

public class Slot {
	
	private Car car;
	private Lock carLock = new ReentrantLock();
	private Condition carCondition = carLock.newCondition();
	
	Car getCar() {
		return car;
	}
	
	boolean setCar (Car car) {
		//System.out.println(car);
		if (this.car != null && car != null && car.getSetupComplete() == false) {
			//System.out.println("Setup Complete");
			return false;
		}
		else {
			try {
				//System.out.println("Locking " + car);
				carLock.lock();
				if (car != null) {
					while(this.car != null) {
						System.out.println("Not null " + car);
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
		}
		return true;
	}
}
