import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;


public class rain {
	final int rainbowr = 50;
	ImageIcon ii = new ImageIcon("map\\rain.png");
	Image rain = ii.getImage();
	int rainx = rain.getWidth(null);
	int rainy = rain.getHeight(null);
	int[][] xy = new int[rainbowr][2];
	Random rnd = new Random();
	public rain(){
		for(int i=0;i<rainbowr;i++){
			for(int j=0;j<2;j++){
				xy[i][j] = rnd.nextInt(1000);
			}
		}
	}
	public void drawRain(Graphics g){
		Mapediter.bgmse.start(2);
		for(int i=0;i<rainbowr;i++){
			g.drawImage(rain, xy[i][0]+Mapediter.me.GetXCoordinate()*Mapediter.BoundaryWidth*Mapediter.magnification-200,
					xy[i][1]+Mapediter.me.GetYCoordinate()*Mapediter.BoundaryHeight*Mapediter.magnification-200, 
					xy[i][0]+Mapediter.me.GetXCoordinate()*Mapediter.BoundaryWidth*Mapediter.magnification-200+rainx/2,
					xy[i][1]+Mapediter.me.GetYCoordinate()*Mapediter.BoundaryHeight*Mapediter.magnification-200+rainy/2, 
					0, 0, rainx, rainy,null);
			if(xy[i][0] < 0 || xy[i][1] > 900){
				xy[i][0] = -rnd.nextInt(1000)+1000;
				xy[i][1] = rnd.nextInt(1000);
			}
			xy[i][0] -= rnd.nextInt(100);
			xy[i][1] += rnd.nextInt(100);
			if(rnd.nextInt(1001) > 999){
				Mapediter.bgmse.start(1);
				//Mapediter.s.send(rnd.nextInt(31), 0, 0, 0, 800);
			}
		}

		
	}

}
