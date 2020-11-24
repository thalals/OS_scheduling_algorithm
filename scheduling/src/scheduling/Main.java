package scheduling;

import java.util.*;

class Process{
	String ID;		//프로세스 아이디
	int Arrival_time;	//도착시간
	int Service_time;	//서비스 시간
	int Priority_Number;	//우선순위
	int Time_quota; 		//시간 할당량 ( 타임 슬라이스 )
}

public class Main {
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("데이터 입력");
		System.out.print("프로세스 수 : ");
		int number = sc.nextInt();
		
		ArrayList<Process> process_list = new ArrayList<Process>();

		for(int i=0;i<number; i++) {
			Process process = new Process();

			System.out.print("프로세스ID : ");
			process.ID = sc.next();
			
			System.out.print("도착시간 : ");
			process.Arrival_time = sc.nextInt();
			
			System.out.print("서비스 시간 : ");
			process.Service_time = sc.nextInt();
			
			System.out.print("우선순위 : ");
			process.Priority_Number = sc.nextInt();
			
			System.out.print("시간 할당량: ");
			process.Time_quota = sc.nextInt();
			
			process_list.add(process);
			System.out.println("-------------------------------------");

		}
		// 체크
		for(Process a : process_list) {
			System.out.println("id : "+ a.ID);
		}
		
		
		FCFS f = new FCFS();
		System.out.println("FCFS 실행");
		f.insert(process_list);
		f.start();
		
		SJF s = new SJF();
		System.out.println("\nSJF 실행");
		s.insert(process_list);
		s.start();
		
	}
	
	
}
