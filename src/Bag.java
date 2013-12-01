import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;   
import org.w3c.dom.Node;
import org.w3c.dom.Element; 
public class Bag{
	int N;
	int choose;
	Listen key;
	JFrame frame;
	JPanel panel;
	JLabel itempic;
	JLabel itemconent;
	JLabel back;
	Image background;

	private ArrayList<JLabel> bagitem;
	public Bag(JFrame frame,Listen key){
		panel = new JPanel();
		bagitem = new ArrayList<JLabel>();
		this.frame = frame;
		this.key = key;
		//panel.setBackground(null);
		panel.setBounds(frame.getWidth()/2-300,frame.getHeight()/2-300,600,600);
		panel.setVisible(false);
		panel.setLayout(null);
		
		//panel.setLayout(null);
		frame.add(panel);
		itempic = new JLabel("",JLabel.CENTER);
		itemconent = new JLabel("",JLabel.CENTER);
		try { 	
			background = new ImageIcon("image//"+"Item.png").getImage();
		}catch (Exception ex) {
			System.out.println("lose itemspace");
		}
		background = background.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
		back = new JLabel();
		back.setBounds(0,0,600,600);
		back.setIcon(new ImageIcon(background));
		back.setBackground(Color.pink);
		/*
		item[0] = new JLabel("SAVE");
		item[0].setBounds(0,0,100,30);
		item[1] = new JLabel("LOAD");
		item[1].setBounds(0,30,100,30);
		item[2] = new JLabel("ITEM");
		item[2].setBounds(0,60,100,30);
		item[3] = new JLabel("EXIT");
		item[3].setBounds(0,90,100,30);
		
		for(int i=0;i<N;i++) panel.add(item[i]);
		*/
	}
	private void init(ArrayList<String> items){
		panel.removeAll();
		bagitem.clear();
		
		itempic.setBounds(30,280,270,270);
		itemconent.setBounds(355,280,210,270);
		itemconent.setFont(new java.awt.Font("Dialog", 1, 30));
		panel.add(itempic);
		panel.add(itemconent);
		//itempic.setBorder(BorderFactory.createLineBorder(Color.red,1));
		//itemconent.setBorder(BorderFactory.createLineBorder(Color.red,1));
		choose = 0;
		N = 0;
		int i;
		for(i=0;i<=items.size();i++){
			JLabel lbl;
			if(i==items.size())lbl = new JLabel("exit", JLabel.CENTER);
			else{
				lbl = new JLabel(items.get(i), JLabel.CENTER);
				N++;
			}
			lbl.setBounds(i%7*80+25,i/7*40+50,80,40);
			lbl.setForeground(Color.black);
			lbl.setFont(new java.awt.Font("Dialog", 1, 18));
			//lbl.setBorder(BorderFactory.createLineBorder(Color.red,1));
			bagitem.add(lbl);
			panel.add(bagitem.get(i));
		}
		panel.add(back);
		bagitem.get(choose).setForeground(Color.red);
		if(N==0)showDetail(null);
		else showDetail(items.get(0));
		//for(i=0;i<25;i++) bagitem.get(i).setBorder(BorderFactory.createLineBorder(Color.red,1));
		//choose = 0;
		//item[0].setForeground(Color.red);
	}
	private void showDetail(String name){
		if(name==null){
			itemconent.setText("                 ");
			itempic.setIcon(null);
			return;
		}
		Node root = LoadXML.getItemDetail(name);
		Element node = (Element)root;
		itemconent.setText("<html>"+root.getFirstChild().getTextContent()+"</html>");
		itempic.setIcon(new ImageIcon(new ImageIcon(node.getAttribute("image")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
		//System.out.println(root.getFirstChild().getTextContent());
	}
	public String run(ArrayList<String> items){
		init(items);
		panel.setVisible(true);
		//System.out.println(N + " " + choose);
		while(true){
			if(key.ENTER) { key.ENTER = false; break;}
			if(key.ESC){ key.ESC = false; choose = N;break; }
			if(key.UP){ 
				bagitem.get(choose).setForeground(Color.black);
				choose-=7; 
				if(choose < 0) choose += 7; 
				key.UP = false;
			}
			if(key.DOWN){ 
				bagitem.get(choose).setForeground(Color.black);
				choose+=7; 
				if(choose > N) choose -= 7 ; 
				key.DOWN = false;
			}
			if(key.LEFT){ 
				bagitem.get(choose).setForeground(Color.black);
				choose--; 
				if(choose < 0) choose ++ ; 
				key.LEFT = false;
			}
			if(key.RIGHT){ 
				bagitem.get(choose).setForeground(Color.black);
				choose++; 
				if(choose > N) choose -- ; 
				key.RIGHT = false;
			}
			bagitem.get(choose).setForeground(Color.red);
			if(choose == N)showDetail(null);
			else showDetail(items.get(choose));
			SLEEP(100);
		}
		panel.setVisible(false);
		if(choose == N)return null;
		return items.get(choose);
		/*init();
		System.out.println("run");
		while(true){
			if(key.Q) { key.Q = false; choose = -1;break;}
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
		return choose;*/
	}
	private void SLEEP(int x){
		try {
				Thread.sleep(x);
		} catch (InterruptedException e) {
		}
	}
}