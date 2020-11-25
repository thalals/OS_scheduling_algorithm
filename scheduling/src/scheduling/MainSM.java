//레디큐 하나로 아직 들어오지 않은 프로세스까지 관리할 수 있을까?
//큐를 두 개 만들어서 진행 시간에 맞추어서 레디큐에 차곡차곡 넣어야 하지 않을까 하는 생각

package scheduling;

import java.util.*;

class Process{
	String ID;		//프로세스 아이디
	int Arrival_time;	//도착시간
	int Service_time;	//서비스 시간
	int Priority_Number;	//우선순위(수가 낮을수록 높음)
	int Wait_time=0; //각 프로세스 별 대기 시간
	int Response_time=-1; //각 프로세스별 응답시간
	int Return_time=0; //각 프로세스별 반환시간
}

public class MainSM {
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		/*System.out.println("데이터 입력");
		System.out.print("프로세스 수 : ");
		//여기서 0나오면 메인에서 걸러야 함. 상정 안 하고 만듦
		int number = sc.nextInt();
		System.out.print("시간 할당량: ");
		int Time_qouta = sc.nextInt();*/
		
		ArrayList<Process> process_list = new ArrayList<Process>();//동적으로 할당되어지는 배열

		/*for(int i=0;i<number; i++) {
			Process process = new Process();

			System.out.print("프로세스ID : ");
			process.ID = sc.next();
			
			System.out.print("도착시간 : ");
			process.Arrival_time = sc.nextInt();
			
			System.out.print("서비스 시간 : ");
			process.Service_time = sc.nextInt();
			
			System.out.print("우선순위 : ");
			process.Priority_Number = sc.nextInt();
			
			process_list.add(process);
			System.out.println("-------------------------------------");

		} //느려. 나중에*/
		
		// 아래는 체크용 코드
		Process process = new Process();
		process.ID="hsm";
		process.Arrival_time=3;
		process.Service_time=4;
		process.Priority_Number=5;
		process_list.add(process);
		
		Process process1 = new Process();
		process1.ID="phm";
		process1.Arrival_time=4;
		process1.Service_time=5;
		process1.Priority_Number=6;
		process_list.add(process1);
		
		for(Process a : process_list) {
			System.out.println("id : "+ a.ID);
		}
		
		
		FCFS f = new FCFS();
		System.out.println("FCFS 실행");
		f.insert(process_list);
		
		SJF s = new SJF();
		System.out.println("\nSJF 실행");
		s.insert(process_list);
		
		NoPrio np = new NoPrio();
		System.out.println("\n비선점형 Priority 실행");
		np.insert(process_list);
	}
	
	
}
