import java.util.PriorityQueue;

public class CookManager {
	int numCooks;
	int count = 0;
	PriorityQueue<Cook> cooks = new PriorityQueue<Cook>((x,y)->x.getCurrentTime()-y.getCurrentTime());
	private static CookManager cm = new CookManager();
	private CookManager() {
		
	}
	
	public static CookManager getInstance() {
		return cm;
	}
	public void setCooks(int n) {
		this.numCooks = n;
		for(int i =0;i<n;i++) {
			cooks.add(new Cook(i+1));
		}
	}
	
	public synchronized Cook getFreeCook(){
		while(cooks.size() ==0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
		return cooks.poll();
	}
	
	public synchronized void clearCook(Cook cook) {
		cooks.add(cook);
		notifyAll();
	}

	
}
