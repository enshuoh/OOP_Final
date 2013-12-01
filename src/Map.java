import java.awt.*;
import javax.swing.*;    
import java.util.*; 
import org.w3c.dom.Node;
import org.w3c.dom.Element;                                                                                                    
public class Map extends JPanel{
	//new
	private ArrayList<Item> items;
	public int ID;
	private Player player;
	private Image mapImage;
	private static Image[] playerImage;
	private Position initPos;
	private static final int stepDistance = 30;
	private boolean fixMap;
	//new
	private boolean isSpaceArray[][];
	private String mapName;
	private Position mapInitPos;
	private ArrayList<LeavePoint> leavePoints;
	public Map(int ID ,Player player){

		this.setPreferredSize(new Dimension(800,600));
		this.ID = ID;
		this.player = player;
		mapInitPos = new Position(94,14);
		init(ID);
	}
	/*
	public Position getInitPos(){
        	return new Position(initPos);
	}
	*/
	private void init(int ID){
		Node node = LoadXML.getMapInfo(ID);
		Element no = (Element)node;
		items = new ArrayList<Item>();
		leavePoints = LoadXML.loadLeavePoints(node);
		setVisible(false);
		fixMap = false;
		if(mapInitPos !=null){
			player.setPos(mapInitPos);
			mapInitPos = null;
			this.ID = ID;
		}
		try{
			mapImage = new ImageIcon(no.getAttribute("image")).getImage();
		}catch(Exception e){
			System.out.println(e);
		}
		if(playerImage == null){
			playerImage = new Image[16];
			for(int i=0;i<16;i++){
				try{
					playerImage[i] = new ImageIcon("image//"+"tori_gaku_"+String.valueOf(i)+".png").getImage();
				}catch(Exception e){
					System.out.println("load error:"+i);
				}
			}
		}
		//
		isSpaceArray = LoadXML.getIsSpace(no.getAttribute("isSpace"));
		//new
		items = LoadXML.getItemInMap(String.valueOf(ID));
		for(int i=0;i<items.size();i++){
			if(items.get(i).isHide(player)){items.remove(items.get(i));i--;}
		}
		setVisible(true);
		//items = new ArrayList<Item>();
	}
	//new
	public Item getItem(Position pos){
		for(int i=0;i<items.size();i++){
			Item tmp = items.get(i);
			if(tmp.getPos().x + tmp.getWidth()-1 < pos.x)continue;
			if(tmp.getPos().x > pos.x + 1)continue;
			if(tmp.getPos().y + tmp.getHeight()-1 < pos.y +1)continue;
			if(tmp.getPos().y > pos.y + 2)continue;
			return tmp;
		}
		return null;
	}
	public void delItem(Item item){
		items.remove(item);
	}
	public boolean isSpace(Position pos){
		if(getItem(pos)!=null)return false;
		if(pos.x<0||pos.y<0) 
			return false;
		return (isSpaceArray[pos.x][pos.y]);
	}
	public void checkLeavePoint(Position pos){
		for(LeavePoint i : leavePoints){
			if(i.mapPos.x == pos.x && i.mapPos.y == pos.y){
				mapInitPos = i.destInitPos;
				init(i.destMapID);
			}
		}		
	}	
	public LeavePoint getLeavePointInfo(Position pos){
		for(LeavePoint i : leavePoints){
			if(i.mapPos.x == pos.x && i.mapPos.y == pos.y)
				return i;
		}
		return null;
	}
	
	@Override
	public void paintComponent(Graphics g){
		int height = this.getParent().getHeight();
		int width = this.getParent().getWidth();
		Position pos = player.getPos();	

		g.fillRect(0,0,width,height);
		if(fixMap){
			int mapWidth = mapImage.getWidth(null);
			int mapHeight = mapImage.getHeight(null);
			int mapPosLx = (width-mapWidth)/2;
			int mapPosLy = (height-mapHeight)/2;
			g.drawImage(mapImage,
				mapPosLx,mapPosLy,
				mapWidth,mapHeight,
				null);
			

			//new
			for(int i=0;i<items.size();i++){
				Item tmp = items.get(i);
				g.drawImage(tmp.image,mapPosLx+tmp.getPos().x*stepDistance,mapPosLy+tmp.getPos().y*stepDistance,tmp.getWidth()*stepDistance,tmp.getHeight()*stepDistance,null);
			}
			//QQ	
			g.drawImage(playerImage[player.getImagenum()],
			mapPosLx+pos.x*stepDistance,mapPosLy+pos.y*stepDistance,stepDistance*2,stepDistance*3,null);
			
			
		}
		else{
			int mapBoundLx = pos.x*stepDistance-width/2;
			int mapBoundLy = pos.y*stepDistance-height/2;
			int mapBoundRx = pos.x*stepDistance+width/2;
			int mapBoundRy = pos.y*stepDistance+height/2;
			g.drawImage(mapImage,
				0,0,width,height,
				mapBoundLx,mapBoundLy,
				mapBoundRx,mapBoundRy,
				null);
			for(int i=0;i<items.size();i++){
				Item tmp = items.get(i);
				int posX = tmp.getPos().x*stepDistance - mapBoundLx;
				int posY =tmp.getPos().y*stepDistance - mapBoundLy;
				g.drawImage(tmp.image,posX,posY,tmp.getWidth()*stepDistance,tmp.getHeight()*stepDistance,null);
			}
			g.drawImage(playerImage[player.getImagenum()],
				width/2,height/2,stepDistance*2,stepDistance*3,null);
		}
	}
}
