
public class SodaMachine extends Machine {
	public static SodaMachine cm = new SodaMachine();
	
	private SodaMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c, int count) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+2*count;
		c.setCurrentTime(availableTime);
	}
	
}
