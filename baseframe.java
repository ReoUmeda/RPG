import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;




public class baseframe extends JFrame{
	boolean verifieda = true;
	boolean buttonpause = false;
	JTextField texta;
	JLabel labelh;
	JButton buttonb;
	JButton buttonj;
	ButtonModel model;
	SwingWorker worker;
	
	  public static void main(String args[]){
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
		    /*JFrame frame = new JFrame("�^�C�g��");
		    frame.add(panel);
		    frame.setVisible(true);
		    JFrame frame2 = new JFrame("�^�C�g��2");
		    frame2.add(panel);
		    frame2.setVisible(true);
		    frame.setSize(1000,500);
		    frame2.setSize(800,300);
		    frame2.setLocationRelativeTo(null);
		    frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		    baseframe bf = new baseframe();
		  }
		class MyWindowListenera extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				//Container cnt = getContentPane();
				//int type = JOptionPane.WARNING_MESSAGE;
				//type=JOptionPane.showConfirmDialog(cnt, "�ۑ�����Ă��Ȃ��f�[�^�͏����܂�����낵���ł���?", "�I��",JOptionPane.YES_NO_OPTION, type);
				//if(type==JOptionPane.YES_OPTION)System.exit(0);
				//lbKey.setText(Integer.toString(type));
				if(Key.mode != 3)System.exit(0);

			}
		}
		public baseframe()
		{
			// �^�C�g���̐ݒ�
			setTitle("Base64�G���R�[�h");
			//addWindowListener(new MyWindowListenera());
			
			
			//�R���|�[�l���g
			JPanel panela = new JPanel();
			JButton buttona = new JButton("�t�@�C���I��");
			buttonb = new JButton("��������");
			buttonj = new JButton("���~");
			JLabel labela = new JLabel("���̉��s");
			labelh = new JLabel("");
			texta = new JTextField("76",9);
			texta.setToolTipText("0����"+Integer.MAX_VALUE+"�܂ł̐����ł�.0�̏ꍇ�͉��s���܂���");
			JLabel labelb = new JLabel("Base64�̕ϊ������܂�");
			model = buttonb.getModel();
			buttonj.setEnabled(false);
			//buttona.setPreferredSize(new Dimension(200, 100));
			//EtchedBorder border = new EtchedBorder(EtchedBorder.LOWERED, Color.white, Color.black);
			//labela.setBorder(border);
			//labela.setHorizontalAlignment(JLabel.CENTER);
			//labela.setVerticalAlignment(JLabel.CENTER);
			//labela.setPreferredSize(new Dimension(130,80));
			panela.setLayout(null);
			
			//�ʒu
			labela.setBounds(10,100,100,20);
			labelb.setBounds(10,10,140,20);
			labelh.setBounds(140,100,100,20);
			buttona.setBounds(10,40,140,40);
			buttonj.setBounds(190,140,140,40);
			buttonb.setBounds(190,40,140,40);
			texta.setBounds(10,130,100,20);
			
			
			//add
			add(panela, BorderLayout.CENTER);
			panela.add(buttona);
			panela.add(buttonb);
			panela.add(buttonj);
			panela.add(labela);
			panela.add(texta);
			panela.add(labelb);
			panela.add(labelh);
			
			//���X�i�o�^
			buttona.addMouseListener(new baseMouseListenera());
			buttonb.addMouseListener(new baseMouseListenera());
			buttonj.addMouseListener(new baseMouseListenera());
			/*lbKey = new JLabel("< �~>");
			lbKey.setForeground(Color.black);
			lbKey.setFont(new Font("Serif", Font.BOLD, 24));
			add(lbKey,BorderLayout.EAST);*/
			texta.setInputVerifier(new IntegerInputVerifier());
			
            //�t���[���̐ݒ�
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setModal(true);
			//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new MyWindowListenera());
			setSize(400, 300);
	        // �T�C�Y�ύX�֎~
	        setResizable(false);
			setVisible(true);
			
			
			

		}
		public class baseMouseListenera extends MouseAdapter{
			int z;
			//ArrayList<Character> alout;
			File file;
			public void mousePressed(MouseEvent e) {
				z=e.getButton();
				if(z==1){
					//System.out.println((JButton)e.getSource()+"");
					if(((JButton)e.getSource()).getText()=="�t�@�C���I��"){
						if(verifieda == true && buttonpause == false){
							worker = new baseWorker();
		                	worker.execute();
		                	
		                }
						//�t�@�C���I��
						/*JFileChooser filechooser = new JFileChooser();
						filechooser.setFileHidingEnabled(false);
						int selected = filechooser.showOpenDialog(getContentPane());
					    if (selected == JFileChooser.APPROVE_OPTION){
					        file = filechooser.getSelectedFile();
					      }else if (selected == JFileChooser.CANCEL_OPTION){
					    	  return;
					      }else if (selected == JFileChooser.ERROR_OPTION){
					    	  return;
					      }

						File file = filechooser.getSelectedFile();
						//System.out.println(file+"");
						labelh.setText("NOW ENCODING");
						System.out.print("19");
						SwingWorker worker = new baseWorker();
		                worker.execute();
						//base64
						base be = new base();
						ArrayList<Character> alin = new ArrayList<Character>();
						ArrayList<Character> alout = new ArrayList<Character>();
						fileio.bread(file, alin);

						z=be.base64encode(alin, alout,alin.size());
						
						//labelh.setText("");
						
						//�t�@�C���ۑ�
						JFileChooser filechooserb = new JFileChooser();
						filechooserb.setFileHidingEnabled(false);
						selected = filechooser.showSaveDialog(getContentPane());
					    if (selected == JFileChooser.APPROVE_OPTION){
					    	file = filechooser.getSelectedFile();
					      }else if (selected == JFileChooser.CANCEL_OPTION){
					    	  return;
					      }else if (selected == JFileChooser.ERROR_OPTION){
					    	  return;
					      }
					    try {
							fileio.twrite(file, alout, z, (Integer.parseInt(texta.getText())-1));
						} catch (NumberFormatException e1) {
						} catch (IOException e1) {
						}*/
						
					}
					else if(((JButton)e.getSource()).getText()=="��������"){
						model.setPressed(false);
						model.setArmed(false);
						if(buttonpause == false){
							istr s = new istr();
							
						}
					}
					else if(((JButton)e.getSource()).getText()=="���~"){
						if(buttonpause==true){
							worker.cancel(true);
							worker = null;
							//System.out.println(worker.isCancelled());
							buttonj.setEnabled(false);
							buttonpause = false;
							labelh.setText("���~����܂���");
						}
					}
				}
				

			}
			public class baseWorker extends SwingWorker {

				/*protected Object doInBackground() throws Exception {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileHidingEnabled(false);
					int selected = filechooser.showOpenDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				        file = filechooser.getSelectedFile();
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
					buttonpause=true;
					buttonj.setEnabled(true);

					File file = filechooser.getSelectedFile();
					//System.out.println(file+"");
					labelh.setText("NOW ENCODING");

					//base64
					base be = new base();
					ArrayList<Character> alin = new ArrayList<Character>();
					ArrayList<Character> alout = new ArrayList<Character>();
					fileio.bread(file, alin);

					z=be.base64encode(alin, alout,alin.size(),Integer.parseInt(texta.getText()));
					
					labelh.setText("");

					

					JFileChooser filechooserb = new JFileChooser() {
						  @Override public void approveSelection() {
							File f = getSelectedFile();
							if(getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
								String filestr = f.getAbsolutePath();
								boolean txtCheck = filestr.endsWith(".txt");
								if(txtCheck == false) f = new File(filestr+".txt");
								}
						    if(f.exists() && getDialogType() == SAVE_DIALOG) {
						      String m = String.format(
						          "%s�͂������łɑ��݂��Ă��܂��D\n�㏑�����܂���?",
						          f.getName());
						      int rv = JOptionPane.showConfirmDialog(
						          this, m, "Save As", JOptionPane.YES_NO_OPTION);
						      if(rv!=JOptionPane.YES_OPTION) {
						        return;
						      }
						    }
						    super.approveSelection();
						  }
						};
						
					filechooserb.setFileHidingEnabled(false);
					FileFilter filter2 = new FileNameExtensionFilter("�e�L�X�g�t�@�C��(*.txt)", "txt");
					filechooserb.addChoosableFileFilter(filter2);
					selected = filechooserb.showSaveDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				    	file = filechooserb.getSelectedFile();
						if(filechooserb.getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
							String filestr = file.getAbsolutePath();
							boolean txtCheck = filestr.endsWith(".txt");
							if(txtCheck == false) file = new File(filestr+".txt");
							}
				    	
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
				    try {
						fileio.twrite(file, alout, z, Integer.parseInt(texta.getText()));
					} catch (NumberFormatException e1) {
					} catch (IOException e1) {
					}
					
					buttonpause = false;
					buttonj.setEnabled(false);
					labelh.setText("�������܂���");
					return 1;
				}*/

				
				protected Object doInBackground() throws Exception {
					/*JFileChooser filechooser = new JFileChooser();
					filechooser.setFileHidingEnabled(false);
					int selected = filechooser.showOpenDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				        file = filechooser.getSelectedFile();
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
					buttonpause=true;
					buttonj.setEnabled(true);

					File file = filechooser.getSelectedFile();
					//System.out.println(file+"");
					int size = (int)file.length();
					char[] alin = new char[size];
					fileio.BinarySelectRead(file, alin,0,size);
					//�o�̓T�C�Y
					int tmpd,l;
					if(size<=0) {
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
						labelh.setText("0byte�ł�");
						return -1;
					}
					l=(3-size%3)%3;
					if((size*4)%3!=0) tmpd = 1;
					else tmpd = 0;
					size=(size*4/3)+tmpd;
					labelh.setText("NOW ENCODING");
					//System.out.println("�����~��");
					//base64
					base be = new base();
					char[] alout = new char[size+l];
					


					z=be.base64encode(alin, alout,size,l);
					if(z == -1) return -1;
					labelh.setText("");

					
					//�ۑ��_�C�A���O
					JFileChooser filechooserb = new JFileChooser() {
						//�㏑�����邩�ǂ���
						  @Override public void approveSelection() {
							File f = getSelectedFile();
							if(getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
								String filestr = f.getAbsolutePath();
								boolean txtCheck = filestr.endsWith(".txt");
								if(txtCheck == false) f = new File(filestr+".txt");
								}
						    if(f.exists() && getDialogType() == SAVE_DIALOG) {
						      String m = String.format(
						          "%s�͂������łɑ��݂��Ă��܂��D\n�㏑�����܂���?",
						          f.getName());
						      int rv = JOptionPane.showConfirmDialog(
						          this, m, "Save As", JOptionPane.YES_NO_OPTION);
						      if(rv!=JOptionPane.YES_OPTION) {
						        return;
						      }
						    }
						    super.approveSelection();
						  }
						};
						
						//�t�@�C���t�B���^�[�̒ǉ�
					filechooserb.setFileHidingEnabled(false);
					FileFilter filter2 = new FileNameExtensionFilter("�e�L�X�g�t�@�C��(*.txt)", "txt");
					filechooserb.addChoosableFileFilter(filter2);
					selected = filechooserb.showSaveDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				    	file = filechooserb.getSelectedFile();
						if(filechooserb.getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
							String filestr = file.getAbsolutePath();
							boolean txtCheck = filestr.endsWith(".txt");
							if(txtCheck == false) file = new File(filestr+".txt");
							}
				    	
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
				    try {
						fileio.twrite(file, alout, z, Integer.parseInt(texta.getText()));
					} catch (NumberFormatException e1) {
					} catch (IOException e1) {
					}
					
					buttonpause = false;
					buttonj.setEnabled(false);
					labelh.setText("�������܂���");
					return 1;*/
					
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileHidingEnabled(false);
					int selected = filechooser.showOpenDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				        file = filechooser.getSelectedFile();
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
					buttonpause=true;
					buttonj.setEnabled(true);

					//�ۑ��_�C�A���O
					File filesave = null;
					JFileChooser filechooserb = new JFileChooser() {
						//�㏑�����邩�ǂ���
						  @Override public void approveSelection() {
							File f = getSelectedFile();
							if(getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
								String filestr = f.getAbsolutePath();
								boolean txtCheck = filestr.endsWith(".txt");
								if(txtCheck == false) f = new File(filestr+".txt");
								}
						    if(f.exists() && getDialogType() == SAVE_DIALOG) {
						      String m = String.format(
						          "%s�͂������łɑ��݂��Ă��܂��D\n�㏑�����܂���?",
						          f.getName());
						      int rv = JOptionPane.showConfirmDialog(
						          this, m, "Save As", JOptionPane.YES_NO_OPTION);
						      if(rv!=JOptionPane.YES_OPTION) {
						        return;
						      }
						    }
						    super.approveSelection();
						  }
						};
						
						//�t�@�C���t�B���^�[�̒ǉ�
					filechooserb.setFileHidingEnabled(false);
					FileFilter filter2 = new FileNameExtensionFilter("�e�L�X�g�t�@�C��(*.txt)", "txt");
					filechooserb.addChoosableFileFilter(filter2);
					selected = filechooserb.showSaveDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
				    	filesave = filechooserb.getSelectedFile();
						if(filechooserb.getFileFilter().toString().endsWith("[description=�e�L�X�g�t�@�C��(*.txt) extensions=[txt]]")){
							String filestr = filesave.getAbsolutePath();
							boolean txtCheck = filestr.endsWith(".txt");
							if(txtCheck == false) filesave = new File(filestr+".txt");
							}
				    	
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  buttonpause = false;
				    	  buttonj.setEnabled(false);
				    	  return 0;
				      }
				    
					File file = filechooser.getSelectedFile();
					//System.out.println(file+"");
					long start = System.currentTimeMillis();
					labelh.setText("NOW ENCODING");
					
					//�t�@�C�����牽byte�����擾������ɕ����ď������ނ����߂�
					//sizelong�͊�{�I��int�^�̑傫���ŏ���������Ȃ��傫���̃t�@�C���p
					long sizelong = file.length();
					
					//���̂ق���for����1�x�ɉ�byte�ǂݏo����
					int sizeElement1 = 20000001;
					
					//�O���̂ق���for��1��ɂ���byte�ǂݏo����
					//sizeMax�͊�{�I��int�^�̑傫���ŏ���������Ȃ��傫���̃t�@�C���p
					int sizeMax = (Integer.MAX_VALUE/sizeElement1)*sizeElement1;
					if(sizelong<=0) {
						buttonpause = false;
				    	buttonj.setEnabled(false);
						labelh.setText("0byte�ł�");
						return -1;
					}
					//sizelong��sizeMax�ȏ�̏ꍇ�ɉ��xfor�����܂킷��
					int countlong = (int) (sizelong/sizeMax);
					if(sizelong%sizeMax > 0) countlong++;
					
					//���ƃt�@�C������byte�ǂݍ��܂Ȃ���΂Ȃ�Ȃ���
					int sizeOrigin;

					//sizeOrigin��20000001�ȏ�̏ꍇ�ɉ��xfor�����܂킷��
					int count;
					//�ǂ��܂Ńt�@�C����ǂݍ��񂾂�
					long offset =0;
					//���s
					int crlf = Integer.parseInt(texta.getText());
					boolean postscript = false;
					int tmpd,l;
					//for���P�x�̕ϊ���̏o�̓T�C�Y
					//��{�I��sizeOrigin��sizeElement1��菬�����Ȃ������ɂ����ς�炸
					//�������Ȃ鎞��1�x�̏�����1�x�����Ȃ����ߒl�͂قڕς��Ȃ�
					int sizeElement2;
					//���s�̂���␳
					int crlfkeep = 0;
					
				
					
					z = 0;
					//�������for���͊�{�I��int�^�̑傫���ŏ���������Ȃ��傫���̃t�@�C���p
					for(int j=0; j<countlong;j++){
						if(sizelong - sizeMax <= 0) sizeOrigin = (int)sizelong;
						else {
							sizeOrigin = sizeMax;
							sizelong -= sizeMax;
						}
						count = sizeOrigin/sizeElement1;
						if(sizeOrigin%sizeElement1 > 0) count++;
						l=(3-sizeElement1%3)%3;
						if((sizeElement1*4)%3!=0) tmpd = 1;
						else tmpd = 0;
						sizeElement2=(sizeElement1*4/3)+tmpd;
						
						for(int i=0;i<count;i++){
							sizeOrigin -= sizeElement1;
							//sizeOrigin��20000001�ȏ�̏ꍇ
							if(sizeOrigin >= 0){
								if(sizeOrigin == 0) sizeOrigin = sizeElement1;
								char[] alin = new char[sizeElement1];
								fileio.BinarySelectRead(file, alin,offset,sizeElement1);
	
								
								//base64
								base be = new base();
								char[] alout = new char[sizeElement2+l];
								
			
			
								z=be.base64encode(alin, alout,sizeElement2,l);
								if(z == -1) {
									labelh.setText("�G���[");
									return -1;
								}
									
								
			
								
			
							    try {
									fileio.twrite(filesave, alout, z, crlf, postscript,crlfkeep);
								} catch (NumberFormatException e1) {
								} catch (IOException e1) {
								}
								
								offset += sizeElement1;
								if(crlf != 0) crlfkeep = (crlfkeep + (z % crlf)) % crlf;
							}
							//sizeOrigin��20000001�ȉ��̏ꍇ
							else if(sizeOrigin < 0){
								
								
								sizeOrigin += sizeElement1;
								char[] alin = new char[sizeOrigin];
								//System.out.println("alin"+alin[0]+"offset"+offset+"sizeOrigin"+sizeOrigin);
								fileio.BinarySelectRead(file, alin,offset,sizeOrigin);
								//�o�̓T�C�Y
								l=(3-sizeOrigin%3)%3;
								if((sizeOrigin*4)%3!=0) tmpd = 1;
								else tmpd = 0;
								sizeOrigin=(sizeOrigin*4/3)+tmpd;
	
								//base64
								base be = new base();
								char[] alout = new char[sizeOrigin+l];
								
			
			
								z=be.base64encode(alin, alout,sizeOrigin,l);
								if(z == -1) {
									labelh.setText("�G���[");
									return -1;
								}
									
								
			
								
			
							    try {
									fileio.twrite(filesave, alout, z, crlf, postscript,crlfkeep);
								} catch (NumberFormatException e1) {
								} catch (IOException e1) {
								}

							}
							postscript = true;
							System.out.println(i);
						}
					}
					long end = System.currentTimeMillis();
					System.out.println((end - start)+"�~���b");
					buttonpause = false;
					buttonj.setEnabled(false);
					labelh.setText("�������܂���");
					return 1;
				}
			}

		}
		class CancelAction extends AbstractAction{
			  public CancelAction() {
			    super("cancel");
			  }
			  @Override public void actionPerformed(ActionEvent evt) {
			    if(worker!=null && !worker.isDone()) {
			      worker.cancel(true);
			    }
			    worker = null;
			  }
			}
		//���͐���
		class IntegerInputVerifier extends InputVerifier{
			public boolean verify(JComponent c) {
			    verifieda = false;
			    JTextField textField = (JTextField)c;
			    try{
			      if (Integer.parseInt(textField.getText()) > -1 && Integer.parseInt(textField.getText()) <= Integer.MAX_VALUE)verifieda = true;
			      else Integer.parseInt("a");
			    }catch(NumberFormatException e) {
			      UIManager.getLookAndFeel().provideErrorFeedback(c);
			      texta.setText("");
			      Toolkit.getDefaultToolkit().beep();
			    }
			    return verifieda;
			  }
			}
}

class istr extends JDialog{
	int c=0;
	int tmp;
	JButton buttong;
	JTextArea areaa;
	JTextArea areab;
	JTextField textb;
	JLabel labelg;
	boolean verifiedb = true;
	boolean conserveflag = true;
	
	public istr(){
		// �^�C�g���̐ݒ�
		//super("��������");
		setTitle("��������");
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800,500);
		addWindowListener(new MyWindowListenerb());
		
		//�R���|�[�l���g
		JPanel panelb = new JPanel();
		JButton buttonc = new JButton("�G���R�[�h");
		JButton buttond = new JButton("�؂���");
		JButton buttone = new JButton("�\��t��");
		JButton buttonf = new JButton("�R�s�[");
		buttong = new JButton("�ۑ�");
		JButton buttoni = new JButton("������");
		JLabel labele = new JLabel("���������");
		JLabel labelc = new JLabel("���̉��s");
		textb = new JTextField("76",9);
		textb.setToolTipText("0����"+Integer.MAX_VALUE+"�܂ł̐����ł�.0�̏ꍇ�͉��s���܂���");
		areaa = new JTextArea(9,9);
		areab = new JTextArea(9,9);
		JLabel labeld = new JLabel("Base64�̕ϊ������܂�");
		JScrollPane scrollpanea = new JScrollPane(areaa);
		JScrollPane scrollpaneb = new JScrollPane(areab);
		JLabel labelf = new JLabel("�ϊ�����");
		labelg = new JLabel("");
		areaa.setLineWrap(true);
		buttong.setEnabled(false);
		areab.addKeyListener(new baseKeyListener());
		panelb.setLayout(null);
		
		//�ʒu
		labelc.setBounds(10,100,100,20);
		labeld.setBounds(10,10,140,20);
		buttonc.setBounds(10,40,140,40);
		buttonf.setBounds(10,170,140,40);
		buttone.setBounds(10,210,140,40);
		buttond.setBounds(10,250,140,40);
		buttong.setBounds(10,350,140,40);
		buttoni.setBounds(10,290,140,40);
		labele.setBounds(300,10,140,40);
		textb.setBounds(10,130,100,20);
		//areaa.setBounds(40,230,100,40);
		scrollpaneb.setBounds(200,280,540,160);
		scrollpanea.setBounds(200,40,540,160);
		labelf.setBounds(300,250,140,40);
		labelg.setBounds(300,210,140,40);
		
		

		
		//add
		add(panelb, BorderLayout.CENTER);
		panelb.add(buttonc);
		panelb.add(buttone);
		panelb.add(buttonf);
		panelb.add(buttond);
		panelb.add(buttong);
		panelb.add(buttoni);
		panelb.add(labele);
		panelb.add(labelc);
		panelb.add(textb);
		panelb.add(labeld);
		panelb.add(scrollpaneb);
		panelb.add(scrollpanea);
		panelb.add(labelf);
		panelb.add(labelg);
		
		
		// ���X�i�̓o�^
		buttonc.addMouseListener(new baseMouseListenerb());
		buttond.addMouseListener(new baseMouseListenerb());
		buttone.addMouseListener(new baseMouseListenerb());
		buttonf.addMouseListener(new baseMouseListenerb());
		buttong.addMouseListener(new baseMouseListenerb());
		buttoni.addMouseListener(new baseMouseListenerb());
		areaa.addMouseListener(new My1MouseListenerb());
		areab.addMouseListener(new My2MouseListenerb());
		textb.setInputVerifier(new IntegerInputVerifier());
		
		setFocusable(true);
		setVisible(true);
		
	}
	class MyWindowListenerb extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			if(buttong.isEnabled()&&conserveflag){
				Container cnt = getContentPane();
				int type = JOptionPane.WARNING_MESSAGE;
				type=JOptionPane.showConfirmDialog(cnt, "�ۑ�����Ă��Ȃ��f�[�^�͏����܂�����낵���ł���?", "�I��",JOptionPane.YES_NO_OPTION, type);
				if(type==JOptionPane.YES_OPTION) setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
			else setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			//lbKey.setText(Integer.toString(type));

		}
	}
	public class baseMouseListenerb extends MouseAdapter{
		int z=0;
		public void mousePressed(MouseEvent e) {
			z=e.getButton();
			if(z==1){
				//System.out.println((JButton)e.getSource()+"");
				if(((JButton)e.getSource()).getText()=="�G���R�[�h"){
					if(areaa.getText().equals("")) return;
					if(verifiedb == true){
						SwingWorker worker = new basestrWorker();
		                worker.execute();

	                }
					/*labelg.setText("NOW ENCODING");
					
					
					base be = new base();
					ArrayList<Character> alin = new ArrayList<Character>();
					ArrayList<Character> alout = new ArrayList<Character>();
					String str = areaa.getText();
					for(int i=0;str.length()>i;i++){
						alin.add(str.charAt(i));
					}
					z=be.base64encode(alin, alout, str.length());
					tmp=z;
					try{
						str=String.valueOf(alout.get(0));
					}
					catch(java.lang.IndexOutOfBoundsException er){}
					for(int i=1;i<z;i++){
						if((Integer.parseInt(textb.getText())) == 1 && i == 1) str=str+"\n";
						//System.out.print(alout.get(i)+"");
						//areab.setText(alout);
						str=str+alout.get(i);
						if((i+1)%(Integer.parseInt(textb.getText())) == 0) str=str+"\n";
					}
					//str=String.valueOf(alout);
					
					areab.setText(str);

					labelg.setText("");*/
				}
				else if(((JButton)e.getSource()).getText()=="�\��t��"){
					if(c==1) areaa.paste();
					else if(c==2){
						areab.paste();
		    			SwingWorker buttongworker = new buttongWorker();
		                buttongworker.execute();
					}
				}
				else if(((JButton)e.getSource()).getText()=="�R�s�["){
					if(c==1) areaa.copy();
					else if(c==2){
						areab.copy();
		    			SwingWorker buttongworker = new buttongWorker();
		                buttongworker.execute();
					}
				}
				else if(((JButton)e.getSource()).getText()=="�؂���"){
					if(c==1) areaa.cut();
					else if(c==2){
						areab.cut();
		    			SwingWorker buttongworker = new buttongWorker();
		                buttongworker.execute();
					}
				}
				else if(((JButton)e.getSource()).getText()=="�ۑ�"){
					if(areab.getText().equals("")) return;
					//JFileChooser filechooser = new JFileChooser();
					JFileChooser filechooser = new JFileChooser() {
						  @Override public void approveSelection() {
						    File f = getSelectedFile();
						    if(f.exists() && getDialogType() == SAVE_DIALOG) {
						      String m = String.format(
						          "%s�͂������łɑ��݂��Ă��܂��D\n�㏑�����܂���?",
						          f.getName());
						      int rv = JOptionPane.showConfirmDialog(
						          this, m, "Save As", JOptionPane.YES_NO_OPTION);
						      if(rv!=JOptionPane.YES_OPTION) {
						        return;
						      }
						    }
						    super.approveSelection();
						  }
						};
					filechooser.setFileHidingEnabled(false);
					int selected = filechooser.showSaveDialog(getContentPane());
				    if (selected == JFileChooser.APPROVE_OPTION){
						ArrayList<Character> fout = new ArrayList<Character>();
						String strc = areab.getText();
						for(int i=0;strc.length()>i;i++){
							fout.add(strc.charAt(i));
						}
				    	 File file = filechooser.getSelectedFile();
						    try {
								fileio.twrite(file, fout, tmp,0);
							} catch (NumberFormatException e1) {
							} catch (IOException e1) {
							}
							conserveflag = false;
				      }else if (selected == JFileChooser.CANCEL_OPTION){
				    	  return;
				      }else if (selected == JFileChooser.ERROR_OPTION){
				    	  return;
				      }

				}
				else if(((JButton)e.getSource()).getText()=="������"){
					if(c==1) areaa.setText("");
					else if(c==2) {
						areab.setText("");
		    			SwingWorker buttongworker = new buttongWorker();
		                buttongworker.execute();
					}
				}
			}
			

		}


		public class basestrWorker extends SwingWorker {

			protected Object doInBackground() throws Exception {
				
				//double s = System.nanoTime();
				//�G���R�[�h���ł��邱�Ƃ��o��
				labelg.setText("NOW ENCODING");
				
				//try{StringBuilder[] ert = new StringBuilder[188743620];}catch(OutOfMemoryError e){System.out.println("afsgdhfjgkhgfhdgfsfdgfh");e.printStackTrace();}
				//base64�̕ϊ�
				base be = new base();
				ArrayList<Character> alin = new ArrayList<Character>();
				//ArrayList<Character> alout = new ArrayList<Character>();
				//String str = areaa.getText();
				StringBuilder str = new StringBuilder(areaa.getText());
				
				char variable;
				for(int i=0;str.length()>i;i++){
					variable = str.charAt(i);
					//1byte�������ǂ����̔���
					if(variable <256){
					alin.add(variable);
					}
					else {
						alin.add((char)(variable/256));
						alin.add((char) (variable%256));
					}
				}
				if(alin.size() > 500000 && Integer.parseInt(textb.getText()) == 1){
					labelg.setText("");
					Container cnt = getContentPane();
					int type = JOptionPane.WARNING_MESSAGE;
					JOptionPane.showMessageDialog(cnt, "���͂������������������܂�"+System.getProperty("line.separator")+"����"+(alin.size()-500000)+"byte�Ԃ񕶎������炵�Ă�������", "�G���[", type);
					alin.clear();
					return -1;
				}
				else if(alin.size() > 1000000){
					labelg.setText("");
					Container cnt = getContentPane();
					int type = JOptionPane.WARNING_MESSAGE;
					JOptionPane.showMessageDialog(cnt, "���͂������������������܂�"+System.getProperty("line.separator")+"����"+(alin.size()-1000000)+"byte�Ԃ񕶎������炵�Ă�������", "�G���[", type);
					alin.clear();
					return -1;
				}
				System.out.println("����"+alin.size()+"byte");
				StringBuilder sb = new StringBuilder();
				//z=be.base64encode(alin, alout, str.length(),Integer.parseInt(textb.getText()));
				z=be.base64encode(alin, alin.size(),Integer.parseInt(textb.getText()),sb);
				//tmp�͕ۑ����鎞�̃T�C�Y
				tmp=z;
				
				
				//long start = System.currentTimeMillis();
				/*try{
					
					str=String.valueOf(alout.get(0));
				}
				catch(java.lang.IndexOutOfBoundsException er){}*/
				//for(int i=0;i<z;i++){
					//if(i % 10000 == 0) System.out.println(i);
					/*if((Integer.parseInt(textb.getText())) == 1 && i == 1) str=str+"\n";
					//System.out.print(alout.get(i)+"");
					//areab.setText(alout);
					//str=str+alout.get(i);
					if(Integer.parseInt(textb.getText()) == 0){}
					else if((i+1)%(Integer.parseInt(textb.getText())) == 0) str=str+"\n";*/
					//if((Integer.parseInt(textb.getText())) == 1 && i == 1) alout.add(i, '\n');
					//if(Integer.parseInt(textb.getText()) == 0){}
					//else if((i+1)%(Integer.parseInt(textb.getText())) == 0) alout.add(i, '\n');
					//str=str+"a";//
					//str=str+alout.get(i);
					//sb.append(alout.get(i));
					
				//}
				//long end = System.currentTimeMillis();
				//System.out.println("end");
				//str=String.valueOf(alout);
				//areab�ɕϊ����ʂ��o��
				//str = alout.toArray();
				//str.valueOf(alout.toArray(null));
				//alout.clear();
				//System.out.println(sb.substring(tmp));
				///String str3 = sb.toString();
				//System.out.println("ab");
				//areab.setText(sb.toString());
				try{areab.setText(sb.toString());}
				catch(OutOfMemoryError e){
					System.out.println("�ȉ��G���[���e");
					e.printStackTrace();
					return -1;}
				//System.out.println(str);
				//���������Əo��
				labelg.setText("�������܂���");
    			SwingWorker buttongworker = new buttongWorker();
                buttongworker.execute();
				
				//double e = System.nanoTime();
				//System.out.println((end - start)+"�~���b");
				//System.out.println((e - s)+"�i�m�b");
				return 1;
			}

		}
	}
	public class baseKeyListener extends KeyAdapter {


		public void keyTyped(KeyEvent e) {
			SwingWorker worker = new buttongWorker();
            worker.execute();

			
			
		}



		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {


		}

	}
	
	public class My1MouseListenerb extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int z=e.getButton();
			if(z==1){
				c=1;
			}
		}
	}
	public class My2MouseListenerb extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int z=e.getButton();
			if(z==1){
				c=2;
			}
		}
	}
	//���͐���
	class IntegerInputVerifier extends InputVerifier{
		public boolean verify(JComponent c) {
		    verifiedb = false;
		    JTextField textField = (JTextField)c;
		    try{
		      if (Integer.parseInt(textField.getText()) > -1 && Integer.parseInt(textField.getText()) <= Integer.MAX_VALUE)verifiedb = true;
		      else Integer.parseInt("a");
		    }catch(NumberFormatException e) {
		      UIManager.getLookAndFeel().provideErrorFeedback(c);
		      textb.setText("");
		      Toolkit.getDefaultToolkit().beep();
		    }
		    return verifiedb;
		  }
		}
	//�ۑ��𖳌������邩�L��������
	public class buttongWorker extends SwingWorker {

		protected Object doInBackground() throws Exception {
			//areab�ɓ��͂��Ȃ��Ɩ���������ƗL����
			if(areab.getText().equals("")) buttong.setEnabled(false);
			else {
				buttong.setEnabled(true);
				conserveflag = true;
			}
			return null;

		}

	}
}

