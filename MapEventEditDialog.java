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
	JTextField textBoundaryHeight; //�}�b�v�`�b�v���E�c
	JTextField textBoundaryWidth; //�}�b�v�`�b�v���E��
	public MapEventEditDialog(){
		setTitle("�}�b�v���E");
		setSize(190,160);
		setModal(true);
		
		//�R���|�[�l���g
		JButton buttonyes = new JButton("OK");
		JButton buttonno = new JButton("�L�����Z��");
		JLabel labelBoundaryWidth = new JLabel("�����E");
		JLabel labelBoundaryHeight = new JLabel("�c���E");
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
		
		// ���X�i�̓o�^
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
					//�}�b�v�`�b�v���E�ύX
					Mapediter.BoundaryHeight = Integer.parseInt(textBoundaryHeight.getText());
					Mapediter.BoundaryWidth = Integer.parseInt(textBoundaryWidth.getText());
					Mapediter.widthWidht =32/Mapediter.BoundaryWidth;
					Mapediter.heightHeight = 32/Mapediter.BoundaryHeight;
					//�}�b�v�`�b�v���E�ۑ�
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
				else JOptionPane.showMessageDialog(MapEventEditDialog.this,"�}�b�v�̑傫����1x1�ȏ�ɂ��Ă�������");
			}catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(MapEventEditDialog.this,"���l����͂��Ă�������");
                textBoundaryWidth.setText("");
                textBoundaryHeight.setText("");
			}
		}
		else if(((JButton)e.getSource()).getText()=="�L�����Z��") setVisible(false);
	}

}