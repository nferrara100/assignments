/**
 * 
 */

/**
 * @author nick
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Position {
	private Car car;
	public Lock lock = new ReentrantLock();
	
	Car getCar() {
		return car;
	}
}
