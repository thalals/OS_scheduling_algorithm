package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI_test extends JFrame {
	
	//더블 버퍼링을 위한 객체
	private Image screenImage;
	private Graphics screenGraphic;

	//이미지를 담을 객체
	//Main class의 위치를 기반으로 getResource의 경로를 가져와 해당 이미지를 가져옴
	private Image Background = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();

	private ImageIcon exitButton = new ImageIcon(Gui_Main.class.getResource("../images/ExitButtonBasic.png"));
	private ImageIcon startButtonImage = new ImageIcon(Gui_Main.class.getResource("../images/startButton.png"));
	private ImageIcon menubarimage = new ImageIcon(Gui_Main.class.getResource("../images/menuBar.png"));

	private JButton exitMenubt = new JButton(exitButton);
	private JButton startButton = new JButton(startButtonImage);	//버튼 생성
	private JLabel menubar = new JLabel(menubarimage);;

	// 마우스 좌표를 담을 변수 생성
	private int mouseX, mouseY;
	
	public GUI_test() {
		setUndecorated(true);
		setTitle("OS 스케줄링");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //창 사이즈
		setResizable(false);	//창크키 변동 불가
		setLocationRelativeTo(null); //윈도우창 가운데에 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
		setVisible(true); //창이 우리 눈에 보이게
		setBackground(new Color(0,0,0,0));	//버튼 같은 이미지를 paintComponents로 그릴때 배경을 투명하게
		setLayout(null);	//지정한 위치에 나타난다..?
		
		//메뉴바 닫기 버틐
		exitMenubt.setBounds(875, 5, 20, 20);
		exitMenubt.setBorderPainted(false);
		exitMenubt.setContentAreaFilled(false);
		exitMenubt.setFocusPainted(false);
		exitMenubt.addMouseListener(new MouseAdapter() {			
			public void mouseEntered(MouseEvent e) {
				exitMenubt.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitMenubt.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(330);
				} catch (InterruptedException ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitMenubt);

		//상단 메뉴바
		menubar.setBounds(0, 0, 912, 27);
		menubar.addMouseListener(new MouseAdapter() {
			//마우스 입력에서 마우스를 눌렀을때 이벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			//마우스 이동을 감지하고 마우스 드레그 이벤트 발생시 창을 이동시킨다.
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menubar);
//		
		startButton.setBounds(315,250,300,60); //x,y,w,h
		startButton.setBorderPainted(false); //기본 제공되는 프레임 지우기
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				//시작버튼 클릭 이벤트
//				startButton.setVisible(false);
//				Background = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png")).getImage();
				
				dispose();
				new input_gui();
			}
		});
		
		

		add(startButton);

	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//창크기만큼의 이미지 겟
		screenGraphic = screenImage.getGraphics();	//그래픽 객체 얻어오기

		screenDraw(screenGraphic);	//화면에 그리기
		g.drawImage(screenImage, 0, 0, null);	//스크린 이미지를 0,0의 위치에 그림

	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);	//introBackground 이미지를 화면 전체에 그려줌
		paintComponents(g);// 항상 고전된 버튼, 메뉴바 등을 구현할 때 사용
		this.repaint();	//paint 함수를 계속 불러와 화면에 계속 그림
	}
}
