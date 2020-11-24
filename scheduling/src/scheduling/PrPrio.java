//cpu �������� ���� �ϴ� �Լ��� �����߰ڴ�.
package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrPrio {
	ArrayList<Process> pr;//�ٸ� �Լ����� �̰� ��ߵ�. �߰��غ�
	 private int timeLapse=0;//�� �ð� ���� �߰��� �ð� ��� �غ���.
	 private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	    private ExecutorService runningThread = Executors.newFixedThreadPool(2);
	 
	    void insert(ArrayList<Process> p) {
	    	pr=p;
	    	for(Process a : p)
	    		readyQueue.add(a);
	    	
	    	readyQueue.sort(Comparator.comparingInt(j -> j.Arrival_time));
	        //���� �ð� ���������� ������ �������� ����ť�� �ʰ� �����
	    	//����ť�� cpu ��� ����ִ� �׿� �ð� �˰��� �߰� ���� �ʿ�.
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
	    
	    void start() {//cpu �������� �����غ���.
	        runningThread.execute(() -> {
	            while (readyQueue.size() != 0) {
	                for (Process job : readyQueue) {
	                    while (job.Service_time > 0) {
	                        log(job);
	                        //sleep(1000); ����. ���߿� ����
	                        timeLapse++;//�ð� ����� ��� �غ���
	                        job.Service_time--;
	                        
	                        if (job.Service_time == 0) {//�ϳ��� ���μ����� ������ ���� �߻�
	                        	readyQueue.removeIf(j -> j.Service_time == 0);
	                        	for(Process a : pr) {//���⿡ ��� ���. �� ���μ������� ũ�� ���ؾ���!
	                        		if(a.Arrival_time<=timeLapse){}//�ϴ� �����ð� ���� ť�� �Դ���,
	                        		//? �̰Ŵ� cpu �������� �����غ� ������ �ƴѵ�...
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

