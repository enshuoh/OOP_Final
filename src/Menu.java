import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu {
	static final int N = 4;
	int choose;
	Listen key;
	JFrame frame;
	JPanel panel;
	JLabel[] item = new JLabel[N];
	JLabel back;
	Image background;
	
	public Menu(JFrame frame,Listen key){
		this.frame = frame;
		this.key = key;
		panel = new JPanel();
		//panel.setBackground(null);
		panel.setOpaque(false);
		panel.setBounds(100,100,300,500);
		panel.setVisible(false);
		panel.setLayout(null);
		frame.add(panel);
		
		
		item[0] = new JLabel("ITEM");
		item[0].setFont(new java.awt.Font("Dialog", 1, 30));
		item[0].setBounds(100,80,100,30);
		item[1] = new JLabel("SAVE");
		item[1].setFont(new java.awt.Font("Dialog", 1, 30));
		item[1].setBounds(100,180,100,30);
		item[2] = new JLabel("LOAD");
		item[2].setFont(new java.awt.Font("Dialog", 1, 30));
		item[2].setBounds(100,280,100,30);
		item[3] = new JLabel("EXIT");
		item[3].setFont(new java.awt.Font("Dialog", 1, 30));
		item[3].setBounds(100,380,100,30);
		
		for(int i=0;i<N;i++) panel.add(item[i]);
		
		background = new ImageIcon("image//"+"menu.png").getImage();
		background = background.getScaledInstance(300, 500, Image.SCALE_DEFAULT);
		back = new JLabel();
		back.setBounds(0,0,300,500);
		back.setIcon(new ImageIcon(background));
		panel.add(back);
	}
	private void init(){
		for(int i=0;i<N;i++) item[i].setForeground(Color.black);
		choose = 0;
		item[0].setForeground(Color.red);
	}
	public int run(){
		init();
		panel.setVisible(true);
		System.out.println("run");
		while(true){
			if(key.ESC) { key.ESC = false; choose = -1;break;}
			if(key.ENTER) { key.ENTER = false; break;}
			if(key.UP){ 
				item[choose].setForeground(Color.black);
				choose--; 
				if(choose < 0) choose = N-1; 
				item[choose].setForeground(Color.red);
				key.UP = false;
			}
			if(key.DOWN){ 
				item[choose].setForeground(Color.black);
				choose++; 
				if(choose >= N) choose = 0; 
				item[choose].setForeground(Color.red);
				key.DOWN = false;
			}
			SLEEP(10);
		}
		
		panel.setVisible(false);
		return choose;
	}
	
	private void SLEEP(int x){
		try {
				Thread.sleep(x);
		} catch (InterruptedException e) {
		}
	}
}