
public class FriesMachine extends Machine {
	public static FriesMachine fm = new FriesMachine();
	
	private FriesMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+3;
		c.setCurrentTime(availableTime);
	}
	
}
