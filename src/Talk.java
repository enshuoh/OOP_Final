import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;    
import java.util.*;       
class Talk extends JPanel{
	static Image background;
	static JLabel picLabel;
	static JTextField textarea;
	static Listen key;
	static JPanel panel;
	static JFrame frame;
	static boolean ENTER;
	class action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Talk.ENTER = true;
		}
	}
	public Talk(Listen key,JFrame frame){
		this.frame = frame;
		this.key = key;
		panel = this;
		panel.setBounds(frame.getWidth()/2-500,frame.getHeight()-330,1000,300);
		textarea = new JTextField();
		try { 
			background = new ImageIcon("image//"+"talk.png").getImage();
		}catch (Exception ex) {
			System.out.println("lose talkspace");
		}
		background = background.getScaledInstance(1000, 300, Image.SCALE_DEFAULT);
		picLabel = new JLabel("gaga",JLabel.CENTER);
		picLabel.setFont(new java.awt.Font("Dialog", 1, 30));
		picLabel.setBounds(0,0,1000,300);
		//picLabel.setHorizontalTextPosition(JLabel.CENTER);
		setLayout(null);
		add(picLabel);
		textarea.setBounds(40,250,900,30);
		textarea.setFont(new java.awt.Font("Dialog", 1, 30));
		add(textarea);
		textarea.addActionListener(new action());
		textarea.addKeyListener(key);
		//setBackground(null);
		//setOpaque(false);
		setVisible(false);
		setVisible(true);
		setVisible(false);
	}
	static String say(String message,String type){
		if(type=="")return say(message,1);
		else return say(message,Integer.valueOf(type));
	}
	static String say(String message,int type){
		if(type == -1)return "";
		for(int i=0;i<message.length();i++){
			if((int)message.charAt(i)==13){
				message = message.substring(0,i)+"<br>"+message.substring(i+1);
			}
		}
		//System.out.println(message);
		ENTER = false;
		picLabel.setText("<html>"+message+"</html>");
		textarea.setText("");
		panel.setVisible(true);
		if(type != 0){
			textarea.setVisible(false);
			while(key.ENTER == false)SLEEP(100);
			}
		else{
			textarea.requestFocus();
			while(ENTER == false)SLEEP(100);
			}
		key.ENTER = false;
		panel.setVisible(false);
		textarea.setVisible(true);
		frame.requestFocus();
		return textarea.getText();
	}
	public void paintComponent(Graphics g) {
		
		g.drawImage(background, 0,0, this); // see javadoc for more info on the parameters   
		//super.paintComponent(g);	   
		//super.paintComponent(g);      
		
	}
	private static void SLEEP(int x){
		try {
				Thread.sleep(x);
		} catch (InterruptedException e) {
		}
	}
}