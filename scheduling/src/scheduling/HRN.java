package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class HRN {
	private int timeLapse=0;	// 대기시간(총 프로세스가 돌아간 시간)
	private double avWait;//대기 시간. 모든 응답시간을 합한 시간.
	private double avReturn;//반환 시간.
	private boolean cpuUse=false;
	private int cpuCount;//cpu 점유 시간 카운트
	
	private List<Process> readyQueue = new CopyOnWriteArrayList<>();
	private List<Process> Queue = new CopyOnWriteArrayList<>(); //아직 레디큐에 도착하지 않은 프로세스 관리용 큐

	private ExecutorService runningThread = Executors.newSingleThreadExecutor();
 
	//모든 프로세스
    void insert(ArrayList<Process> p) {
    	for(Process a : p) {
    		a.Priority_Number = 0;		//우선순위 초기화 (입력될 필요 없음)
    		Queue.add(a);
    	}    	

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
    
    void start(Process p) {
    	cpuCount=0;//대기 시간 체크+cpu 내 점유 시간 확인
    	cpuUse=true;//QueueJob 부분 코드 이제 안 씀(아마)
    	readyQueue.remove(0);//cpu에서 사용하는 프로세스는 레디큐에서 삭제
    	
//        runningThread.execute(() -> {
            while (readyQueue.size() != 0) {
                for (Process job : readyQueue) {
                	while(job.Service_time > 0) {
                		timeLapse++;	// 대기시간(총 Cpu 돌아가는 시간)
                		log(job);
                		sleep(1000);
                		job.Service_time--;
                	}            	
                	if (job.Service_time == 0)
                		readyQueue.removeIf(j -> j.Service_time == 0);
                	
                	//우선순위 계산
                	for(Process a : readyQueue) {
                		a.Priority_Number = (timeLapse-a.Arrival_time)+a.Service_time/a.Service_time;
                	}
                	
                	//우선순위 정렬 내림차순
                    readyQueue.sort(Comparator.comparingDouble(j -> j.Priority_Number));
                    Collections.reverse(readyQueue);
                }
            }
 
//            runningThread.shutdownNow();
            //System.exit(0);
//        });
    }
    
    void ReadyQueueChange() {//레디큐-cpu 사이 제어 함수
    	if(cpuUse==false&&readyQueue.size()!=0)
    		start(readyQueue.get(0));//start 함수는 인접한 레디큐 제어 함수에서만 관리하도록 한다.
    	else
    		QueueJob();
    }
    
    void QueueJob() {//레디큐-준비큐 사이 제어 함수
    	if(Queue.size()==0&&readyQueue.size()==0&&cpuUse==false) {
    		System.exit(0);//다 비어있으면 종료
    	}
    	if(Queue.size()!=0&&Queue.get(0).Arrival_time<=timeLapse) {//레디큐에 도착한 경우
    		
    		/*Queue.get(0).Response_time=timeLapse-Queue.get(0).Arrival_time;//현재 시간을 응답시간으로 포함
    		avResponse+=Queue.get(0).Response_time;//평균 응답 시간 도출을 위한 덧셈
    		Queue.get(0).Wait_time+=Queue.get(0).Response_time;//대기 시간에는 응답시간이 포함되어 있음
    		위 세 줄은 어디에 넣어야 할지를 모르겠네. 연구가 필요해보인다.*/

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
