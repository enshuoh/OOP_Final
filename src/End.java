import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;     

public class End{
	static int isEnd(Player player){
		int i;
		for(i=1;i<=5;i++){
			if(player.haveHide("End"+i))return i;
		}
		return 0;
	}
	static void GoEnd(JFrame frame,int i){
		 Image background = new ImageIcon("image/final_"+i+".png").getImage();
		 background = background.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
		 JLabel back = new JLabel();
		 back.setBounds(0,0,frame.getWidth(), frame.getHeight());
		 back.setIcon(new ImageIcon(background));
		 frame.add(back,1);
		 frame.validate();
		 frame.repaint();
		 if(i==1){ //烤好
			Talk.say("你成功的通過了這學期",1);
			Talk.say("這次經驗也讓你對學習產生濃烈的興趣",1);
			Talk.say("讓你大四成為了神，進而順利畢業",1);
			
		 }
		 else if(i==2){//系館
			Talk.say("經由你的捐贈",1);
			Talk.say("資訊系逐漸自成一個校區",1);
			Talk.say("而在原系館中庭擺放了一個十倍放大的銅像",1);
			Talk.say("用來紀念資訊系傑出校友─大恩碩像",1);
			
		 }
		  else if(i==3){//出國
			Talk.say("你放棄了學業，踏上了桌球國手之路",1);
			Talk.say("過五關斬六將",1);
			Talk.say("終於成為了世界桌球王",1);
			
		 }
		 else if(i==4){//0分 game over
			Talk.say("輕輕的我走了",1);
			Talk.say("正如我輕輕地來",1);
			Talk.say("我揮一揮衣袖",1);
			Talk.say("不帶走任何學分",1);
		 }
		 else if(i==5){//final
			Talk.say("你完成了final project",1);
			Talk.say("跟其他同學一起過了OOP",1);
			Talk.say("不過大四還有更嚴苛的挑戰在等著你",1);
			Talk.say("同學！加油吧！",1);
		 }
		 
	}
}