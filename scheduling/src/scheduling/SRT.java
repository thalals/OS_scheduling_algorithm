package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class SRT {
	private int timeLapse=0;	// ���ð�(�� ���μ����� ���ư� �ð�)
	private double avResponse=0;//ù ����ð�. ��ȭ�� �ý��ۿ� �߿��ϴ�.
	private double avWait;//��� �ð�. ��� ����ð��� ���� �ð�.
	private double avReturn;//��ȯ �ð�.
	private int size;//ó�� ���μ����� ť ����� �˾ƾ� ��հ��� ����
	private boolean cpuUse=false;
	private int cpuCount;//cpu ���� �ð� ī��Ʈ
	private int timeslice=0;
	
	
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //���� ����ť�� �������� ���� ���μ��� ������ ť
	
	
	//insert
    void insert(ArrayList<Process> p, int timeslice) {
    	this.timeslice = timeslice;
    	
    	for(Process job : p) {
    		job.Priority_Number = 0;		//�켱���� �ʱ�ȭ (�Էµ� �ʿ� ����)
    		job.time_Remain = job.Service_time;	// ������ ���� �����ð� ����ϱ� ����
    		Queue.add(job);
    	}
    	
    	Queue.sort(Comparator.comparingInt(j -> j.Arrival_time));
    	//���� �ð� ���������� ������ �������� ����ť�� �ʰ� �����
    	size=Queue.size();
    	QueueJob();//ù ���ۿ� cpu�� ���� �� �ƴ϶� ť�� ���¸� ����
    }
    void flowTime() {
    	sleep(990);
        // ������� ����ؼ� 10msec ������
    }
 
    void log(Process job) {
        System.out.print(timeLapse+"s : "+job.ID + " is running [");
        for(int i=0; i<readyQueue.size();i++)
        	System.out.print(readyQueue.get(i).ID+" ");
        System.out.println("]");
    }
    
    //����
    void start(Process p) {//��� �̺κ��� cpu�� �۵��Ǵ� �Լ� �κ�.
    	cpuCount=0;//��� �ð� üũ+cpu �� ���� �ð� Ȯ��
    	cpuUse=true;//QueueJob �κ� �ڵ� ���� �� ��(�Ƹ�)
        	
        while(p.time_Remain!=0 && cpuCount!=timeslice) {		//Ÿ�� ������ ��ŭ, ���� �ð� ��ŭ
        	log(p);
        	cpuCount++;
       		timeLapse++;//1�� ����!
    		ReadyQueueAdd();//���ť�� ���ð� ���� 1�� ����
    		p.time_Remain--;
        	QueueJob();
       	}
        
        if(p.time_Remain==0) {
        	readyQueue.remove(0);
	        p.Return_time=timeLapse-p.Arrival_time;//�� ���μ��� ��ȯ �ð� ����
	    	avReturn+=(double)p.Return_time;//��� ��ȯ �ð� ������ ���� ����
	    	avResponse+=(double)p.Response_time;//��� ���� �ð� ������ ���� ����
	    	avWait+=(double)p.Wait_time;//��� ��� �ð� ������ ���� ����
	    	
	    	System.out.println(p.ID+" ���μ��� ���� �ð�: "+p.Response_time+
	    			"/ ��� �ð�: "+p.Wait_time+
	    			"/ ��ȯ �ð�: "+p.Return_time);
	        
        }
    	cpuUse=false;
       	ReadyQueueChange();
        	
    }
    
    //���μ��� ���ð� �߰�
    void ReadyQueueAdd() {
    	for(Process p : readyQueue) {
    		p.Wait_time++;
    	}
    }
    
    
    //����ť-cpu ���� ���� �Լ�
    void ReadyQueueChange() {
        
    	if(cpuUse==false&&readyQueue.size()!=0) {
    		//�ش� ���μ����� cpu�� ó�� �Ҵ�޴���
    		if(readyQueue.get(0).Response_time==-1){
    			readyQueue.get(0).Response_time=timeLapse-readyQueue.get(0).Arrival_time;	//���� ���� ����ð��� ����������
    		}
    		//ó�� �Ҵ� �޴°� �ƴ� ��
    		else {
    			readyQueue.sort(Comparator.comparingInt(time -> time.time_Remain));
    		}
    		
    		start(readyQueue.get(0));//start �Լ��� ������ ����ť ���� �Լ������� �����ϵ��� �Ѵ�.		
    		
    	}
    	
    	else
    		QueueJob();
    }
  
    
    void QueueJob() {//����ť-�غ�ť ���� ���� �Լ�
    	if(Queue.size()==0&&readyQueue.size()==0&&cpuUse==false) {//�� ��������� ��հ� ��� �� ��������
    		System.out.println("��� ���� �ð�: "+(avResponse/size)+
    				"/ ��� ��� �ð� "+(avWait/size)+
    				"/ ��� ��ȯ �ð� "+(avReturn/size));
    		return;
    	}
    	
    	//�� ���μ����� ����ť�� ������ ���
    	if(Queue.size()!=0&&Queue.get(0).Arrival_time<=timeLapse) {
    		readyQueue.add(Queue.get(0));
    		Queue.remove(0);
    		ReadyQueueChange();
    	}
    	else if(cpuUse==false){//����ť�� ���� ����� �ð��� ������ �ʾҰ� cpu�� ��������� ���� ��. ���� ó������ ����
    		System.out.println(timeLapse+"s : "+"Nothing runs");
    		timeLapse++;
        	QueueJob();//����ť�� ó�� �����ϴ� ���μ����� ���� ������ �̸� �ݺ�
    	}
    	//cpu�� ������� ���¿��� �غ� ť�� �����ϴ� ��찡 �����? ����� ���⿡ �ڵ� �߰� �ٶ�
    }
    
    void sleep(int msec) {
        try {
            TimeUnit.MILLISECONDS.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
