public class Car extends Thread {
	
	private int nSlot;
	private int mSlot;
	private Grid grid;
	private Velocity velocity;
	
	
	public Car (Grid grid, Velocity velocity, int nSlot, int mSlot) {
		this.grid = grid;
		this.velocity = velocity;
		this.nSlot = nSlot;
		this.mSlot = mSlot;
	}
	
	public void run () {
		try {
			while(true) {
				if(!grid.updateCarSlot(this)){
					break;
				}
				
				Thread.sleep(velocity.getSpeed());
				nSlot += velocity.getN();
				mSlot += velocity.getM();
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
		return nSlot - velocity.getN();
	}
	
	public int getPrevM () {
		return mSlot - velocity.getM();
	}
	
	public Velocity getVelocity () {
		return velocity;
	}
}