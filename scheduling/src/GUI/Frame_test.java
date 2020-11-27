package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame_test extends JFrame {
	
	//더블 버퍼링을 위한 객체
	private Image screenImage;
	private Graphics screenGraphics;

	private Image introBackground = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();

	//이미지를 담을 객체
	//Main class의 위치를 기반으로 getResource의 경로를 가져와 해당 이미지를 가져옴
	private ImageIcon startButtonImage = new ImageIcon(Gui_Main.class.getResource("../images/startButton.png"));

	private JButton startButton = new JButton(startButtonImage);	//버튼 생성

	public Frame_test() {
		setTitle("OS 스케줄링");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //창 사이즈
		setResizable(false);	//창크키 변동 불가
		JFrame frame = new JFrame("Frame1");
		frame.setSize(912,585);
		frame.setLocationRelativeTo(null); //윈도우창 가운데에 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
		frame.setVisible(true); //창이 우리 눈에 보이게
		setBackground(new Color(0,0,0,0));

		setLayout(null);	//지정한 위치에 나타난다..?
		
		startButton.setBounds(250,200,412,100); //x,y,w,h
		startButton.setBorderPainted(false); //기본 제공되는 프레임 지우기
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.setVisible(true);
		startButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//시작버튼 클릭 이벤트
			}
		});
		
		frame.add(startButton);
		System.out.println(startButton);


	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//창크기만큼의 이미지 겟
		screenGraphics = screenImage.getGraphics();	//그래픽 객체 얻어오기

		screenDraw(screenGraphics);	//화면에 그리기
		g.drawImage(introBackground, 0, 0, null);	//스크린 이미지를 0,0의 위치에 그림

	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);	//introBackground 이미지를 화면 전체에 그려줌
		paintComponents(g);// 항상 고전된 버튼, 메뉴바 등을 구현할 때 사용
		this.repaint();	//paint 함수를 계속 불러와 화면에 계속 그림
	}
}
