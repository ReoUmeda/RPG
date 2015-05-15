//1G120080 �~�c�扠
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
	

	
	static int[][] Map;//���C���[1 ��
	static int[][] Map2;//���C���[2
	static int[][] Map3;//���C���[3 ��
	static int[][] Map4;//�C�x���g
	static int[][] Map5;//�ʂ�邩�ǂ����Ƃ��̂��ꂾ�悠��
	static int[][] MapImage;//���ۂɏ������ނ悤(���ꂪ�\�������)
	static int MapMode = 0;//Map�̂ǂ̃��C���[��ҏW���邩?
	static int BoundaryWidth=16,BoundaryHeight=16; // �}�b�v�`�b�v���E
	static int MapChipNumder=-1;//���ݑI�����Ă���}�b�v�`�b�v�i���o�[
	static int MapPassNumder=-1;//�}�b�v���ʂ�邩
	static int cow,row; //�}�b�v�c�� (�t����?)
	static int MapChipPngWidth = -1; //�}�b�v�`�b�v�̉��̒���
	static int MapChipPngHeight = -1; //�}�b�v�`�b�v�̏c�̒���
	static int MapChipPngWidth_MapChipSizeWidth = -1; //�}�b�v�`�b�v�̉��̒���/�}�b�v�`�b�v���E��
	static int MapChipPngHeight_MapChipSizeHeight = -1; //�}�b�v�`�b�v�̏c�̒���/�}�b�v�`�b�v���E�c
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
	static Image MapEditerImage4;//���ݎg�p���Ă��Ȃ�  �}�b�v�`�b�v�̐F��ύX�悤�\��
	static ImageIcon iconTmp = new ImageIcon("System/EV 32�~32alfa.png");
	static Image MapEditerImage5 = iconTmp.getImage();//Event�悤
	
	static java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.DisplayMode displayMode = env.getDefaultScreenDevice().getDisplayMode();
	// �ϐ�width��height�ɉ�ʂ̉𑜓x�̕��ƍ�������
	static int DesktopWidth = displayMode.getWidth();
	static int DesktopHeight = displayMode.getHeight();
	
	int MouseButtonForward = -1;//MousePush���̃{�^���̒l���͂���
	int MouseButtonX = -1;//MousePush����X�̒l���͂���
	int MouseButtonY = -1;//MousePush����Y�̒l���͂���
	int MouseButtonNowPointX = -1;//���݂̃}�E�X�̈ʒuX
	int MouseButtonNowPointY = -1;//���݂̃}�E�X�̈ʒuY
	
	
	LedSocket s = null;//�����悤
	Random rnd = new Random();
	
	JPanel panelediter = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			


			 //x,y���W�l��
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
		  
		  //�O�ϕύX
		  //�Ƃ肠����
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
				type=JOptionPane.showConfirmDialog(cnt, "�ۑ�����Ă��Ȃ��f�[�^�͏����܂�����낵���ł���?", "�I��",JOptionPane.YES_NO_OPTION, type);
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

			//�O�ϕύX
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

			
			// �^�C�g���̐ݒ�
			setTitle("Mapediter");
			//addWindowListener(new MyWindowListenera());
			
			
			//�R���|�[�l���g
			JMenuBar mapmake = new JMenuBar();
			JMenu menufile = new JMenu("�t�@�C��");
			JMenuItem menufileitem1 = new JMenuItem("�V�K�쐬");
			JMenuItem menufileitem3 = new JMenuItem("�ۑ�");
			JMenuItem menufileitem2 = new JMenuItem("�t�@�C���ǂݍ���");
			JMenu layer = new JMenu("���C���[");
			JCheckBoxMenuItem layeritem1 = new JCheckBoxMenuItem("���C���[1");
			JCheckBoxMenuItem layeritem2 = new JCheckBoxMenuItem("���C���[2");
			JCheckBoxMenuItem layeritem3 = new JCheckBoxMenuItem("���C���[3");
			JCheckBoxMenuItem layeritem4 = new JCheckBoxMenuItem("�C�x���g");
			JCheckBoxMenuItem layeritem5 = new JCheckBoxMenuItem("�ʉߐݒ�");
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
			//�t�@�C��
			mapmake.add(menufile);
			menufile.add(menufileitem1);
			menufile.add(menufileitem3);
			menufile.add(menufileitem2);
			//���C���[
			mapmake.add(layer);
			layer.add(layeritem1);
			layer.add(layeritem2);
			layer.add(layeritem3);
			layer.add(layeritem4);
			layer.add(layeritem5);
			
			//���X�i�o�^
			//�t�@�C��
			menufileitem1.addActionListener(new MenuFileItemActionListener());
			menufileitem2.addActionListener(new MenuFileItemActionListener());
			menufileitem3.addActionListener(new MenuFileItemActionListener());
			//���C���[
			layeritem1.addActionListener(new LayerItemActionListener());
			layeritem2.addActionListener(new LayerItemActionListener());
			layeritem3.addActionListener(new LayerItemActionListener());
			layeritem4.addActionListener(new LayerItemActionListener());
			layeritem5.addActionListener(new LayerItemActionListener());
			

			
            //�t���[���̐ݒ�
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setModal(true);
			//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new MyWindowListenerb());
			panelediter.addMouseListener(new MapediterMouseListener());
			panelediter.addMouseMotionListener(new MapediterMouseMotionListener());
			setSize(800, 600);
			
			//�t���[���T�C�Y
			 int fwidth = getWidth();
			 int fheight = getHeight();
			 System.out.println("�t���[���T�C�Y��"+fwidth);
			 System.out.println("�t���[���T�C�Y�c"+fheight);
			
	        // �T�C�Y�ύX�֎~
	        //setResizable(false);

			mapediterscroll = new JScrollPane(panelediter);
			mapediterscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
			add(mapediterscroll);
			
			setLocationRelativeTo(null);//�t���[���𒆉��ɕ\��
			JFrameSet(Width,Height);//�p�l���T�C�Y
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
				if(((JMenuItem)e.getSource()).getText()=="�V�K�쐬"){
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
				else if(((JMenuItem)e.getSource()).getText()=="�ۑ�"){
				}
				else if(((JMenuItem)e.getSource()).getText()=="�t�@�C���ǂݍ���"){
				}

			}
		}
		public class LayerItemActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(((JMenuItem)e.getSource()).getText()=="���C���[1"){
					MapMode = 0;
					Mapediter.MapEditerImage = fileio.LoadImage(png);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="���C���[2"){
					MapMode = 1;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="���C���[3"){
					MapMode = 2;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="�C�x���g"){
					MapMode = 3;
					Mapediter.MapEditerImage = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage2 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(png,255,255,255,127);
					repaint();
				}
				else if(((JMenuItem)e.getSource()).getText()=="�ʉߐݒ�"){
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
		
		//repaint�ł���
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
			      sleepTime = idealSleep - (newTime - oldTime) - error; // �x�~�ł��鎞��  
			      if (sleepTime < 0x20000) sleepTime = 0x20000; // �Œ�ł�2ms�͋x�~  
			      oldTime = newTime;  
			      try {
					Thread.sleep(sleepTime >> 16);// �x�~
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			      newTime = System.currentTimeMillis() << 16;  
			      error = newTime - oldTime - sleepTime; // �x�~���Ԃ̌덷  
			    }  
			}

		}
				    
}





class mapchip extends JDialog{
	//ImageIcon mapchipicon = new ImageIcon(me.png); //�^�C�g���C���[�W�ǂݍ���
	//ImageIcon mapchipicon = new ImageIcon(me.png);
	Image mapchipimage = fileio.LoadImage(Mapediter.png);
	MapDraw md = new MapDraw();
	static int MouseButtonX = -1;//MousePush����X�̒l���͂���
	static int MouseButtonY = -1;//MousePush����Y�̒l���͂���
	static int MouseButtonNowPointX = -1;//���݂̃}�E�X�̈ʒuX
	static int MouseButtonNowPointY = -1;//���݂̃}�E�X�̈ʒuY
	static int MouseButtonAllOrOne = 0;//�����I�����ǂ����̃t���O

	
	
	JPanel PanelMapChip = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 //x,y���W�l��
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
		//�}�b�v�`�b�v�̑傫�����m�F
		Mapediter.MapChipPngWidth = mapchipimage.getWidth(this);
		Mapediter.MapChipPngHeight = mapchipimage.getHeight(this);
		Mapediter.MapChipPngWidth_MapChipSizeWidth = Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth;
		Mapediter.MapChipPngHeight_MapChipSizeHeight = Mapediter.MapChipPngHeight/Mapediter.BoundaryHeight;
		
		//�}�b�v�`�b�v�\����ʂ̍ő�̑傫��
		//+18�͓K��
		int PngWidthMax = Mapediter.MapChipPngWidth+18,PngHeightMax = Mapediter.MapChipPngHeight+18;
		
		setTitle("�}�b�v�`�b�v");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.print(mapchipimage.getHeight(this));
		//add
		add(PanelMapChip, BorderLayout.CENTER);
		PanelMapChip.addMouseListener(new mapchipMouseListener());
		
		JScrollPane mapchipscroll = new JScrollPane(PanelMapChip);
		add(mapchipscroll);
		//mapchipscroll.setPreferredSize(new Dimension(128,600));
		
		//�}�b�v�`�b�v�\�����
		if((Mapediter.DesktopHeight -100) < PngHeightMax)
			PngHeightMax = Mapediter.DesktopHeight -100;
		if((Mapediter.DesktopWidth -100) < PngWidthMax){
			PngWidthMax = Mapediter.MapChipPngWidth -100;
		}
		
		PanelMapChip.addMouseMotionListener(new MapChipMouseMotionListener());
		
		getContentPane().setPreferredSize(new Dimension(PngWidthMax,PngHeightMax));//�p�l���T�C�Y
		
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

			/*int MouseButtonX = -1;//MousePush����X�̒l���͂���
			int MouseButtonY = -1;//MousePush����Y�̒l���͂���
			int MouseButtonNowPointX = -1;//���݂̃}�E�X�̈ʒuX
			int MouseButtonNowPointY = -1;//���݂̃}�E�X�̈ʒuY*/
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
			MouseButtonNowPointX = -1000;//���݂̃}�E�X�̈ʒuX
			MouseButtonNowPointY = -1000;//���݂̃}�E�X�̈ʒuY
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
		setTitle("�}�b�v�쐬");
		setSize(300,200);
		setModal(true);
		
		//�R���|�[�l���g
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("�L�����Z��");
		JButton buttondetail = new JButton("�`�b�v���E");
		JLabel labelcow = new JLabel("�s��");//�t�ɂȂ��Ă�?
		JLabel labelrow = new JLabel("��");//�t�ɂȂ��Ă�?
		JLabel labelpng = new JLabel("�}�b�v�`�b�v");
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
		
		// ���X�i�̓o�^
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
					//�}�b�v�쐬
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
						System.out.println("�ȉ��G���[���e");
						erroer1.printStackTrace();
						Container cnt = getContentPane();
						int type = JOptionPane.WARNING_MESSAGE;
						JOptionPane.showMessageDialog(cnt, "�s�����񐔂��傫�����܂�", "�G���[", type);
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
					//�}�b�v�`�b�v
					me.png = ("map\\"+textpng.getText());
					
					//MapChip�摜�ǂݍ���
					Mapediter.MapEditerImage = fileio.LoadImage(me.png);
					Mapediter.MapEditerImage2 = fileio.LoadImage(me.png,255,255,255,127);
					Mapediter.MapEditerImage3 = fileio.LoadImage(me.png,255,255,255,127);
					Mapediter.MapEditerImage4 = fileio.LoadImage(me.png,255,255,255,127);
					
					Mapediter.mc = new mapchip();
					setVisible(false);
				}
				else JOptionPane.showMessageDialog(mapdefinition.this,"�}�b�v�̑傫����1x1�ȏ�ɂ��Ă�������");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(mapdefinition.this,"0����"+Integer.MAX_VALUE+"�܂ł̐��l����͂��Ă�������");
                textcow.setText("");
                textrow.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="�L�����Z��") setVisible(false);
		else if(((JButton)e.getSource()).getText()=="�`�b�v���E"){
			MapDetail md = new MapDetail();
		}
	}

}

class MapDetail extends JDialog implements ActionListener{
	Mapediter me;
	JTextField textBoundaryHeight; //�}�b�v�`�b�v���E�c
	JTextField textBoundaryWidth; //�}�b�v�`�b�v���E��
	public MapDetail(){
		setTitle("�}�b�v���E");
		setSize(190,160);
		setModal(true);
		
		//�R���|�[�l���g
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("�L�����Z��");
		JLabel labelBoundaryWidth = new JLabel("�����E");
		JLabel labelBoundaryHeight = new JLabel("�c���E");
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
		
		// ���X�i�̓o�^
		buttonyes.addActionListener(this);
		buttonno.addActionListener(this);
		
		setResizable(false);
		setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText()=="OK"){
			try{
				if(Integer.parseInt(textBoundaryHeight.getText()) > 0 && Integer.parseInt(textBoundaryWidth.getText()) > 0){
					//�}�b�v�`�b�v���E�ύX
					me.BoundaryHeight = Integer.parseInt(textBoundaryHeight.getText());
					me.BoundaryWidth = Integer.parseInt(textBoundaryWidth.getText());
					//�}�b�v�`�b�v���E�ۑ�
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
				else JOptionPane.showMessageDialog(MapDetail.this,"�}�b�v�̑傫����1x1�ȏ�ɂ��Ă�������");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(MapDetail.this,"���l����͂��Ă�������");
                textBoundaryWidth.setText("");
                textBoundaryHeight.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="�L�����Z��") setVisible(false);
	}

}


class MapPass extends JDialog{
	//ImageIcon mapchipicon = new ImageIcon(me.png); //�^�C�g���C���[�W�ǂݍ���
	//ImageIcon mapchipicon = new ImageIcon(me.png);
	Image mapchipimage = fileio.LoadImage(Mapediter.png);
	MapDraw md = new MapDraw();
	static int MouseButtonX = -1;//MousePush����X�̒l���͂���
	static int MouseButtonY = -1;//MousePush����Y�̒l���͂���
	static int MouseButtonNowPointX = -1;//���݂̃}�E�X�̈ʒuX
	static int MouseButtonNowPointY = -1;//���݂̃}�E�X�̈ʒuY
	static int MouseButtonAllOrOne = 0;//�����I�����ǂ����̃t���O

	
	
	JPanel PanelMapChip = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 //x,y���W�l��
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
		//�}�b�v�`�b�v�̑傫�����m�F
		Mapediter.MapChipPngWidth = mapchipimage.getWidth(this);
		Mapediter.MapChipPngHeight = mapchipimage.getHeight(this);
		Mapediter.MapChipPngWidth_MapChipSizeWidth = Mapediter.MapChipPngWidth/Mapediter.BoundaryWidth;
		Mapediter.MapChipPngHeight_MapChipSizeHeight = Mapediter.MapChipPngHeight/Mapediter.BoundaryHeight;
		
		//�}�b�v�`�b�v�\����ʂ̍ő�̑傫��
		//+18�͓K��
		int PngWidthMax = Mapediter.MapChipPngWidth+18,PngHeightMax = Mapediter.MapChipPngHeight+18;
		
		setTitle("�}�b�v�`�b�v");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.print(mapchipimage.getHeight(this));
		//add
		add(PanelMapChip, BorderLayout.CENTER);
		PanelMapChip.addMouseListener(new mapchipMouseListener());
		
		JScrollPane mapchipscroll = new JScrollPane(PanelMapChip);
		add(mapchipscroll);
		//mapchipscroll.setPreferredSize(new Dimension(128,600));
		
		//�}�b�v�`�b�v�\�����
		if((Mapediter.DesktopHeight -100) < PngHeightMax)
			PngHeightMax = Mapediter.DesktopHeight -100;
		if((Mapediter.DesktopWidth -100) < PngWidthMax){
			PngWidthMax = Mapediter.MapChipPngWidth -100;
		}
		
		PanelMapChip.addMouseMotionListener(new MapChipMouseMotionListener());
		
		getContentPane().setPreferredSize(new Dimension(PngWidthMax,PngHeightMax));//�p�l���T�C�Y
		
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

			/*int MouseButtonX = -1;//MousePush����X�̒l���͂���
			int MouseButtonY = -1;//MousePush����Y�̒l���͂���
			int MouseButtonNowPointX = -1;//���݂̃}�E�X�̈ʒuX
			int MouseButtonNowPointY = -1;//���݂̃}�E�X�̈ʒuY*/
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
			MouseButtonNowPointX = -1000;//���݂̃}�E�X�̈ʒuX
			MouseButtonNowPointY = -1000;//���݂̃}�E�X�̈ʒuY
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


