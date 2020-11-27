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
	JButton process_button = new JButton(" 저장 ");
	JTextField inputtxt = new JTextField(15);	//텍스트 필드
	JLabel process_number = new JLabel("프로세스 개수 : ");
	
	public input_gui(){
		setLayout(null); 
		
		//배경 불러오기
		icon = new ImageIcon(Gui_Main.class.getResource("../images/yellow_blank.png"));
		JPanel background_pannel = new JPanel() {        
			public void paintComponent(Graphics g) {
                
                g.drawImage(icon.getImage(), 0, 0, null);
               
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        
        

        //input 한 묶음(프로세스 개수)
		pannel.add(process_number);
		pannel.add(inputtxt);
		pannel.add(process_button);
	
		pannel.setBounds(315,250,300,60); //x,y,w,h

		
		background_pannel.add(pannel);
		frame.add(background_pannel);
		

		//프로세스 개수 입력했을 때
		process_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int process_number = Integer.parseInt(inputtxt.getText());
				System.out.println(process_number);
				inputtxt.disable();
				
				
			}
		});
		
		
		frame.setVisible(true);
		frame.setTitle("OS 스케줄링 입력창 ");
		frame.setSize(Gui_Main.SCREEEN_WIDTH,Gui_Main.SCREEEN_HEIGHT);
		frame.setResizable(false);	//창크키 변동 불가
		frame.setLocationRelativeTo(null); //윈도우창 가운데에 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫으면 프로그램 종료
		frame.setVisible(true); //창이 우리 눈에 보이게
		frame.setBackground(new Color(0,0,0,0));	//버튼 같은 이미지를 paintComponents로 그릴때 배경을 투명하게
		frame.setLayout(null);	//지정한 위치에 나타난다..?
		
	}
	
	public void input_data() {
		pannel.add(process_number);
		pannel.add(inputtxt);
		pannel.add(process_button);
	}
	

}
