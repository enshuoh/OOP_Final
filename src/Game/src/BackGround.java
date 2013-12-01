package Game.usage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.awt.Rectangle;

public class BackGround extends JPanel  {
	private String img_name = "image/room2.png";
	private Image img;
	private int x,y,w,h,X,Y;
	private MainRole hero;
	private professor pro;
	private boolean moveUp,moveDown,fire,end_win,end_lost;
	private int sy;
	
	private int load_time,numToShoot;
	JFrame frame;
	public BackGround(int x,int y,int w,int h,JFrame frame){
		//init();
		//addMouseListener(new Mouse());
		//setSize(frame.getWidth(),frame.getHeight());
		//setLocation(0,0);
		//moveUp = moveDown = fire = false;
		//y=20;
		this.y=0;
		this.x=0;
		this.X = x;
		this.Y = y;
		this.w = w;
		this.h = h;
		this.frame = frame;
		sy = 2;
		load_time = 20;
		numToShoot = 1;
		ImageIcon imgicon = new ImageIcon(img_name);
	    img = imgicon.getImage();
		setBackground(new Color(0, 0, 0));
		setDoubleBuffered(true);
		hero = new MainRole(10,300,50,30);
		pro = new professor(700,300,50,70);
		frame.addKeyListener(new Control());
		setFocusable(true);
		end_lost = end_win = false;

	}
	public String run(String diff){
		int load = 1;
		if(diff.equals("1"))load = 1;
		else if(diff.equals("2"))load = 2;
		else if(diff.equals("3"))load = 3;
		else if(diff.equals("4"))load = 4;
		int tmp = 10;
		while(true){
			frame.validate();
			repaint();
			play(load);
			try{
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				
			}
			if(hero.getBlood() <= 0 || pro.getBlood() <= 0){
				tmp--;
			}
			if(tmp == 0){break;}
			if(end_lost)return "lose";
			if(end_win)return "win";
		}
		if(hero.getBlood() <= 0)return "lose";
		else if(pro.getBlood() <= 0)return "win";
		
		return "other";
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	public Image getI(){
		return img;
	}
	public void play(int load){
		if(moveUp){
			if(hero.getY() < 0){
				hero.setY(600);
			}
			hero.move(-sy);
		}
		if(moveDown){
			if(hero.getY() > 600){
				hero.setY(0);
			}
			hero.move(sy);
		}
		if(pro.getY() > 600){
			pro.setY(0);
		}
		else if(pro.getY() < 0){
			pro.setY(600);
		}
		pro.move(sy*load);
		// moving bullets
		ArrayList<tear> tmptear = hero.getB();
		for (int i = 0; i < tmptear.size(); i++) {//System.out.println("Move!!! "+tmptear.size());
			tear tmpt =  tmptear.get(i);
			tmpt.move(3);
			if (tmpt.getX() > 800 || tmpt.getX() < 0 || tmpt.getY() > 600 || tmpt.getY() < 0) {
				tmptear.remove(i);
			}
		}
		ArrayList<weapon> tmpweapon = pro.getB();
		for (int i = 0; i < tmpweapon.size(); i++) {
			weapon tmpw =  tmpweapon.get(i);
			tmpw.move(load*2);
			if (tmpw.getX() > 800 || tmpw.getX() < 0 || tmpw.getY() > 600 || tmpw.getY() < 0) {
				tmpweapon.remove(i);
			}
		}
		// check if shooting
		pro.fire(load_time/load,numToShoot);
		if (fire) {
			System.out.println("BackGround fire");
			hero.fire(load_time, numToShoot);
		}
		attack(load);
	}
	public void attack(int load){
		ArrayList<weapon> tmpweapon = pro.getB();
		ArrayList<tear> tmptear = hero.getB();
		Rectangle r_pro = pro.getBounds();
		Rectangle r_hero = hero.getBounds();
		boolean success = false;
		for(int i=0;i<tmpweapon.size();i++){
			weapon tmp = tmpweapon.get(i);
			success = r_hero.intersects(tmp.getBounds());
			if(success){
				tmpweapon.remove(i);
				hero.setBlood();
				if(load==2){hero.setBlood();hero.setBlood();hero.setBlood();}
				if(load==3){hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();}
				if(load==4){hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();
				hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();hero.setBlood();
				}
				success = false;
				System.out.println("hero blood!!! "+hero.getBlood());
			}
		}
		for(int i=0;i<tmptear.size();i++){
			tear tmp = tmptear.get(i);
			success = r_pro.intersects(tmp.getBounds());
			if(success){
				tmptear.remove(i);
				pro.setBlood();
				success = false;
				System.out.println("Pro blood!!! "+pro.getBlood());
			}
		}
	}
	public void paint(Graphics g) {
		y = Y;
		x = X;
		super.paint(g);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.rotate(hero.getA(), hero.getX() + hero.getW(),hero.getY() + hero.getH() / 2);
		// draw the image
		/*if(hero.getBlood() <= 0){
			ImageIcon end = new ImageIcon("Lose.png");
		    img = end.getImage();
			g2d.drawImage(img,0,0,800,600,this);
			return;
		}
		else if(pro.getBlood() <= 0){
			ImageIcon end = new ImageIcon("Win.png");
		    img = end.getImage();
			g2d.drawImage(img,0,0,800,600,this);
			return;
		}*/
		
		g2d.drawImage(getI(), getX(), getY(), getW(), getH(), this);
		g2d.drawImage(hero.getI(),hero.getX()+x,hero.getY()+y,hero.getW(),hero.getH(), this);
		g2d.drawImage(pro.getI(),pro.getX()+x,pro.getY()+y,pro.getW(),pro.getH(), this);
		ArrayList<tear> tears = hero.getB();
		for (int i = 0; i < tears.size(); i++) {//System.out.println(tears.size());
			tear tmp =  tears.get(i);
			g2d.drawImage(tmp.getI(),tmp.getX()+x,tmp.getY()+y,tmp.getW(),tmp.getH(),this);
		}
		ArrayList<weapon> weapons = pro.getB();
		for (int i = 0; i < weapons.size(); i++) {
			weapon tmp =  weapons.get(i);
			g2d.drawImage(tmp.getI(),tmp.getX()+x,tmp.getY()+y,tmp.getW(),tmp.getH(),this);
		}
		g2d.setColor(new Color(255, 99, 71));
		int tmp_x = 400-2*hero.getBlood();
		g2d.fillRect(tmp_x+x,20+y, 2*hero.getBlood(),10);
		
		g2d.setColor(new Color(0, 0, 128));
		g2d.fillRect(400+x,20+y, 2*pro.getBlood(),10);
		y = 0;
		x = 0;
	}
	private class Control extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moveUp = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveDown = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("Fire!!!!!!");
				fire = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				end_win = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_L) {
				end_lost = true;
			}
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moveUp = false;
		    }
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveDown = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				fire = false;
			}
		}
	}
}
