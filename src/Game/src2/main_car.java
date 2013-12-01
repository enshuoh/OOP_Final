package Game.usage2;
import java.awt.*;
import javax.swing.*;
public class main_car {
	private int x,y,w,h;
	private String img_name = "image/car_4.png";
	private Image img;
	public main_car(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
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
		return new Rectangle((int) x+5, (int) y+10, w-10, h-20);
	}
	public void setX(int x){
		this.x = x;
	}
	public void move(int sx){
		x += sx;
	}
}
