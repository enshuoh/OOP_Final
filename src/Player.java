import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;     


class Status implements Serializable{
		public int posx=0, posy=0, direct=0, imagenum=0, mapID = 1; //direct DLRU
		public ArrayList<String> items  = new ArrayList<String>();
		public ArrayList<String> hideitems = new ArrayList<String>();
	}
public class Player {
	
	Status status;
	Listen key;
	JFrame frame;
	JLabel lbl;
	Menu menu;
	Map map;
	Bag bag;
	public Player(JFrame frame,Listen key,int x){
		this.frame = frame;
		LoadXML.init();
		status = new Status();
		this.key = key;
		frame.add(new Talk(key,frame));
		menu = new Menu(frame,key);
		addHide("饅頭隱藏");
		map = new Map(status.mapID,this);
		bag = new Bag(frame,key);
		Game.init(frame,key);
		//frame.setSize(800, 600);
		
		frame.add(map);
		if(x==1)load();
		frame.setVisible(true);
		if(x==0){
			Talk.say("我叫許恩碩，是個資訊系大三的學生",1);
			Talk.say("現在我面臨了人生中最大的困難",1);
			Talk.say("就是我想不到晚餐要吃什麼!!!",1);
			Talk.say("啊不是，是我OOP要被當了",1);
			Talk.say("這科被當後我就會二一後三二接著四三和五四了",1);
			Talk.say("我的人生不容許如此失敗",1);
			Talk.say("因此，我要改變一切!!",1);
			Talk.say("ESC：選單<br>ENTER：確定<br>  上下左右：上下左右",1);
		}
	}
	private void move(){
		if(key.UP&&map.isSpace(new Position(status.posx,status.posy-1)))status.posy--;
		if(key.DOWN&&map.isSpace(new Position(status.posx,status.posy+1)))status.posy++;
		if(key.LEFT&&map.isSpace(new Position(status.posx-1,status.posy)))status.posx--;
		if(key.RIGHT&&map.isSpace(new Position(status.posx+1,status.posy)))status.posx++;
		
		if(key.UP){
			if(status.direct==3)status.imagenum = (status.imagenum+1)%4;
			else status.imagenum = 0;
			status.direct=3;
		}
		else if(key.DOWN) {
			if(status.direct==0)status.imagenum = (status.imagenum+1)%4;
			else status.imagenum = 0;
			status.direct=0;
		}
		else if(key.LEFT){
			if(status.direct==1)status.imagenum = (status.imagenum+1)%4;
			else status.imagenum = 0;
			status.direct=1;
		}
		else if(key.RIGHT){
			if(status.direct==2)status.imagenum = (status.imagenum+1)%4;
			else status.imagenum = 0;
			status.direct=2;
		}
	}
	private Item getInfrontItem(){
		if(status.direct==3)return map.getItem(new Position(status.posx,status.posy-1));
		if(status.direct==0)return map.getItem(new Position(status.posx,status.posy+1));
		if(status.direct==1)return map.getItem(new Position(status.posx-1,status.posy));
		if(status.direct==2)return map.getItem(new Position(status.posx+1,status.posy));
		return null;
	}
	public void run(){
		while(true){
			if(key.ESC){
				key.ESC = false;
				int choose = menu.run();
				if(choose == -1);
				else if(choose == 1)save();
				else if(choose == 2)load();
				else if(choose == 0){
					String tmp = bag.run(status.items);
					if(tmp != null){
						Item.USE(tmp,this,getInfrontItem());
					}
				}
				else if(choose == 3)break;
				key.reset();
			}
			if(key.ENTER){
				key.ENTER = false;
				Item item = getInfrontItem();
				if(item!=null){
					if(item.action(this)==1){
						if(item.isHide(this))map.delItem(item);
					}
					System.out.println(item.isHide(this));
					key.reset();
				}
				/*if(item instanceof NPC){
					((NPC)item).action(this);
					
				}
				else map.delItem(item);*/
			}
			move();
			map.repaint();
			System.out.println(status.posx+" "+status.posy);
			map.checkLeavePoint(new Position(status.posx,status.posy));
			if(End.isEnd(this)!=0){
				End.GoEnd(frame,End.isEnd(this));
				return;
			} 

			//System.out.println("QQ:"+status.direct+" "+status.imagenum);
			SLEEP(100);
		}
	}
	public boolean haveHide(String name){
		if(name.equals(""))return true;
		return status.hideitems.contains(name);
	}
	public void removeHide(String name){
		status.hideitems.remove(name);
	}
	public void addHide(String name){
		status.hideitems.add(name);
	}
	
	public boolean haveItem(String name){
		if(name.equals(""))return true;
		return status.items.contains(name);
	}
	public void removeItem(String name){
		status.items.remove(name);
	}
	public void addItem(String name){
		status.items.add(name);
	}
	public void setPos(Position pos){
		status.posx = pos.x;
		status.posy = pos.y;
		return;
	}
	public Position getPos(){
		return new Position(this.status.posx,this.status.posy);
	}
	public int getImagenum(){
		return status.direct*4+status.imagenum;
	}
	private void save(){
		try {
			FileOutputStream fs = new FileOutputStream("saveXD");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			status.mapID = map.ID;
			os.writeObject(status);
			os.close();
			fs.close();
			System.out.println("save");
		}
		catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
			System.out.println("not save");
		}
	}
	private void load(){
		try {
            FileInputStream fs = new FileInputStream("saveXD");
            ObjectInputStream is = new ObjectInputStream(fs);
            status = (Status) is.readObject();
			int tmpx=status.posx,tmpy=status.posy,tmpd=status.direct;
			frame.remove(map);
			map = new Map(status.mapID,this);
			frame.add(map);
			status.posx = tmpx;
			status.posy = tmpy;
			status.direct = tmpd;
			map.repaint();
            is.close();
            fs.close();
            System.out.println("load");
			frame.setVisible(true);
		}
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("not load");
        }
	}
	private void SLEEP(int x){
		try {
				Thread.sleep(x);
		} catch (InterruptedException e) {
		}
	}
}
