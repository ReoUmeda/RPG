import java.net.*;
import java.io.*;

public class illumination {

/** ポート番号 */
public int port_no = 14649;
public String hostName = "172.20.11.68";
public static void main(String[] args){
/*try{
// ソケットを生成*Socket socket = new Socket(hostName, port_no);

// 出力ストリームを取得
PrintWriter osStr = new PrintWriter(socket.getOutputStream(), true);

// 入力ストリームを取得
InputStream is = socket.getInputStream();
BufferedReader irStr = new BufferedReader(
new InputStreamReader(is));

// 「Hello World」をサーバーに送る
osStr.write("Hello World");
osStr.flush();

// データが来るまで、「あみん」してる（古！）
while(is.available() == 0);

// 読み込んだ内容をそのまま出力ストリームに書き出す
while (is.available() != 0) {
byte[] data = new byte[is.available()];
irStr.read(data, 0, is.available());
System.out.print(data);
} 
System.out.println();

// 入出力ストリームを閉じる
osStr.close();
irStr.close();

// ソケットを閉じる
socket.close();
} catch(IOException e) {
e.printStackTrace();
}*/
}
}