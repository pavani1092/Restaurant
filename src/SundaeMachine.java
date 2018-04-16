
public class SundaeMachine extends Machine {
	public static SundaeMachine sm = new SundaeMachine();
	
	private SundaeMachine() {};
	
	@Override
	public synchronized void AccessMachine(Cook c) {
		availableTime = Math.max(availableTime, c.getCurrentTime())+2;
		c.setCurrentTime(availableTime);
	}
	
}
