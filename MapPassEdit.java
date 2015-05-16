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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



class MapPassEdit extends JDialog{
	static ImageIcon iconTmp2 = new ImageIcon("System/batu3.png");
	static Image MapEditerImage7 = iconTmp2.getImage();//通行設定よう  ばつ
	static ImageIcon iconTmp3 = new ImageIcon("System/maru4.png");
	static Image MapEditerImage8 = iconTmp3.getImage();//通行設定よう  ばつ
	static int MouseButtonX = -1;//MousePush時のXの値がはいる
	static int MouseButtonY = -1;//MousePush時のYの値がはいる
	static int MouseButtonNowPointX = -1;//現在のマウスの位置X
	static int MouseButtonNowPointY = -1;//現在のマウスの位置Y
	static int MouseButtonAllOrOne = 0;//複数選択かどうかのフラグ

	
	
	JPanel PanelMapChip = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			 g.drawImage(MapEditerImage7,0,0,
						32,32,
						0, 0,
						32, 32,null);
			 
			 g.drawImage(MapEditerImage8,32,0,
						64,32,
						0, 0,
						32, 32,null);

					//g.drawImage(image2,x出力最初,y出力最初,
				 					//出力最後,出力最後,
				 					//元画像最初,元画像最初,
				 					//元画像最後,元画像最後,null);


		 }
	};
	
	public MapPassEdit(){

		

		setTitle("通行設定");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//add
		add(PanelMapChip, BorderLayout.CENTER);
		PanelMapChip.addMouseListener(new mapchipMouseListener());
		
		JScrollPane mapchipscroll = new JScrollPane(PanelMapChip);
		add(mapchipscroll);
		//mapchipscroll.setPreferredSize(new Dimension(128,600));
		
		//マップチップ表示画面

		
		PanelMapChip.addMouseMotionListener(new MapChipMouseMotionListener());
		
		getContentPane().setPreferredSize(new Dimension(100,100));//パネルサイズ
		
		//JFrameSet(mapchipimage.getWidth(this)+2,mapchipimage.getWidth(this)+2);
		//PanelMapChip.setPreferredSize(new Dimension(128,3300));
		pack();
		add(mapchipscroll);
		//setSize(128,600);
		setVisible(true);
		PanelMapChip.setPreferredSize(new Dimension(100,100));
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
			if(x < 64 && y < 32)
				Mapediter.MapPassNumder =x/32;
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
