
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
	}

	public void startCooking(Diner diner) {
		this.diner = diner;
		
		while(checkOrders()) {
			if(currentTime>600) {
				int x = 1;
				x+=2;
			}
			int machine = Machine.getNextAvailable(needBurger, needFries,needCoke,needSundae);
			switch(machine) {
				case 1:
					BurgerMachine.bm.AccessMachine(this,diner.burger);
					diner.burgerTime = getCurrentTime() - diner.burger*5;
					diner.burger = 0;
					break;
				case 2:
					FriesMachine.fm.AccessMachine(this,diner.fries);
					diner.friesTime = getCurrentTime() - diner.fries*3 ;
					diner.fries =0;
					break;
				case 3:
					SodaMachine.cm.AccessMachine(this,diner.coke);
					diner.sodaTime = getCurrentTime()- 2 ;
					diner.coke = 0;
					break;
				case 4:
					SundaeMachine.sm.AccessMachine(this,diner.sundae);
					diner.sundaeTime = getCurrentTime()- 1;
					diner.sundae = 0;
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
