//1G120080 梅田玲旺
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Mapediter extends JFrame{
	boolean verifieda = true;
	boolean buttonpause = false;
	JTextField texta;
	JLabel labelh;
	JButton buttonb;
	JButton buttonj;
	ButtonModel model;
	static mapchip mc;
	SwingWorker worker;
	RePaintThread rpt = new RePaintThread();
	Thread RPTThread = new Thread(rpt);
	JScrollPane mapediterscroll;
	

	
	static int[][] Map;//レイヤー1 上
	static int[][] Map2;//レイヤー2
	static int[][] Map3;//レイヤー3 下
	static int[][] Map4;//イベント
	static int[][] Map5;//通れるかどうかとかのあれだよあれ
	static int[][] MapImage;//実際に書き込むよう(これが表示される)
	static int MapMode = 0;//Mapのどのレイヤーを編集するか?
	static int BoundaryWidth=16,BoundaryHeight=16; // マップチップ境界
	static int MapChipNumder=-1;//現在選択しているマップチップナンバー
	static int MapPassNumder=-1;//マップが通れるか
	static int cow,row; //マップ縦横 (逆かも?)
	static int MapChipPngWidth = -1; //マップチップの横の長さ
	static int MapChipPngHeight = -1; //マップチップの縦の長さ
	static int MapChipPngWidth_MapChipSizeWidth = -1; //マップチップの横の長さ/マップチップ境界横
	static int MapChipPngHeight_MapChipSizeHeight = -1; //マップチップの縦の長さ/マップチップ境界縦
	static String png=null;
	final static int Width =800, Height = 600;
    int ewidth;
    int eheight;
    MapDraw md = new MapDraw();
    MapChipManage mcm = new MapChipManage();
    static BufferedImage MapEditerBuffer;
	static ImageIcon MapEditerIcon;
	static Image MapEditerImage;
	static Image MapEditerImage2;
	static Image MapEditerImage3;
	static Image MapEditerImage4;//現在使用していない  マップチップの色を変更よう予定
	static ImageIcon iconTmp = new ImageIcon("System/EV 32×32alfa.png");
	static Image MapEditerImage5 = iconTmp.getImage();//Eventよう
	
	static java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.DisplayMode displayMode = env.getDefaultScreenDevice().getDisplayMode();
	// 変数widthとheightに画面の解像度の幅と高さを代入
	static int DesktopWidth = displayMode.getWidth();
	static int DesktopHeight = displayMode.getHeight();
	
	int MouseButtonForward = -1;//MousePush時のボタンの値がはいる
	int MouseButtonX = -1;//MousePush時のXの値がはいる
	int MouseButtonY = -1;//MousePush時のYの値がはいる
	int MouseButtonNowPointX = -1;//現在のマウスの位置X
	int MouseButtonNowPointY = -1;//現在のマウスの位置Y
	
	
	LedSocket s = null;//調光よう
	Random rnd = new Random();
	
	JPanel panelediter = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			


			 //x,y座標獲得
			 ewidth = getWidth();
			 eheight = getHeight();
			// System.out.println(ewidth);
			 //System.out.println(eheight);
			 //System.out.println(getContentPane().getHeight());
			 mapediterscroll.getX();
			 //g.drawImage(image2,xx,yy,xx+16,yy+16,x*2,y*12,x*2+16,y*12+16,null);

			 md.Layer(Map, Map2,Map3,Map4,Map5,MapImage,0,
					 row,cow,BoundaryWidth,BoundaryHeight,
					 mapediterscroll.getViewport().getViewPosition().x,mapediterscroll.getViewport().getViewPosition().y,
					 getContentPane().getWidth(),getContentPane().getHeight());
			 
			 md.show(g,MapEditerImage,MapImage,BoundaryWidth,BoundaryHeight,MapChipPngWidth,
					 mapediterscroll.getViewport().getViewPosition().x,mapediterscroll.getViewport().getViewPosition().y,
					 getContentPane().getWidth(),getContentPane().getHeight(),
					 row,cow,MouseButtonNowPointX,MouseButtonNowPointY);


		 }
	};
	  public static void main(String args[]){
		  
		  //外観変更
		  //とりあえず
		  uniLookAndFeel mes = new uniLookAndFeel(null);
		  //Mapediter me = new Mapediter();
		    //setUndecorated(true);
		  }
		class MyWindowListenerb extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				
				Container cnt = getContentPane();
				int type = JOptionPane.WARNING_MESSAGE;
				type=JOptionPane.showConfirmDialog(cnt, "保存されていないデータは消えますがよろしいですか?", "終了",JOptionPane.YES_NO_OPTION, type);
				if(s != null)
					try {
						s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(type==JOptionPane.YES_OPTION){
					if(Key.mode != 3)System.exit(0);
					else {
						setVisible(false);
						mc.setVisible(false);
					}
				}


			}
		}
		public Mapediter()
		{
			//System.out.println(System.getProperty("java.ext.dirs"));

			//外観変更
			/*try {
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
				}*/

			
			// タイトルの設定
			setTitle("Mapediter");
			//addWindowListener(new MyWindowListenera());
			
			
			//コンポーネント
			JMenuBar mapmake = new JMenuBar();
			JMenu menufile = new JMenu("ファイル");
			JMenuItem menufileitem1 = new JMenuItem("新規作成");
			JMenuItem menufileitem3 = new JMenuItem("保存");
			JMenuItem menufileitem2 = new JMenuItem("ファイル読み込み");
			JMenu layer = new JMenu("レイヤー");
			JCheckBoxMenuItem layeritem1 = new JCheckBoxMenuItem("レイヤー1");
			JCheckBoxMenuItem layeritem2 = new JCheckBoxMenuItem("レイヤー2");
			JCheckBoxMenuItem layeritem3 = new JCheckBoxMenuItem("レイヤー3");
			JCheckBoxMenuItem layeritem4 = new JCheckBoxMenuItem("イベント");
			JCheckBoxMenuItem layeritem5 = new JCheckBoxMenuItem("通過設定");
			ButtonGroup layerGroup = new ButtonGroup();
			layerGroup.add(layeritem1);
			layerGroup.add(layeritem2);
			layerGroup.add(layeritem3);
			layerGroup.add(layeritem4);
			layerGroup.add(layeritem5);
			layeritem1.setSelected(true);
			
			
			
			//add
			add(panelediter, BorderLayout.CENTER);
			setJMenuBar(mapmake);
			//ファイル
			mapmake.add(menufile);
			menufile.add(menufileitem1);
			menufile.add(menufileitem3);
			menufile.add(menufileitem2);
			//レイヤー
			mapmake.add(layer);
			layer.add(layeritem1);
			layer.add(layeritem2);
			layer.add(layeritem3);
			layer.add(layeritem4);
			layer.add(layeritem5);
			
			//リスナ登録
			//ファイル
			menufileitem1.addActionListener(new MenuFileItemActionListener());
			menufileitem2.addActionListener(new MenuFileItemActionListener());
			menufileitem3.addActionListener(new MenuFileItemActionListener());
			//レイヤー
			layeritem1.addActionListener(new LayerItemActionListener());
			layeritem2.addActionListener(new LayerItemActionListener());
			layeritem3.addActionListener(new LayerItemActionListener());
			layeritem4.addActionListener(new LayerItemActionListener());
			layeritem5.addActionListener(new LayerItemActionListener());
			

			
            //フレームの設定
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setModal(true);
			//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new MyWindowListenerb());
			panelediter.addMouseListener(new MapediterMouseListener());
			panelediter.addMouseMotionListener(new MapediterMouseMotionListener());
			setSize(800, 600);
			
			//フレームサイズ
			 int fwidth = getWidth();
			 int fheight = getHeight();
			 System.out.println("フレームサイズ横"+fwidth);
			 System.out.println("フレームサイズ縦"+fheight);
			
	        // サイズ変更禁止
	        //setResizable(false);

			mapediterscroll = new JScrollPane(panelediter);
			mapediterscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
			add(mapediterscroll);
			
			setLocationRelativeTo(null);//フレームを中央に表示
			JFrameSet(Width,Height);//パネルサイズ
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			setVisible(true);
			
			//setUndecorated(true);
			
			ArrayList<String> boundaryR = new ArrayList<String>();
			File boundaryRfile = new File("src\\boundary.con");
			if(fileio.textread(boundaryRfile, boundaryR)){
				BoundaryHeight = Integer.parseInt(boundaryR.get(0));
				BoundaryWidth = Integer.parseInt(boundaryR.get(1));
			}
			boundaryR.clear();
			

			RPTThread.start();
			//rtc = rtc & 0xFF;
			

		}
		public void JFrameSet(int SetWidth,int SetHeight){
			getContentPane().setPreferredSize(new Dimension(SetWidth,SetHeight));
			pack();
		}
		public class MenuFileItemActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(((JMenuItem)e.getSource()).getText()=="新規作成"){
					mapdefinition mc = new mapdefinition();
					panelediter.setPreferredSize(new Dimension(row*BoundaryWidth+10,cow*BoundaryHeight+10));
					panelediter.revalidate();
					repaint();
					if(s == null)
						try {
							s = new LedSocket();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				else if(((JMenuItem)e.getSource()).getText()=="保存"){
				}
				else if(((JMenuItem)e.getSource()).getText()=="ファイル読み込み"){
				}

			}
		}
		public class LayerItemActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(((JMenuItem)e.getSource()).getText()=="レイヤー1"){
					MapMode = 0;
					Mapediter.MapEditerImage = fileio.LoadImage(png);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="レイヤー2"){
					MapMode = 1;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="レイヤー3"){
					MapMode = 2;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="イベント"){
					MapMode = 3;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png,255,255,255,127);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="通過設定"){
					MapMode = 4;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png,255,255,255,127);
					
					repaint();
				}


			}
		}
		public class MapediterMouseListener implements MouseListener{


			public void mouseClicked(MouseEvent e) {
				if(MapMode == 3 && e.getClickCount() == 2){
					mcm.MapChipManageEvent(e);
				}

			}


			public void mousePressed(MouseEvent e) {
				mcm.MapChipManageMapMousePressed(e);
				MouseButtonForward = e.getButton();
				MouseButtonX = e.getX();
				MouseButtonY = e.getY();
				repaint();
				//s.send(rnd.nextInt(30),rnd.nextInt(1001),rnd.nextInt(1001),rnd.nextInt(1001));
			}


			public void mouseReleased(MouseEvent e) {


			}


			public void mouseEntered(MouseEvent e) {


			}

		
			public void mouseExited(MouseEvent e) {


			}



		}
		public class MapediterMouseMotionListener implements MouseMotionListener {


			public void mouseDragged(MouseEvent e) {
				MouseButtonNowPointX = e.getX();
				MouseButtonNowPointY = e.getY();
				/*mcm.MapChipManageMapMouseDragged(e,MouseButtonForward,
						mapchip.MouseButtonX,mapchip.MouseButtonY,
						mapchip.MouseButtonNowPointX,mapchip.MouseButtonNowPointY);*/
				mcm.MapChipManageMapMouseDragged(e, MouseButtonForward);
				repaint();
				//s.send(rnd.nextInt(30),rnd.nextInt(1001),rnd.nextInt(1001),rnd.nextInt(1001),rnd.nextInt(1001));
				
			}



			public void mouseMoved(MouseEvent e) {
				MouseButtonNowPointX = e.getX();
				MouseButtonNowPointY = e.getY();
				//s.send(rnd.nextInt(30),rnd.nextInt(1001),rnd.nextInt(1001),rnd.nextInt(1001),rnd.nextInt(1001));
			}

		}
		
		//repaintでっす
		class RePaintThread implements Runnable{
			public void run(){
			    long error = 0;  
			    int fps = 60;  
			    long idealSleep = (1000 << 16) / fps;  
			    long oldTime;  
			    long newTime = System.currentTimeMillis() << 16; 
			    long sleepTime;
			    while (true) {  
			      oldTime = newTime;  
			      repaint();  
			      newTime = System.currentTimeMillis() << 16;  
			      sleepTime = idealSleep - (newTime - oldTime) - error; // 休止できる時間  
			      if (sleepTime < 0x20000) sleepTime = 0x20000; // 最低でも2msは休止  
			      oldTime = newTime;  
			      try {
					Thread.sleep(sleepTime >> 16);// 休止
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			      newTime = System.currentTimeMillis() << 16;  
			      error = newTime - oldTime - sleepTime; // 休止時間の誤差  
			    }  
			}

		}
				    
}





class mapchip extends JDialog{
	//ImageIcon mapchipicon = new ImageIcon(me.png); //タイトルイメージ読み込み
	//ImageIcon mapchipicon = new ImageIcon(me.png);
	Image mapchipimage = fileio.LoadImage(Mapediter.png);
	MapDraw md = new MapDraw();
	static int MouseButtonX = -1;//MousePush時のXの値がはいる
	static int MouseButtonY = -1;//MousePush時のYの値がはいる
	static int MouseButtonNowPointX = -1;//現在のマウスの位置X
	static int MouseButtonNowPointY = -1;//現在のマウスの位置Y
	static int MouseButtonAllOrOne = 0;//複数選択かどうかのフラグ

	
	
	JPanel PanelMapChip = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 //x,y座標獲得
			 //System.out.println(getWidth());
			 //System.out.println(getHeight());
			/* g.drawImage(mapchipimage,0, 0,this);
			 for(int i=0;i<800;i++){
				 for(int j=0;j<206;j++){
					 g.drawRect(i*me.BoundaryWidth, j*me.BoundaryHeight, me.BoundaryWidth, me.BoundaryHeight);
				 }
			 }*/
			 md.MapChipshow(g, mapchipimage, Mapediter.BoundaryWidth, Mapediter.BoundaryHeight,
					 Mapediter.MapChipPngWidth_MapChipSizeWidth,Mapediter.MapChipPngHeight_MapChipSizeHeight,this,
					 Mapediter.MapChipNumder,MouseButtonX,MouseButtonY,
					 MouseButtonNowPointX,MouseButtonNowPointY);

		 }
	};
	
	public mapchip(){
		//マップチップの大きさを確認
		Mapediter.MapChipPngWidth = mapchipimage.getWidth(this);
		Mapediter.MapChipPngHeight = mapchipimage.getHeight(this);
		Mapediter.MapChipPngWidth_MapChipSizeWidth = Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth;
		Mapediter.MapChipPngHeight_MapChipSizeHeight = Mapediter.MapChipPngHeight/Mapediter.BoundaryHeight;
		
		//マップチップ表示画面の最大の大きさ
		//+18は適当
		int PngWidthMax = Mapediter.MapChipPngWidth+18,PngHeightMax = Mapediter.MapChipPngHeight+18;
		
		setTitle("マップチップ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.print(mapchipimage.getHeight(this));
		//add
		add(PanelMapChip, BorderLayout.CENTER);
		PanelMapChip.addMouseListener(new mapchipMouseListener());
		
		JScrollPane mapchipscroll = new JScrollPane(PanelMapChip);
		add(mapchipscroll);
		//mapchipscroll.setPreferredSize(new Dimension(128,600));
		
		//マップチップ表示画面
		if((Mapediter.DesktopHeight -100) < PngHeightMax)
			PngHeightMax = Mapediter.DesktopHeight -100;
		if((Mapediter.DesktopWidth -100) < PngWidthMax){
			PngWidthMax = Mapediter.MapChipPngWidth -100;
		}
		
		PanelMapChip.addMouseMotionListener(new MapChipMouseMotionListener());
		
		getContentPane().setPreferredSize(new Dimension(PngWidthMax,PngHeightMax));//パネルサイズ
		
		//JFrameSet(mapchipimage.getWidth(this)+2,mapchipimage.getWidth(this)+2);
		//PanelMapChip.setPreferredSize(new Dimension(128,3300));
		pack();
		add(mapchipscroll);
		//setSize(128,600);
		setVisible(true);
		PanelMapChip.setPreferredSize(new Dimension(mapchipimage.getWidth(this),mapchipimage.getHeight(this)));
		PanelMapChip.revalidate();
		repaint();
		
	}
	
	public void MapChipRepaint(){
		repaint();
	}
	
	public void JFrameSet(int SetWidth,int SetHeight){
		getContentPane().setPreferredSize(new Dimension(SetWidth,SetHeight));
		pack();
	}
	public void MapChipImageChange(){
		
	}
	
	public class mapchipMouseListener implements MouseListener {


		public void mouseClicked(MouseEvent e) {

			/*int MouseButtonX = -1;//MousePush時のXの値がはいる
			int MouseButtonY = -1;//MousePush時のYの値がはいる
			int MouseButtonNowPointX = -1;//現在のマウスの位置X
			int MouseButtonNowPointY = -1;//現在のマウスの位置Y*/
		}


		public void mousePressed(MouseEvent e) {
			int x,y,z;
			x= e.getX();
			y=e.getY();
			z=e.getButton();
			MouseButtonX = x;
			MouseButtonY = y;
			MouseButtonAllOrOne = 1;
			
			Mapediter.MapChipNumder =(x/Mapediter.BoundaryWidth)+(y/Mapediter.BoundaryHeight)*(Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth);
			repaint();

		}


		public void mouseReleased(MouseEvent e) {
			MouseButtonX = -1000;
			MouseButtonY = -1000;
			MouseButtonNowPointX = -1000;//現在のマウスの位置X
			MouseButtonNowPointY = -1000;//現在のマウスの位置Y
			MouseButtonAllOrOne = 0;


		}


		public void mouseEntered(MouseEvent e) {


		}

	
		public void mouseExited(MouseEvent e) {


		}

	}
	
	public class MapChipMouseMotionListener extends MouseMotionAdapter {


		public void mouseDragged(MouseEvent e) {
			MouseButtonNowPointX = e.getX();
			MouseButtonNowPointY = e.getY();

			
			repaint();
			
			
		}


	}


}

class mapdefinition extends JDialog implements ActionListener{
	Mapediter me;
	JTextField textcow;
	JTextField textrow;
	JTextField textpng;
	public mapdefinition(){
		setTitle("マップ作成");
		setSize(300,200);
		setModal(true);
		
		//コンポーネント
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("キャンセル");
		JButton buttondetail = new JButton("チップ境界");
		JLabel labelcow = new JLabel("行数");//逆になってね?
		JLabel labelrow = new JLabel("列数");//逆になってね?
		JLabel labelpng = new JLabel("マップチップ");
		textcow = new JTextField();
		textrow = new JTextField();
		textpng = new JTextField("map00.png");
		JPanel panelbutton = new JPanel();
		panelbutton.setLayout(new FlowLayout());
		JPanel panelMapDefinition = new JPanel();
		panelMapDefinition.setLayout(new GridLayout(6,1));

		//add
		panelbutton.add(buttonyes);
		panelbutton.add(buttonno);
		panelbutton.add(buttondetail);
		add(panelbutton, BorderLayout.SOUTH);
		panelMapDefinition.add(labelcow);
		panelMapDefinition.add(textcow);
		panelMapDefinition.add(labelrow);
		panelMapDefinition.add(textrow);
		panelMapDefinition.add(labelpng);
		panelMapDefinition.add(textpng);
		add(panelMapDefinition, BorderLayout.CENTER);
		
		// リスナの登録
		buttonyes.addActionListener(this);
		buttonno.addActionListener(this);
		buttondetail.addActionListener(this);
		
		setResizable(false);
		setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText()=="OK"){
			
			if(Mapediter.mc != null)
				Mapediter.mc.setVisible(false);
				

			try{
				if(Integer.parseInt(textcow.getText()) > 0 && Integer.parseInt(textrow.getText()) > 0){
					//マップ作成
					me.row = Integer.parseInt(textcow.getText());
					me.cow = Integer.parseInt(textrow.getText());
					try{
						me.Map = new int[me.row][me.cow];
						me.Map2 = new int[me.row][me.cow];
						me.Map3 = new int[me.row][me.cow];
						me.Map4 = new int[me.row][me.cow];
						me.Map5 = new int[me.row][me.cow];
						me.MapImage = new int[me.row][me.cow];
					
					}
					catch(OutOfMemoryError erroer1){
						System.out.println("以下エラー内容");
						erroer1.printStackTrace();
						Container cnt = getContentPane();
						int type = JOptionPane.WARNING_MESSAGE;
						JOptionPane.showMessageDialog(cnt, "行数か列数が大きすぎます", "エラー", type);
						return;}
					for(int i=0;i<me.row;i++){
						for(int j=0;j<me.cow;j++){
							me.Map[i][j] = -1;
							me.Map2[i][j] = -1;
							me.Map3[i][j] = -1;
							me.Map4[i][j] = -1;
							me.Map5[i][j] = -1;
						}
					}
					//マップチップ
					me.png = ("map\\"+textpng.getText());
					
					//MapChip画像読み込み
					Mapediter.MapEditerImage = fileio.LoadImage(me.png);
					Mapediter.MapEditerImage2 = fileio.LoadImage(me.png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(me.png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(me.png,255,255,255,127);
					
					Mapediter.mc = new mapchip();
					setVisible(false);
				}
				else JOptionPane.showMessageDialog(mapdefinition.this,"マップの大きさは1x1以上にしてください");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(mapdefinition.this,"0から"+Integer.MAX_VALUE+"までの数値を入力してください");
                textcow.setText("");
                textrow.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="キャンセル") setVisible(false);
		else if(((JButton)e.getSource()).getText()=="チップ境界"){
			MapDetail md = new MapDetail();
		}
	}

}

class MapDetail extends JDialog implements ActionListener{
	Mapediter me;
	JTextField textBoundaryHeight; //マップチップ境界縦
	JTextField textBoundaryWidth; //マップチップ境界横
	public MapDetail(){
		setTitle("マップ境界");
		setSize(190,160);
		setModal(true);
		
		//コンポーネント
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("キャンセル");
		JLabel labelBoundaryWidth = new JLabel("横境界");
		JLabel labelBoundaryHeight = new JLabel("縦境界");
		textBoundaryWidth = new JTextField(Integer.toString(me.BoundaryWidth));
		textBoundaryHeight = new JTextField(Integer.toString(me.BoundaryHeight));
		JPanel panelbutton = new JPanel();
		panelbutton.setLayout(new FlowLayout());
		JPanel panelMapDetail = new JPanel();
		panelMapDetail.setLayout(new GridLayout(4,1));

		
		//add
		panelbutton.add(buttonyes);
		panelbutton.add(buttonno);
		add(panelbutton, BorderLayout.SOUTH);
		panelMapDetail.add(labelBoundaryWidth);
		panelMapDetail.add(textBoundaryWidth);
		panelMapDetail.add(labelBoundaryHeight);
		panelMapDetail.add(textBoundaryHeight);
		add(panelMapDetail, BorderLayout.CENTER);
		
		// リスナの登録
		buttonyes.addActionListener(this);
		buttonno.addActionListener(this);
		
		setResizable(false);
		setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText()=="OK"){
			try{
				if(Integer.parseInt(textBoundaryHeight.getText()) > 0 && Integer.parseInt(textBoundaryWidth.getText()) > 0){
					//マップチップ境界変更
					me.BoundaryHeight = Integer.parseInt(textBoundaryHeight.getText());
					me.BoundaryWidth = Integer.parseInt(textBoundaryWidth.getText());
					//マップチップ境界保存
					ArrayList<String> boundaryw = new ArrayList<String>();
					boundaryw.add(Integer.toString(me.BoundaryHeight));
					boundaryw.add(Integer.toString(me.BoundaryWidth));
					File boundaryWfile = new File("src\\boundary.con");
					try {
						fileio.textwrite(boundaryWfile, boundaryw, boundaryw.size());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					boundaryw.clear();
					setVisible(false);
				}
				else JOptionPane.showMessageDialog(MapDetail.this,"マップの大きさは1x1以上にしてください");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(MapDetail.this,"数値を入力してください");
                textBoundaryWidth.setText("");
                textBoundaryHeight.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="キャンセル") setVisible(false);
	}

}


class MapPass extends JDialog{
	//ImageIcon mapchipicon = new ImageIcon(me.png); //タイトルイメージ読み込み
	//ImageIcon mapchipicon = new ImageIcon(me.png);
	Image mapchipimage = fileio.LoadImage(Mapediter.png);
	MapDraw md = new MapDraw();
	static int MouseButtonX = -1;//MousePush時のXの値がはいる
	static int MouseButtonY = -1;//MousePush時のYの値がはいる
	static int MouseButtonNowPointX = -1;//現在のマウスの位置X
	static int MouseButtonNowPointY = -1;//現在のマウスの位置Y
	static int MouseButtonAllOrOne = 0;//複数選択かどうかのフラグ

	
	
	JPanel PanelMapChip = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 //x,y座標獲得
			 //System.out.println(getWidth());
			 //System.out.println(getHeight());
			/* g.drawImage(mapchipimage,0, 0,this);
			 for(int i=0;i<800;i++){
				 for(int j=0;j<206;j++){
					 g.drawRect(i*me.BoundaryWidth, j*me.BoundaryHeight, me.BoundaryWidth, me.BoundaryHeight);
				 }
			 }*/
			 md.MapChipshow(g, mapchipimage, Mapediter.BoundaryWidth, Mapediter.BoundaryHeight,
					 Mapediter.MapChipPngWidth_MapChipSizeWidth,Mapediter.MapChipPngHeight_MapChipSizeHeight,this,
					 Mapediter.MapChipNumder,MouseButtonX,MouseButtonY,
					 MouseButtonNowPointX,MouseButtonNowPointY);

		 }
	};
	
	public MapPass(){
		//マップチップの大きさを確認
		Mapediter.MapChipPngWidth = mapchipimage.getWidth(this);
		Mapediter.MapChipPngHeight = mapchipimage.getHeight(this);
		Mapediter.MapChipPngWidth_MapChipSizeWidth = Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth;
		Mapediter.MapChipPngHeight_MapChipSizeHeight = Mapediter.MapChipPngHeight/Mapediter.BoundaryHeight;
		
		//マップチップ表示画面の最大の大きさ
		//+18は適当
		int PngWidthMax = Mapediter.MapChipPngWidth+18,PngHeightMax = Mapediter.MapChipPngHeight+18;
		
		setTitle("マップチップ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.print(mapchipimage.getHeight(this));
		//add
		add(PanelMapChip, BorderLayout.CENTER);
		PanelMapChip.addMouseListener(new mapchipMouseListener());
		
		JScrollPane mapchipscroll = new JScrollPane(PanelMapChip);
		add(mapchipscroll);
		//mapchipscroll.setPreferredSize(new Dimension(128,600));
		
		//マップチップ表示画面
		if((Mapediter.DesktopHeight -100) < PngHeightMax)
			PngHeightMax = Mapediter.DesktopHeight -100;
		if((Mapediter.DesktopWidth -100) < PngWidthMax){
			PngWidthMax = Mapediter.MapChipPngWidth -100;
		}
		
		PanelMapChip.addMouseMotionListener(new MapChipMouseMotionListener());
		
		getContentPane().setPreferredSize(new Dimension(PngWidthMax,PngHeightMax));//パネルサイズ
		
		//JFrameSet(mapchipimage.getWidth(this)+2,mapchipimage.getWidth(this)+2);
		//PanelMapChip.setPreferredSize(new Dimension(128,3300));
		pack();
		add(mapchipscroll);
		//setSize(128,600);
		setVisible(true);
		PanelMapChip.setPreferredSize(new Dimension(mapchipimage.getWidth(this),mapchipimage.getHeight(this)));
		PanelMapChip.revalidate();
		repaint();
		
	}
	
	public void MapChipRepaint(){
		repaint();
	}
	
	public void JFrameSet(int SetWidth,int SetHeight){
		getContentPane().setPreferredSize(new Dimension(SetWidth,SetHeight));
		pack();
	}
	public void MapChipImageChange(){
		
	}
	
	public class mapchipMouseListener implements MouseListener {


		public void mouseClicked(MouseEvent e) {

			/*int MouseButtonX = -1;//MousePush時のXの値がはいる
			int MouseButtonY = -1;//MousePush時のYの値がはいる
			int MouseButtonNowPointX = -1;//現在のマウスの位置X
			int MouseButtonNowPointY = -1;//現在のマウスの位置Y*/
		}


		public void mousePressed(MouseEvent e) {
			int x,y,z;
			x= e.getX();
			y=e.getY();
			z=e.getButton();
			MouseButtonX = x;
			MouseButtonY = y;
			MouseButtonAllOrOne = 1;
			
			Mapediter.MapChipNumder =(x/Mapediter.BoundaryWidth)+(y/Mapediter.BoundaryHeight)*(Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth);
			repaint();

		}


		public void mouseReleased(MouseEvent e) {
			MouseButtonX = -1000;
			MouseButtonY = -1000;
			MouseButtonNowPointX = -1000;//現在のマウスの位置X
			MouseButtonNowPointY = -1000;//現在のマウスの位置Y
			MouseButtonAllOrOne = 0;


		}


		public void mouseEntered(MouseEvent e) {


		}

	
		public void mouseExited(MouseEvent e) {


		}

	}
	
	public class MapChipMouseMotionListener extends MouseMotionAdapter {


		public void mouseDragged(MouseEvent e) {
			MouseButtonNowPointX = e.getX();
			MouseButtonNowPointY = e.getY();

			
			repaint();
			
			
		}


	}


}


