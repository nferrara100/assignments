import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
	private int speed;
	private int nSlot;
	private int mSlot;
	private int prevNSlot;
	private int prevMSlot;
	private Grid grid;
	private boolean isN;
	
	
	public Car (Grid grid) {
		speed = ThreadLocalRandom.current().nextInt(5, 40);
		this.grid = grid;
		
		if(ThreadLocalRandom.current().nextInt(2) == 1) {
			nSlot = ThreadLocalRandom.current().nextInt(1, grid.nSlots);
			mSlot = 0;
			isN = true;
			grid.setCarSlot(this); //Need to prevent double slotting cars
		}
		else {
			mSlot = ThreadLocalRandom.current().nextInt(1, grid.mSlots);
			nSlot = 0;
			isN = false;
			grid.setCarSlot(this);
		}
	}
	
	//@Override
	public void run () {
		try {
			boolean running = true;
			while(running  && Thread.currentThread().isInterrupted()==false) {//temp
				//System.out.println("Car " + i);
				Thread.sleep(speed);
				if (isN) {
					prevMSlot = mSlot;
					mSlot++;
				}
				else {
					prevNSlot = nSlot;
					nSlot++;
				}
				//System.out.println(mSlot + " " + nSlot);
				if(!grid.setCarSlot(this)){
					running = false;
				}
			}
		}
		catch(InterruptedException ex) {
			System.out.println("Interupted");
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Car dead");
		}
	}
	
	public int getN () {
		return nSlot;
	}
	public int getM () {
		return mSlot;
	}
	public int getPrevN () {
		return prevNSlot;
	}
	public int getPrevM () {
		return prevMSlot;
	}
	public boolean getIsN () {
		return isN;
	}
}