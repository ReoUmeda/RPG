import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class MapDraw {

	//�g�p�s��
	public void show(Graphics g, Image MapImage, int[][] map, int cow, int row,int BoundaryWidth,int BoundaryHeight,int pngWidth) {
		 try{
			 for(int i=0;i<cow;i++){
				 for(int j=0;j<row;j++){
					 if(map[i][j] == -1) g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
					 else g.drawImage(MapImage,BoundaryWidth*i,BoundaryHeight*j,
							 
							 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
							 
							 (BoundaryWidth*map[i][j])%pngWidth,((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight,
							 (BoundaryWidth*map[i][j])%pngWidth+BoundaryWidth,(((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight+BoundaryHeight),null);
						//g.drawImage(image2,x�o�͍ŏ�,y�o�͍ŏ�,
					 					//�o�͍Ō�,�o�͍Ō�,
					 					//���摜�ŏ�,���摜�ŏ�,
					 					//���摜�Ō�,���摜�Ō�,null);
				 }
			 }
		 }catch(NullPointerException e){
	 }
		
	}
	//�X�N���[������V�^
	public void show(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int scrollX,int scrollY,int panelX,int panelY,int MaxX,int MaxY,int MouseX,int MouseY) {
		//�`�悷��n�߂̏ꏊ(����?)
		int cowFirst = scrollY/BoundaryHeight;
		int rowFirst = scrollX/BoundaryWidth;
		//�`�悷��I���(�E��?)
		int cow = (panelY/BoundaryHeight) + cowFirst;
		int row = (panelX/BoundaryWidth) + rowFirst;
		//�G���[�p(�͂ݏo�Ă�Ƃ�)
		if(MaxY < cow)
			cow = MaxY;
		if(MaxX < row)
			row = MaxX;
		long start;
		long end;
		start = System.currentTimeMillis();
		//Graphics2D g2 = (Graphics2D)g;
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		try{
			 for(int i=rowFirst;i<row;i++){
				 for(int j=cowFirst;j<cow;j++){
					 if(map[i][j] == -1) 
						 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
					 else g.drawImage(MapImage,BoundaryWidth*i,BoundaryHeight*j,
							 
							 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
							 
							 (map[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth, (map[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight,
							 ((map[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth)+BoundaryWidth, ((map[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight)+BoundaryHeight,null);
							 /*(BoundaryWidth*map[i][j])%pngWidth,((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight,
							 (BoundaryWidth*map[i][j])%pngWidth+BoundaryWidth,(((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight+BoundaryHeight),null);*/
						//g.drawImage(image2,x�o�͍ŏ�,y�o�͍ŏ�,
					 					//�o�͍Ō�,�o�͍Ō�,
					 					//���摜�ŏ�,���摜�ŏ�,
					 					//���摜�Ō�,���摜�Ō�,null);
					 //System.out.println(cow);
				 }
			 }
			 
		 }catch(NullPointerException e){
	 }
		 

		 g.setColor(Color.red);
		 g.drawRect((MouseX/BoundaryWidth)*BoundaryWidth, (MouseY/BoundaryHeight)*BoundaryHeight, BoundaryWidth, BoundaryHeight);
		System.out.println(System.currentTimeMillis()-start);
	}
	
	//���C���[���ǂ��̂����̂���ꏊ
	//�ŏ��ɌĂ΂��
	//�K���Ɋg������(��Z�Ƃ��X�N���[���Ƃ�)
	public void Layer(int[][] Map,int[][] Map2,int[][] Map3,int[][] Map4,int[][] Map5,int[][] MapImage,int LayerNum,int MaxX,int MaxY,int BoundaryWidth,int BoundaryHeight,int scrollX,int scrollY,int panelX,int panelY){
		//�`�悷��n�߂̏ꏊ(����?)
		int cowFirst = scrollY/BoundaryHeight;
		int rowFirst = scrollX/BoundaryWidth;
		//�`�悷��I���(�E��?)
		int cow = (panelY/BoundaryHeight) + cowFirst;
		int row = (panelX/BoundaryWidth) + rowFirst;
		//�G���[�p(�͂ݏo�Ă�Ƃ�)
		if(MaxY < cow)
			cow = MaxY;
		if(MaxX < row)
			row = MaxX;
		//�m�[�}����
		if(LayerNum == 0)
			LayerNormal(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);
	}
	//�m�[�}����
	public void LayerNormal(int[][] Map,int[][] Map2,int[][] Map3,int[][] Map4,int[][] Map5,int[][] MapImage,int layernum,int cow,int row,int cowFirst,int rowFirst){
		for(int i=rowFirst;i<row;i++){
			 for(int j=cowFirst;j<cow;j++){
				 MapImage[i][j] = Map3[i][j];
				 if(Map2[i][j] != -1)
					 MapImage[i][j] = Map2[i][j];
				 if(Map[i][j] != -1)
					 MapImage[i][j] = Map[i][j];
			 }
		}
		
	}
	
	//�Ȃ񂩂���ł�
	//�}�b�v�`�b�v�̂Ƃ��̂��
	public void MapChipshow(Graphics g, Image MapImage,int BoundaryWidth,int BoundaryHeight,int pngWidth,int pngHeight,ImageObserver ImageObserver,int MapChipNumber, int mouseButtonX, int mouseButtonY, int mouseButtonNowPointX, int mouseButtonNowPointY){
		int rectX;//�}�b�v�`�b�v�I��͈�X
		int rectY;//�}�b�v�`�b�v�I��͈�Y
		g.drawImage(MapImage,0, 0,ImageObserver);
		 
		 for(int i=0;i<pngWidth;i++){
			 for(int j=0;j<pngHeight;j++){
				 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
			 }
		 }
		 g.setColor(Color.red);
		 g.drawRect((MapChipNumber%pngWidth)*BoundaryWidth, (MapChipNumber/pngWidth)*BoundaryHeight, BoundaryWidth, BoundaryHeight);
		 //�����I��
		 //�Ւn
		 //�J����
		/* if(mapchip.MouseButtonAllOrOne == 1){
			 rectX = mouseButtonNowPointX - mouseButtonX;
			 rectY = mouseButtonNowPointY - mouseButtonY;
			 if((mouseButtonNowPointX - mouseButtonX) % BoundaryWidth != 0)
				 rectX = rectX + BoundaryWidth;
			 if((mouseButtonNowPointY - mouseButtonY) % BoundaryHeight != 0)
				 rectY = rectY + BoundaryHeight;
			 g.setColor(Color.blue);
			 g.drawRect((mouseButtonX/BoundaryWidth)*BoundaryWidth, (mouseButtonY/BoundaryHeight)*BoundaryHeight, (rectX/BoundaryWidth)*BoundaryWidth, (rectY/BoundaryHeight)*BoundaryHeight);
			 }
			 */

		 
	}
	
	//�����x�̕ύX
	//changeImage�ɓ����x��ς������摜
	//transparency�ɓ����x(0����255)
	public static void changeTransparent(BufferedImage changeImage,int transparency){
		int width = changeImage.getWidth();
		int height = changeImage.getHeight();
		transparency = transparency << 24;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				changeImage.setRGB(i, j,
						changeImage.getRGB(i, j) + transparency);
			}
		}
		
		
	}
	
	//�����x�̕ύX
	//changeImage�ɓ����x��ς������摜
	//transparency�ɓ����x(0����1)
	public static void changeTransparent(Image changeImage,float transparency){
		Graphics g = changeImage.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
		g2.drawImage(changeImage, 0, 0, null);
		g2.dispose();
		
		
	}
	
}
