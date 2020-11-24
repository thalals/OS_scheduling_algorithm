package scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//FCFS처리 -> 비선점형 순차적 처리
public class FCFS {
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();	//순회 전용 카피 arraylist 안전함, 빠름
    private ExecutorService runningThread = Executors.newSingleThreadExecutor(); //스레드 생성 , singleThread : 작업을 예약한 순으로 처리
    
    //프로세스 리스트 삽입
    void insert(ArrayList<Process> p) {
    	for(Process job : p)
    		readyQueue.add(job);
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
                            readyQueue.removeIf(j -> j.Service_time == 0);	// 작업이 끝나면 삭제
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

