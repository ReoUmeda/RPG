import java.awt.Image;
import java.util.ArrayList;


public class MapEvent{
	private int MapEventNumber = -1;
	private Image image = ImageLoadClass.ImageLoad("");
	private int x,y;//現在位置
	private int px = 0,py = 0;//現在ピクセル
	private double[] free = new double[Common.freeVariableMax];
	private int MapPassNumber = 0;
	private int MapMoveProbability = 0;//移動確立0～100まで
	private int direction = Common.DOWN;//向いている方向
	private ArrayList<Byte> walkRoadPath = new ArrayList<Byte>();//移動軌跡
	private int LRcount = 0;//左右どちらのほうに歩くてきな
	private int LRNext = 0;//次が右か左か
	private int moveSpeed = 1*Mapediter.magnification;//一ループでなんピクセルすすむか
    //  移動中（スクロール中）か
    private boolean isMoving = false;
    //  移動中の場合の移動ピクセル数
    private int movingLength = 0;
	
	public MapEvent(){
		/*image = ImageLoadClass.ImageLoad("");
		MapEventNumber = -1;
		MapPassNumber = 0;
		MapMoveProbability = 0;
		direction = Common.DOWN;
		isMoving = false;
		py = 0;
		px = 0;
		LRcount = 0;
		moveSpeed = 2;
		movingLength = 0;*/
		
	}
	public MapEvent(int num){
		MapEventNumber = num;
	}
	public MapEvent(int num,int pass){
		MapEventNumber = num;
		MapPassNumber = pass;
	}
	public MapEvent(int num,int thisX,int thisY){
		MapEventNumber = num;
		x = thisX;
		y = thisY;
		
	}
	public MapEvent(int num,int pass,int thisX,int thisY){
		MapEventNumber = num;
		MapPassNumber = pass;
		x = thisX;
		y = thisY;
		
	}
	
	public MapEvent(int num,String s){
		MapEventNumber = num;
		image = ImageLoadClass.ImageLoad(s);
	}
	public MapEvent(int num,int pass,String s){
		MapEventNumber = num;
		MapPassNumber = pass;
		image = ImageLoadClass.ImageLoad(s);
	}
	public MapEvent(int num,int thisX,int thisY,String s){
		MapEventNumber = num;
		x = thisX;
		y = thisY;
		image = ImageLoadClass.ImageLoad(s);
		
	}
	public MapEvent(int num,int pass,int thisX,int thisY,String s){
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
	
	/*
	 * 一マス単位での移動です
	 */
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
		if(xx < 0){
			xx = 0;
			ret = false;
		}
		else if(yy < 0){
			yy = 0;
			ret = false;
		}
		else if(xx >= Mapediter.row){
			x = Mapediter.row - 1;
			ret = false;
		}
		else if(yy >= Mapediter.cow){
			y = Mapediter.cow - 1;
			ret = false;
		}
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
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed*Mapediter.magnification;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	/*public void setPx(int px) {
		this.px = px;
	}*/
	public int getPx() {
		return px;
	}
	/*public void setPy(int py) {
		this.py = py;
	}*/
	public int getPy() {
		return py;
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	public boolean isMoving() {
		//System.out.println(isMoving);
		return isMoving;
	}
	/*
	 * ピクセル単位での移動です
	 */
    public boolean Move() {
        switch (direction) {
            case Common.LEFT:
                if (moveLeft()) {
                    // 移動が完了した
                	LRcount = 0;
                    return true;
                }
                break;
            case Common.RIGHT:
                if (moveRight()) {
                    // 移動が完了した
                	LRcount = 0;
                    return true;
                }
                break;
            case Common.UP:
                if (moveUp()) {
                    // 移動が完了した
                	LRcount = 0;
                    return true;
                }
                break;
            case Common.DOWN:
                if (moveDown()) {
                    // 移動が完了した
                	LRcount = 0;
                    return true;
                }
                break;
        }
        
        // 移動が完了していない
        return false;
    }
    /**
     * 左へ移動する。
     * @return 移動が完了したらtrueを返す。移動中はfalseを返す。
     */
    private boolean moveLeft() {
        // 1マス先の座標
        int nextX = x - 1;
        if(LRcount == 0 && LRNext == 0){
        	LRcount = 1;
        	LRNext = 1;
        }
        else if(LRcount == 0 && LRNext == 1){
        	LRcount = 2;
        	LRNext = 0;
        }
        // その場所に障害物がなければ移動を開始
        if (isHit(nextX, y)) {
            // SPEEDピクセル分移動
            px -= moveSpeed;
            // 移動距離を加算
            movingLength += moveSpeed;
            // 移動が1マス分を超えていたら
            if (movingLength >= Mapediter.BoundaryWidth*Mapediter.magnification) {
                // 移動する
                x -= movingLength/(Mapediter.BoundaryWidth*Mapediter.magnification);
                if (x < 0) x = 0;
                px = 0;
                // 移動が完了
                movingLength = 0;
                isMoving = false;
                return true;
            }
        } 
        else {
            isMoving = false;
        }
        
        return false;
    }
    /**
     * 右へ移動する。
     * @return 移動が完了したらtrueを返す。移動中はfalseを返す。
     */
    private boolean moveRight() {
        // 1マス先の座標
        int nextX = x + 1;
        if(LRcount == 0 && LRNext == 0){
        	LRcount = 1;
        	LRNext = 1;
        }
        else if(LRcount == 0 && LRNext == 1){
        	LRcount = 2;
        	LRNext = 0;
        }
        // その場所に障害物がなければ移動を開始
        if (isHit(nextX, y)) {
            // SPEEDピクセル分移動
            px += moveSpeed;
            if (px >= Mapediter.row){
            	px = Mapediter.row-1;
            }
            // 移動距離を加算
            movingLength += moveSpeed;
            // 移動が1マス分を超えていたら
            if (movingLength >= Mapediter.BoundaryWidth*Mapediter.magnification) {
                // 移動する
                x += movingLength/(Mapediter.BoundaryWidth*Mapediter.magnification);
                if (x >= Mapediter.row){
                	x = Mapediter.row-1;
                }
                px = 0;
                movingLength = 0;
                // 移動が完了
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
        }
        
        return false;
    }
    
    /**
     * 上へ移動する。
     * @return 移動が完了したらtrueを返す。移動中はfalseを返す。
     */
    private boolean moveUp() {
        // 1マス先の座標
        int nextY = y - 1;
        if(LRcount == 0 && LRNext == 0){
        	LRcount = 1;
        	LRNext = 1;
        }
        else if(LRcount == 0 && LRNext == 1){
        	LRcount = 2;
        	LRNext = 0;
        }
        // その場所に障害物がなければ移動を開始
        if (isHit(x, nextY)) {
            // SPEEDピクセル分移動
            py -= moveSpeed;
            // 移動距離を加算
            movingLength += moveSpeed;
            // 移動が1マス分を超えていたら
            if (movingLength >= Mapediter.BoundaryHeight*Mapediter.magnification) {
                // 移動する
                y -= movingLength/(Mapediter.BoundaryHeight*Mapediter.magnification);
                if (y < 0) y = 0;
                py = 0;
                movingLength = 0;
                // 移動が完了
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
        }
        
        return false;
    }
    
    /**
     * 下へ移動する。
     * @return 移動が完了したらtrueを返す。移動中はfalseを返す。
     */
    private boolean moveDown() {
        // 1マス先の座標
        int nextY = y + 1;
        if(LRcount == 0 && LRNext == 0){
        	LRcount = 1;
        	LRNext = 1;
        }
        else if(LRcount == 0 && LRNext == 1){
        	LRcount = 2;
        	LRNext = 0;
        }
        // その場所に障害物がなければ移動を開始
        if (isHit(x, nextY)) {
            // SPEEDピクセル分移動
            py += moveSpeed;
            if (py >= Mapediter.cow){
            	py = Mapediter.cow - 1;
            }
            // 移動距離を加算
            movingLength += moveSpeed;
            // 移動が1マス分を超えていたら
            if (movingLength >= Mapediter.BoundaryHeight*Mapediter.magnification) {
                // 移動する
                y += movingLength/(Mapediter.BoundaryHeight*Mapediter.magnification);
                if (y >= Mapediter.cow){
                	y = Mapediter.cow - 1;
                }
                py = 0;
                movingLength = 0;
                // 移動が完了
                isMoving = false;
                return true;
            }
        } else {
            isMoving = false;
        }
        
        return false;
    }
    

    

}