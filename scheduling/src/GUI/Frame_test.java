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
	
	//���� ���۸��� ���� ��ü
	private Image screenImage;
	private Graphics screenGraphics;

	private Image introBackground = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();

	//�̹����� ���� ��ü
	//Main class�� ��ġ�� ������� getResource�� ��θ� ������ �ش� �̹����� ������
	private ImageIcon startButtonImage = new ImageIcon(Gui_Main.class.getResource("../images/startButton.png"));

	private JButton startButton = new JButton(startButtonImage);	//��ư ����

	public Frame_test() {
		setTitle("OS �����ٸ�");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //â ������
		setResizable(false);	//âũŰ ���� �Ұ�
		JFrame frame = new JFrame("Frame1");
		frame.setSize(912,585);
		frame.setLocationRelativeTo(null); //������â ����� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ������ ���α׷� ����
		frame.setVisible(true); //â�� �츮 ���� ���̰�
		setBackground(new Color(0,0,0,0));

		setLayout(null);	//������ ��ġ�� ��Ÿ����..?
		
		startButton.setBounds(250,200,412,100); //x,y,w,h
		startButton.setBorderPainted(false); //�⺻ �����Ǵ� ������ �����
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.setVisible(true);
		startButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//���۹�ư Ŭ�� �̺�Ʈ
			}
		});
		
		frame.add(startButton);
		System.out.println(startButton);


	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//âũ�⸸ŭ�� �̹��� ��
		screenGraphics = screenImage.getGraphics();	//�׷��� ��ü ������

		screenDraw(screenGraphics);	//ȭ�鿡 �׸���
		g.drawImage(introBackground, 0, 0, null);	//��ũ�� �̹����� 0,0�� ��ġ�� �׸�

	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);	//introBackground �̹����� ȭ�� ��ü�� �׷���
		paintComponents(g);// �׻� ������ ��ư, �޴��� ���� ������ �� ���
		this.repaint();	//paint �Լ��� ��� �ҷ��� ȭ�鿡 ��� �׸�
	}
}
