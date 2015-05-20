import java.awt.Image;
import java.util.ArrayList;


public class MapEvent{
	private int MapEventNumber;
	private Image image;
	private int x;
	private int y;
	private double[] free = new double[Common.freeVariableMax];
	private int MapPassNumber;
	private int MapMoveProbability;//移動確立0～100まで
	private int direction;//向いている方向
	private ArrayList<Byte> walkRoadPath = new ArrayList<Byte>();//移動軌跡
	private int LRcount = 0;//左右どちらのほうに歩くてきな
	
	public MapEvent(){
		image = ImageLoadClass.ImageLoad("");
		MapEventNumber = -1;
		MapPassNumber = 0;
		MapMoveProbability = 0;
		direction = Common.DOWN;
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
		x = tmp;
		if(tmp < 0)
			x = 0;
		
	}
	public void SetYCoordinate(int tmp){
		y = tmp;
		if(tmp < 0)
			y = 0;
		
	}
	public void SetXYCoordinate(int[] tmp){
		x = tmp[0];
		if(tmp[1] < 0)
			x = 0;
		y = tmp[1];
		if(tmp[1] < 0)
			y = 0;
		
	}
	
	/*
	 * 現在の位置に足す
	 */
	public void AddXCoordinate(int tmp){
		x += tmp;
		if(x < 0)
			x = 0;
	}
	public void AddYCoordinate(int tmp){
		y += tmp;
		if(y < 0)
			y = 0;
	}
	public void AddXYCoordinate(int[] tmp){
		x += tmp[0];
		if(x < 0)
			x = 0;
		y += tmp[1];
		if(y < 0)
			y = 0;
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
	/*
	 * バグ発生の原因となる可能性がある
	 */
	public void setAllMapMoveProbability(int mapMoveProbability) {
		MapMoveProbability = mapMoveProbability;
	}
	public void setMapMoveProbability(int mapMoveProbability) {
		MapMoveProbability = mapMoveProbability;
		if(MapMoveProbability < 0){
			MapMoveProbability = 0;
		}
		else if(MapMoveProbability > 100){
			MapMoveProbability = 100;
		}
	}
	public int getMapMoveProbability() {
		return MapMoveProbability;
	}
	public void setDirection(int direction) {
		this.direction = direction;
		if(direction > Common.Max){
			direction = Common.DOWN;
		}
		
	}
	public int getDirection() {
		return direction;
	}
	
	
	public void Move(int direction){
		setDirection(direction);
		switch(direction){
		case Common.UP:
			if(isHit(x,y-1)){
				AddYCoordinate(-1);
			}
			break;
		case Common.DOWN:
			if(isHit(x,y+1)){
				AddYCoordinate(1);
			}
			break;
		case Common.LEFT:
			if(isHit(x-1,y)){
				AddXCoordinate(-1);
			}
			break;
		case Common.RIGHT:
			if(isHit(x+1,y)){
				AddXCoordinate(1);
			}
			break;
		}

	}
	//通行可能かどうかの判定
	public boolean isHit(int xx,int yy){
		boolean ret = true;
		if(xx < 0 || yy < 0 || xx >= Mapediter.row || yy >= Mapediter.cow)
			ret = false;
		else if(Mapediter.Map5[xx][yy] == 0)
			ret = false;
		if(MapEventNumber == 0 && ret == false){
			Mapediter.bgmse.start(0);
		}
		return ret;
	}
	public void setWalkRoadPath(int num) {
		if(num < Common.Max)
			this.walkRoadPath.add((byte) num);
	}
	public void setWalkRoadPath(int setnum,int num) {
		if(num < Common.Max)
			this.walkRoadPath.add(setnum,(byte) num);
	}
	public ArrayList<Byte> getWalkRoadPath(int num) {
		return walkRoadPath;
	}
	public Byte getWalkRoadPath2(int num) {
		return walkRoadPath.get(num);
	}
	public int getLRcount() {
		return LRcount;
	}

}