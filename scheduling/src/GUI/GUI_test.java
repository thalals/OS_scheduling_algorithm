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
	
	//���� ���۸��� ���� ��ü
	private Image screenImage;
	private Graphics screenGraphic;

	//�̹����� ���� ��ü
	//Main class�� ��ġ�� ������� getResource�� ��θ� ������ �ش� �̹����� ������
	private Image Background = new ImageIcon(Gui_Main.class.getResource("../images/introBackground.PNG")).getImage();

	private ImageIcon exitButton = new ImageIcon(Gui_Main.class.getResource("../images/ExitButtonBasic.png"));
	private ImageIcon startButtonImage = new ImageIcon(Gui_Main.class.getResource("../images/startButton.png"));
	private ImageIcon menubarimage = new ImageIcon(Gui_Main.class.getResource("../images/menuBar.png"));

	private JButton exitMenubt = new JButton(exitButton);
	private JButton startButton = new JButton(startButtonImage);	//��ư ����
	private JLabel menubar = new JLabel(menubarimage);;

	// ���콺 ��ǥ�� ���� ���� ����
	private int mouseX, mouseY;
	
	public GUI_test() {
		setUndecorated(true);
		setTitle("OS �����ٸ�");
		setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT); //â ������
		setResizable(false);	//âũŰ ���� �Ұ�
		setLocationRelativeTo(null); //������â ����� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ������ ���α׷� ����
		setVisible(true); //â�� �츮 ���� ���̰�
		setBackground(new Color(0,0,0,0));	//��ư ���� �̹����� paintComponents�� �׸��� ����� �����ϰ�
		setLayout(null);	//������ ��ġ�� ��Ÿ����..?
		
		//�޴��� �ݱ� ���y
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

		//��� �޴���
		menubar.setBounds(0, 0, 912, 27);
		menubar.addMouseListener(new MouseAdapter() {
			//���콺 �Է¿��� ���콺�� �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			//���콺 �̵��� �����ϰ� ���콺 �巹�� �̺�Ʈ �߻��� â�� �̵���Ų��.
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
		startButton.setBorderPainted(false); //�⺻ �����Ǵ� ������ �����
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
				//���۹�ư Ŭ�� �̺�Ʈ
//				startButton.setVisible(false);
//				Background = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png")).getImage();
				new input_gui();
			}
		});
		
		

		add(startButton);

	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);	//âũ�⸸ŭ�� �̹��� ��
		screenGraphic = screenImage.getGraphics();	//�׷��� ��ü ������

		screenDraw(screenGraphic);	//ȭ�鿡 �׸���
		g.drawImage(screenImage, 0, 0, null);	//��ũ�� �̹����� 0,0�� ��ġ�� �׸�

	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null);	//introBackground �̹����� ȭ�� ��ü�� �׷���
		paintComponents(g);// �׻� ������ ��ư, �޴��� ���� ������ �� ���
		this.repaint();	//paint �Լ��� ��� �ҷ��� ȭ�鿡 ��� �׸�
	}
}
