
public class Velocity {
	
	private int nSlots;
	private int mSlots;
	private int speed;
	
	public int getN() {
		return nSlots;
	}

	public int getM() {
		return mSlots;
	}

	public int getSpeed() {
		return speed;
	}

	public Velocity (int nSlots, int mSlots, int speed) {
		this.nSlots = nSlots;
		this.mSlots = mSlots;
		this.speed = speed;
	}
}
