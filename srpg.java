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
	ImageIcon icon = new ImageIcon("src/image/config.png"); //タイトルイメージ読み込み
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
		 タイトルの設定
		setTitle("1G120080  梅田玲旺");


        // サイズ変更禁止
        //setResizable(false);

        

		// コンテナへの追加
		add(panelr, BorderLayout.CENTER); // グラフィックス描画用パネル



		//AudioClip se0 = java.applet.Applet.newAudioClip(Mainmode.class.getResource("SS_SE00.wav"));

		//pong = Applet.newAudioClip(url);

		// リスナの登録
		setFocusable(true);
		addKeyListener(new rpgKeyListener());


		
		// フレームの設定
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
		int xx = 16;//マップチップx
		int yy = 16;//マップチップy
		g.drawImage(image, 0, 0,10,10,100,100, 900, 900,panel);
		ImageIcon icon = new ImageIcon("src/syuzinkou00.png"); //タイトルイメージ読み込み
		Image image = icon.getImage();
		g.drawImage(image, x,y,panel);
		g.drawImage(image,300,300,32,64,null);
		ImageIcon icon2 = new ImageIcon("src/map/map00.png"); //タイトルイメージ読み込み
		Image image2 = icon2.getImage();
		//g.drawImage(image2, 16,16,32,32,300,300,316,316,panel);
		//g.drawImage(image2, 300,300,panel);
		g.drawImage(image2,xx,yy,xx+16,yy+16,x*2,y*12,x*2+16,y*12+16,null);
		//g.drawImage(image2,x出力最初,y出力最初,出力最後,出力最後,元画像最初,元画像最初,元画像最後,元画像最後,null);
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
		// TODO 自動生成されたメソッド・スタブ
		
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
