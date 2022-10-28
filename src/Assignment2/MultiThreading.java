package Assignment2;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

class ImplementLock {
	Lock lock = new ReentrantLock();
	
	public void acquireLock(ArrayList<String> Query, Statement statement_transaction1, 
			String transaction_type){
		lock.lock();
		
		try {
			operation(Query, statement_transaction1, transaction_type);
		} catch (Exception e)
	    {
	           e.printStackTrace();
	    }
	}
	
	public void operation(ArrayList<String> Query, Statement statement_transaction1, String transaction_type) throws SQLException {
		for(int i = 0; i< Query.size() ;i++) {
			if(Query.get(i).contains("UPDATE")) {
				statement_transaction1.executeUpdate(Query.get(i));

			} else if (Query.get(i).contains("SELECT")) {
				statement_transaction1.executeQuery(Query.get(i));

			} 
			
			System.out.println("Query ran from :"+ transaction_type + "is: " + Query.get(i));
		}

	    releaseLock();
	}
	public void releaseLock(){
		lock.unlock();
	}
}

public class MultiThreading extends Thread {
	
ArrayList<String> Query =  new ArrayList<String>();
Statement statement_transaction1 = null;
String transaction_type;

ImplementLock implementlock = new ImplementLock();




public MultiThreading (ArrayList<String> Query, Statement statement_transaction1, String transaction_type) {
	this.Query = Query;
	this.statement_transaction1 = statement_transaction1;
	this.transaction_type = transaction_type;
	
}

@Override
public void run() {
	
	implementlock.acquireLock(Query, statement_transaction1, transaction_type);
	
}
}
