import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/*
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class srpg implements Runnable{
	ImageIcon icon = new ImageIcon("src/image/config.png"); //�^�C�g���C���[�W�ǂݍ���
	Image image = icon.getImage();
	JPanel panelr = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 g.drawImage(image, 0, 0, 900, 900,this);
		 }
	};
	
	public static void main(String[] arg){
		srpg blacksatansan = new srpg();
	}
	public srpg(){
		 �^�C�g���̐ݒ�
		setTitle("1G120080  �~�c�扠");


        // �T�C�Y�ύX�֎~
        //setResizable(false);

        

		// �R���e�i�ւ̒ǉ�
		add(panelr, BorderLayout.CENTER); // �O���t�B�b�N�X�`��p�p�l��



		//AudioClip se0 = java.applet.Applet.newAudioClip(Mainmode.class.getResource("SS_SE00.wav"));

		//pong = Applet.newAudioClip(url);

		// ���X�i�̓o�^
		setFocusable(true);
		addKeyListener(new rpgKeyListener());


		
		// �t���[���̐ݒ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(806, 632);
		setVisible(true);
	}
	public srpg(JPanel panel) {

	}
	public srpg(Graphics g, JPanel panel) {
		panelr = panel;
		int x = 16;
		int y = 16;
		int xx = 16;//�}�b�v�`�b�vx
		int yy = 16;//�}�b�v�`�b�vy
		g.drawImage(image, 0, 0,10,10,100,100, 900, 900,panel);
		ImageIcon icon = new ImageIcon("src/syuzinkou00.png"); //�^�C�g���C���[�W�ǂݍ���
		Image image = icon.getImage();
		g.drawImage(image, x,y,panel);
		g.drawImage(image,300,300,32,64,null);
		ImageIcon icon2 = new ImageIcon("src/map/map00.png"); //�^�C�g���C���[�W�ǂݍ���
		Image image2 = icon2.getImage();
		//g.drawImage(image2, 16,16,32,32,300,300,316,316,panel);
		//g.drawImage(image2, 300,300,panel);
		g.drawImage(image2,xx,yy,xx+16,yy+16,x*2,y*12,x*2+16,y*12+16,null);
		//g.drawImage(image2,x�o�͍ŏ�,y�o�͍ŏ�,�o�͍Ō�,�o�͍Ō�,���摜�ŏ�,���摜�ŏ�,���摜�Ō�,���摜�Ō�,null);
		int arr[][] = new int[92][8];
		int r;
		for(int i = 0; i<92;i++){
			for(int j = 0;j<8;j++){
				arr[i][j] = i*8+j;
				//g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*arr[i][j],y*arr[i][j],x*arr[i][j]+16,y*arr[i][j]+16,null);
				g.drawImage(image2,xx*j+20,yy*i+20,xx*j+16+20,yy*i+16+20,(x*arr[i][j])%128,((y*arr[i][j])/128)*yy,(x*arr[i][j])%128+16,(((y*arr[i][j])/128)*yy+16),null);
				System.out.println("x"+(x*arr[i][j])%128+"y"+((y*arr[i][j])/128)*yy+"");
				//System.out.println("x"+(x*arr[i][j])+"y"+(y*arr[i][j])+"");
				/*switch(r){
				case 0:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*r/8,y*r,x*r/8+16,y*r+16,null);
				break;
				case 1:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*2,y*3,x*2+16,y*3+16,null);
				break;
				case 2:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*7,y*9,x*7+16,y*9+16,null);
				break;
				case 3:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				case 4:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				case 5:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				case 6:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				case 7:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				case 8:
				g.drawImage(image2,xx*j,yy*i,xx*j+16,yy*i+16,x*0,y*0,x*0+16,y*0+16,null);
				break;
				}
			}
		}
	}
	@Override
	public void run() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
	public class rpgKeyListener extends KeyAdapter {


		public void keyTyped(KeyEvent e) {


		}


		public void keyPressed(KeyEvent e) {
			int get = e.getKeyCode();
			System.out.println(get+"a");
			
		}

		public void keyReleased(KeyEvent e) {


		}

	}

}*/
