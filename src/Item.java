import java.awt.*;
import javax.swing.*;
import org.w3c.dom.Node;
import java.util.*; 
import org.w3c.dom.Element; 
class Item{
	public Image image;
	public String name,hide;
	private Position pos;
	private int width,height;
	Node node;
	public Item(String hide,String name,String imagePath,Position pos,int width,int height,Node node){
		this.name = name;
		this.hide = hide;
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.node = node;
		try{
			image = new ImageIcon(imagePath).getImage();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}
	public Position getPos(){
		return pos;
	}
	public boolean isHide(Player player){
		if(hide.equals(""))return false;
		return player.haveHide(hide);
	}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	public int action(Player player){
		if(canDO(node,player)==false)return 0;
		DO(node,player);
		return 1;
	}
	public int action(String name,Player player){
		for(Node node = this.node.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("useitem"))continue;
			Element no = (Element)node;
			if(!no.getAttribute("item").equals(name))continue;
			if(canDO(node,player)){
				DO(node,player);
				return 1;
			}
		}
		return 0;
	}
	static void PLAY(Node root,Player player){
		String result = Game.GO(((Element)root).getAttribute("game"),((Element)root).getAttribute("diff"));
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("result"))continue;
			Element no = (Element)node;
			if(no.getAttribute("value").equals(result)){
				if(canDO(node,player)){
					DO(node,player);
				return;
				}
			}
		}
	}
	
	static boolean canDO(Node root,Player player){
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("need"))continue;
			Element no = (Element)node;
			if(!player.haveItem(no.getAttribute("item")))return false;
		}
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("needhide"))continue;
			Element no = (Element)node;
			if(!player.haveHide(no.getAttribute("item")))return false;
		}
		return true;
	}
	static void DO(Node root,Player player){
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("take"))continue;
			Element no = (Element)node;
			player.removeItem(no.getAttribute("item"));
		}
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("takehide"))continue;
			Element no = (Element)node;
			player.removeHide(no.getAttribute("item"));
		}
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("give"))continue;
			Element no = (Element)node;
			player.addItem(no.getAttribute("item"));
		}
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("givehide"))continue;
			Element no = (Element)node;
			player.addHide(no.getAttribute("item"));
		}
		String ans = Talk.say(root.getFirstChild().getTextContent(),((Element)root).getAttribute("end"));
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("play"))continue;
			PLAY(node,player);
		}
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("talk"))continue;
			Element no = (Element)node;
			if(!ans.contains(no.getAttribute("key")))continue;
			if(canDO(node,player)){
				DO(node,player);
				return;
			}
		}
	}
	static void USE(String name,Player player,Item item){
		if(item!=null && item.action(name,player)==1)return;
		Node root = LoadXML.getItemDetail(name);
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(!node.getNodeName().equals("use"))continue;
			if(canDO(node,player)){
				DO(node,player);
				return;
			}
		}
	}
}