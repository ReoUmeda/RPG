import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


class MapEventEditDialog extends JDialog implements ActionListener{
	JTextField textBoundaryHeight; //マップチップ境界縦
	JTextField textBoundaryWidth; //マップチップ境界横
	public MapEventEditDialog(){
		setTitle("マップ境界");
		setSize(190,160);
		setModal(true);
		
		//コンポーネント
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("キャンセル");
		JLabel labelBoundaryWidth = new JLabel("横境界");
		JLabel labelBoundaryHeight = new JLabel("縦境界");
		textBoundaryWidth = new JTextField(Integer.toString(Mapediter.BoundaryWidth));
		textBoundaryHeight = new JTextField(Integer.toString(Mapediter.BoundaryHeight));
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


	public MapEventEditDialog(int mapEventNumber) {
		// TODO Auto-generated constructor stub
	}


	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText()=="OK"){
			try{
				if(Integer.parseInt(textBoundaryHeight.getText()) > 0 && Integer.parseInt(textBoundaryWidth.getText()) > 0){
					//マップチップ境界変更
					Mapediter.BoundaryHeight = Integer.parseInt(textBoundaryHeight.getText());
					Mapediter.BoundaryWidth = Integer.parseInt(textBoundaryWidth.getText());
					Mapediter.widthWidht =32/Mapediter.BoundaryWidth;
					Mapediter.heightHeight = 32/Mapediter.BoundaryHeight;
					//マップチップ境界保存
					ArrayList<String> boundaryw = new ArrayList<String>();
					boundaryw.add(Integer.toString(Mapediter.BoundaryHeight));
					boundaryw.add(Integer.toString(Mapediter.BoundaryWidth));
					File boundaryWfile = new File("src\\boundary.con");
					try {
						fileio.textwrite(boundaryWfile, boundaryw, boundaryw.size());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					boundaryw.clear();
					setVisible(false);
				}
				else JOptionPane.showMessageDialog(MapEventEditDialog.this,"マップの大きさは1x1以上にしてください");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(MapEventEditDialog.this,"数値を入力してください");
                textBoundaryWidth.setText("");
                textBoundaryHeight.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="キャンセル") setVisible(false);
	}

}