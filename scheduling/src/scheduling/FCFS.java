package scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//FCFSó�� -> ������ ������ ó��
public class FCFS {
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();	//��ȸ ���� ī�� arraylist ������, ����
    private ExecutorService runningThread = Executors.newSingleThreadExecutor(); //������ ���� , singleThread : �۾��� ������ ������ ó��
    
    //���μ��� ����Ʈ ����
    void insert(ArrayList<Process> p) {
    	for(Process job : p)
    		readyQueue.add(job);
    }
 
    void flowTime() {
        sleep(990); 
        // ������� ����ؼ� 10msec ������
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
                            readyQueue.removeIf(j -> j.Service_time == 0);	// �۾��� ������ ����
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

