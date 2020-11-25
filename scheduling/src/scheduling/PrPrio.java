package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

//���� �켱���� ó��
public class PrPrio {
	private int timeLapse=0;//�� �ð� ���� �߰��� �ð� ��� �غ���.
	private double avResponse=0;//ù ����ð�. ��ȭ�� �ý��ۿ� �߿��ϴ�.
	private double avWait=0;//��� �ð�. ��� ����ð��� ���� �ð�.
	private double avReturn=0;//��ȯ �ð�.
	private int size;//ó�� ���μ����� ť ����� �˾ƾ� ��հ��� ����
	private boolean cpuUse=false;
	private boolean newProcess=false;//���ο� ���μ����� ���ť�� ���Դ��� Ȯ��
	private int cpuCount;//cpu ���� �ð� ī��Ʈ
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();	//��ȸ ���� ī�� arraylist ������, ����
	//read ���Ҹ� �ϴ� readyQueue
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //���� ����ť�� �������� ���� ���μ��� ������ ť
    //���߿� ��Ʈ��Ʈ�� ����� �� ������?
    //���μ��� ����Ʈ ����
    void insert(ArrayList<Process> p) {
    	for(Process job : p) {
    		job.Wait_time=0;
    		job.Response_time=-1;
    		job.Return_time=0;
    		job.time_Remain=job.Service_time;
    		Queue.add(job);
    	}
    	Queue.sort(Comparator.comparingInt(j -> j.Arrival_time));
    	//���� �ð� ���������� ������ �������� ����ť�� �ʰ� �����
    	size=Queue.size();
    	QueueJob();//ù ���ۿ� cpu�� ���� �� �ƴ϶� ť�� ���¸� ����
    }
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
    
    void start(Process p, int index) {//��� �̺κ��� cpu�� �۵��Ǵ� �Լ� �κ�.
    	cpuCount=0;//��� �ð� üũ+cpu �� ���� �ð� Ȯ��
    	cpuUse=true;//QueueJob �κ� �ڵ� ���� �� ��(�Ƹ�)
    	readyQueue.remove(index);//cpu���� ����ϴ� ���μ����� ����ť���� ����
        //runningThread.execute(() -> {//�����带 �����ϰڴ�.-�ּ�ó��.
        	while(cpuCount!=p.time_Remain) {//���� �ð��� ������ ������ ����
        		log(p);
        		cpuCount++;
        		timeLapse++;//1�� ����!
        		ReadyQueueAdd();//���ť�� ���ð� ���� 1�� ����
        		QueueJob();//�غ�ť�� ���ť�� �� �غ� �Ǿ�����
        		if(newProcess==true) {
        			newProcess=false;
        			if(p.Priority_Number>readyQueue.get(readyQueue.size()-1).Priority_Number) {
        				break;
        			}
        		}
        	}
        	cpuUse=false;
        	if(cpuCount==p.time_Remain) {
            	p.Return_time=timeLapse-p.Arrival_time;//�� ���μ��� ��ȯ �ð� ����
            	avReturn+=(double)p.Return_time;//��� ��ȯ �ð� ������ ���� ����
            	avResponse+=(double)p.Response_time;//��� ���� �ð� ������ ���� ����
            	avWait+=(double)p.Wait_time;//��� ��� �ð� ������ ���� ����
            	System.out.println(p.ID+" ���μ��� ���� �ð�: "+p.Response_time+
            			"/ ��� �ð�: "+p.Wait_time+
            			"/ ��ȯ �ð�: "+p.Return_time);
        	}
        	else{
        		p.time_Remain=p.time_Remain-cpuCount;
				readyQueue.add(p);
        	}
        	ReadyQueueChange(GetPriIndex());
        	//runningThread.shutdown();������ �ּ� ó��
        //}); ������ �ּ� ó��
    }
    
    void ReadyQueueAdd() {
    	for(Process p : readyQueue) {
    		p.Wait_time++;
    	}
    }
    
    void ReadyQueueChange(int index) {//����ť-cpu ���� ���� �Լ�
    	if(cpuUse==false&&readyQueue.size()!=0) {
    		if(readyQueue.get(index).Response_time==-1)//�ش� ���μ����� cpu�� ó�� �Ҵ�޴���
    			readyQueue.get(index).Response_time=timeLapse-readyQueue.get(index).Arrival_time;
    			//���� ���� ����ð��� ����������
    		start(readyQueue.get(index),index);//start �Լ��� ������ ����ť ���� �Լ������� �����ϵ��� �Ѵ�.
    	}
    	else
    		QueueJob();
    }
    
    int GetPriIndex() {//���� ª�� �켱������ ������ ���μ����� �ε����� ã���ִ� �Լ�
    	int minSize=0;
    	for(int i=1;i<readyQueue.size();i++) {
    		if(readyQueue.get(i).Priority_Number<readyQueue.get(minSize).Priority_Number)
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
    		newProcess=true;
    		Queue.remove(0);
    		ReadyQueueChange(GetPriIndex());
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

