import java.util.PriorityQueue;

public class TableManager {
	int numTables;
	PriorityQueue<Table> tables = new PriorityQueue<Table>((x,y)->x.availableTime-y.availableTime);
	int count = 0;
	private static TableManager tm = new TableManager();
	Object dummy = new Object();
	private TableManager() {
		
	}
	
	public static TableManager getInstance() {
		return tm;
	}
	public void setTables(int n) {
		this.numTables = n;
		for(int i=0;i<n;i++) {
			tables.add(new Table(i+1));
		}
	}
	
	
	
	
	public synchronized Table getFreeTabel(Diner d){
		while (d.table == null) {
			if (Restaurant.getInstance().isAtTop(d) && tables.size()>0) {
				Table t = tables.poll();
				d.currentTime =d.tableTime = Math.max(d.entryTime, t.availableTime);
				//System.out.println("assigned "+t.id+" to "+d.getName());
				Restaurant.getInstance().removeDiner();
				notifyAll();
				return t;
			}

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public synchronized void clearTable(Table t) {
		tables.add(t);
		notifyAll();
	}

	
}
