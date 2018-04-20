
public class Machine {
	public static int BURGER =1;
	public static int FRIES = 2;
	public static int SODA = 3;
	public static int SUNDAE =4;
	public int availableTime = 0;
	public static Object accessLock = new Object();
	public synchronized void AccessMachine(Cook c, int count) {
	}
	
	public synchronized int getTime() {
		return availableTime;
	}
	public static int getNextAvailable(boolean burger, boolean fries, boolean coke, boolean sundae) {
		synchronized(accessLock){
			int t = Integer.MAX_VALUE;
			int res = -1;
			if(burger) {
				int tm = BurgerMachine.bm.getTime() ;
				if(tm<t) {
					t = tm;
					res = BURGER;
				}	
			}
			if(fries) {
				int tm = FriesMachine.fm.getTime() ;
				if(tm<t) {
					t = tm;
					res = FRIES;
				}	
			}
			if(coke) {
				int tm = SodaMachine.cm.getTime() ;
				if(tm<t) {
					t = tm;
					res = SODA;
				}	
			}
			if(sundae) {
				int tm = SundaeMachine.sm.getTime() ;
				if(tm<t) {
					t = tm;
					res = SUNDAE;
				}	
			}
			
			return res;
		}
	}
}
