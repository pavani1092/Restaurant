
public class BurgerMachine extends Machine {
	public static BurgerMachine bm = new BurgerMachine();
	
	private BurgerMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+5;
		
		c.setCurrentTime(availableTime);
	}
	
}
