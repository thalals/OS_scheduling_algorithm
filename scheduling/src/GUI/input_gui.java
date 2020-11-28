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
import javax.swing.JTable;
import javax.swing.JTextField;

public class input_gui extends JFrame {
	
	private Image background;
    JScrollPane scrollPane;


	ImageIcon icon;
	
	JFrame frame = new JFrame();
	
	JButton process_button = new JButton(" ���� ");
	JTextField number_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
	JLabel process_number = new JLabel("���μ��� ���� : ",JLabel.LEFT);
	
	//�Է� �� üũ ���̺�(ǥ)
	String header[] = {"���μ��� ID","�����ð�","���� �ð�","�켱����"};
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
//		frame.setLayout(null); 
//		getContentPane().setLayout(null);
		
		//��� �ҷ�����
		icon = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png"));
		JPanel background_pannel = new JPanel() {        
			public void paintComponent(Graphics g) {
                
                g.drawImage(icon.getImage(), 0, 0, null);
               
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        background_pannel.setLayout(null);
        process_button.setBounds(750, 150, 65, 20);
        number_input.setBounds(580, 150, 150,22);
        process_number.setBounds(480, 150, 100,23);
        //input �� ����(���μ��� ����)
        background_pannel.add(process_number);
        background_pannel.add(number_input);
        background_pannel.add(process_button);
	
        /*----------------------������ ���μ��� ���� �Է�, �׿��� ������ �Է� �κ�---------------------------------------*/

        JLabel process_id = new JLabel("���μ��� ID    : ");
		JLabel process_arrival = new JLabel("�����ð�         : ");
		JLabel process_service = new JLabel("���� �ð�    : ");
		JLabel process_priority = new JLabel("�켱����         : ");

		
		JTextField id_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField arrival_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField service_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField priority_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		
		//process id
		process_id.setBounds(480, 200, 100,23);
		id_input.setBounds(580, 200, 150,22);

        background_pannel.add(process_id);
        background_pannel.add(id_input);

		
		//process �����ð�
        process_arrival.setBounds(480, 250, 100,23);
        arrival_input.setBounds(580, 250, 150,22);
        
        background_pannel.add(process_arrival);
        background_pannel.add(arrival_input);

		//process ���� �ð�
        process_service.setBounds(480, 300, 100,23);
        service_input.setBounds(580, 300, 150,22);
        
        background_pannel.add(process_service);
        background_pannel.add(service_input);
        
        //process �켱����
        process_priority.setBounds(480, 350, 100,23);
        priority_input.setBounds(580, 350, 150,22);
        
        background_pannel.add(process_priority);
        background_pannel.add(priority_input);
		
        // ���μ��� ����, �ʱ�ȭ ��ư, ���� ��ư
        JButton save_button = new JButton("���μ��� ���� ����");
        JButton reset_button = new JButton("�ʱ�ȭ");
        JButton next_button = new JButton("����");

        save_button.setBounds(580, 400, 140,22);
        reset_button.setBounds(700, 500, 80,22);
        next_button.setBounds(800, 500, 80,22);

        background_pannel.add(save_button);
        background_pannel.add(reset_button);
        background_pannel.add(next_button);

        /*--------------------������ �Է�â ���̾ƿ� �Ϸ� ----------------------------*/
		
        JScrollPane scrollPane = new JScrollPane(table);
        
        
        table.setBounds(100, 150, 300, 300);
        scrollPane.setBounds(100, 150, 300, 240);

        background_pannel.add(scrollPane);
        
        frame.add(background_pannel);
		

		//���μ��� ���� �Է����� ��
		process_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int process_number = Integer.parseInt(number_input.getText());
				System.out.println(process_number);
				number_input.disable();
				
				input_form();
				
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
	
	public void input_form() {
		JLabel process_id = new JLabel("���μ��� ID : ");
		JLabel process_arrival = new JLabel("�����ð� : ");
		JLabel process_service = new JLabel("���� �ð� : ");
		JLabel process_priority = new JLabel("�켱���� : ");

		
		JTextField id_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField arrival_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField service_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		JTextField priority_input = new JTextField(15);	//�ؽ�Ʈ �ʵ�
		
		//process id
//		panel1.add(process_id);
//		panel1.add(id_input);
//		
//		//process �����ð�
//		panel2.add(process_arrival);
//		panel2.add(arrival_input);
//
//		//process ���� �ð�
//		panel3.add(process_service);
//		panel3.add(service_input);
//
//		panel4.add(process_priority);
//		panel4.add(priority_input);
		
//		frame.add(panel);
		
	}
	

}
