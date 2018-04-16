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
		System.out.println(""+"name"+" "+"entryTime"+" "+"tableTime"+" "+"cookTime"+" "+"burgerTime"+" "+"friesTime"+" "+"sodaTime"+" "+"sundaeTime");;
		for(int i=0;i<r.numDiners;i++)
			diner[i].start();
		
	}
}
