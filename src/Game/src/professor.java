package Game.usage;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;

public class professor{
	private int x,y,w,h,tmpLoad;
	private String img_name = "image/professor.png";
	private Image img;
	private ArrayList<weapon> weapon_bullet;
	public static Random ran = new Random();
	public int dir = 1;
	private int blood;
	public professor(int x,int y,int w,int h){
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		blood = 100;
		weapon_bullet = new ArrayList<weapon>();
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
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}
	public ArrayList<weapon> getB(){
		return weapon_bullet;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setBlood(){
		this.blood -= 5;
	}
	public void move(int sy){
		if(ran.nextInt(90) == 5)
			dir *= -1;
		y += sy * dir;
	}
	public void fire(int Load, int number) {
		// if reloading time is done
		//System.out.println("tmpLoad "+tmpLoad);
		if (tmpLoad == 0) {
			for (int i = 0; i < number; i++) {
				weapon bullet = new weapon(0,0,0,0,ran.nextInt(3)); 
				// setting the bullet
				bullet.setX(x - w);
				bullet.setY(y + h / 2);
				bullet.setW(20);
				bullet.setH(10);
				// adding the bullet to the array list
				weapon_bullet.add(bullet);
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
