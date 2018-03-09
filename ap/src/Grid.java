import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Grid {
	private Car[][] carSlots;
	int nSlots;
	int mSlots;
	
    //private final Lock lock = new ReentrantLock();
    //private final Condition notFull = lock.newCondition();
	
	public Grid (int nSlots, int mSlots) {
		carSlots = new Car[nSlots][mSlots];
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
				char carChar = (carSlots[n][m] == null)?' ':(carSlots[n][m].getIsN())?'o':'-';
				returnString += carChar + "|";
			}
			returnString += newline;
		}
		
		returnString += margin + newline + newline;
		return returnString;
	}
	
	public boolean setCarSlot (Car car) {
		//System.out.println("n: " + car.getN() + " m: " + car.getM());
		//System.out.println(carSlots[car.getN()][car.getM()] + "String");
		//try{Thread.sleep(2);}catch(Exception Ex){}
		
		

	    //lock.lock();
		Lock lock = new ReentrantLock();
	    Condition notFull = lock.newCondition();
	    
	    try {
	    
	    	while (carSlots[car.getN()][car.getM()] != null) {
                notFull.await();
            }
	    	
	    	try {
		    	lock.lock();
		    	for(int m = 0; m < mSlots; m++) {
					for(int n = 0; n < nSlots; n++) {
						if (carSlots[n][m] == car) {
							carSlots[n][m] = null;
						}
					}
				}
				
				carSlots[car.getN()][car.getM()] = car;
				this.notifyAll();
	    	}
	    	finally {
	    		lock.unlock();
	    	}
	    }
	    catch (InterruptedException ex) {
	    	//lock.unlock();
	    	//setCarSlot(car);
	    	
	    }
	    return false;
	}
}
