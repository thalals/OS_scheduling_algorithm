package GUI;

import java.awt.*;
import javax.swing.*;

public class input_gui extends JFrame {
	

	Image i = new ImageIcon(Gui_Main.class.getResource("../images/yello_blank.PNG")).getImage();
	input_gui(){
        this.setTitle("이미지 그리기 연습");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MyPanel panel = new MyPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JButton("Hello"));
        
        this.add(panel);
        this.setSize(400,400);
        this.setVisible(true);
    }
    class MyPanel extends JPanel{
            
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(i,0,0,getWidth(),getHeight(),this);
        }
    }




}
