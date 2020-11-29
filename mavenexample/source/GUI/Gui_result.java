package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import scheduling.Process;
import scheduling.*;

public class Gui_result extends JFrame {
	
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
	String header[] = {"ID","도착시간","서비스 시간","우선순위","대기시간", "응답시간", "반환시간"};
	String Contents[][]= {
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },

			};
	
	JTable table = new JTable(Contents,header);
	
	//입력받은 리스트 받아오기
	public Gui_result(ArrayList<Process> list){

		//배경 불러오기
		icon = new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\yellow_blank.png");
		JPanel background_pannel = new JPanel() {        
			public void paintComponent(Graphics g) {
                
                g.drawImage(icon.getImage(), 0, 0, null);
               
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        background_pannel.setLayout(null);
       
        // 테이블
        JScrollPane scrollPane = new JScrollPane(table);    

	    table.setBounds(588, 100, 385, 200);
        scrollPane.setBounds(588, 100, 400, 185);

	    
	    background_pannel.add(scrollPane);
        
	    /*-------------------------버튼 위치-------------------------------------*/
	    JLabel imgLabel = new JLabel(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\Button_Back.png"));
	      imgLabel.setBounds(10, 90, 585, 144);
	      background_pannel.add(imgLabel);

	      JButton FCFS = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\FCFS.png"));
	      FCFS.setBorderPainted(false);
	      FCFS.setFocusPainted(false);
	      FCFS.setContentAreaFilled(false);
	      imgLabel.add(FCFS);
	      FCFS.setBounds(20, 25, 134, 42);

	      JButton SJF = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\SJF.png"));
	      SJF.setBorderPainted(false);
	      SJF.setFocusPainted(false);
	      SJF.setContentAreaFilled(false);
	      imgLabel.add(SJF);
	      SJF.setBounds(155, 25, 134, 42);

	      JButton HRN = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\HRW.png"));
	      HRN.setBorderPainted(false);
	      HRN.setFocusPainted(false);
	      HRN.setContentAreaFilled(false);
	      imgLabel.add(HRN);
	      HRN.setBounds(295, 25, 134, 42);

	      JButton NoPrio = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\NoPrio.png"));
	      NoPrio.setBorderPainted(false);
	      NoPrio.setFocusPainted(false);
	      NoPrio.setContentAreaFilled(false);
	      imgLabel.add(NoPrio);
	      NoPrio.setBounds(435, 25, 134, 42);
	      
	      JButton RR = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\RR.png"));
	      RR.setBorderPainted(false);
	      RR.setFocusPainted(false);
	      RR.setContentAreaFilled(false);
	      imgLabel.add(RR);
	      RR.setBounds(80,75, 134, 42);
	      
	      JButton SRT = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\SRT.png"));
	      SRT.setBorderPainted(false);
	      SRT.setFocusPainted(false);
	      SRT.setContentAreaFilled(false);
	      imgLabel.add(SRT);
	      SRT.setBounds(220,75, 134, 42);
	      
	      JButton Prio = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\Prio.png"));
	      Prio.setBorderPainted(false);
	      Prio.setFocusPainted(false);
	      Prio.setContentAreaFilled(false);
	      imgLabel.add(Prio);
	      Prio.setBounds(360, 75, 134, 42);
	      
	      JLabel avg_wait_label = new JLabel("평균 대기 시간 : ");
	      JLabel avg_reponse_label = new JLabel("평균 응답 시간 : ");
	      JLabel avg_return_label = new JLabel("평균 반환 시간 : ");
	      
	      avg_wait_label.setBounds(155,250,100,20);
	      avg_reponse_label.setBounds(155,280,100,20);
	      avg_return_label.setBounds(155,310,100,20);
	      
	      JTextField avg_wait_txt = new JTextField();
	      JTextField avg_reponse_txt = new JTextField();
	      JTextField avg_return_txt = new JTextField();
	      
	      avg_wait_txt.setBounds(255,250,100,20);
	      avg_reponse_txt.setBounds(255,280,100,20);
	      avg_return_txt.setBounds(255,310,100,20);
	      
	      avg_wait_txt.setEnabled(false);
	      avg_reponse_txt.setEnabled(false);
	      avg_return_txt.setEnabled(false);
	      
	      background_pannel.add(avg_wait_txt);
	      background_pannel.add(avg_reponse_txt);
	      background_pannel.add(avg_return_txt);
	      
	      background_pannel.add(avg_wait_label);
	      background_pannel.add(avg_reponse_label);
	      background_pannel.add(avg_return_label);

	      JPanel panel = new JPanel();
	      panel.setForeground(SystemColor.inactiveCaption);
	      panel.setBounds(20, 350, 960, 240);
	      background_pannel.add(panel);
	      
	      ArrayList<Process> process_list = list;	// 입력받은 리스트 받아오기
	      
	      
	      for(int i =0; i<process_list.size();i++) {
		      table.setValueAt(process_list.get(i).getID(), i, 0);
		      table.setValueAt(Integer.toString(process_list.get(i).getArrival_time()), i, 1);
		      table.setValueAt(Integer.toString(process_list.get(i).getService_time()), i, 2);
		      table.setValueAt(Double.toString(process_list.get(i).getPriority_Number()), i, 3);
		  }
			
	      frame.add(background_pannel);

	      //FCFS 버튼
	      FCFS.addActionListener(new ActionListener() {
	    	  	public void mouseEntered(MouseEvent e) {
	    	  		FCFS.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				public void mouseExited(MouseEvent e) {
					FCFS.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				public void actionPerformed(ActionEvent e) {
					System.out.println("FCFS");
					
					scheduling.FCFS f = new FCFS();
					f.insert(process_list);
					
					for(int i =0; i<process_list.size();i++) {
						
						System.out.println(process_list.get(i).getID()+"프로세스별 대기,응답,반환 "+process_list.get(i).getWait_time()+" "+process_list.get(i).getResponse_time()+" "+process_list.get(i).getReturn_time()+" ");
						table.setValueAt(Integer.toString(process_list.get(i).getWait_time()), i, 4);
						table.setValueAt(Integer.toString(process_list.get(i).getResponse_time()), i, 5);
						table.setValueAt(Integer.toString(process_list.get(i).getReturn_time()), i, 6);
					}
					
//					avg_wait_txt.setText();
//				    avg_reponse_txt.setText(false);
//				    avg_return_txt.setText
					
				}
	      });
	      
	      
	      frame.setVisible(true);
	      frame.setTitle("OS 스케줄링 입력창 ");
	      frame.setSize(Gui_Main.SCREEEN_WIDTH+100,Gui_Main.SCREEEN_HEIGHT+50);
	      frame.setResizable(false);	//창크키 변동 불가
	      frame.setLocationRelativeTo(null); //윈도우창 가운데에 생성
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
	      frame.setVisible(true); //창이 우리 눈에 보이게
	      frame.setBackground(new Color(0,0,0,0));	//버튼 같은 이미지를 paintComponents로 그릴때 배경을 투명하게
	      frame.setLayout(null);	//지정한 위치에 나타난다..?
		
	}
	
}
