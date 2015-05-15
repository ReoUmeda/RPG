import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.synth.SynthUI;



public class uniLookAndFeel{
	public static void main(String args[]){

		uniLookAndFeel mes = new uniLookAndFeel(args);

	}
	public uniLookAndFeel(String args[]){
		
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			//SwingUtilities.updateComponentTreeUI(this);
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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			//SwingUtilities.updateComponentTreeUI(this);
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
		
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//SwingUtilities.updateComponentTreeUI(this);
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
		
		*/
		
		
		/*   SynthLookAndFeel synth = new SynthLookAndFeel();
		   try {
			synth.load(SynthUI.class.getResourceAsStream("demo.xml"), SynthUI.class);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			UIManager.setLookAndFeel(synth);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		/*try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}*/
		
		
		Mapediter me = new Mapediter();
	}


}