import java.util.concurrent.ThreadLocalRandom;

public class Velocity implements Cloneable {
	
	private int nSlots;
	private int mSlots;
	private int speed;
	private int speedVariation;
	
	public int getN() {
		return nSlots;
	}

	public int getM() {
		return mSlots;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void randomizeSpeed () {
		speed = speed + ThreadLocalRandom.current().nextInt(0, speedVariation);
		System.out.println("Speed: " + speed);
	}

	public Velocity (int nSlots, int mSlots, int speed, int speedVariation) {
		this.nSlots = nSlots;
		this.mSlots = mSlots;
		this.speed = speed;
		this.speedVariation = speedVariation;
	}
	
	public Velocity (int nSlots, int mSlots, int speed) {
		this(nSlots, mSlots, speed, 400);
	}
	
	public Velocity (int nSlots, int mSlots) {
		this(nSlots, mSlots, 750);
	}
	
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
