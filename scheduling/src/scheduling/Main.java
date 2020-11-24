package scheduling;

import java.util.*;

class Process{
	String ID;		//���μ��� ���̵�
	int Arrival_time;	//�����ð�
	int Service_time;	//���� �ð�
	int Priority_Number;	//�켱����
	int Time_quota; 		//�ð� �Ҵ緮 ( Ÿ�� �����̽� )
}

public class Main {
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ �Է�");
		System.out.print("���μ��� �� : ");
		int number = sc.nextInt();
		
		ArrayList<Process> process_list = new ArrayList<Process>();

		for(int i=0;i<number; i++) {
			Process process = new Process();

			System.out.print("���μ���ID : ");
			process.ID = sc.next();
			
			System.out.print("�����ð� : ");
			process.Arrival_time = sc.nextInt();
			
			System.out.print("���� �ð� : ");
			process.Service_time = sc.nextInt();
			
			System.out.print("�켱���� : ");
			process.Priority_Number = sc.nextInt();
			
			System.out.print("�ð� �Ҵ緮: ");
			process.Time_quota = sc.nextInt();
			
			process_list.add(process);
			System.out.println("-------------------------------------");

		}
		// üũ
		for(Process a : process_list) {
			System.out.println("id : "+ a.ID);
		}
		
		
		FCFS f = new FCFS();
		System.out.println("FCFS ����");
		f.insert(process_list);
		f.start();
		
		SJF s = new SJF();
		System.out.println("\nSJF ����");
		s.insert(process_list);
		s.start();
		
	}
	
	
}
