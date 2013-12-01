import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;  
class Start{
	static JFrame frame;
	static Listen key;
	static JLabel back;
	static Image background;
	static JLabel[] item = new JLabel[3];
	public static void main(String[] arg){
		frame = new JFrame("RPG");
		while(true){
			int choose = run();
			key.reset();
			if(choose == 0){
				Player player = new Player(frame,key,0);
				player.run();
			}
			else if(choose == 1){
				Player player = new Player(frame,key,1);
				player.run();
			}
			else if(choose == 2){
				System.exit(0); 
				
			}
		}
	}
	static private void init(){
		frame.getContentPane().removeAll();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		frame.setBounds(bounds);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		key = new Listen();
		background = new ImageIcon("image//"+"main_menu.png").getImage();
		background = background.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
		back = new JLabel();
		back.setBounds(0,0,frame.getWidth(), frame.getHeight());
		back.setIcon(new ImageIcon(background));
		
		
		item[0] = new JLabel("START");
		item[0].setFont(new java.awt.Font("Dialog", 1, 40));
		item[0].setBounds(frame.getWidth()*47/100,frame.getHeight()*53/100,200,100);
		item[1] = new JLabel("LOAD");
		item[1].setFont(new java.awt.Font("Dialog", 1, 40));
		item[1].setBounds(frame.getWidth()*47/100,frame.getHeight()*65/100,200,100);
		item[2] = new JLabel("EXIT");
		item[2].setFont(new java.awt.Font("Dialog", 1, 40));
		item[2].setBounds(frame.getWidth()*47/100,frame.getHeight()*77/100,200,100);
		
		frame.addKeyListener(key);
		for(int i=0;i<3;i++) frame.add(item[i]);
		frame.add(back);
		frame.setVisible(true);
		for(int i=0;i<3;i++) item[i].setForeground(Color.black);
		item[0].setForeground(Color.red);

	}
	static private int run(){
		int choose = 0;
		init();
		System.out.println("QQ");
		while(true){
			if(key.ENTER) { key.ENTER = false; break;}
			if(key.UP){ 
				item[choose].setForeground(Color.black);
				choose--; 
				if(choose < 0) choose = 3-1; 
				item[choose].setForeground(Color.red);
				key.UP = false;
			}
			if(key.DOWN){ 
				item[choose].setForeground(Color.black);
				choose++; 
				if(choose >= 3) choose = 0; 
				item[choose].setForeground(Color.red);
				key.DOWN = false;
			}
			SLEEP(100);
		}
		for(int i=0;i<3;i++) frame.remove(item[i]);
		frame.remove(back);
		
		return choose;
	}
	private static void SLEEP(int x){
		try {
				Thread.sleep(x);
		} catch (InterruptedException e) {
		}
	}
}