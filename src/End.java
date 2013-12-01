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
		 if(i==1){ //�N�n
			Talk.say("�A���\���q�L�F�o�Ǵ�",1);
			Talk.say("�o���g��]���A��ǲ߲��Ϳ@�P������",1);
			Talk.say("���A�j�|�����F���A�i�Ӷ��Q���~",1);
			
		 }
		 else if(i==2){//�t�]
			Talk.say("�g�ѧA������",1);
			Talk.say("��T�t�v���ۦ��@�Ӯհ�",1);
			Talk.say("�Ӧb��t�]���x�\��F�@�ӤQ����j���ɹ�",1);
			Talk.say("�ΨӬ�����T�t�ǥX�դ͢w�j���ӹ�",1);
			
		 }
		  else if(i==3){//�X��
			Talk.say("�A���F�Ƿ~�A��W�F��y��⤧��",1);
			Talk.say("�L�����٤��N",1);
			Talk.say("�ש󦨬��F�@�ɮ�y��",1);
			
		 }
		 else if(i==4){//0�� game over
			Talk.say("�������ڨ��F",1);
			Talk.say("���p�ڻ����a��",1);
			Talk.say("�ڴ��@����S",1);
			Talk.say("���a������Ǥ�",1);
		 }
		 else if(i==5){//final
			Talk.say("�A�����Ffinal project",1);
			Talk.say("���L�P�Ǥ@�_�L�FOOP",1);
			Talk.say("���L�j�|�٦����Y�V���D�Ԧb���ۧA",1);
			Talk.say("�P�ǡI�[�o�a�I",1);
		 }
		 
	}
}