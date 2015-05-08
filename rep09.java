/*// 1G120080 梅田玲旺
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class rep09 extends JFrame implements Runnable
{
	int NUM=7; // 移動体の個数
	GuiMap map;
	Mover[] mv = new Mover[NUM];
	Thread[] th = new Thread[NUM];
	Thread th0;
	double time=0.0;
	JPanel pn;
	JRadioButton rb1, rb2, rb3, tmp;
	ButtonGroup bg;
	JLabel lbKey;
	int change=0;

	JPanel panel = new JPanel()
	{
		 public void paintComponent(Graphics g)
		 {
			map.paintMap(g,mv,time);

		 }
	};

	public static void main(String[] args)
	{
		rep09 e03 = new rep09();
	}


	public rep09()
	{
		// タイトルの設定
		super("1G120080  梅田玲旺");
		// マップ生成
		map = new GuiMap(0);

		// 移動物（player(1)とother(1<n）生成
		for (int i=0; i<NUM; i++) mv[i] = new Mover(i+1, map);
		map.printMap();

		// コンポーネントの作成
		lbKey = new JLabel("< 止>");
		lbKey.setForeground(Color.black);
		lbKey.setFont(new Font("Serif", Font.BOLD, 24));
		pn = new JPanel();
		rb1 = new JRadioButton("障害物",true);
		rb2 = new JRadioButton("移動",false);
		rb1.setFont(new Font("Serif", Font.BOLD, 24));
		rb2.setFont(new Font("Serif", Font.BOLD, 24));
		rb3 = new JRadioButton("餌",false);
		rb3.setFont(new Font("Serif", Font.BOLD, 24));
		bg = new ButtonGroup();

		// ボタングループへの追加
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);


		// コンテナへの追加
		add(panel, BorderLayout.CENTER); // グラフィックス描画用パネル
		pn.add(rb1);
		pn.add(rb2);
		pn.add(rb3);
		add(pn,BorderLayout.SOUTH);
		add(lbKey,BorderLayout.EAST);






		// リスナの登録
		addMouseListener(new MyMouseListener());
		rb1.addActionListener(new rb1ActionListener());
		rb2.addActionListener(new rb2ActionListener());
		rb3.addActionListener(new rb3ActionListener());
		addKeyListener(new MyKeyListener());
		setFocusable(true);

		// フレームの設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);

		// スレッドの起動
		th0 = new Thread(this);
		th0.start();
		for (int i=0; i<NUM; i++) {
			th[i] = new Thread(mv[i]);
			th[i].start();
		}
	}

	class SampleWindowListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}

	public void run()
	{
		for (int i=0;; i++) {

			//フォーカスする-->キー入力イベントを受付ける
			requestFocusInWindow();

			try {
				System.out.println("\nrun(exam03)"+i);
				if (map.Mode==1) time += 1;
				repaint();
				if(mv[0].HP<=0 && change==0){
					map.Mode=0;
					change=1;
					Container cnt = getContentPane();
					int type = JOptionPane.INFORMATION_MESSAGE;
					JOptionPane.showMessageDialog(cnt, "もう動けません", "終了", type);
					map.Mode=1;
					repaint();
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	public class MyMouseListener implements MouseListener {


		public void mouseClicked(MouseEvent e) {


		}


		public void mousePressed(MouseEvent e) {
			int x,y,z;
			x= e.getX();
			y=e.getY();
			z=e.getButton();
			System.out.println("mousepress "+y+","+x+"");
			map.setGuiMap(x, y, z);
			repaint();

		}


		public void mouseReleased(MouseEvent e) {


		}


		public void mouseEntered(MouseEvent e) {


		}

	
		public void mouseExited(MouseEvent e) {


		}

	}
	public class MyKeyListener implements KeyListener {


		public void keyTyped(KeyEvent e) {


		}


		public void keyPressed(KeyEvent e) {
			int g = e.getKeyCode();
			switch(g){
			case KeyEvent.VK_NUMPAD8: mv[0].Key = 8; break;
			case KeyEvent.VK_NUMPAD2: mv[0].Key = 2; break;
			case KeyEvent.VK_NUMPAD4: mv[0].Key = 4; break;
			case KeyEvent.VK_NUMPAD6: mv[0].Key = 6; break;
				default: mv[0].Key = 0;
			}
			lbKey.setText(mv[0].direction());
		}

		public void keyReleased(KeyEvent e) {


		}

	}
	public class rb1ActionListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			map.Mode=0;

		}

	}
	public class rb2ActionListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			map.Mode=1;

		}

	}
	public class rb3ActionListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			map.Mode=2;

		}

	}

}*/
