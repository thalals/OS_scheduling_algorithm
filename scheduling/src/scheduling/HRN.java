package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;


public class HRN{
	private int timeLapse=0;	// ���ð�(�� ���μ����� ���ư� �ð�)
	private double avResponse=0;//ù ����ð�. ��ȭ�� �ý��ۿ� �߿��ϴ�.
	private double avWait;//��� �ð�. ��� ����ð��� ���� �ð�.
	private double avReturn;//��ȯ �ð�.
	private int size;//ó�� ���μ����� ť ����� �˾ƾ� ��հ��� ����
	private boolean cpuUse=false;
	private int cpuCount;//cpu ���� �ð� ī��Ʈ
	
	
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //���� ����ť�� �������� ���� ���μ��� ������ ť
	
	
    void insert(ArrayList<Process> p) {
    	for(Process job : p) {
    		job.Wait_time=0;
    		job.Response_time=-1;
    		job.Return_time=0;
    		job.HRN_Priority = 0;		//�켱���� �ʱ�ȭ (�Էµ� �ʿ� ����)
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
    
    
    void start(Process p) {//��� �̺κ��� cpu�� �۵��Ǵ� �Լ� �κ�.
    	cpuCount=0;//��� �ð� üũ+cpu �� ���� �ð� Ȯ��
    	cpuUse=true;//QueueJob �κ� �ڵ� ���� �� ��(�Ƹ�)
    	readyQueue.remove(0);//cpu���� ����ϴ� ���μ����� ����ť���� ����
    	
        while(cpuCount!=p.Service_time) {//���� �ð��� ������ ������ ����
        	log(p);
        	cpuCount++;
       		timeLapse++;//1�� ����!
    		ReadyQueueAdd();//���ť�� ���ð� ���� 1�� ����
        	QueueJob();
       	}
        p.Return_time=timeLapse-p.Arrival_time;//�� ���μ��� ��ȯ �ð� ����
    	avReturn+=(double)p.Return_time;//��� ��ȯ �ð� ������ ���� ����
    	avResponse+=(double)p.Response_time;//��� ���� �ð� ������ ���� ����
    	avWait+=(double)p.Wait_time;//��� ��� �ð� ������ ���� ����
    	System.out.println(p.ID+" ���μ��� ���� �ð�: "+p.Response_time+
    			"/ ��� �ð�: "+p.Wait_time+
    			"/ ��ȯ �ð�: "+p.Return_time);
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
    	
    	//�켱���� ����
		for(Process a : readyQueue) {
    		a.HRN_Priority = (timeLapse-a.Arrival_time)+a.Service_time/a.Service_time;
    	}
		readyQueue.sort(Comparator.comparingDouble(j -> j.HRN_Priority));
        
    	if(cpuUse==false&&readyQueue.size()!=0) {
    		if(readyQueue.get(0).Response_time==-1)//�ش� ���μ����� cpu�� ó�� �Ҵ�޴���

    			readyQueue.get(0).Response_time=timeLapse-readyQueue.get(0).Arrival_time;
    			//���� ���� ����ð��� ����������
    		start(readyQueue.get(0));//start �Լ��� ������ ����ť ���� �Լ������� �����ϵ��� �Ѵ�.
    	}
    	else
    		QueueJob();
    }
    
    //���� ª�� ���񽺽ð��� ������ ���μ����� �ε����� ã���ִ� �Լ�
    int GetPriIndex() {
    	int minSize=0;
    	for(int i=1;i<readyQueue.size();i++) {
    		if(readyQueue.get(i).HRN_Priority<readyQueue.get(minSize).HRN_Priority)
    			minSize=i;
    	}
    	return minSize;
    }
    
    void QueueJob() {//����ť-�غ�ť ���� ���� �Լ�
    	if(Queue.size()==0&&readyQueue.size()==0&&cpuUse==false) {//�� ��������� ��հ� ��� �� ��������
    		System.out.println("��� ���� �ð�: "+(avResponse/size)+
    				"/ ��� ��� �ð� "+(avWait/size)+
    				"/ ��� ��ȯ �ð� "+(avReturn/size));
    		return;
    	}
    	if(Queue.size()!=0&&Queue.get(0).Arrival_time<=timeLapse) {//����ť�� ������ ���
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
