import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;






public class config {
	JPanel confpanel = new JPanel();
	ImageIcon icon = new ImageIcon("src/image/config.png"); //イメージ読み込み
	Image image = icon.getImage();
	public  config(){
		//キー変更読み込み
		ArrayList<String> keyre = new ArrayList<String>();
		File krfile = new File("src\\key.con");
		if(fileio.textread(krfile, keyre)){
			Key.KeyUP = Integer.parseInt(keyre.get(0));
			Key.KeyDOWN = Integer.parseInt(keyre.get(1));
			Key.KeyLEFT = Integer.parseInt(keyre.get(2));
			Key.KeyRIGTH = Integer.parseInt(keyre.get(3));
			Key.KeyCROSS = Integer.parseInt(keyre.get(4));
			Key.KeyCIRCLE = Integer.parseInt(keyre.get(5));
			Key.KeyTRIANGLE = Integer.parseInt(keyre.get(6));
			Key.KeySQUARE = Integer.parseInt(keyre.get(7));
			Key.KeySTART = Integer.parseInt(keyre.get(8));
			Key.KeySELECT = Integer.parseInt(keyre.get(9));
			Key.KeyR1 = Integer.parseInt(keyre.get(10));
			Key.KeyR2 = Integer.parseInt(keyre.get(11));
			Key.KeyL1 = Integer.parseInt(keyre.get(12));
			Key.KeyL2 = Integer.parseInt(keyre.get(13));
		}
		keyre.clear();
	}
	 void printconfig(Graphics g, int width, int height, JPanel panel, int select2,int select3){
		 confpanel = panel;
		 //g.drawImage(image, 0, 0, width, height,confpanel);
		 g.setFont(new Font("Serif", Font.BOLD, width/52+height/40));
		 //g.setColor(Color.red);

		 //選択画面表示
		 
		 g.drawString("〇ボタン",(int) ((width*1/7)),(int) (height*1/10));
		 g.drawString(Integer.toString(Key.KeyCIRCLE)+stradd(Key.KeyCIRCLE),(int) ((width*3/7)),(int) (height*1/10));
		 
		 
		 g.drawString("×ボタン",(int) ((width*1/7)),(int) (height*2/10));
		 g.drawString(Integer.toString(Key.KeyCROSS)+stradd(Key.KeyCROSS),(int) ((width*3/7)),(int) (height*2/10));
		 
		 
		 g.drawString("□ボタン",(int) ((width*1/7)),(int) (height*3/10));
		 g.drawString(Integer.toString(Key.KeySQUARE)+stradd(Key.KeySQUARE),(int) ((width*3/7)),(int) (height*3/10));
		 
		 
		 g.drawString("△ボタン",(int) ((width*1/7)),(int) (height*4/10));
		 g.drawString(Integer.toString(Key.KeyTRIANGLE)+stradd(Key.KeyTRIANGLE),(int) ((width*3/7)),(int) (height*4/10));
		 
		 
		 g.drawString("方向キー上",(int) ((width*1/7)),(int) (height*5/10));
		 g.drawString(Integer.toString(Key.KeyUP)+stradd(Key.KeyUP),(int) ((width*3/7)),(int) (height*5/10));
		 
		 
		 g.drawString("方向キー右",(int) ((width*1/7)),(int) (height*6/10));
		 g.drawString(Integer.toString(Key.KeyRIGTH)+stradd(Key.KeyRIGTH),(int) ((width*3/7)),(int) (height*6/10));
		 
		 
		 g.drawString("方向キー下",(int) ((width*1/7)),(int) (height*7/10));
		 g.drawString(Integer.toString(Key.KeyDOWN)+stradd(Key.KeyDOWN),(int) ((width*3/7)),(int) (height*7/10));
		 
		 
		 
		 g.drawString("方向キー左",(int) ((width*4/7)),(int) (height*1/10));
		 g.drawString(Integer.toString(Key.KeyLEFT)+stradd(Key.KeyLEFT),(int) ((width*6/7)),(int) (height*1/10));
		 
		 
		 g.drawString("R1",(int) ((width*4/7)),(int) (height*2/10));
		 g.drawString(Integer.toString(Key.KeyR1)+stradd(Key.KeyR1),(int) ((width*6/7)),(int) (height*2/10));
		 
		 
		 g.drawString("R2",(int) ((width*4/7)),(int) (height*3/10));
		 g.drawString(Integer.toString(Key.KeyR2)+stradd(Key.KeyR2),(int) ((width*6/7)),(int) (height*3/10));
		 
		 
		 g.drawString("L1",(int) ((width*4/7)),(int) (height*4/10));
		 g.drawString(Integer.toString(Key.KeyL1)+stradd(Key.KeyL1),(int) ((width*6/7)),(int) (height*4/10));
		 
		 
		 g.drawString("L2",(int) ((width*4/7)),(int) (height*5/10));
		 g.drawString(Integer.toString(Key.KeyL2)+stradd(Key.KeyL2),(int) ((width*6/7)),(int) (height*5/10));
		 
		 
		 g.drawString("START",(int) ((width*4/7)),(int) (height*6/10));
		 g.drawString(Integer.toString(Key.KeySTART)+stradd(Key.KeySTART),(int) ((width*6/7)),(int) (height*6/10));
		 
		 
		 g.drawString("SELECT",(int) ((width*4/7)),(int) (height*7/10));
		 g.drawString(Integer.toString(Key.KeySELECT)+stradd(Key.KeySELECT),(int) ((width*6/7)),(int) (height*7/10));
		 
		 
		 g.drawString("初期化",(int) ((width*0.45)),(int) (height*8/10));
		 g.drawString("保存して終了",(int) ((width*0.45)),(int) (height*9/10));
		 
		 //キー選択
		 if(select3 == 0){
			 switch(select2){
				 case 0:
					 g.drawString("→",(int) ((width*1/10)),height*1/10);
					 g.setColor(Color.red);
					 g.drawString("〇ボタン",(int) ((width*1/7)),(int) (height*1/10));
					 break;
				 case 1:
					 g.drawString("→",(int) ((width*1/10)),height*2/10);
					 g.setColor(Color.red);
					 g.drawString("×ボタン",(int) ((width*1/7)),(int) (height*2/10));
					 break;
				 case 2:
					 g.drawString("→",(int) ((width*1/10)),height*3/10);
					 g.setColor(Color.red);
					 g.drawString("□ボタン",(int) ((width*1/7)),(int) (height*3/10));
					 break;
				 case 3:
					 g.drawString("→",(int) ((width*1/10)),height*4/10);
					 g.setColor(Color.red);
					 g.drawString("△ボタン",(int) ((width*1/7)),(int) (height*4/10));
					 break;
				 case 4:
					 g.drawString("→",(int) ((width*1/10)),height*5/10);
					 g.setColor(Color.red);
					 g.drawString("方向キー上",(int) ((width*1/7)),(int) (height*5/10));
					 break;
				 case 5:
					 g.drawString("→",(int) ((width*1/10)),height*6/10);
					 g.setColor(Color.red);
					 g.drawString("方向キー右",(int) ((width*1/7)),(int) (height*6/10));
					 break;
				 case 6:
					 g.drawString("→",(int) ((width*1/10)),height*7/10);
					 g.setColor(Color.red);
					 g.drawString("方向キー下",(int) ((width*1/7)),(int) (height*7/10));
					 break;
				 case 7:
					 g.drawString("→",(int) ((width*0.525)),height*1/10);
					 g.setColor(Color.red);
					 g.drawString("方向キー左",(int) ((width*4/7)),(int) (height*1/10));
					 break;
				 case 8:
					 g.drawString("→",(int) ((width*0.525)),height*2/10);
					 g.setColor(Color.red);
					 g.drawString("R1",(int) ((width*4/7)),(int) (height*2/10));
					 break;
				 case 9:
					 g.drawString("→",(int) ((width*0.525)),height*3/10);
					 g.setColor(Color.red);
					 g.drawString("R2",(int) ((width*4/7)),(int) (height*3/10));
					 break;
				 case 10:
					 g.drawString("→",(int) ((width*0.525)),height*4/10);
					 g.setColor(Color.red);
					 g.drawString("L1",(int) ((width*4/7)),(int) (height*4/10));
					 break;
				 case 11:
					 g.drawString("→",(int) ((width*0.525)),height*5/10);
					 g.setColor(Color.red);
					 g.drawString("L2",(int) ((width*4/7)),(int) (height*5/10));
					 break;
				 case 12:
					 g.drawString("→",(int) ((width*0.525)),height*6/10);
					 g.setColor(Color.red);
					 g.drawString("START",(int) ((width*4/7)),(int) (height*6/10));
					 break;
				 case 13:
					 g.drawString("→",(int) ((width*0.525)),height*7/10);
					 g.setColor(Color.red);
					 g.drawString("SELECT",(int) ((width*4/7)),(int) (height*7/10));
					 break;
				 case 14:
					 g.drawString("→",(int) ((width*0.4)),height*8/10);
					 g.setColor(Color.red);
					 g.drawString("初期化",(int) ((width*0.45)),(int) (height*8/10));
					 break;
				 case 15:
					 g.drawString("→",(int) ((width*0.4)),height*9/10);
					 g.setColor(Color.red);
					 g.drawString("保存して終了",(int) ((width*0.45)),(int) (height*9/10));
					 break;
				 
			 }
		 }
		 
		 else if(select3 == 1){
			 switch(select2){
				 case 0:
					 g.drawString("→",(int) ((width*1/10)),height*1/10);
					 g.setColor(Color.blue);
					 g.drawString("〇ボタン",(int) ((width*1/7)),(int) (height*1/10));
					 break;
				 case 1:
					 g.drawString("→",(int) ((width*1/10)),height*2/10);
					 g.setColor(Color.blue);
					 g.drawString("×ボタン",(int) ((width*1/7)),(int) (height*2/10));
					 break;
				 case 2:
					 g.drawString("→",(int) ((width*1/10)),height*3/10);
					 g.setColor(Color.blue);
					 g.drawString("□ボタン",(int) ((width*1/7)),(int) (height*3/10));
					 break;
				 case 3:
					 g.drawString("→",(int) ((width*1/10)),height*4/10);
					 g.setColor(Color.blue);
					 g.drawString("△ボタン",(int) ((width*1/7)),(int) (height*4/10));
					 break;
				 case 4:
					 g.drawString("→",(int) ((width*1/10)),height*5/10);
					 g.setColor(Color.blue);
					 g.drawString("方向キー上",(int) ((width*1/7)),(int) (height*5/10));
					 break;
				 case 5:
					 g.drawString("→",(int) ((width*1/10)),height*6/10);
					 g.setColor(Color.blue);
					 g.drawString("方向キー右",(int) ((width*1/7)),(int) (height*6/10));
					 break;
				 case 6:
					 g.drawString("→",(int) ((width*1/10)),height*7/10);
					 g.setColor(Color.blue);
					 g.drawString("方向キー下",(int) ((width*1/7)),(int) (height*7/10));
					 break;
				 case 7:
					 g.drawString("→",(int) ((width*0.525)),height*1/10);
					 g.setColor(Color.blue);
					 g.drawString("方向キー左",(int) ((width*4/7)),(int) (height*1/10));
					 break;
				 case 8:
					 g.drawString("→",(int) ((width*0.525)),height*2/10);
					 g.setColor(Color.blue);
					 g.drawString("R1",(int) ((width*4/7)),(int) (height*2/10));
					 break;
				 case 9:
					 g.drawString("→",(int) ((width*0.525)),height*3/10);
					 g.setColor(Color.blue);
					 g.drawString("R2",(int) ((width*4/7)),(int) (height*3/10));
					 break;
				 case 10:
					 g.drawString("→",(int) ((width*0.525)),height*4/10);
					 g.setColor(Color.blue);
					 g.drawString("L1",(int) ((width*4/7)),(int) (height*4/10));
					 break;
				 case 11:
					 g.drawString("→",(int) ((width*0.525)),height*5/10);
					 g.setColor(Color.blue);
					 g.drawString("L2",(int) ((width*4/7)),(int) (height*5/10));
					 break;
				 case 12:
					 g.drawString("→",(int) ((width*0.525)),height*6/10);
					 g.setColor(Color.blue);
					 g.drawString("START",(int) ((width*4/7)),(int) (height*6/10));
					 break;
				 case 13:
					 g.drawString("→",(int) ((width*0.525)),height*7/10);
					 g.setColor(Color.blue);
					 g.drawString("SELECT",(int) ((width*4/7)),(int) (height*7/10));
					 break;
			 }
		 }

	}
	 
	 boolean changconfig(int select2, int Keyboard){
		 int KeyNum = -1;
		 
		 //同じところok
		 if((select2 == 0 && Keyboard == Key.KeyCIRCLE)  || (select2 == 1 && Keyboard == Key.KeyCROSS)
				 || (select2 == 6 && Keyboard == Key.KeyDOWN) || (select2 == 10 && Keyboard == Key.KeyL1)
				 || (select2 == 11 && Keyboard == Key.KeyL2)  || (select2 == 7 && Keyboard == Key.KeyLEFT)
				 || (select2 == 8 && Keyboard == Key.KeyR1)   || (select2 == 9 && Keyboard == Key.KeyR2) 
				 || (select2 == 5 && Keyboard == Key.KeyRIGTH)|| (select2 == 13 && Keyboard == Key.KeySELECT)
				 || (select2 == 2 && Keyboard == Key.KeySQUARE)  || (select2 == 12 && Keyboard == Key.KeySTART)
				 || (select2 == 3 && Keyboard == Key.KeyTRIANGLE)  || (select2 == 4 && Keyboard == Key.KeyUP)) {}
         //変更不可能	 
		 else if(Keyboard == KeyEvent.VK_ESCAPE || Keyboard == KeyEvent.VK_F1|| Keyboard == KeyEvent.VK_F2
				 || Keyboard == KeyEvent.VK_F3|| Keyboard == KeyEvent.VK_F4
				 || Keyboard == Key.KeyCIRCLE  || Keyboard == Key.KeyCROSS ||Keyboard == Key.KeyDOWN
				 || Keyboard == Key.KeyL1 || Keyboard == Key.KeyL2  || Keyboard == Key.KeyLEFT
				 || Keyboard == Key.KeyR1  || Keyboard == Key.KeyR2 || Keyboard == Key.KeyRIGTH
				 || Keyboard == Key.KeySELECT || Keyboard == Key.KeySQUARE  || Keyboard == Key.KeySTART
				 || Keyboard == Key.KeyTRIANGLE  || Keyboard == Key.KeyUP) return false;
		 
		 switch(select2){
		 	case 0:
		 		Key.KeyCIRCLE = Keyboard;
		 		KeyNum = 5;
		 		break;
		 	case 1:
				Key.KeyCROSS = Keyboard;
				KeyNum = 4;
				break;
			case 2:
				Key.KeySQUARE = Keyboard;
				KeyNum = 7;
				break;
			case 3:
				Key.KeyTRIANGLE = Keyboard;
				KeyNum = 6;
				break;
			case 4:
				Key.KeyUP = Keyboard;
				KeyNum = 0;
				break;
			case 5:
				Key.KeyRIGTH = Keyboard;
				KeyNum = 3;
				break;
			case 6:
				Key.KeyDOWN = Keyboard;
				KeyNum = 1;
				break;
			case 7:
				Key.KeyLEFT = Keyboard;
				KeyNum = 2;
				break;
			case 8:
				Key.KeyR1 = Keyboard;
				KeyNum = 10;
				break;
			case 9:
				Key.KeyR2 = Keyboard;
				KeyNum = 11;
				break;
			case 10:
				Key.KeyL1 = Keyboard;
				KeyNum = 12;
				break;
			case 11:
				Key.KeyL2 = Keyboard;
				KeyNum = 13;
				break;
			case 12:
				Key.KeySTART = Keyboard;
				KeyNum = 8;
				break;
			case 13:
				Key.KeySELECT = Keyboard;
				KeyNum = 9;
				break;
		 }
		 
		 return true;
		 
	 }
	 void setconfig(){
			
			Key.KeyCon("KeyUP", KeyEvent.VK_UP);
			Key.KeyCon("KeyDOWN", KeyEvent.VK_DOWN);
			Key.KeyCon("KeyLEFT", KeyEvent.VK_LEFT);
			Key.KeyCon("KeyRIGTH", KeyEvent.VK_RIGHT);
			Key.KeyCon("KeyCROSS", KeyEvent.VK_X);//×
			Key.KeyCon("KeyCIRCLE", KeyEvent.VK_Z);//〇
			Key.KeyCon("KeyTRIANGLE", KeyEvent.VK_S);//△
			Key.KeyCon("KeySQUARE", KeyEvent.VK_A);//□
			Key.KeyCon("KeySTART", KeyEvent.VK_ENTER);
			Key.KeyCon("KeySELECT", KeyEvent.VK_SHIFT);
			Key.KeyCon("KeyR1", KeyEvent.VK_Q);
			Key.KeyCon("KeyR2", KeyEvent.VK_1);
			Key.KeyCon("KeyL1", KeyEvent.VK_W);
			Key.KeyCon("KeyL2", KeyEvent.VK_2);
		 
	 }
	 String stradd(int num){
		 String str1 = "";
		 //ASCII制御文字以外
		 if(num >=42 && num <= 96){
			 str1 = "("+Character.toString((char) num)+")";
		 }
		 //それ以外
		 else{
			 switch(num){
			 	case KeyEvent.VK_UP:
			 		str1 = "(↑)";
			 		break;
			 	case  KeyEvent.VK_DOWN:
			 		str1 = "(↓)";
			 		break;
			 	case  KeyEvent.VK_LEFT:
			 		str1 = "(←)";
			 		break;
			 	case KeyEvent.VK_RIGHT:
			 		str1 = "(→)";
			 		break;
			 	case KeyEvent.VK_ENTER:
			 		str1 = "(ENT)";
			 		break;
			 	case KeyEvent.VK_SHIFT:
			 		str1 = "(SHI)";
			 		break;
			 	case KeyEvent.VK_NUMPAD0:
			 		str1 = "(TK0)";
			 		break;
			 	case KeyEvent.VK_NUMPAD1:
			 		str1 = "(TK1)";
			 		break;
			 	case KeyEvent.VK_NUMPAD2:
			 		str1 = "(TK2)";
			 		break;
			 	case KeyEvent.VK_NUMPAD3:
			 		str1 = "(TK3)";
			 		break;
			 	case KeyEvent.VK_NUMPAD4:
			 		str1 = "(TK4)";
			 		break;
			 	case KeyEvent.VK_NUMPAD5:
			 		str1 = "(TK5)";
			 		break;
			 	case KeyEvent.VK_NUMPAD6:
			 		str1 = "(TK6)";
			 		break;
			 	case KeyEvent.VK_NUMPAD7:
			 		str1 = "(TK7)";
			 		break;
			 	case KeyEvent.VK_NUMPAD8:
			 		str1 = "(TK8)";
			 		break;
			 	case KeyEvent.VK_NUMPAD9:
			 		str1 = "(TK9)";
			 		break;
			 	case KeyEvent.VK_SPACE:
			 		str1 = "(SPA)";
			 		break;
			 	case KeyEvent.VK_BACK_SPACE:
			 		str1 = "(BSPA)";
			 		break;
			 	case KeyEvent.VK_CIRCUMFLEX:
			 		str1 = "(^)";
			 		break;
			 	case KeyEvent.VK_TAB:
			 		str1 = "(TAB)";
			 		break;
			 	case KeyEvent.VK_F5:
			 		str1 = "(F5)";
			 		break;
			 	case KeyEvent.VK_F6:
			 		str1 = "(F6)";
			 		break;
			 	case KeyEvent.VK_F7:
			 		str1 = "(F7)";
			 		break;
			 	case KeyEvent.VK_F8:
			 		str1 = "(F8)";
			 		break;
			 	case KeyEvent.VK_F9:
			 		str1 = "(F9)";
			 		break;
			 	case KeyEvent.VK_F10:
			 		str1 = "(F10)";
			 		break;
			 	case KeyEvent.VK_F11:
			 		str1 = "(F11)";
			 		break;
			 	case KeyEvent.VK_F12:
			 		str1 = "(F12)";
			 		break;
			 	case KeyEvent.VK_COLON:
			 		str1 = "(:)";
			 		break;
			 	case KeyEvent.VK_COMMA:
			 		str1 = "(,)";
			 		break;
			 	case KeyEvent.VK_EQUALS:
			 		str1 = "(=)";
			 		break;
			 	case KeyEvent.VK_PERIOD:
			 		str1 = "(.)";
			 		break;
			 	case KeyEvent.VK_SLASH:
			 		str1 = "(/)";
			 		break;
			 	case KeyEvent.VK_SEMICOLON:
			 		str1 = "(;)";
			 		break;
			 	case KeyEvent.VK_NONCONVERT:
			 		str1 = "(無変換)";
			 		break;
			 	case KeyEvent.VK_PLUS:
			 		str1 = "(+)";
			 		break;
			 	case KeyEvent.VK_WINDOWS:
			 		str1 = "(WIN)";
			 		break;
			 	case KeyEvent.VK_AT:
			 		str1 = "(@)";
			 		break;
			 	case KeyEvent.VK_DELETE:
			 		str1 = "(DEL)";
			 		break;
			 	case KeyEvent.VK_NUM_LOCK:
			 		str1 = "(NLOC)";
			 		break;
			 	case KeyEvent.VK_CONTROL:
			 		str1 = "(CTRL)";
			 		break;
			 	case KeyEvent.VK_ALT:
			 		str1 = "(ALT)";
			 		break;
			 	case KeyEvent.VK_F24:
			 		str1 = "(F24)";
			 		break;
			 	case KeyEvent.VK_F23:
			 		str1 = "(F23)";
			 		break;
			 	case KeyEvent.VK_F22:
			 		str1 = "(F22)";
			 		break;
			 	case KeyEvent.VK_F21:
			 		str1 = "(F21)";
			 		break;
			 	case KeyEvent.VK_F20:
			 		str1 = "(F20)";
			 		break;
			 	case KeyEvent.VK_F13:
			 		str1 = "(F13)";
			 		break;
			 	case KeyEvent.VK_F14:
			 		str1 = "(F14)";
			 		break;
			 	case KeyEvent.VK_F15:
			 		str1 = "(F15)";
			 		break;
			 	case KeyEvent.VK_F16:
			 		str1 = "(F16)";
			 		break;
			 	case KeyEvent.VK_F17:
			 		str1 = "(F17)";
			 		break;
			 	case KeyEvent.VK_F18:
			 		str1 = "(F18)";
			 		break;
			 	case KeyEvent.VK_F19:
			 		str1 = "(F19)";
			 		break;
			 	case KeyEvent.VK_F4:
			 		str1 = "(F4)";
			 		break;
			 	case KeyEvent.VK_F3:
			 		str1 = "(F3)";
			 		break;
			 	case KeyEvent.VK_F2:
			 		str1 = "(F2)";
			 		break;
			 	case KeyEvent.VK_F1:
			 		str1 = "(F1)";
			 		break;
			 	case KeyEvent.VK_ESCAPE:
			 		str1 = "(ESCAPE)";
			 		break;
			 }
		 }
			return str1;
		 
	 }

	/*public class confKeyListener extends KeyAdapter {


		public void keyTyped(KeyEvent e) {


		}


		public void keyPressed(KeyEvent e) {
			int g = e.getKeyCode();
			System.out.println(g);
	
		}

		public void keyReleased(KeyEvent e) {


		}

	}*/
	 /*		 if(Keyboard == KeyEvent.VK_ESCAPE || Keyboard == KeyEvent.VK_F1|| Keyboard == KeyEvent.VK_F2
				 || Keyboard == KeyEvent.VK_F3|| Keyboard == KeyEvent.VK_F4
				 || (select2 != 0 && Keyboard != Key.KeyCIRCLE)  || (select2 != 1 && Keyboard != Key.KeyCROSS) ||(select2 != 6 && Keyboard != Key.KeyDOWN)
				 || (select2 != 10 && Keyboard != Key.KeyL1) || (select2 != 11 && Keyboard != Key.KeyL2)  || (select2 != 7 && Keyboard != Key.KeyLEFT)
				 || (select2 != 8 && Keyboard != Key.KeyR1)  || (select2 != 9 && Keyboard != Key.KeyR2) || (select2 != 5 && Keyboard != Key.KeyRIGTH)
				 || (select2 != 13 && Keyboard != Key.KeySELECT) || (select2 != 2 && Keyboard != Key.KeySQUARE)  || (select2 != 12 && Keyboard != Key.KeySTART)
				 || (select2 != 3 && Keyboard != Key.KeyTRIANGLE)  || (select2 != 4 && Keyboard != Key.KeyUP)) return false;*/


}
