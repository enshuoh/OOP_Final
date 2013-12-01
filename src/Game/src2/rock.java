package Game.usage2;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class rock {
	private String[] file_name = {"image/car_1.png","image/car_2.png","image/car_3.png","image/car_4.png"};
	private int x,y,w,h,car;
	private Image img;
	public rock(int x,int y,int w,int h,int car){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.car = car;
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
		ImageIcon img_icon = new ImageIcon(file_name[car]);
		img = img_icon.getImage();
		return img;
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x+3, (int) y+3, w-10, h-20);
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
