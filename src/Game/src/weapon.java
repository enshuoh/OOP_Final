package Game.usage;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class weapon {
	private int x,y,w,h,d;
	private String img_name = "image/weapon.png";
	private Image img;
	public weapon (int x,int y,int w,int h,int d){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.d = d;
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
	public Image getI(){
		return img;
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}
	public void setY(int y){
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setW(int w){
		this.w = w;
	}
	public void setH(int h){
		this.h = h;
	}
	public void move(int sx){
		if(d==1)y++;
		else if(d==2)y--;
		x -= sx;
	}
}
