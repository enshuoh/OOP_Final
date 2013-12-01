import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;  
import Game.usage.BackGround;
import Game.usage2.BackGround2;
class Game{
	static private Thread loop;
	static JFrame frame;
	static Listen key;
	static public void init(JFrame frame,Listen key){
		Game.frame = frame;
		Game.key = key;
	}
	static public String GO(String gamename,String diff){
		if(gamename.equals("pingpung")){
			BackGround game = new BackGround(frame.getWidth()/2-400,frame.getHeight()/2-300,800,600,frame);
			//BackGround game = new BackGround(frame.getWidth()/2-400,frame.getHeight()/2-300,800,600,frame);
			frame.add(game,-1);
			String result = game.run(diff);
			key.reset();
			frame.remove(game);
			frame.validate();
			frame.repaint();
			System.out.println(result);
			return result;
		}
		if(gamename.equals("car")){
			BackGround2 game = new BackGround2(frame.getWidth()/2-200,frame.getHeight()/2-360,400,720,frame);
			//BackGround game = new BackGround(frame.getWidth()/2-400,frame.getHeight()/2-300,800,600,frame);
			frame.add(game,-1);
			String result = game.run(diff);
			key.reset();
			frame.remove(game);
			frame.validate();
			frame.repaint();
			System.out.println(result);
			return result;
		}
		return "";
	}
}