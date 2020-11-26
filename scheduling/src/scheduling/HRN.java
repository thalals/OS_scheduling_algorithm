package scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;


public class HRN{
	private int timeLapse=0;	// 대기시간(총 프로세스가 돌아간 시간)
	private double avResponse=0;//첫 응답시간. 대화형 시스템에 중요하대.
	private double avWait;//대기 시간. 모든 응답시간을 합한 시간.
	private double avReturn;//반환 시간.
	private int size;//처음 프로세스의 큐 사이즈를 알아야 평균값을 구함
	private boolean cpuUse=false;
	private int cpuCount;//cpu 점유 시간 카운트
	
	
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //아직 레디큐에 도착하지 않은 프로세스 관리용 큐
	
	
    void insert(ArrayList<Process> p) {
    	for(Process job : p) {
    		job.Wait_time=0;
    		job.Response_time=-1;
    		job.Return_time=0;
    		job.HRN_Priority = 0;		//우선순위 초기화 (입력될 필요 없음)
    		Queue.add(job);
    	}
    	Queue.sort(Comparator.comparingInt(j -> j.Arrival_time));
    	//도착 시간 관점에서는 도착이 늦을수록 레디큐에 늦게 적재됨
    	size=Queue.size();
    	QueueJob();//첫 시작에 cpu를 보는 게 아니라 큐의 상태를 보자
    }
    void flowTime() {
    	sleep(990);
        // 오버헤드 고려해서 10msec 빠르게
    }
 
    void log(Process job) {
        System.out.print(timeLapse+"s : "+job.ID + " is running [");
        for(int i=0; i<readyQueue.size();i++)
        	System.out.print(readyQueue.get(i).ID+" ");
        System.out.println("]");
    }
    
    
    void start(Process p) {//사실 이부분이 cpu가 작동되는 함수 부분.
    	cpuCount=0;//대기 시간 체크+cpu 내 점유 시간 확인
    	cpuUse=true;//QueueJob 부분 코드 이제 안 씀(아마)
    	readyQueue.remove(0);//cpu에서 사용하는 프로세스는 레디큐에서 삭제
    	
        while(cpuCount!=p.Service_time) {//서비스 시간을 충족할 때까지 수행
        	log(p);
        	cpuCount++;
       		timeLapse++;//1초 딸깍!
    		ReadyQueueAdd();//대기큐의 대기시간 전부 1씩 증까
        	QueueJob();
       	}
        p.Return_time=timeLapse-p.Arrival_time;//각 프로세스 반환 시간 도출
    	avReturn+=(double)p.Return_time;//평균 반환 시간 도출을 위한 덧셈
    	avResponse+=(double)p.Response_time;//평균 응답 시간 도출을 위한 덧셈
    	avWait+=(double)p.Wait_time;//평균 대기 시간 도출을 위한 덧셈
    	System.out.println(p.ID+" 프로세스 응답 시간: "+p.Response_time+
    			"/ 대기 시간: "+p.Wait_time+
    			"/ 반환 시간: "+p.Return_time);
    	cpuUse=false;
       	
       	ReadyQueueChange();
        	
    }
    
    //프로세스 대기시간 추가
    void ReadyQueueAdd() {
    	for(Process p : readyQueue) {
    		p.Wait_time++;
    	}
    }

    //레디큐-cpu 사이 제어 함수
    void ReadyQueueChange() {
    	
    	//우선순위 정렬
		for(Process a : readyQueue) {
    		a.HRN_Priority = (timeLapse-a.Arrival_time)+a.Service_time/a.Service_time;
    	}
		readyQueue.sort(Comparator.comparingDouble(j -> j.HRN_Priority));
        
    	if(cpuUse==false&&readyQueue.size()!=0) {
    		if(readyQueue.get(0).Response_time==-1)//해당 프로세스가 cpu에 처음 할당받는지

    			readyQueue.get(0).Response_time=timeLapse-readyQueue.get(0).Arrival_time;
    			//고러면 최초 응답시간을 만들어줘야죠
    		start(readyQueue.get(0));//start 함수는 인접한 레디큐 제어 함수에서만 관리하도록 한다.
    	}
    	else
    		QueueJob();
    }
    
    //가장 짧은 서비스시간을 가지는 프로세스의 인덱스를 찾아주는 함수
    int GetPriIndex() {
    	int minSize=0;
    	for(int i=1;i<readyQueue.size();i++) {
    		if(readyQueue.get(i).HRN_Priority<readyQueue.get(minSize).HRN_Priority)
    			minSize=i;
    	}
    	return minSize;
    }
    
    void QueueJob() {//레디큐-준비큐 사이 제어 함수
    	if(Queue.size()==0&&readyQueue.size()==0&&cpuUse==false) {//다 비어있으면 평균값 출력 후 정상종료
    		System.out.println("평균 응답 시간: "+(avResponse/size)+
    				"/ 평균 대기 시간 "+(avWait/size)+
    				"/ 평균 반환 시간 "+(avReturn/size));
    		return;
    	}
    	if(Queue.size()!=0&&Queue.get(0).Arrival_time<=timeLapse) {//레디큐에 도착한 경우
    		readyQueue.add(Queue.get(0));
    		Queue.remove(0);
    		ReadyQueueChange();
    	}
    	else if(cpuUse==false){//레디큐에 오는 충분한 시간이 지나지 않았고 cpu가 사용중이지 않을 때. 완전 처음에만 적용
    		System.out.println(timeLapse+"s : "+"Nothing runs");
    		timeLapse++;
        	QueueJob();//레디큐에 처음 도착하는 프로세스가 있을 때까지 이를 반복
    	}
    	//cpu가 사용중인 상태에서 준비 큐에 접근하는 경우가 생길까? 생기면 여기에 코드 추가 바람
    }
    
    void sleep(int msec) {
        try {
            TimeUnit.MILLISECONDS.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
