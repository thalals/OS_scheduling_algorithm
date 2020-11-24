package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//FCFSó�� -> ������ ������ ó��
public class FCFS {
	private int timeLapse=0;//�� �ð� ���� �߰��� �ð� ��� �غ���.
	private double avResponse;//ù ����ð�. ��ȭ�� �ý��ۿ� �߿��ϴ�.
	private double avWait;//��� �ð�. ��� ����ð��� ���� �ð�.
	private double avReturn;//��ȯ �ð�.
	private boolean cpuUse=false;
	private int cpuCount;//cpu ���� �ð� ī��Ʈ
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();	//��ȸ ���� ī�� arraylist ������, ����
	//read ���Ҹ� �ϴ� readyQueue
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //���� ����ť�� �������� ���� ���μ��� ������ ť
    private ExecutorService runningThread = Executors.newSingleThreadExecutor(); //������ ���� , singleThread : �۾��� ������ ������ ó��
    //���߿� ��Ʈ��Ʈ�� ����� �� ������?
    //���μ��� ����Ʈ ����
    void insert(ArrayList<Process> p) {
    	for(Process job : p)
    		Queue.add(job);
    	Queue.sort(Comparator.comparingInt(j -> j.Arrival_time));
    	QueueJob();//ù ���ۿ� cpu�� ���� �� �ƴ϶� ť�� ���¸� ����
    }
    //���� �ð� ���������� ������ �������� ����ť�� �ʰ� �����
	//����ť�� cpu ��� ����ִ� �׿� �ð��� ��� ������? �˰��� �߰� ���� �ʿ�.-����ť�� �ذ�
 
    void flowTime() {//�������� �̰Ŵ�
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
        //runningThread.execute(() -> {//�����带 �����ϰڴ�.-�ּ�ó��.
        	while(cpuCount!=p.Service_time) {//���� �ð��� ������ ������ ����
        		log(p);
        		cpuCount++;
        		timeLapse++;//1�� ����!
        		QueueJob();
        	}
        	p.Return_time=timeLapse-p.Arrival_time;//�� ���μ��� ��ȯ �ð� ����
        	avReturn+=(double)p.Return_time;//��� ��ȯ �ð� ������ ���� ����
        	cpuUse=false;
        	ReadyQueueChange();
        	//runningThread.shutdown();������ �ּ� ó��
        //}); ������ �ּ� ó��
    }
    
    void ReadyQueueChange() {//����ť-cpu ���� ���� �Լ�
    	if(cpuUse==false&&readyQueue.size()!=0)
    		start(readyQueue.get(0));//start �Լ��� ������ ����ť ���� �Լ������� �����ϵ��� �Ѵ�.
    	else
    		QueueJob();
    }
    
    void QueueJob() {//����ť-�غ�ť ���� ���� �Լ�
    	if(Queue.size()==0&&readyQueue.size()==0&&cpuUse==false) {
    		System.exit(0);//�� ��������� ����
    	}
    	if(Queue.size()!=0&&Queue.get(0).Arrival_time<=timeLapse) {//����ť�� ������ ���
    		
    		/*Queue.get(0).Response_time=timeLapse-Queue.get(0).Arrival_time;//���� �ð��� ����ð����� ����
    		avResponse+=Queue.get(0).Response_time;//��� ���� �ð� ������ ���� ����
    		Queue.get(0).Wait_time+=Queue.get(0).Response_time;//��� �ð����� ����ð��� ���ԵǾ� ����
    		�� �� ���� ��� �־�� ������ �𸣰ڳ�. ������ �ʿ��غ��δ�.*/

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

