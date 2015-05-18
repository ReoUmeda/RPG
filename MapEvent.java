import java.awt.Image;


public class MapEvent{
	private int MapEventNumber;
	private Image image;
	private int x;
	private int y;
	private double[] free = new double[Common.freeVariableMax];
	private int MapPassNumber;
	
	public MapEvent(){
		image = ImageLoadClass.ImageLoad("");
		MapEventNumber = -1;
		MapPassNumber = 0;
	}
	public MapEvent(int num){
		super();
		MapEventNumber = num;
	}
	public MapEvent(int num,int pass){
		super();
		MapEventNumber = num;
		MapPassNumber = pass;
	}
	public MapEvent(int num,int thisX,int thisY){
		super();
		MapEventNumber = num;
		x = thisX;
		y = thisY;
		
	}
	public MapEvent(int num,int pass,int thisX,int thisY){
		super();
		MapEventNumber = num;
		MapPassNumber = pass;
		x = thisX;
		y = thisY;
		
	}
	
	public MapEvent(int num,String s){
		super();
		MapEventNumber = num;
		image = ImageLoadClass.ImageLoad(s);
	}
	public MapEvent(int num,int pass,String s){
		super();
		MapEventNumber = num;
		MapPassNumber = pass;
		image = ImageLoadClass.ImageLoad(s);
	}
	public MapEvent(int num,int thisX,int thisY,String s){
		super();
		MapEventNumber = num;
		x = thisX;
		y = thisY;
		image = ImageLoadClass.ImageLoad(s);
		
	}
	public MapEvent(int num,int pass,int thisX,int thisY,String s){
		super();
		MapEventNumber = num;
		MapPassNumber = pass;
		x = thisX;
		y = thisY;
		image = ImageLoadClass.ImageLoad(s);
		
	}
	
	
	public int GetXCoordinate(){
		return x;
		
	}
	public int GetYCoordinate(){
		return y;
		
	}
	public int[] GetXYCoordinate(){
		int[] ret = new int[2];
		ret[0] = x;
		ret[1] = y;
		return ret;
	}
	/*
	 * 位置を変更
	 */
	public void SetXCoordinate(int tmp){
		if(tmp < 0)
			x = tmp;
		
	}
	public void SetYCoordinate(int tmp){
		if(tmp < 0)
			y = tmp;
		
	}
	public void SetXYCoordinate(int[] tmp){
		if(tmp[0] < 0)
			x = tmp[0];
		if(tmp[1] < 0)
			y = tmp[1];
		
	}
	
	/*
	 * 現在の位置に足す
	 */
	public void AddXCoordinate(int tmp){
		if(x+tmp < 0)
			x = x + tmp;
		
	}
	public void AddYCoordinate(int tmp){
		if(y+tmp < 0)
			y = y + tmp;
		
	}
	public void AddXYCoordinate(int[] tmp){
		if(x+tmp[0] < 0)
			x = x + tmp[0];
		if(y+tmp[1] < 0)
			y = y + tmp[1];
		
	}
	
	/*
	 * -の値に変更可能
	 * 使うとバグが発生する恐れあり
	 */
	public void SetAllXYCoordinate(int[] tmp){
			x = tmp[0];
			y = tmp[1];
		
	}
	
	public Image GetImage(){
		return image;
	}
	public void SetImage(String s){
		image = ImageLoadClass.ImageLoad(s);
	}
	
	public int GetMapEventNumber(){
		return MapEventNumber;
	}
	/*
	 * 現在ナンバーはユニークではありません
	 * -1にすると無効になる予定
	 */
	public void SetMapEventNumber(int tmp){
		MapEventNumber = tmp;
		
	}
	
	public double GetFreeVariable(int ArrayElement){
		if(ArrayElement < Common.freeVariableMax)
			return free[ArrayElement];
		return Double.NaN;
		
	}
	
	public void SetFreeVariable(double set,int ArrayElement){
		if(ArrayElement < Common.freeVariableMax)
			free[ArrayElement] = set;
	}
	
	public int GetMapPassNumber(){
		return MapPassNumber;
	}
	
	public void SetMapPassNumber(int pass){
		MapPassNumber = pass;
	}
	

}