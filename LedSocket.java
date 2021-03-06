import java.net.*;
import java.util.Random;
import java.io.*;



//照明番号は左端から1
//前から後ろに番号が増えていく
/*
 * {0,3
 * 1,4
 * 2,5
 */

public class LedSocket extends Socket {
    /**
     * 辣ｧ譏弱?邱乗焚
     */
    public static final int LED_COUNT = 29;
    /**
     * 辣ｧ譏?縺､縺ｮLED縺ｮ濶ｲ謨ｰ
     */
    public static final int LED_COLOR = 4;
    /**
     * 繝?ヵ繧ｩ繝ｫ繝医〒菴ｿ逕ｨ縺輔ｌ繧九?繝ｼ繝育分蜿ｷ
     */
    public static final int DEFAULT_PORT = 14649;
    /**
     * 繝?ヵ繧ｩ繝ｫ繝医〒菴ｿ逕ｨ縺輔ｌ繧九?繧ｹ繝亥錐
     */
    public static final String DEFAULT_HOST = "172.20.11.68";
    /**
     * 辣ｧ譏弱↓騾∽ｿ｡縺吶ｋ菫｡蜿ｷ縺ｮ譛?､ｧ蛟､
     */
    public static final int MAX_LEVEL = 1000;
    /**
     * 辣ｧ譏弱↓騾∽ｿ｡縺吶ｋ菫｡蜿ｷ縺ｮ譛?ｰ丞?
     */
    public static final int MIN_LEVEL = 0;

    private static final int SLEEP_COUNT = 20;
    private OutputStream os;
    private BufferedWriter bw;

    /**
     * 繝?ヵ繧ｩ繝ｫ繝医?繝帙せ繝亥錐縺ｨ繝昴?繝育分蜿ｷ繧剃ｽｿ逕ｨ縺励※謗･邯壹＠縺ｾ縺呻ｼ?     */
    public LedSocket() throws UnknownHostException, IOException {
	this(DEFAULT_HOST, DEFAULT_PORT);
    }

    /**
     * 繝帙せ繝亥錐縺ｨ繝昴?繝育分蜿ｷ繧呈欠螳壹＠縺ｦ謗･邯壹＠縺ｾ縺呻ｼ?     * @param host 繝帙せ繝亥錐
     * @param port 繝昴?繝育分蜿ｷ
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
     * 辣ｧ譏弱☆縺ｹ縺ｦ縺ｫ蜷後§菫｡蜿ｷ蛟､繧帝?菫｡縺励∪縺呻ｼ?br>
     * 縺昴ｌ縺槭ｌ縺ｮ菫｡蜿ｷ蛟､縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ1000縺ｧ縺呻ｼ?     * @param red 襍､濶ｲLED縺ｮ菫｡蜿ｷ蛟､
     * @param green 邱題牡LED縺ｮ菫｡蜿ｷ蛟､
     * @param blue 髱定牡LED縺ｮ菫｡蜿ｷ蛟､
     * @param yellow 鮟?牡LED縺ｮ菫｡蜿ｷ蛟､
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
     * 辣ｧ譏守分蜿ｷ繧呈欠螳壹＠縺ｦ菫｡蜿ｷ蛟､繧帝?菫｡縺励∪縺呻ｼ?br>
     * 縺昴ｌ縺槭ｌ縺ｮ菫｡蜿ｷ蛟､縺ｮ譛牙柑遽?峇0縺九ｉ1000縺ｧ縺呻ｼ?br>
     * 辣ｧ譏守分蜿ｷ縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ28縺ｧ縺呻ｼ?br>
     * @param index 蟇ｾ雎｡縺ｨ縺ｪ繧記ED縺ｮ逡ｪ蜿ｷ??     * @param red 襍､濶ｲLED縺ｮ菫｡蜿ｷ蛟､
     * @param green 邱題牡LED縺ｮ菫｡蜿ｷ蛟､
     * @param blue 髱定牡LED縺ｮ菫｡蜿ｷ蛟､
     * @param yellow 鮟?牡LED縺ｮ菫｡蜿ｷ蛟､
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
     * 辣ｧ譏弱☆縺ｹ縺ｦ縺ｫ蜷後§菫｡蜿ｷ蛟､繧帝?菫｡縺励∪縺呻ｼ?br>
     * 縺昴ｌ縺槭ｌ縺ｮ菫｡蜿ｷ蛟､縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ1000縺ｧ縺呻ｼ?     * @param led 襍､繝ｻ邱代?髱偵?鮟??4濶ｲ縺ｮ菫｡蜿ｷ蛟､繧呈?邏阪＠縺滄?蛻?     */
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
     * 辣ｧ譏守分蜿ｷ繧呈欠螳壹＠縺ｦ菫｡蜿ｷ蛟､繧帝?菫｡縺励∪縺呻ｼ?br>
     * 縺昴ｌ縺槭ｌ縺ｮ菫｡蜿ｷ蛟､縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ1000縺ｧ縺呻ｼ?br>
     * 辣ｧ譏守分蜿ｷ縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ28縺ｧ縺呻ｼ?     * @param id 辣ｧ譏守分蜿ｷ
     * @param led 襍､繝ｻ邱代?髱偵?鮟??4濶ｲ縺ｮ菫｡蜿ｷ蛟､繧呈?邏阪＠縺滄?蛻?     */
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
     * 辣ｧ譏弱☆縺ｹ縺ｦ縺ｫ菫｡蜿ｷ蛟､繧帝?菫｡縺励∪縺呻ｼ?br>
     * 縺昴ｌ縺槭ｌ縺ｮ菫｡蜿ｷ蛟､縺ｮ譛牙柑遽?峇縺ｯ0縺九ｉ1000縺ｧ縺呻ｼ?     * @param led 襍､繝ｻ邱代?髱偵?鮟??4濶ｲ縺ｮ菫｡蜿ｷ蛟､繧呈?邏阪＠縺滄?蛻暦ｼ朱?蛻励?螟ｧ縺阪＆縺ｯint[29][4]縺ｧ縺呻ｼ?     */
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

	    for(int i=0;i<3;i++){
		   /* red = rnm.nextInt(max);
		    green = rnm.nextInt(max);
		    blue = rnm.nextInt(max);
		    yellow = rnm.nextInt(max);
		    index = rnm.nextInt(30);*/
		    //s.send(index,red, green, blue, yellow);
	    	 s.send(0, 0, 0, 0);
	    	 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 s.send(1000, 1000, 1000, 1000);
	    	 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    }
	    s.send(10,10,10,10);
	    s.send(1, 1, 1, 1);
	    s.send(1000, 0, 0, 0);
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

