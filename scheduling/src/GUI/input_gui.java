package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import scheduling.Process;

public class input_gui extends JFrame {
	
	ArrayList<Process> gui_process_list = new ArrayList<Process>();	//프로세스 저장할 리스트
	
    JScrollPane scrollPane;
    public static int input_process_number=0;
    
    int count =0;	//프로세스 정보 입력 횟수 측정
	ImageIcon icon;
	
	JFrame frame = new JFrame();
	
	JButton process_button = new JButton(" 저장 ");
	JTextField number_input = new JTextField(15);	//텍스트 필드
	JLabel process_number = new JLabel("프로세스 개수 : ",JLabel.LEFT);
	
	//입력 값 체크 테이블(표)
	String header[] = {"프로세스 ID","도착시간","서비스 시간","우선순위"};
	String Contents[][]= {
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			{ null, null, null, null },
			};
	
	JTable table = new JTable(Contents,header);
	
	public input_gui(){

		//배경 불러오기
		icon = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png"));
		JPanel background_pannel = new JPanel() {        
			public void paintComponent(Graphics g) {
                
                g.drawImage(icon.getImage(), 0, 0, null);
               
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        background_pannel.setLayout(null);
        process_button.setBounds(750, 150, 65, 20);
        number_input.setBounds(580, 150, 150,22);
        process_number.setBounds(480, 150, 100,23);
        //input 한 묶음(프로세스 개수)
        background_pannel.add(process_number);
        background_pannel.add(number_input);
        background_pannel.add(process_button);
	
        /*----------------------위에는 프로세스 개수 입력, 및에는 나머지 입력 부분---------------------------------------*/

        JLabel process_id = new JLabel("프로세스 ID    : ");
		JLabel process_arrival = new JLabel("도착시간         : ");
		JLabel process_service = new JLabel("서비스 시간    : ");
		JLabel process_priority = new JLabel("우선순위         : ");

		
		JTextField id_input = new JTextField(15);	//텍스트 필드
		JTextField arrival_input = new JTextField(15);	//텍스트 필드
		JTextField service_input = new JTextField(15);	//텍스트 필드
		JTextField priority_input = new JTextField(15);	//텍스트 필드
		
		//process id
		process_id.setBounds(480, 200, 100,23);
		id_input.setBounds(580, 200, 150,22);

        background_pannel.add(process_id);
        background_pannel.add(id_input);

		
		//process 도착시간
        process_arrival.setBounds(480, 250, 100,23);
        arrival_input.setBounds(580, 250, 150,22);
        
        background_pannel.add(process_arrival);
        background_pannel.add(arrival_input);

		//process 서비스 시간
        process_service.setBounds(480, 300, 100,23);
        service_input.setBounds(580, 300, 150,22);
        
        background_pannel.add(process_service);
        background_pannel.add(service_input);
        
        //process 우선순위
        process_priority.setBounds(480, 350, 100,23);
        priority_input.setBounds(580, 350, 150,22);
        
        background_pannel.add(process_priority);
        background_pannel.add(priority_input);
		
        // 프로세스 저장, 초기화 버튼, 다음 버튼
        JButton save_button = new JButton("프로세스 정보 저장");
        JButton reset_button = new JButton("초기화");
        JButton next_button = new JButton("다음");

        save_button.setBounds(580, 400, 140,22);
        reset_button.setBounds(700, 500, 80,22);
        next_button.setBounds(800, 500, 80,22);

        background_pannel.add(save_button);
        background_pannel.add(reset_button);
        background_pannel.add(next_button);

        /*--------------------오른쪽 입력창 레이아웃 완료 ----------------------------*/
		
        // 왼쪽 표 레이아웃 설정 및 출력 부분
        JScrollPane scrollPane = new JScrollPane(table);    
        
        table.setBounds(100, 150, 300, 300);
        scrollPane.setBounds(100, 150, 300, 240);

        background_pannel.add(scrollPane);
        
        frame.add(background_pannel);
		
        /*------------------------버튼 입력 액션 구현 ------------------------------------*/
        
		//프로세스 개수 입력했을 때
		process_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				input_process_number = Integer.parseInt(number_input.getText());
				System.out.println(input_process_number);
				number_input.setEnabled(false);	//버튼 누르면 수정 못하게
			}
		});
		
		
		//프로세스 정보 저장 버튼을 눌렀을때
		save_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("프로세스 정보 저장 ");
				Process process = new Process();
				
				//사용자가 입력한 정보 저장
				process.setID(id_input.getText());
				process.setService_time(Integer.parseInt(service_input.getText()));	
				process.setArrival_time(Integer.parseInt(arrival_input.getText()));
				process.setPriority_Number(Double.parseDouble(priority_input.getText()));
				
				gui_process_list.add(process);
				
				for(Process d : gui_process_list)
					System.out.println(d.getID());
				// 작성후 입력한 값 지우기
				id_input.setText(null);
				service_input.setText(null);
				arrival_input.setText(null);
				priority_input.setText(null);
				
				// 화면 테이블에 출력할 값 저장
				table.setValueAt(process.getID(), count, 0);
				table.setValueAt(Integer.toString(process.getArrival_time()), count, 1);
				table.setValueAt(Integer.toString(process.getService_time()), count, 2);
				table.setValueAt(Double.toString(process.getPriority_Number()), count, 3);

				
				count++;  //정보저장 횟수 카운트
				
				if(count == input_process_number) {
					id_input.setEnabled(false);
					service_input.setEnabled(false);
					arrival_input.setEnabled(false);
					priority_input.setEnabled(false);
				}
			}
		});
		
		//초기화 버튼
		reset_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				count =0;
				//리스트 안에 데이터 전부 지우기
				gui_process_list.clear();
				number_input.setEnabled(true);
				
				id_input.setEnabled(true);
				service_input.setEnabled(true);
				arrival_input.setEnabled(true);
				priority_input.setEnabled(true);
				
				// 테이블 초기화
				for(int i=0;i<table.getRowCount();i++) {
					for(int j=0;j<table.getColumnCount();j++)
						table.setValueAt(null, i, j);
				}
			}
		});
		
		//다음 버튼
		
		
		frame.setVisible(true);
		frame.setTitle("OS 스케줄링 입력창 ");
		frame.setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);
		frame.setResizable(false);	//창크키 변동 불가
		frame.setLocationRelativeTo(null); //윈도우창 가운데에 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
		frame.setVisible(true); //창이 우리 눈에 보이게
		frame.setBackground(new Color(0,0,0,0));	//버튼 같은 이미지를 paintComponents로 그릴때 배경을 투명하게
		frame.setLayout(null);	//지정한 위치에 나타난다..?
		
	}
	
}
