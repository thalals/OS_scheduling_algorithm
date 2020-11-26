//����ť �ϳ��� ���� ������ ���� ���μ������� ������ �� ������?
//ť�� �� �� ���� ���� �ð��� ���߾ ����ť�� �������� �־�� ���� ������ �ϴ� ����

package scheduling;

import java.util.*;

class Process{
	String ID;		//���μ��� ���̵�
	int Arrival_time;	//�����ð�
	int Service_time;	//���� �ð�
	double Priority_Number;	//�켱����(���� �������� ����)
	double HRN_Priority;//hrn �����ٸ� ����. �켱���� ������
	int Wait_time=0; //�� ���μ��� �� ��� �ð�
	int Response_time=-1; //�� ���μ����� ����ð�
	int Return_time=0; //�� ���μ����� ��ȯ�ð�
	int time_Remain=0;//������ ����. ���� �ð� ������
}

public class Main {
	
	public static void main(String[] args) {
		int Time_quota; //�ð� �Ҵ緮 ( Ÿ�� �����̽� ). ���μ��� ���� Ÿ�� �����̽� ������ �ʿ��ұ�?

		Scanner sc = new Scanner(System.in);
		
//		System.out.println("������ �Է�");
//		System.out.print("���μ��� �� : ");
//		//���⼭ 0������ ���ο��� �ɷ��� ��. ���� �� �ϰ� ����
//		int number = sc.nextInt();
		
		ArrayList<Process> process_list = new ArrayList<Process>();//�������� �Ҵ�Ǿ����� �迭

//		for(int i=0;i<number; i++) {
//			Process process = new Process();
//
//			System.out.print("���μ���ID : ");
//			process.ID = sc.next();
//			
//			System.out.print("�����ð� : ");
//			process.Arrival_time = sc.nextInt();
//			
//			System.out.print("���� �ð� : ");
//			process.Service_time = sc.nextInt();
//			
//			System.out.print("�켱���� : ");
//			process.Priority_Number = sc.nextInt();
//			
//			System.out.print("�ð� �Ҵ緮: ");//for�� ������ ����� �� �� ����.
//			process.Time_quota = sc.nextInt();
//			
//			process_list.add(process);
//			System.out.println("-------------------------------------");
//
//		} //����. ���߿�
		// �Ʒ��� üũ�� �ڵ�
		
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
		
		Process process2 = new Process();
		process2.ID="hi";
		process2.Arrival_time=5;
		process2.Service_time=3;
		process2.Priority_Number=2;
		process_list.add(process2);
		
		for(Process a : process_list) {
			System.out.println("id : "+ a.ID);
		}
		Time_quota = 2;
		RR r = new RR();
		System.out.println("\n���� �κ� RR ����");
		r.insert(process_list, Time_quota);
		
		HRN h = new HRN();
		System.out.println("\nHRN ����");
		h.insert(process_list);
		
		SRT rt = new SRT();
		System.out.println("\nSRT  ����");
		rt.insert(process_list, Time_quota);
		
		FCFS f = new FCFS();
		System.out.println("\nFCFS ����");
		f.insert(process_list);
		
		SJF s = new SJF();
		System.out.println("\nSJF ����");
		s.insert(process_list);
		
		NoPrio np = new NoPrio();
		System.out.println("\n������ Priority ����");
		np.insert(process_list);
		

		
		sc.close();
		
	}
}