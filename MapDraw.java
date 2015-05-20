import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class MapDraw {

	//使用不可
	public void show(Graphics g, Image MapImage, int[][] map, int cow, int row,int BoundaryWidth,int BoundaryHeight,int pngWidth) {
		 try{
			 for(int i=0;i<cow;i++){
				 for(int j=0;j<row;j++){
					 if(map[i][j] == -1) g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
					 else g.drawImage(MapImage,BoundaryWidth*i,BoundaryHeight*j,
							 
							 BoundaryWidth*i+BoundaryWidth,BoundaryHeight*j+BoundaryHeight,
							 
							 (BoundaryWidth*map[i][j])%pngWidth,((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight,
							 (BoundaryWidth*map[i][j])%pngWidth+BoundaryWidth,(((BoundaryHeight*map[i][j])/pngWidth)*BoundaryHeight+BoundaryHeight),null);
						//g.drawImage(image2,x出力最初,y出力最初,
					 					//出力最後,出力最後,
					 					//元画像最初,元画像最初,
					 					//元画像最後,元画像最後,null);
				 }
			 }
		 }catch(NullPointerException e){
	 }
		
	}
	//スクロールする新型
	public void show(Graphics g, Image MapImage, int[][] map,int BoundaryWidth,int BoundaryHeight,int pngWidth,int scrollX,int scrollY,int panelX,int panelY,int MaxX,int MaxY,int MouseX,int MouseY) {
		//描画する始めの場所(左上?)
		int cowFirst = scrollY/BoundaryHeight;
		int rowFirst = scrollX/BoundaryWidth;
		//描画する終わり(右下?)
		int cow = (panelY/BoundaryHeight) + cowFirst;
		int row = (panelX/BoundaryWidth) + rowFirst;
		//エラー用(はみ出てるとこ)
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
	
	//通常レイヤーイベント
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
						//g.drawImage(image2,x出力最初,y出力最初,
					 					//出力最後,出力最後,
					 					//元画像最初,元画像最初,
					 					//元画像最後,元画像最後,null);
						
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
	
	
	//Game時
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
	//イベント用描画
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
	
	
	
	//移動設定用
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
	
	
	//レイヤーをどうのこうのする場所
	//最初に呼ばれる
	//適当に拡張して(乗算とかスクリーンとか)
	public void Layer(int[][] Map,int[][] Map2,int[][] Map3,int[][] Map4,int[][] Map5,int[][] MapImage,int LayerNum,int MaxX,int MaxY,int BoundaryWidth,int BoundaryHeight,int scrollX,int scrollY,int panelX,int panelY){
		//描画する始めの場所(左上?)
		int cowFirst = scrollY/BoundaryHeight;
		int rowFirst = scrollX/BoundaryWidth;
		//描画する終わり(右下?)
		int cow = (panelY/BoundaryHeight) + cowFirst;
		int row = (panelX/BoundaryWidth) + rowFirst;
		//エラー用(はみ出てるとこ)
		if(MaxY < cow)
			cow = MaxY;
		if(MaxX < row)
			row = MaxX;
		//ノーマル版
		if(Mapediter.MapMode < 3)
			LayerNormal(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);
		else if(Mapediter.MapMode == 3)
			LayerEvent(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);
		else if(Mapediter.MapMode == 4)
			LayerNormal(Map, Map2,Map3,Map4,Map5,MapImage,0,cow,row,cowFirst,rowFirst);

			
			
	}
	//ノーマル版
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

	
	//なんかあれです
	//マップチップのとこのやつ
	public void MapChipshow(Graphics g, Image MapImage,int BoundaryWidth,int BoundaryHeight,int pngWidth,int pngHeight,ImageObserver ImageObserver,int MapChipNumber, int mouseButtonX, int mouseButtonY, int mouseButtonNowPointX, int mouseButtonNowPointY,int[][] MapChipPass){
		int rectX;//マップチップ選択範囲X
		int rectY;//マップチップ選択範囲Y
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
		 //複数選択
		 //跡地
		 //開発中
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
	
	//透明度の変更
	//changeImageに透明度を変えたい画像
	//transparencyに透明度(0から255)
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
	
	//透明度の変更
	//changeImageに透明度を変えたい画像
	//transparencyに透明度(0から255)
	public static void changeTransparent(BufferedImage changeImage,int R,int G,int B,int A){
		Color c = new Color(R,G,B,A);
		Graphics2D g2 = (Graphics2D) changeImage.getGraphics();
		g2.drawImage(changeImage, 0, 0, null);
		g2.setColor(c);
		g2.fillRect(0, 0, changeImage.getWidth(null),changeImage.getHeight(null));
		g2.dispose();
		
		
	}
	
	
	
	

	
}
