package scheduling;

public class Process {
	String ID;		//���μ��� ���̵�
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getArrival_time() {
		return Arrival_time;
	}
	public void setArrival_time(int arrival_time) {
		Arrival_time = arrival_time;
	}
	public int getService_time() {
		return Service_time;
	}
	public void setService_time(int service_time) {
		Service_time = service_time;
	}
	public double getPriority_Number() {
		return Priority_Number;
	}
	public void setPriority_Number(double priority_Number) {
		Priority_Number = priority_Number;
	}
	public double getHRN_Priority() {
		return HRN_Priority;
	}
	public void setHRN_Priority(double hRN_Priority) {
		HRN_Priority = hRN_Priority;
	}
	public int getWait_time() {
		return Wait_time;
	}
	public void setWait_time(int wait_time) {
		Wait_time = wait_time;
	}
	public int getResponse_time() {
		return Response_time;
	}
	public void setResponse_time(int response_time) {
		Response_time = response_time;
	}
	public int getReturn_time() {
		return Return_time;
	}
	public void setReturn_time(int return_time) {
		Return_time = return_time;
	}
	public int getTime_Remain() {
		return time_Remain;
	}
	public void setTime_Remain(int time_Remain) {
		this.time_Remain = time_Remain;
	}
	int Arrival_time;	//�����ð�
	int Service_time;	//���� �ð�
	double Priority_Number;	//�켱����(���� �������� ����)
	double HRN_Priority;//hrn �����ٸ� ����. �켱���� ������
	int Wait_time=0; //�� ���μ��� �� ��� �ð�
	int Response_time=-1; //�� ���μ����� ����ð�
	int Return_time=0; //�� ���μ����� ��ȯ�ð�
	int time_Remain=0;//������ ����. ���� �ð� ������
}
