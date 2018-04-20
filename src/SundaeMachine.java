
public class SundaeMachine extends Machine {
	public static SundaeMachine sm = new SundaeMachine();
	
	private SundaeMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c, int count) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+2*count;
		c.setCurrentTime(availableTime);
	}
	
}
