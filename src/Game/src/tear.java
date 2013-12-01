package Game.usage;
import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;

public class tear{
	private int x,y,w,h;
	private String img_name = "image/tear.png";
	private Image img;
	public tear(){
		
	}
	public tear(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
		x += sx;
	}
}
