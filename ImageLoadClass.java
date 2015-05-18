import java.awt.Image;

import javax.swing.ImageIcon;


public class ImageLoadClass {
	public static Image ImageLoad (String s){
		ImageIcon imageIcon = new ImageIcon(s);
		
		return imageIcon.getImage();
	}

}
