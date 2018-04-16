
public class Diner extends Thread {
	public int entryTime;
	public Table table = null;
	private Cook cook;
	public int burger;
	public int fries;
	public int coke;
	public int sundae;
	public int name;
	public int currentTime =0;
	public int tableTime =0;
	public int burgerTime = -1;
	public int friesTime = -1;
	public int sodaTime = -1;
	public int sundaeTime = -1;
	public int cookTime;
	public Diner(int entryTime, int burger, int fries, int coke, int sundae, int name) {
		//System.out.println("in diner "+name+" "+Thread.currentThread().getName());
		//Thread.currentThread().setName("diner-"+name);
		this.entryTime = entryTime;
		this.burger = burger;
		this.fries = fries;
		this.coke = coke;
		this.sundae = sundae;
		this.name = name;
		this.setName("diner-"+name);
	}
	
	public void run() {
		table = TableManager.getInstance().getFreeTabel(this);
		cook = CookManager.getInstance().getFreeCook();
		cook.setCurrentTime(Math.max(cook.getCurrentTime(), currentTime));
		cookTime = cook.getCurrentTime();
		cook.startCooking(this);
		try {
			cook.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		table.availableTime = cook.getCurrentTime()+30;
		CookManager.getInstance().clearCook(cook);		
		TableManager.getInstance().clearTable(table);
		System.out.println();
		System.out.println(""+name+" "+entryTime+" "+tableTime+" "+cookTime+" "+burgerTime+" "+friesTime+" "+sodaTime+" "+sundaeTime);;
		
	}
	
	
}
