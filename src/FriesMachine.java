
public class FriesMachine extends Machine {
	public static FriesMachine fm = new FriesMachine();
	
	private FriesMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c, int count) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+3*count;
		c.setCurrentTime(availableTime);
	}
	
}
