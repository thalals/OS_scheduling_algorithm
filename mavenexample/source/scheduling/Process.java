package scheduling;

public class Process {
	String ID;		//프로세스 아이디
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
	int Arrival_time;	//도착시간
	int Service_time;	//서비스 시간
	double Priority_Number;	//우선순위(수가 낮을수록 높음)
	double HRN_Priority;//hrn 스케줄링 전용. 우선순위 측정기
	int Wait_time=0; //각 프로세스 별 대기 시간
	int Response_time=-1; //각 프로세스별 응답시간
	int Return_time=0; //각 프로세스별 반환시간
	int time_Remain=0;//선점형 전용. 남은 시간 측정기
}
