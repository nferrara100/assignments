import java.util.concurrent.ThreadLocalRandom;

public class Car extends Thread {
	
	private int speed;
	private int nSlot;
	private int mSlot;
	private Grid grid;
	private boolean isN;
	private boolean setupComplete = false;
	
	
	public Car (Grid grid) {
		speed = ThreadLocalRandom.current().nextInt(750, 1500);
		this.grid = grid;
		
		do {
			if(ThreadLocalRandom.current().nextInt(2) == 1) {
				nSlot = ThreadLocalRandom.current().nextInt(1, grid.nSlots);
				mSlot = 0;
				isN = true;
			}
			else {
				mSlot = ThreadLocalRandom.current().nextInt(1, grid.mSlots);
				nSlot = 0;
				isN = false;
			}
		} while (!grid.updateCarSlot(this));
		setupComplete = true;
	}
	
	public void run () {
		//System.out.println("running");
		try {
			boolean running = true;
			while(running) {
				//System.out.println("Car " + i);
				Thread.sleep(speed);
				if (isN) {
					mSlot++;
				}
				else {
					nSlot++;
				}
				//System.out.println(mSlot + " " + nSlot);
				if(!grid.updateCarSlot(this)){
					running = false;
				}
			}
		}
		catch(InterruptedException ex) {
			System.out.println("Interupted");
		}
	}
	
	public int getN () {
		return nSlot;
	}
	
	public int getM () {
		return mSlot;
	}
	
	public int getPrevN () {
		if(!isN) {
			return nSlot - 1;
		}
		else {
			return nSlot;
		}
	}
	
	public int getPrevM () {
		if(isN) {
			return mSlot - 1;
		}
		else {
			return mSlot;
		}
	}
	
	public boolean getIsN () {
		return isN;
	}
	
	public boolean getSetupComplete () {
		return setupComplete;
	}
}