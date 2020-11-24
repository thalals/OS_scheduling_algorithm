package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SJF {
	 private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	    private ExecutorService runningThread = Executors.newSingleThreadExecutor();
	 
	    void insert(ArrayList<Process> p) {
	    	for(Process a : p)
	    		readyQueue.add(a);
	    	
	        readyQueue.sort(Comparator.comparingInt(j -> j.Service_time));
	        // 가장 짧은 작업시간을 가진 프로세스를 앞으로 보내서 먼저 실행하게 함.
	        // 그러나 현재 작업중인 프로세스를 밀어내지는 못함 (비선점 스케줄링)   
	    }
	 
	    void flowTime() {
	    	sleep(990);
	        // 오버헤드 고려해서 10msec 빠르게
	    }
	 
	    void log(Process job) {
	        System.out.print(job.ID + " is running [");
	        for(int i=0; i<readyQueue.size();i++)
	        	System.out.print(readyQueue.get(i).ID+" ");
	        System.out.println("]");
	    }
	    
	    void start() {
	        runningThread.execute(() -> {
	            while (readyQueue.size() != 0) {
	                for (Process job : readyQueue) {
	                    while (job.Service_time > 0) {
	                        log(job);
	                        sleep(1000);
	                        job.Service_time--;
	 
	                        if (job.Service_time == 0)
	                            readyQueue.removeIf(j -> j.Service_time == 0);
	                    }
	                }
	            }
	 
	            runningThread.shutdownNow();
	            System.exit(0);
	        });
	    }
	    
	    void sleep(int msec) {
	        try {
	            TimeUnit.MILLISECONDS.sleep(msec);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}

