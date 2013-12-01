import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException; 

public class LoadXML{
	static Element root;
	static Element itemroot;
	static Element maproot;
	public static void init(){
		DOMParser parser = new DOMParser();
		Document document = parser.parse("XML\\item.xml");
		root = document.getDocumentElement();
		
		DOMParser parser2 = new DOMParser();
		Document document2 = parser.parse("XML\\itemdetail.xml");
		itemroot = document2.getDocumentElement();
		
		DOMParser parser3 = new DOMParser();
		Document document3 = parser.parse("XML\\map.xml");
		maproot = document3.getDocumentElement();		
		
	}
	public static ArrayList<Item> getItemInMap(String mapname){
		ArrayList<Item> array = new ArrayList<Item>();
		
		for(Node node = root.getFirstChild();node!=null;node = node.getNextSibling()){
			if(node.getNodeName().equals("#text"))continue;
			Element no = (Element)node;
			if(no.getAttribute("map").equals(mapname)){
				Position pos = new Position(Integer.valueOf(no.getAttribute("posx")),Integer.valueOf(no.getAttribute("posy")));
				int width = Integer.valueOf(no.getAttribute("width"));
				int height = Integer.valueOf(no.getAttribute("height"));
				array.add(new Item(no.getAttribute("hide"),no.getAttribute("name"),no.getAttribute("image"),pos,width,height,node));
			}
		}
		return array;
	}
	public static Node getItemDetail(String itemname){
		for(Node node = itemroot.getFirstChild();node!=null;node = node.getNextSibling()){
			if(node.getNodeName().equals("#text"))continue;
			Element no = (Element)node;
			if(no.getAttribute("name").equals(itemname)==false) continue;
			return node;
		}
		return null;
	}
	public static Node getMapInfo(int ID){
		for(Node node = maproot.getFirstChild();node!=null;node = node.getNextSibling()){
			if(node.getNodeName().equals("#text"))continue;
			Element no = (Element)node;
			if(no.getAttribute("id").equals(String.valueOf(ID))==false) continue;
			return node;
		}
		return null;
	}
	public static ArrayList<LeavePoint> loadLeavePoints(Node node){
		ArrayList<LeavePoint> leavePoints = new ArrayList<LeavePoint>();
		Element no = (Element)node;
		for(Node tmp = node.getFirstChild();tmp!=null;tmp = tmp.getNextSibling()){
			if(!tmp.getNodeName().equals("leavePoint"))continue;
			Element leavePoint = (Element)tmp;
			int mapX = Integer.parseInt(leavePoint.getAttribute("mapPosX"));
			int mapY = Integer.parseInt(leavePoint.getAttribute("mapPosY"));
			int destMapID = Integer.parseInt(leavePoint.getAttribute("destMapID"));
			int destX = Integer.parseInt(leavePoint.getAttribute("destPosX"));
			int destY = Integer.parseInt(leavePoint.getAttribute("destPosY"));
			
			LeavePoint lp = new LeavePoint(
								new Position(mapX,mapY),
								destMapID,
								new Position(destX,destY)
							);
			leavePoints.add(lp);
		}
		return leavePoints;
	}
	public static boolean[][] getIsSpace(String FileName){
		try{
			Scanner scan = new Scanner(new File(FileName));
		
			int maxX = scan.nextInt();
			int maxY = scan.nextInt();
			boolean isSpace[][] = new boolean[maxX+1][maxY+1];
			for(int y=0;y<=maxY;y++){
				for(int x=0;x<=maxX;x++){
					if(scan.nextInt()==1)
						isSpace[x][y]=true;
				}
			}
			return isSpace;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	

	
	
	public static void main(String[] arg){
		init();
		getItemInMap("123");
	}
}
class DOMParser { 
   DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
   //Load and parse XML file into DOM 
   public Document parse(String filePath) { 
      Document document = null; 
      try { 
         //DOM parser instance 
         DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
         //parse an XML file into a DOM tree 
         document = builder.parse(new File(filePath)); 
      } catch (ParserConfigurationException e) { 
         e.printStackTrace();  
      } catch (SAXException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
      return document; 
   } 
}
