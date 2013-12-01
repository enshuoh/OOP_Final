package Game.usage2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.lang.Math;


public class BackGround2 extends JPanel {
	private int x,y,w,h;
	private String file_name = "image/background_1.png";
	private Image img;
	private int time;
	private int tmp_fire,tmp_add,tmp_sub;
	private ArrayList<rock> rock_list = new ArrayList<rock>();
	private ArrayList<Add_center> right_list = new ArrayList<Add_center>();
	private ArrayList<Add_center> left_list = new ArrayList<Add_center>();
	//private Add_center right,left;
	public static Random ran = new Random();
	private boolean moveR,moveL,end_win,end_lost;
	private main_car mainrole;
	private JFrame frame;
	public BackGround2(int x,int y,int w,int h,JFrame frame){
		moveR = moveL = false;
		end_win = end_lost = false;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.frame = frame;
		tmp_fire = 0;
		tmp_add = 0;
		tmp_sub = 200;
		time = 30;
		mainrole = new main_car(x+20,y+h-220,75,130);
		ImageIcon ImgIcon = new ImageIcon(file_name);
		img = ImgIcon.getImage();
		frame.addKeyListener(new Control());
		
		//right = new Add_center(x+125,y,12,h);
		//left = new Add_center(x+262,y,12,h);
		
	}
	public String run(String diff){
		int level = 5,count = 500;
		while(true){
			frame.validate();
			repaint();
			fall(level);
			if(count == 0){
				level = level+1;
				count = 500;
			}
			else count -= 1;
			if(play(level)){
				try{
					Thread.sleep(10);
				}
				catch(InterruptedException e){
				
				}
			}
			else{
				break;
			}
			if(time <= 0){
				return "win";
			}
			if(end_win){
				return "win";
			}
			if(end_lost){
				return "lose";
			}
		}
		return "lose";
	}
	public boolean play(int level){
		if( crush()){
			return false;
		}
		for (int i=0;i<rock_list.size();i++){
			rock tmp = rock_list.get(i);
			tmp.move(level-3);
			if(tmp.getY() > y+h){
				rock_list.remove(i);
			}
		}
		for (int i=0;i<right_list.size();i++){
			Add_center tmp_addR = right_list.get(i);
			Add_center tmp_addL = left_list.get(i);
			tmp_addR.move(level);
			tmp_addL.move(level);
			if(tmp_addR.getY() > y+h){
				right_list.remove(i);
				left_list.remove(i);
			}
		}
		if(moveR){
			mainrole.move(5);
			if(mainrole.getX() > x+w-75){
				mainrole.setX(x+w-75);
			}
		}
		else if(moveL){
			mainrole.move(-5);
			if(mainrole.getX() < x){
				mainrole.setX(x);
			}
		}
		return true;
		
	}
	public boolean crush(){
		Rectangle r_mainrole = mainrole.getBounds();
		for (int i=0;i<rock_list.size();i++){
			rock tmp = rock_list.get(i);
			if(r_mainrole.intersects(tmp.getBounds())){
				return true;
			}
		}
		return false;
	}
	public void fall(int level){
		int random = ran.nextInt(100)%3;
		if(tmp_fire == 0){
			rock r;
			if(random == 0){
				r = new rock(x+25,y-130,75,130,ran.nextInt(2)+1);
			}
			else if(random == 1){
				r = new rock(x+162,y-130,75,130,ran.nextInt(2)+1);
			}
			else{
				r = new rock(x+300,y-130,75,130,ran.nextInt(2)+1);
			}
			rock_list.add(r);
			tmp_fire = (int) Math.floor(600/(level-3));
		}
		else tmp_fire -= 1;
		if(tmp_add == 0){
			Add_center right = new Add_center(x+125,y-90,12,90);
			Add_center left = new Add_center(x+262,y-90,12,90);
			right_list.add(right);
			left_list.add(left);
			tmp_add = (int) Math.floor(240/level);
		}
		else tmp_add -= 1;
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img,x,y,w,h,this);
		for (int i=0;i<rock_list.size();i++){
			rock tmp = rock_list.get(i);
			g.drawImage(tmp.getI(),tmp.getX(),tmp.getY(),tmp.getW(),tmp.getH(),this);	
		}
		for (int i=0;i<right_list.size();i++){
			Add_center tmp_addR = right_list.get(i);
			Add_center tmp_addL = left_list.get(i);
			g.drawImage(tmp_addR.getI(),tmp_addR.getX(),tmp_addR.getY(),tmp_addR.getW(),tmp_addR.getH(),this);
			g.drawImage(tmp_addL.getI(),tmp_addL.getX(),tmp_addL.getY(),tmp_addL.getW(),tmp_addL.getH(),this);
		}
		//g.drawImage(right.getI(),right.getX(),right.getY(),right.getW(),right.getH(),this);
		//g.drawImage(left.getI(),left.getX(),left.getY(),left.getW(),left.getH(),this);
		g.drawImage(mainrole.getI(),mainrole.getX(),mainrole.getY(),mainrole.getW(),mainrole.getH(),this);
		
		g.setColor(new Color(255,0,0));
		if(tmp_sub == 0){
			time = time-1;
			tmp_sub = 150;
		}
		else tmp_sub -= 1;
		g.fillRect(x-50,y+h-time*10+10, 10,time*10);
	}
	private class Control extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("Right!!!");
				moveR = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("Left!!!");
				moveL = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				end_win = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_L) {
				end_lost = true;
			}
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveR = false;
		    }
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveL = false;
			}
		}
	}
}
