import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableThreads {
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
			
		//Lambda expression for market news
		Callable<Integer> mn = () -> {
			
			for(int i = 0; i<10; i++) {
				Thread.sleep(1000); //sleep for 1 second
				System.out.println("The market is improving" + i);
			}
			//Just return some number to illustrate return
			return 12345;
		};
		
		//lambda exppression for portfolio
		Callable<Integer> p = () ->{
			for(int i = 0; i<10; i++) {
			Thread.sleep(700);
			System.out.println("You have " + (500+i) + " shares of IBM");
			}
			//any number
			return 10;
		};
		
		
		//A placeholder for Future objects
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		
		//A placeholder for the results returned by threads
		List<Integer> results = new ArrayList<Integer>();
		
		final ExecutorService service = Executors.newFixedThreadPool(2);
		
		try {
			
			futures.add(service.submit(p));
			futures.add(service.submit(mn));
			
			for(Future<Integer> future : futures) {
				results.add(future.get());
			}
		}finally{
			service.shutdown();
		}
		
		for(Integer res : results) {
			System.out.println("\nGot the result: " + res);
		}
		
		
		
	}

}
