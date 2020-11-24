//cpu 관점에서 일을 하는 함수를 만들어야겠다.
package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrPrio {
	ArrayList<Process> pr;//다른 함수에도 이거 써야됨. 추가해봄
	 private int timeLapse=0;//총 시간 변수 추가로 시간 제어를 해보자.
	 private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	    private ExecutorService runningThread = Executors.newFixedThreadPool(2);
	 
	    void insert(ArrayList<Process> p) {
	    	pr=p;
	    	for(Process a : p)
	    		readyQueue.add(a);
	    	
	    	readyQueue.sort(Comparator.comparingInt(j -> j.Arrival_time));
	        //도착 시간 관점에서는 도착이 늦을수록 레디큐에 늦게 적재됨
	    	//레디큐와 cpu 모두 비어있는 잉여 시간 알고리즘 추가 구축 필요.
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
	    
	    void start() {//cpu 관점에서 생각해보자.
	        runningThread.execute(() -> {
	            while (readyQueue.size() != 0) {
	                for (Process job : readyQueue) {
	                    while (job.Service_time > 0) {
	                        log(job);
	                        //sleep(1000); 느려. 나중에 제거
	                        timeLapse++;//시간 경과도 제어를 해보자
	                        job.Service_time--;
	                        
	                        if (job.Service_time == 0) {//하나의 프로세스의 완전한 종료 발생
	                        	readyQueue.removeIf(j -> j.Service_time == 0);
	                        	for(Process a : pr) {//여기에 어레이 사용. 각 프로세스별로 크기 비교해야지!
	                        		if(a.Arrival_time<=timeLapse){}//일단 점유시간 동안 큐에 왔는지,
	                        		//? 이거는 cpu 관점에서 생각해볼 사항이 아닌데...
	                        	}
	                        }
	                            
	                    }
	                }
	            }
	            runningThread.shutdownNow();
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

