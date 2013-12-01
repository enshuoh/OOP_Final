package Game.usage;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class MainRole {
	private int x,y,w,h,tmpLoad;
	private String img_name = "image/mainrole.png";
	private Image img;
	private ArrayList<tear> tear_bullet;
	private int blood;
	public MainRole(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		blood = 100;
		tear_bullet = new ArrayList<tear>();
		ImageIcon img_icon = new ImageIcon(img_name);
		img = img_icon.getImage();
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
	public int getBlood(){
		return blood;
	}
	public Image getI(){
		return img;
	}
	public ArrayList<tear> getB(){
		return tear_bullet;
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}
	public void setY(int y){
		this.y = y;
	}
	public void setBlood(){
		this.blood -= 5;
	}
	public void move(int sy){
		y += sy;
	}
	public void fire(int Load, int number) {
		// if reloading time is done
		//System.out.println("tmpLoad "+tmpLoad);
		if (tmpLoad == 0) {
			for (int i = 0; i < number; i++) {
				tear bullet = new tear(0,0,0,0); 
				// setting the bullet
				bullet.setX(x + w);
				bullet.setY(y + h / 2);
				bullet.setW(20);
				bullet.setH(10);
				// adding the bullet to the array list
				tear_bullet.add(bullet);
				//System.out.println("Loading!!!!!");
			}
			//reset the reload time
			tmpLoad = Load;
		} 
		else {
			tmpLoad -= 1;
		}
	}
	
}
