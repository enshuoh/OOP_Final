import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Listen implements KeyListener{
	public boolean ESC = false, UP = false , DOWN = false, LEFT = false, RIGHT = false, ENTER = false;
	public void reset(){
		ESC = false;
		UP = false; 
		DOWN = false; 
		LEFT = false; 
		RIGHT = false; 
		ENTER = false;
	}
	public void keyPressed(KeyEvent e) {
		int key1 = e.getKeyCode();
		switch (key1) {
			case KeyEvent.VK_UP:
				UP = true;
				System.out.println("up");
				break;

			case KeyEvent.VK_LEFT:
				LEFT = true;
				System.out.println("left");
				break;

			case KeyEvent.VK_DOWN:
				DOWN = true;
				System.out.println("down");
				break;

			case KeyEvent.VK_RIGHT:
				RIGHT = true;
				System.out.println("right");
				break;
			
			case KeyEvent.VK_ENTER:
				ENTER = true;
				System.out.println("enter");
				break;
			
			case  KeyEvent.VK_ESCAPE :
				ESC = true;
				break;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key1 = e.getKeyCode();
		switch (key1) {
			case KeyEvent.VK_UP:
				UP = false;
				System.out.println("upx");
				break;

			case KeyEvent.VK_LEFT:
				LEFT = false;
				System.out.println("leftx");
				break;

			case KeyEvent.VK_DOWN:
				DOWN = false;
				System.out.println("downx");
				break;

			case KeyEvent.VK_RIGHT:
				RIGHT = false;
				System.out.println("rightx");
				break;
			
			
		}
	}
	public void keyTyped(KeyEvent e) {
	}
}