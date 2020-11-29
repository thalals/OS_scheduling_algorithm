package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import scheduling.Process;

public class tmp extends JFrame{

	 ImageIcon icon;

	 JFrame frame = new JFrame();
	 private JPanel background_pannel_1;
	 private JTable table;
	
	tmp(ArrayList<Process> list){
		// 배경 불러오기
	      icon = new ImageIcon(Gui_Main.class.getResource("../images/Back.png"));
	      frame.getContentPane().setLayout(null);
	      background_pannel_1 = new JPanel() {
	         public void paintComponent(Graphics g) {

	            g.drawImage(icon.getImage(), 0, 0, null);

	            setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
	            super.paintComponent(g);
	         }
	      };
	      
//	      background_pannel_1.setBounds(0, 0, 906, 556);
	      frame.getContentPane().add(background_pannel_1);
	      input_button(background_pannel_1);
	      
//	      table = new JTable();
//	      table.setBounds(607, 10, 287, 199);
//	      background_pannel_1.add(table);

	      frame.setVisible(true);
	      frame.setTitle("OS 스케줄링 결과창");
	      frame.setSize(Gui_Main.SCREEEN_WIDTH, Gui_Main.SCREEEN_HEIGHT);
	      frame.setResizable(false); // 창크키 변동 불가
	      frame.setLocationRelativeTo(null); // 윈도우창 가운데에 생성
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창을 닫으면 프로그램 종료
	      frame.setVisible(true); // 창이 우리 눈에 보이게
	      frame.setBackground(new Color(0, 0, 0, 0));
	      frame.setLayout(null);	//지정한 위치에 나타난다..?

	   }

	   public void input_button(JPanel background_pannel) {
	      background_pannel_1.setLayout(null);
	      JLabel imgLabel = new JLabel(
	            new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\Button_Back.png"));
	      imgLabel.setBounds(15, 80, 585, 144);
	      background_pannel.add(imgLabel);

	      JButton FCFS = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\FCFS.png"));
	      FCFS.setBorderPainted(false);
	      FCFS.setFocusPainted(false);
	      FCFS.setContentAreaFilled(false);
	      imgLabel.add(FCFS);
	      FCFS.setBounds(15, 25, 134, 42);

	      JButton SJF = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\SJF.png"));
	      SJF.setBorderPainted(false);
	      SJF.setFocusPainted(false);
	      SJF.setContentAreaFilled(false);
	      imgLabel.add(SJF);
	      SJF.setBounds(155, 25, 134, 42);

	      JButton HRW = new JButton(new ImageIcon("D:\\Study\\os_scheduling_algorithm\\mavenexample\\source\\images\\HRW.png"));
	      HRW.setBorderPainted(false);
	      HRW.setFocusPainted(false);
	      HRW.setContentAreaFilled(false);
	      imgLabel.add(HRW);
	      HRW.setBounds(295, 25, 134, 42);

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

	   }
}
