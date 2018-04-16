
public class SodaMachine extends Machine {
	public static SodaMachine cm = new SodaMachine();
	
	private SodaMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+2;
		c.setCurrentTime(availableTime);
	}
	
}
