package Game.usage2;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Add_center{
	private String file_name1 = "image/add.png";
	private int x,y,w,h;
	private Image img;
	public Add_center(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		ImageIcon img_icon = new ImageIcon(file_name1);
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
	public void move(int sy){
		y += sy;
	}
}
