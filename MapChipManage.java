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
				
				Mapediter.Map5[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = 
					mapchip.MapChipPass[Mapediter.MapChipNumder%Mapediter.MapChipPngWidth_MapChipSizeWidth][Mapediter.MapChipNumder/Mapediter.MapChipPngWidth_MapChipSizeWidth];
				
				
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
				
				Mapediter.Map5[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = 
					mapchip.MapChipPass[Mapediter.MapChipNumder%Mapediter.MapChipPngWidth_MapChipSizeWidth][Mapediter.MapChipNumder/Mapediter.MapChipPngWidth_MapChipSizeWidth];
				
			}catch(ArrayIndexOutOfBoundsException e1){
				//System.out.println(e1);
			}
			//System.out.println(Mapediter.MapChipNumder);
		}
		
		//System.out.println("mousepress "+y+","+x+"");
	}

	
	//�����I�� ������
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
				//�������ɑI���������\��t����
				/*for(i=BlockFirstX;i<BlockLastX;i++){
					for(j=BlockFirstY;j<BlockLastY;j++){
						Mapediter.Map[i][j] = Mapediter.MapChipNumder+i*j+j;
					}
				}*/
			}catch(ArrayIndexOutOfBoundsException e1){

			}

		}

		
	}

	public void MapChipManageEvent(MouseEvent e) {
		int x,y;
		x= e.getX();
		y=e.getY();
		
		if(Mapediter.Map4 != null) {
			try{
				Mapediter.Map4[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = 1;
			}
			catch(ArrayIndexOutOfBoundsException e1){
				System.out.println("x:"+x/Mapediter.BoundaryWidth+" y:"+(y/Mapediter.BoundaryHeight));
			}
			//System.out.println(Mapediter.Map4[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight]+"aaa");
			/*for(int i = 0;i<11;i++){
				for(int j=0;j<11;j++){
					System.out.print(Mapediter.Map4[i][j]);
				}
				//System.out.println("");
			
			}*/
		}
		
		
	}
	public void MapChipManagePass(MouseEvent e) {
		int x,y;
		x= e.getX();
		y=e.getY();
		
		if(Mapediter.Map5 != null) {
			try{
				Mapediter.Map5[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight] = Mapediter.MapPassNumder;
			}
			catch(ArrayIndexOutOfBoundsException e1){
				System.out.println("x:"+x/Mapediter.BoundaryWidth+" y:"+(y/Mapediter.BoundaryHeight));
			}
			//System.out.println(Mapediter.Map4[x/Mapediter.BoundaryWidth][y/Mapediter.BoundaryHeight]+"aaa");
			/*for(int i = 0;i<11;i++){
				for(int j=0;j<11;j++){
					System.out.print(Mapediter.Map4[i][j]);
				}
				//System.out.println("");
			
			}*/
		}
		
		
	}

}
