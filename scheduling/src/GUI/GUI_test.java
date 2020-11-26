package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.tools.javac.Main;

public class GUI_test extends JFrame {
	
	//���� ���۸��� ���� ��ü
	private Image screenImage;
	private Graphics screenGraphics;
	
	//�̹����� ���� ��ü
	private Image introBackground;
	
	public GUI_test() {
		setTitle("OS �����ٸ�");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //â ������
		setResizable(false);	//âũŰ ���� �Ұ�
		setLocationRelativeTo(null); //������â ����� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ������ ���α׷� ����
		setVisible(true); //â�� �츮 ���� ���̰�
		
		//Main class�� ��ġ�� ������� getResource�� ��θ� ������ �ش� �̹����� ������
		introBackground = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//âũ�⸸ŭ�� �̹��� ��
		screenGraphics = screenImage.getGraphics();	//�׷��� ��ü ������
		screenDraw(screenGraphics);	//ȭ�鿡 �׸���
		g.drawImage(introBackground, 0, 0, null);	//��ũ�� �̹����� 0,0�� ��ġ�� �׸�
		
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);	//introBackground �̹����� ȭ�� ��ü�� �׷���
		this.repaint();	//paint �Լ��� ��� �ҷ��� ȭ�鿡 ��� �׸�
	}
}
