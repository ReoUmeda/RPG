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
						//g.drawImage(image2,x出力最初,y出力最初,
					 					//出力最後,出力最後,
					 					//元画像最初,元画像最初,
					 					//元画像最後,元画像最後,null);
					 //System.out.println(cow);
				 }
			 }
			 
		 }catch(NullPointerException e){
	 }
		 

		 g.setColor(Color.red);
		 g.drawRect((MouseX/BoundaryWidth)*BoundaryWidth, (MouseY/BoundaryHeight)*BoundaryHeight, BoundaryWidth, BoundaryHeight);
		System.out.println(System.currentTimeMillis()-start);
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
		if(LayerNum == 0)
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
	
	//なんかあれです
	//マップチップのとこのやつ
	public void MapChipshow(Graphics g, Image MapImage,int BoundaryWidth,int BoundaryHeight,int pngWidth,int pngHeight,ImageObserver ImageObserver,int MapChipNumber, int mouseButtonX, int mouseButtonY, int mouseButtonNowPointX, int mouseButtonNowPointY){
		int rectX;//マップチップ選択範囲X
		int rectY;//マップチップ選択範囲Y
		g.drawImage(MapImage,0, 0,ImageObserver);
		 
		 for(int i=0;i<pngWidth;i++){
			 for(int j=0;j<pngHeight;j++){
				 g.drawRect(i*BoundaryWidth, j*BoundaryHeight, BoundaryWidth, BoundaryHeight);
			 }
		 }
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
	//transparencyに透明度(0から1)
	public static void changeTransparent(Image changeImage,float transparency){
		Graphics g = changeImage.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
		g2.drawImage(changeImage, 0, 0, null);
		g2.dispose();
		
		
	}
	
}
