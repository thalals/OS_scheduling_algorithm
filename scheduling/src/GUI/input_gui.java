package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class input_gui extends JFrame {
	
	private Image background;
    JScrollPane scrollPane;


	ImageIcon icon;
	
	JFrame frame = new JFrame();
	JPanel pannel = new JPanel();
	JPanel button_pannel = new JPanel();
	JButton process_button = new JButton(" ���� ");
	JTextField inputtxt = new JTextField(15);	//�ؽ�Ʈ �ʵ�
	JLabel process_number = new JLabel("���μ��� ���� : ");
	
	public input_gui(){
		setLayout(null); 
		
		//��� �ҷ�����
		icon = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png"));
		JPanel background_pannel = new JPanel() {        
			public void paintComponent(Graphics g) {
                
                g.drawImage(icon.getImage(), 0, 0, null);
               
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        
        

        //input �� ����(���μ��� ����)
		pannel.add(process_number);
		pannel.add(inputtxt);
		pannel.add(process_button);
	
		pannel.setBounds(315,250,300,60); //x,y,w,h

		
		background_pannel.add(pannel);
		frame.add(background_pannel);
		

		//���μ��� ���� �Է����� ��
		process_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int process_number = Integer.parseInt(inputtxt.getText());
				System.out.println(process_number);
				inputtxt.disable();
				
				
			}
		});
		
		
		frame.setVisible(true);
		frame.setTitle("OS �����ٸ� �Է�â ");
		frame.setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);
		frame.setResizable(false);	//âũŰ ���� �Ұ�
		frame.setLocationRelativeTo(null); //������â ����� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ������ ���α׷� ����
		frame.setVisible(true); //â�� �츮 ���� ���̰�
		frame.setBackground(new Color(0,0,0,0));	//��ư ���� �̹����� paintComponents�� �׸��� ����� �����ϰ�
		frame.setLayout(null);	//������ ��ġ�� ��Ÿ����..?
		
	}
	
	public void input_data() {
		pannel.add(process_number);
		pannel.add(inputtxt);
		pannel.add(process_button);
	}
	

}
