package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.tools.javac.Main;

public class GUI_test extends JFrame {
	
	//더블 버퍼링을 위한 객체
	private Image screenImage;
	private Graphics screenGraphics;
	
	//이미지를 담을 객체
	private Image introBackground;
	
	public GUI_test() {
		setTitle("OS 스케줄링");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //창 사이즈
		setResizable(false);	//창크키 변동 불가
		setLocationRelativeTo(null); //윈도우창 가운데에 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
		setVisible(true); //창이 우리 눈에 보이게
		
		//Main class의 위치를 기반으로 getResource의 경로를 가져와 해당 이미지를 가져옴
		introBackground = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//창크기만큼의 이미지 겟
		screenGraphics = screenImage.getGraphics();	//그래픽 객체 얻어오기
		screenDraw(screenGraphics);	//화면에 그리기
		g.drawImage(introBackground, 0, 0, null);	//스크린 이미지를 0,0의 위치에 그림
		
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);	//introBackground 이미지를 화면 전체에 그려줌
		this.repaint();	//paint 함수를 계속 불러와 화면에 계속 그림
	}
}
