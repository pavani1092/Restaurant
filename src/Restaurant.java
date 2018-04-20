import java.util.PriorityQueue;
import java.util.Scanner;

public class Restaurant {
	private int numDiners;
	private int cooks;
	private int tables;
	private static int globalTime =0;
	private PriorityQueue<Diner> diners;
	public static Restaurant r = new Restaurant();
	public static Restaurant getInstance() {
		return r;
	}
	public static synchronized int checkNupdate(int t) {
		globalTime = Math.max(globalTime, t);
		return globalTime;
	}
	public static synchronized int updatedTime(int t) {
		globalTime += t;
		return globalTime;
	}
	public boolean isAtTop(Diner diner) {
		//System.out.println("top el "+diners.peek().getName());
		if(!diners.isEmpty() && diners.peek().getName().equals(diner.getName())) {
			return true;
		}
		return false;
	}
	
	public void removeDiner() {
		synchronized(diners) {
			diners.poll();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		r.numDiners = sc.nextInt();
		r.tables = sc.nextInt();
		r.cooks = sc.nextInt();
		r.diners = new PriorityQueue<Diner>((x,y)->(x.entryTime-y.entryTime));
		Diner[] diner = new Diner[r.numDiners];
		TableManager.getInstance().setTables(r.tables);
		CookManager.getInstance().setCooks(r.cooks);
		for( int i =0;i< r.numDiners;i++) {
			int t = sc.nextInt();
			int b = sc.nextInt();
			int f = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			Diner d = new Diner(t,b,f,c,s,i+1);
			synchronized(r.diners) {
				r.diners.add(d);
				diner[i] = d;
			}
		}
		System.out.println();
		//System.out.println("diner arrivalTime tableID tableTime cook cookTime burgerAccessAt friesAccessAt sodaAccessAt sundaeAccessAt foodReadyAt leavesAt");
		for(int i=0;i<r.numDiners;i++)
			diner[i].start();
		int last = 0;
		for(int i=0;i<r.numDiners;i++) {
			try {
				diner[i].join();
				last = Math.max(diner[i].currentTime, last);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0;i<r.numDiners;i++) {
			Diner d = diner[i];
			String op[] = new String[10];
			op[0] = "Diner:"+d.name;
			op[1] = "TableID:"+d.table.id;
			op[2] = "TableTime:"+d.tableTime;
			op[3] = "CookID:"+d.cook.name;
			op[4] = "CookTime:"+d.cookTime;
			op[5] = "BurgerTime:"+d.burgerTime;
			op[6] = "FriesTime:"+d.friesTime;
			op[7] = "SodaTime:"+d.sodaTime;
			op[8] = "SundaeTime:"+d.sundaeTime;
			op[9] = "FoodReadyTime:"+(d.currentTime-30);
			System.out.format("%10s%13s%15s%12s%14s%16s%15s%15s%16s%20s\n",(Object[])op);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		}
		
		System.out.println("Last Diner leaves at "+last);	
		
	}
}
