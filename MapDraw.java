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
		start = System.currentTimeMillis();
		//Graphics2D g2 = (Graphics2D)g;

		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));

		//if(Mapediter.MapMode <3)
		if(Mapediter.MapMode == 5){
			showGame(g,MapImage,map,BoundaryWidth,BoundaryHeight,pngWidth,cowFirst,rowFirst,cow,row);
		}
		else{
			showNormal(g,MapImage,map,BoundaryWidth,BoundaryHeight,pngWidth,cowFirst,rowFirst,cow,row);
			 g.setColor(Color.red);
			 g.drawRect((MouseX/BoundaryWidth)*BoundaryWidth*Mapediter.magnification, (MouseY/BoundaryHeight)*BoundaryHeight*Mapediter.magnification, BoundaryWidth*Mapediter.magnification, BoundaryHeight*Mapediter.magnification);
		}
			
		/*else if(Mapediter.MapMode == 3)
			showEvent(g,MapImage,map,BoundaryWidth,BoundaryHeight,pngWidth,cowFirst,rowFirst,cow,row);
		else if(Mapediter.MapMode == 4)
			showPass(g,MapImage,map,BoundaryWidth,BoundaryHeight,pngWidth,cowFirst,rowFirst,cow,row);*/
		 

		//System.out.println(System.currentTimeMillis()-start);
	}
	
	//�ʏ탌�C���[�C�x���g
	public void showNormal(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int cowFirst,int rowFirst,int cow,int row){
		int[] arg = new int[4];
		double tmp = (int)((double)pngWidth/(double)BoundaryWidth);

		try{
			 for(int i=rowFirst;i<row;i++){
				 for(int j=cowFirst;j<cow;j++){
					 if(map[i][j] == -1) 
						 g.drawRect(i*BoundaryWidth*Mapediter.magnification, j*BoundaryHeight*Mapediter.magnification, BoundaryWidth*Mapediter.magnification, BoundaryHeight*Mapediter.magnification);
					 else {
						 
						 arg[0] = BoundaryWidth*i*Mapediter.magnification;
						 arg[1] = BoundaryHeight*j*Mapediter.magnification;
						 arg[2] = arg[0] + BoundaryWidth*Mapediter.magnification;
						 arg[3] = arg[1] + BoundaryHeight*Mapediter.magnification;
						 
						 g.drawImage(Mapediter.MapEditerImage3,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map3[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map3[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map3[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map3[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);

						g.drawImage(Mapediter.MapEditerImage2,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map2[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map2[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map2[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map2[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);

						g.drawImage(Mapediter.MapEditerImage,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);

						
						
						/* g.drawImage(Mapediter.MapEditerImage3,BoundaryWidth*i,BoundaryHeight*j,
								 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
								 (Mapediter.Map3[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth, (Mapediter.Map3[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight,
								 ((Mapediter.Map3[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth)+BoundaryWidth, ((Mapediter.Map3[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight)+BoundaryHeight,null);
						 g.drawImage(Mapediter.MapEditerImage2,BoundaryWidth*i,BoundaryHeight*j,
								 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
								 (Mapediter.Map2[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth, (Mapediter.Map2[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight,
								 ((Mapediter.Map2[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth)+BoundaryWidth, ((Mapediter.Map2[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight)+BoundaryHeight,null);
						 g.drawImage(MapImage,BoundaryWidth*i,BoundaryHeight*j,
								 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
								 (Mapediter.Map[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth, (Mapediter.Map[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight,
								 ((Mapediter.Map[i][j]%(pngWidth/BoundaryWidth))*BoundaryWidth)+BoundaryWidth, ((Mapediter.Map[i][j]/(pngWidth/BoundaryWidth))*BoundaryHeight)+BoundaryHeight,null);
						 */
						//g.drawImage(image2,x�o�͍ŏ�,y�o�͍ŏ�,
					 					//�o�͍Ō�,�o�͍Ō�,
					 					//���摜�ŏ�,���摜�ŏ�,
					 					//���摜�Ō�,���摜�Ō�,null);
						
					 }
					 if(Mapediter.MapMode == 3)
						 showEvent2(g,i,j,arg,BoundaryWidth,BoundaryHeight);
					if(Mapediter.MapMode == 4)
						showPass2(g,i,j,arg,BoundaryWidth,BoundaryHeight);

				 }
			 }
			 //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+Mapediter.MapMode);

			 
		 }catch(NullPointerException e){
	 }
		
	}
	
	
	//Game��
	public void showGame(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int cowFirst,int rowFirst,int cow,int row){
		int[] arg = new int[4];
		double tmp = (int)((double)pngWidth/(double)BoundaryWidth);

		try{
			 for(int i=rowFirst;i<row;i++){
				 for(int j=cowFirst;j<cow;j++){
					 if(map[i][j] == -1) 
						 g.drawRect(i*BoundaryWidth*Mapediter.magnification, (j*BoundaryHeight+BoundaryHeight)*Mapediter.magnification, BoundaryWidth*Mapediter.magnification, BoundaryHeight*2*Mapediter.magnification);
					 if(map[i][j] != -1){
						 
						 arg[0] = BoundaryWidth*i*Mapediter.magnification;
						 arg[1] = (BoundaryHeight*j+BoundaryHeight)*Mapediter.magnification;
						 arg[2] = arg[0] + BoundaryWidth*Mapediter.magnification;
						 arg[3] = arg[1] + BoundaryHeight*Mapediter.magnification;
						 
						 g.drawImage(Mapediter.MapEditerImage3,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map3[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map3[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map3[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map3[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);

						g.drawImage(Mapediter.MapEditerImage2,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map2[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map2[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map2[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map2[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);

						g.drawImage(Mapediter.MapEditerImage,arg[0],arg[1],
							arg[2],arg[3],
							(int)(Mapediter.Map[i][j]%tmp)*BoundaryWidth, (int)(Mapediter.Map[i][j]/tmp)*BoundaryHeight,
							(int)(Mapediter.Map[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(Mapediter.Map[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);
						
					 }


				 }
			 }
			 //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+Mapediter.MapMode);
				if(Mapediter.MapMode == 5)
					showGame(g,BoundaryWidth,BoundaryHeight);
			 
		 }catch(NullPointerException e){
	 }
		
	}


	private void showGame(Graphics g, int BoundaryWidth, int BoundaryHeight) {
		g.drawImage(Mapediter.me.GetImage(),BoundaryWidth*Mapediter.me.GetXCoordinate()*Mapediter.magnification,BoundaryHeight*Mapediter.me.GetYCoordinate()*Mapediter.magnification,
				(BoundaryWidth*Mapediter.me.GetXCoordinate()+BoundaryWidth)*Mapediter.magnification,(BoundaryHeight*Mapediter.me.GetYCoordinate()+BoundaryHeight*2)*Mapediter.magnification,
				0, Mapediter.me.getDirection()*BoundaryHeight*2,
				BoundaryWidth, Mapediter.me.getDirection()*BoundaryHeight*2 + BoundaryHeight*2,null);
		
		
	}
	//�C�x���g�p�`��
	public void showEvent(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int cowFirst,int rowFirst,int cow,int row){
		int[] arg = new int[4];
		double tmp = (double)pngWidth/(double)BoundaryWidth;
		try{
			 for(int i=rowFirst;i<row;i++){
				 for(int j=cowFirst;j<cow;j++){
					 if(map[i][j] == -1) 
						 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
					 else if(Mapediter.Map4[i][j] != -1){
						 
						 arg[0] = BoundaryWidth*i;
						 arg[1] = BoundaryHeight*j;
						 arg[2] = arg[0] + BoundaryWidth;
						 arg[3] = arg[1] + BoundaryHeight;
						 
						 g.drawImage(Mapediter.MapEditerImage5,arg[0],arg[1],
									arg[2],arg[3],
									0, 0,
									(int)(BoundaryWidth*(32d/BoundaryWidth)), (int)(BoundaryHeight*(32d/BoundaryHeight)),null);
					 }
					 else {
						 arg[0] = BoundaryWidth*i;
						 arg[1] = BoundaryHeight*j;
						 arg[2] = arg[0] + BoundaryWidth;
						 arg[3] = arg[1] + BoundaryHeight;
						 
						 
						 g.drawImage(MapImage,arg[0],arg[1],
						 	arg[2],arg[3],
						 	(int)(map[i][j]%tmp)*BoundaryWidth, (int)(map[i][j]/tmp)*BoundaryHeight,
						 	(int)(map[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(map[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);
					 }
				 }
			 }
			 
		 }catch(NullPointerException e){
	 }
		
	}
	
	private void showEvent2(Graphics g, int i, int j, int[] arg,
			int BoundaryWidth, int BoundaryHeight) {
		if(Mapediter.Map4[i][j] != -1){
			
			arg[0] = BoundaryWidth*i*Mapediter.magnification;
			arg[1] = BoundaryHeight*j*Mapediter.magnification;
			arg[2] = arg[0] + BoundaryWidth*Mapediter.magnification;
			arg[3] = arg[1] + BoundaryHeight*Mapediter.magnification;
			
			g.drawImage(Mapediter.MapEditerImage5,arg[0],arg[1],
					arg[2],arg[3],
					0, 0,
					(int)(BoundaryWidth*(32d/BoundaryWidth)), (int)(BoundaryHeight*(32d/BoundaryHeight)),null);
		 }
		
	}
	
	
	
	//�ړ��ݒ�p
	public void showPass(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int cowFirst,int rowFirst,int cow,int row){
		int[] arg = new int[4];
		double tmp = (double)pngWidth/(double)BoundaryWidth;
		try{
			 for(int i=rowFirst;i<row;i++){
				 for(int j=cowFirst;j<cow;j++){
					 if(map[i][j] == -1) 
						 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
					 else {

						 if(true) {
							 arg[0] = BoundaryWidth*i;
							 arg[1] = BoundaryHeight*j;
							 arg[2] = arg[0] + BoundaryWidth;
							 arg[3] = arg[1] + BoundaryHeight;
							 
							 
							 g.drawImage(MapImage,arg[0],arg[1],
							 	arg[2],arg[3],
							 	(int)(map[i][j]%tmp)*BoundaryWidth, (int)(map[i][j]/tmp)*BoundaryHeight,
							 	(int)(map[i][j]%tmp)*BoundaryWidth + BoundaryWidth, (int)(map[i][j]/tmp)*BoundaryHeight + BoundaryHeight,null);
						 }
						 if(Mapediter.Map5[i][j] != -1){
							 
							 arg[0] = BoundaryWidth*i;
							 arg[1] = BoundaryHeight*j;
							 arg[2] = arg[0] + BoundaryWidth;
							 arg[3] = arg[1] + BoundaryHeight;
							 g.drawImage(Mapediter.MapEditerImage7,arg[0],arg[1],
										arg[2],arg[3],
										0, 0,
										(int)(BoundaryWidth*(32d/BoundaryWidth)), (int)(BoundaryHeight*(32d/BoundaryHeight)),null);
						 }
					 }
					 
					 
				 }
			 }
			 
		 }catch(NullPointerException e){
	 }
	}
	
	private void showPass2(Graphics g, int i, int j, int[] arg, int BoundaryWidth,
			int BoundaryHeight) {
		if(Mapediter.Map5[i][j] != -1){
			arg[0] = BoundaryWidth*i*Mapediter.magnification;
			arg[1] = BoundaryHeight*j*Mapediter.magnification;
			arg[2] = arg[0] + BoundaryWidth*Mapediter.magnification;
			arg[3] = arg[1] + BoundaryHeight*Mapediter.magnification;
			g.drawImage(Mapediter.MapEditerImage6[Mapediter.Map5[i][j]],arg[0],arg[1],
					arg[2],arg[3],
					0, 0,
					(int)(BoundaryWidth*(32d/BoundaryWidth)), (int)(BoundaryHeight*(32d/BoundaryHeight)),null);
		 }
		
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
		if(Mapediter.MapMode < 3)
			LayerNormal(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);
		else if(Mapediter.MapMode == 3)
			LayerEvent(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);
		else if(Mapediter.MapMode == 4)
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
	
	public void LayerEvent(int[][] Map,int[][] Map2,int[][] Map3,int[][] Map4,int[][] Map5,int[][] MapImage,int layernum,int cow,int row,int cowFirst,int rowFirst){
		
		for(int i=rowFirst;i<row;i++){
			 for(int j=cowFirst;j<cow;j++){
				 MapImage[i][j] = Map3[i][j];
				 if(Map2[i][j] != -1)
					 MapImage[i][j] = Map2[i][j];
				 if(Map[i][j] != -1)
					 MapImage[i][j] = Map[i][j];
				 if(Map4[i][j] != -1){
					 MapImage[i][j] = Map4[i][j];
					 System.out.println(Map4[i][j]);
				 }
			 }
		}
		System.out.println(Map4[0][0]);
		
	}
	
	public void LayerPass(int[][] Map,int[][] Map2,int[][] Map3,int[][] Map4,int[][] Map5,int[][] MapImage,int layernum,int cow,int row,int cowFirst,int rowFirst){
		
		for(int i=rowFirst;i<row;i++){
			 for(int j=cowFirst;j<cow;j++){
				 MapImage[i][j] = Map3[i][j];
				 if(Map2[i][j] != -1)
					 MapImage[i][j] = Map2[i][j];
				 if(Map[i][j] != -1)
					 MapImage[i][j] = Map[i][j];
				 if(Map5[i][j] != -1){
					 MapImage[i][j] = Map5[i][j];
					 System.out.println(Map5[i][j]);
				 }
			 }
		}
		System.out.println(Map4[0][0]);
		
	}

	
	//�Ȃ񂩂���ł�
	//�}�b�v�`�b�v�̂Ƃ��̂��
	public void MapChipshow(Graphics g, Image MapImage,int BoundaryWidth,int BoundaryHeight,int pngWidth,int pngHeight,ImageObserver ImageObserver,int MapChipNumber, int mouseButtonX, int mouseButtonY, int mouseButtonNowPointX, int mouseButtonNowPointY,int[][] MapChipPass){
		int rectX;//�}�b�v�`�b�v�I��͈�X
		int rectY;//�}�b�v�`�b�v�I��͈�Y
		double tmp = (double)pngWidth/(double)BoundaryWidth;
		g.drawImage(MapImage,0, 0,ImageObserver);
		 
		 for(int i=0;i<pngWidth;i++){
			 for(int j=0;j<pngHeight;j++){
				 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
				 if(Mapediter.MapMode == 4)
					 if(MapChipPass[i][j] == 0)g.drawImage(MapPassEdit.MapEditerImage7,i*BoundaryWidth,j*BoundaryHeight,
							 i*BoundaryWidth+BoundaryWidth,j*BoundaryHeight+BoundaryHeight,
							 0, 0,
							 32, 32,null);
					 else if(MapChipPass[i][j] == 1)g.drawImage(MapPassEdit.MapEditerImage8,i*BoundaryWidth,j*BoundaryHeight,
						 i*BoundaryWidth+BoundaryWidth,j*BoundaryHeight+BoundaryHeight,
						 0, 0,
						 32, 32,null);
			 }
		 }
		 if(pngWidth == 0)
			 pngWidth = 1;
		 
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
	//transparency�ɓ����x(0����255)
	public static void changeTransparent(BufferedImage changeImage,int R,int G,int B,int A){
		Color c = new Color(R,G,B,A);
		Graphics2D g2 = (Graphics2D) changeImage.getGraphics();
		g2.drawImage(changeImage, 0, 0, null);
		g2.setColor(c);
		g2.fillRect(0, 0, changeImage.getWidth(null),changeImage.getHeight(null));
		g2.dispose();
		
		
	}
	
	
	
	

	
}
