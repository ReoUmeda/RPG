import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;








public class Mainmode extends JFrame implements Runnable{

    int width = getWidth();
    int height = getHeight();
    int select =0;//�^�C�g����ʑI��
    int select3 =0;//Extra�I��
    int select4 =0;//config�L�[�I��
    int select5 =0;//config�L�[�ύX��
    int ch = 0;
    baseframe bf;
    Mapediter me;
    boolean xy = true; //�ŏ����ǂ����̃t���O
	ImageIcon icon = new ImageIcon("src/image/title.png"); //�^�C�g���C���[�W�ǂݍ���
	Image image = icon.getImage();
	Thread th0;
	config conf;
	static Mainmode main;
	//AudioClip se0 = Applet.newAudioClip(getClass().getResource("src/SS_SE001.wav"));
	//URL url = getClass().getClassLoader().getResource("src/SS_SE001.wav");
	

	
	JPanel panel = new JPanel()
	{


		 public void paintComponent(Graphics g)
		 {
			 //x,y���W�l��
			 width = getWidth();
			 height = getHeight();
			 if(xy == true){
				 System.out.println(width);
				 System.out.println(height);
				 xy = false;
			 }

			 //�^�C�g�����
			 if(Key.mode == 0){
				 main.requestFocusInWindow();
				 
				 //�^�C�g���摜
				 //g.drawImage(image, 0, 0, width, height,this);
				 //�I��
				 if(width/height >= 2) g.setFont(new Font("Serif", Font.BOLD, width/110+height/20));
				 else g.setFont(new Font("Serif", Font.BOLD, width/28+height/22));
				 if(select == 0){
					 g.setColor(Color.red);
					 g.drawString("�X�^�[�g",(int) ((width*1/3)*1.3),height*2/3);
					 
					 g.setColor(Color.black);
					 g.drawString("��[��",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.12)+width/80);
					 g.drawString("Extra",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.25)+width/60);
					 g.drawString("�ݒ�",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.4)+width/40);
					 g.drawString("��",(int) ((width*1/3)),height*2/3);
				 }
				 else if(select == 1){
					 g.setColor(Color.red);
					 g.drawString("��[��",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.12)+width/80);
					 
					 g.setColor(Color.black);
					 g.drawString("�X�^�[�g",(int) ((width*1/3)*1.3),height*2/3);
					 g.drawString("Extra",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.25)+width/60);
					 g.drawString("�ݒ�",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.4)+width/40);
					 g.drawString("��",(int) ((width*1/3)),(int) ((height*2/3)*1.12)+width/80);
				 }
				 else if(select == 2){
					 g.setColor(Color.red);
					 g.drawString("Extra",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.25)+width/60);
					 
					 g.setColor(Color.black);
					 g.drawString("�X�^�[�g",(int) ((width*1/3)*1.3),height*2/3);
					 g.drawString("��[��",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.12)+width/80);
					 g.drawString("�ݒ�",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.4)+width/40);
					 g.drawString("��",(int) ((width*1/3)),(int) ((height*2/3)*1.25)+width/60);
				 }
				 else if(select == 3){
					 g.setColor(Color.red);
					 g.drawString("�ݒ�",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.4)+width/40);
					 
					 g.setColor(Color.black);
					 g.drawString("�X�^�[�g",(int) ((width*1/3)*1.3),height*2/3);
					 g.drawString("��[��",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.12)+width/80);
					 g.drawString("Extra",(int) ((width*1/3)*1.3),(int) ((height*2/3)*1.25)+width/60);
					 
					 g.drawString("��",(int) ((width*1/3)),(int) ((height*2/3)*1.4)+width/40);
				 }

				 ch=0;
				 
				 
			 }
			 if(Key.mode == 1){
				/* if(ch==0){
					 srpg satansan = new srpg(g,panel);
					 ch=1;
				 }
				 srpg satansan = new srpg(g,panel);*/
				 
			 }
			 
			 //extra
			 if(Key.mode==3){
				 //requestFocusInWindow();
				 if(width/height >= 2) g.setFont(new Font("Serif", Font.BOLD, width/110+height/20));
				 else g.setFont(new Font("Serif", Font.BOLD, width/28+height/22));
				 if(select3 == 0){
					 g.setColor(Color.red);
					 g.drawString("Base64",(int) ((width*1/5)),height/2);
							 
					 g.setColor(Color.black);
					 g.drawString("�}�b�v�ҏW",(int) (width*3/5),(int) (height/2));
					 g.drawString("��",(int) ((width*1/9)),height/2);
					 }
				 else if(select3 == 1){
					 g.setColor(Color.red);
					 g.drawString("�}�b�v�ҏW",(int) (width*3/5),(int) (height/2));
							 
					 g.setColor(Color.black);
					 g.drawString("Base64",(int) (width*1/5),height/2);
	
					 g.drawString("��",(int) ((width*4/8)),(int) (height/2));
					 }
					 //baseframe bf = new baseframe();
					 //ch=1;
				 
			 }
			 
			 //�ݒ�
			 if(Key.mode==4){
				 //requestFocusInWindow();

				 conf.printconfig(g,width,height,panel,select4,select5);
			 }
	



		 }

	};
	
	public static void main(String args[]){
		//Mainmode main = new Mainmode();
		//main.Mainmodea();
		main = new Mainmode();

	}
	public Mainmode()
	{
		 try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//�O�ϕύX
			try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					JFrame.setDefaultLookAndFeelDecorated(true);
					SwingUtilities.updateComponentTreeUI(this);
					System.out.println(UIManager.getInstalledLookAndFeels());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// �^�C�g���̐ݒ�
		setTitle("1G120080  �~�c�扠");


        // �T�C�Y�ύX�֎~
        //setResizable(false);



        //�L�[�ݒ�
        conf = new config();
        
        

		// �R���e�i�ւ̒ǉ�
		add(panel, BorderLayout.CENTER); // �O���t�B�b�N�X�`��p�p�l��



		//AudioClip se0 = java.applet.Applet.newAudioClip(Mainmode.class.getResource("SS_SE00.wav"));

		//pong = Applet.newAudioClip(url);

		// ���X�i�̓o�^
		setFocusable(true);
		addMouseListener(new MainMouseListener());
		addKeyListener(new MainKeyListener());

		th0 = new Thread(this);
		th0.start();
		
		// �t���[���̐ݒ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(806, 632);
		setVisible(true);
		
		
		//AudioClip se0 = Applet.newAudioClip(getClass().getResource("src/BGM/tam-g07.mid"));
	}
	public class MainMouseListener extends MouseAdapter {


		public void mousePressed(MouseEvent e) {
			int x,y,z;
			x= e.getX();
			y=e.getY();
			z=e.getButton();
			System.out.println("mousepress y"+y+",x"+x+"");
			
		}

	}
	public class MainKeyListener extends KeyAdapter {


		public void keyTyped(KeyEvent e) {


		}


		public void keyPressed(KeyEvent e) {
			int get = e.getKeyCode();
			System.out.println(get);
			
			//���[�h�I��
			if(Key.mode == 0){
				if(Key.KeyUP == get){
					select = (select-1)%4;
					if(select == -1) select = 3;
					Toolkit.getDefaultToolkit().beep();
				}
				else if(Key.KeyDOWN == get){
					select = (select+1)%4;
					//se0.play();
					//pong.play();
				}
				else if(Key.KeyCIRCLE == get || Key.KeySTART == get){
					if(select == 0)Key.mode = 1;
					//else if(select == 1)Key.mode = 2;
					else if(select == 2)Key.mode = 3;
					else if(select == 3)Key.mode = 4;
				}
	
				repaint();
			}
			
			//Extra�p
			else if(Key.mode == 3){
				if(Key.KeyUP == get){
					select3 = (select3-1)%2;
					if(select3 == -1) select3 = 1;
					Toolkit.getDefaultToolkit().beep();
				}
				else if(Key.KeyDOWN == get){
					select3 = (select3+1)%2;
					//se0.play();
					//pong.play();
				}
				else if(Key.KeyRIGTH == get){
					if(select3<14)select3 = (select3+1)%2;
				}
				else if(Key.KeyLEFT == get){ 
					if(select3<2){
						select3 = (select3-1)%2;
						if(select3 < 0) select3 = 2+select3;
						
					}
					
				}
				else if(Key.KeyCIRCLE == get || Key.KeySTART == get){
					if(select3 == 0) bf = new baseframe();
					else if(select3 == 1) me = new Mapediter();
				}
	
				repaint();
			}
			//�L�[�ݒ�
			else if(Key.mode == 4){
				if(select5 == 0){
					if(Key.KeyUP == get){
						select4 = (select4-1)%16;
						if(select4 == -1) select4 = 15;
						Toolkit.getDefaultToolkit().beep();
						}
					else if(Key.KeyDOWN == get) select4 = (select4+1)%16;
					else if(Key.KeyRIGTH == get){
						if(select4<14)select4 = (select4+7)%14;
					}
					else if(Key.KeyLEFT == get){ 
						if(select4<14){
							select4 = (select4-7)%14;
							if(select4 < 0) select4 = 14+select4;
							
						}
						
					}
					else if(Key.KeyCIRCLE == get || Key.KeySTART == get){
						//������
						if(select4 == 14) conf.setconfig();
						//�t�@�C���ɕۑ����ă^�C�g���ɖ߂�
						else if(select4 == 15){
							ArrayList<String> keywr = new ArrayList<String>();
							keywr.add(Integer.toString(Key.KeyUP));
							keywr.add(Integer.toString(Key.KeyDOWN));
							keywr.add(Integer.toString(Key.KeyLEFT));
							keywr.add(Integer.toString(Key.KeyRIGTH));
							keywr.add(Integer.toString(Key.KeyCROSS));
							keywr.add(Integer.toString(Key.KeyCIRCLE));
							keywr.add(Integer.toString(Key.KeyTRIANGLE));
							keywr.add(Integer.toString(Key.KeySQUARE));
							keywr.add(Integer.toString(Key.KeySTART));
							keywr.add(Integer.toString(Key.KeySELECT));
							keywr.add(Integer.toString(Key.KeyR1));
							keywr.add(Integer.toString(Key.KeyR2));
							keywr.add(Integer.toString(Key.KeyL1));
							keywr.add(Integer.toString(Key.KeyL2));
							File kwfile = new File("src\\key.con");
							try {
								fileio.textwrite(kwfile, keywr, keywr.size());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							keywr.clear();
							Key.mode = 0;
							select4 = 0;
						}
						else select5 = 1;
					}
					else if(Key.KeyCROSS == get){
						//�ۑ����ďI���������ꍇ�L�[�����������ďI��
						if(select4 == 15){
							conf.setconfig();
							Key.mode = 0;
							select4 = 0;
						}
						//�ۑ����ďI���ɔ��
						select4 = 15;
					}
					repaint();
				}
				else if(select5 == 1){
					if(conf.changconfig(select4, get) == true){
						select5 = 0;
						repaint();
					}
					else ;
					
				}
			}
		}

		public void keyReleased(KeyEvent e) {


		}

	}

	public void run() {

		for(;;){
			repaint();
			try {
				Thread.sleep(10);
				} catch (InterruptedException e) {}
		}
		
	}



}



//�h�b�g�G���E
//http://yms.main.jp/page2/tile-townI01.html
//TAM Music Factory
//http://www.tam-music.com/
