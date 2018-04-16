
public class Cook extends Thread{
	public int name;
	private int currentTime =0;
	public boolean needBurger = false;
	public boolean needFries = false;
	public boolean needCoke = false;
	public boolean needSundae = false;
	private Diner diner;
	public Cook(int i) {
		//System.out.println("in cook "+i+" "+Thread.currentThread().getName());
		this.name = i;
		this.setName("cook-"+i);
	}

	public void startCooking(Diner diner) {
		this.diner = diner;
		
		while(checkOrders()) {
			int machine = Machine.getNextAvailable(needBurger, needFries,needCoke,needSundae);
			switch(machine) {
				case 1:
					diner.burger--;
					BurgerMachine.bm.AccessMachine(this);
					diner.burgerTime = getCurrentTime();
					break;
				case 2:
					diner.fries--;
					FriesMachine.fm.AccessMachine(this);
					diner.friesTime = getCurrentTime();
					break;
				case 3:
					diner.coke--;
					SodaMachine.cm.AccessMachine(this);
					diner.sodaTime = getCurrentTime();
					break;
				case 4:
					diner.sundae--;
					SundaeMachine.sm.AccessMachine(this);
					diner.sundaeTime = getCurrentTime();
					break;
				default: break;	
			}
		}
		
		//System.out.println("diner "+diner.name+" cook finished");
		
	}

	public boolean checkOrders() {
		needBurger = needFries = needCoke = needSundae = false;
		if(diner.burger>0)
			needBurger = true;
		if(diner.fries>0)
			needFries = true;
		if(diner.coke>0)
			needCoke = true;
		if(diner.sundae>0)
			needSundae = true;
		return needBurger || needFries || needCoke ||needSundae;
	}
	public synchronized int getCurrentTime() {
		return currentTime;
	}

	public synchronized void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

}
