import java.awt.event.MouseEvent;


public class MapChipManage {
	public void MapChipManageMapMousePressed(MouseEvent e){
		int x,y,z;
		x= e.getX();
		y=e.getY();
		z=e.getButton();
		if(Mapediter.Map != null && z == MouseEvent.BUTTON1) {
			try{
				if(Mapediter.MapMode == 0)
					Mapediter.Map[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
				else if(Mapediter.MapMode == 1)
					Mapediter.Map2[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
				else if(Mapediter.MapMode == 2)
					Mapediter.Map3[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
			}
			catch(ArrayIndexOutOfBoundsException e1){
				System.out.println("x:"+x/Mapediter.BoundaryWidth+" y:"+(y/Mapediter.BoundaryHeight));
			}
			System.out.println(Mapediter.MapChipNumder);
		}
		else if(z == MouseEvent.BUTTON3){
			if(Mapediter.MapMode == 0)
				Mapediter.MapChipNumder = Mapediter.Map[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight];
			else if(Mapediter.MapMode == 1)
				Mapediter.MapChipNumder = Mapediter.Map2[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight];
			else if(Mapediter.MapMode == 2)
				Mapediter.MapChipNumder = Mapediter.Map3[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight];
		}
		//System.out.println("mousepress "+y+","+x+"");
	}
	
	public void MapChipManageMapMouseDragged(MouseEvent e,int MouseButtonForward){
		int x,y,z;
		x= e.getX();
		y=e.getY();

		if(Mapediter.Map != null && MouseButtonForward == MouseEvent.BUTTON1) {
			try{
				if(Mapediter.MapMode == 0)
					Mapediter.Map[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
				else if(Mapediter.MapMode == 1)
					Mapediter.Map2[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
				else if(Mapediter.MapMode == 2)
					Mapediter.Map3[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
			}catch(ArrayIndexOutOfBoundsException e1){
				//System.out.println(e1);
			}
			//System.out.println(Mapediter.MapChipNumder);
		}
		//System.out.println("mousepress "+y+","+x+"");
	}

	
	//ï°êîëIë ñ¢äÆê¨
	public void MapChipManageMapMouseDragged(MouseEvent e,
			int MouseButtonForward, int MouseButtonX, int MouseButtonY,
			int MouseButtonNowPointX, int MouseButtonNowPointY) {
		int x,y,i=0,j=0,BlockFirstX,BlockFirstY,BlockLastX,BlockLastY;
		x= e.getX();
		y=e.getY();
		BlockFirstX = x/Mapediter.BoundaryWidth;
		BlockFirstY = y/Mapediter.BoundaryHeight;
		
		BlockLastX = MouseButtonNowPointX-MouseButtonX;
		BlockLastY = MouseButtonNowPointY-MouseButtonY;
		
		if(BlockLastX % Mapediter.BoundaryWidth != 0)
			BlockLastX = BlockLastX + Mapediter.BoundaryWidth;
		if(BlockLastY % Mapediter.BoundaryHeight != 0)
			BlockLastY = BlockLastY + Mapediter.BoundaryHeight;
		
		BlockLastX = (BlockLastX/Mapediter.BoundaryWidth) + BlockFirstX;
		BlockLastY = (BlockLastY/Mapediter.BoundaryHeight) + BlockFirstY;
		
		if(Mapediter.row < BlockLastX)
			BlockLastX = Mapediter.row;
		if(Mapediter.cow < BlockLastY)
			BlockLastY = Mapediter.cow;
		

		if(Mapediter.Map != null && MouseButtonForward == MouseEvent.BUTTON1) {
			try{
				Mapediter.Map[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapChipNumder;
				//Ç¢Ç¡Ç´Ç…ëIëÇµÇΩÇ‚Ç¬Çì\ÇËïtÇØÇÈ
				/*for(i=BlockFirstX;i<BlockLastX;i++){
					for(j=BlockFirstY;j<BlockLastY;j++){
						Mapediter.Map[i][j] = Mapediter.MapChipNumder+i*j+j;
					}
				}*/
			}catch(ArrayIndexOutOfBoundsException e1){

			}

		}

		
	}

}
