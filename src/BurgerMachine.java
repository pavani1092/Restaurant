
public class BurgerMachine extends Machine {
	public static BurgerMachine bm = new BurgerMachine();
	
	private BurgerMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c, int count) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+5*count;
		
		c.setCurrentTime(availableTime);
	}
	
}
