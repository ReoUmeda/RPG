import java.net.*;
import java.util.Random;
import java.io.*;



//�Ɩ��ԍ��͍��[����1
//�O������ɔԍ��������Ă���
/*
 * {0,3
 * 1,4
 * 2,5
 */

public class LedSocket extends Socket {
    /**
     * 照明�?総数
     */
    public static final int LED_COUNT = 29;
    /**
     * 照�?つのLEDの色数
     */
    public static final int LED_COLOR = 4;
    /**
     * �?��ォルトで使用される�?ート番号
     */
    public static final int DEFAULT_PORT = 14649;
    /**
     * �?��ォルトで使用される�?スト名
     */
    public static final String DEFAULT_HOST = "172.20.11.68";
    /**
     * 照明に送信する信号の�?��値
     */
    public static final int MAX_LEVEL = 1000;
    /**
     * 照明に送信する信号の�?���?
     */
    public static final int MIN_LEVEL = 0;

    private static final int SLEEP_COUNT = 20;
    private OutputStream os;
    private BufferedWriter bw;

    /**
     * �?��ォルト�?ホスト名とポ�?ト番号を使用して接続します�?     */
    public LedSocket() throws UnknownHostException, IOException {
	this(DEFAULT_HOST, DEFAULT_PORT);
    }

    /**
     * ホスト名とポ�?ト番号を指定して接続します�?     * @param host ホスト名
     * @param port ポ�?ト番号
     */
    public LedSocket(final String host, final int port) throws UnknownHostException, IOException {
	super(host, port);
	try {
	    os = getOutputStream();
	    bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	catch(Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    /**
     * 照明すべてに同じ信号値を�?信します�?br>
     * それぞれの信号値の有効�?��は0から1000です�?     * @param red 赤色LEDの信号値
     * @param green 緑色LEDの信号値
     * @param blue 青色LEDの信号値
     * @param yellow �?��LEDの信号値
     */
    public void send(final int red, final int green, final int blue, final int yellow) {
	int led[] = new int[4];
	led[0] = red;
	led[1] = green;
	led[2] = blue;
	led[3] = yellow;
	send(led);
    }

    /**
     * 照明番号を指定して信号値を�?信します�?br>
     * それぞれの信号値の有効�?��0から1000です�?br>
     * 照明番号の有効�?��は0から28です�?br>
     * @param index 対象となるLEDの番号??     * @param red 赤色LEDの信号値
     * @param green 緑色LEDの信号値
     * @param blue 青色LEDの信号値
     * @param yellow �?��LEDの信号値
     */
    public void send(final int index, final int red, final int green, final int blue, final int yellow) {
	int led[] = new int[4];
	led[0] = red;
	led[1] = green;
	led[2] = blue;
	led[3] = yellow;
	send(index, led);
    }

    /**
     * 照明すべてに同じ信号値を�?信します�?br>
     * それぞれの信号値の有効�?��は0から1000です�?     * @param led 赤・緑�?青�?�??4色の信号値を�?納した�?�?     */
    public void send(final int led[]) {
	for(int i=0; i<LED_COLOR; i++) {
	    if(led[i] < MIN_LEVEL || MAX_LEVEL < led[i]) {
		return;
	    }
	}
	try {
	    bw.write("SET_ALL" +  " " +
		     String.valueOf(led[0]) + "," +
		     String.valueOf(led[1]) + "," +
		     String.valueOf(led[2]) + "," +
		     String.valueOf(led[3]) + "," +
		     "\n");
	    bw.flush();
	    Thread.sleep(SLEEP_COUNT);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 照明番号を指定して信号値を�?信します�?br>
     * それぞれの信号値の有効�?��は0から1000です�?br>
     * 照明番号の有効�?��は0から28です�?     * @param id 照明番号
     * @param led 赤・緑�?青�?�??4色の信号値を�?納した�?�?     */
    public void send(final int id, final int led[]) {
	if(id < 0 || LED_COUNT < id) {
	    return;
	}
	for(int i=0; i<LED_COLOR; i++) {
	    if(led[i] < MIN_LEVEL || MAX_LEVEL < led[i]) {
		return;
	    }
	}
	try {
	    bw.write("SET_SINGLE " + String.valueOf(id) + " " +
		     String.valueOf(led[0]) + "," +
		     String.valueOf(led[1]) + "," +
		     String.valueOf(led[2]) + "," +
		     String.valueOf(led[3]) + "," +
		     "\n");
	    bw.flush();
	    Thread.sleep(SLEEP_COUNT);
	    bw.write("UPDATE\n");
	    bw.flush();
	    Thread.sleep(SLEEP_COUNT);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }

    public void setSingle(final int id, final int led[]) {
    	if(id < 0 || LED_COUNT < id) {
    	    return;
    	}
    	for(int i=0; i<LED_COLOR; i++) {
    	    if(led[i] < MIN_LEVEL || MAX_LEVEL < led[i]) {
    		return;
    	    }
    	}
    	try {
    	    bw.write("SET_SINGLE " + String.valueOf(id) + " " +
    		     String.valueOf(led[0]) + "," +
    		     String.valueOf(led[1]) + "," +
    		     String.valueOf(led[2]) + "," +
    		     String.valueOf(led[3]) + "," +
    		     "\n");
    	    bw.flush();
    	    Thread.sleep(SLEEP_COUNT);
    	}
    	catch(Exception e) {
    	    e.printStackTrace();
    	}
        }

    	public void update() {
    	try {
    	    bw.write("UPDATE\n");
    	    bw.flush();
    	    Thread.sleep(SLEEP_COUNT);
    	}
    	catch(Exception e) {
    	    e.printStackTrace();
    	}
        }

    /**
     * 照明すべてに信号値を�?信します�?br>
     * それぞれの信号値の有効�?��は0から1000です�?     * @param led 赤・緑�?青�?�??4色の信号値を�?納した�?列．�?列�?大きさはint[29][4]です�?     */
    public void send(final int led[][]) {
	boolean isValid = true;
	for(int i=0; i<LED_COUNT; i++) {
	    isValid = isValid && (led[i].length == LED_COLOR);
	}
	if ( led.length != LED_COUNT || isValid == false) {
	    return;
	}
	for(int i=0; i<LED_COUNT; i++) {
	    for(int j=0; j<LED_COLOR; j++) {
		if(led[i][j] < MIN_LEVEL || MAX_LEVEL < led[i][j]) {
		    return;
		}
	    }
	}

	try {
	    for(int i=0; i<LED_COUNT; i++) {
		bw.write("SET_SINGLE " + String.valueOf(i) + " " +
			 String.valueOf(led[i][0]) + "," +
			 String.valueOf(led[i][1]) + "," +
			 String.valueOf(led[i][2]) + "," +
			 String.valueOf(led[i][3]) + "," +
			 "\n");
		bw.flush();
		Thread.sleep(SLEEP_COUNT);
	    }
	    bw.write("UPDATE\n");
	    bw.flush();
	    Thread.sleep(SLEEP_COUNT);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }
	public static void main(String args[]) throws IOException, UnknownHostException {
	    LedSocket s = new LedSocket(DEFAULT_HOST, DEFAULT_PORT);

	    int red = 200, green = 200, blue = 300, yellow = 400;
	    int index = 10;
	    int max = 1000;
	    Random rnm = new Random();

	    /*for(int i=0;i<30;i++){
		    red = rnm.nextInt(max);
		    green = rnm.nextInt(max);
		    blue = rnm.nextInt(max);
		    yellow = rnm.nextInt(max);
		    index = rnm.nextInt(30);
		    s.send(index,red, green, blue, yellow);
		    try {
				Thread.sleep(1000);
			    }
			    catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			    }
	    }*/
	    s.send(0,0,0,0);
	    System.out.println();
	   /* try {
		Thread.sleep(1000);
	    }
	    catch(InterruptedException e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    s.send(index, 1000, green, blue, yellow);*/
	    s.close();
	}

}

