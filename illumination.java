import java.net.*;
import java.io.*;

public class illumination {

/** �|�[�g�ԍ� */
public int port_no = 14649;
public String hostName = "172.20.11.68";
public static void main(String[] args){
/*try{
// �\�P�b�g�𐶐�*Socket socket = new Socket(hostName, port_no);

// �o�̓X�g���[�����擾
PrintWriter osStr = new PrintWriter(socket.getOutputStream(), true);

// ���̓X�g���[�����擾
InputStream is = socket.getInputStream();
BufferedReader irStr = new BufferedReader(
new InputStreamReader(is));

// �uHello World�v���T�[�o�[�ɑ���
osStr.write("Hello World");
osStr.flush();

// �f�[�^������܂ŁA�u���݂�v���Ă�i�ÁI�j
while(is.available() == 0);

// �ǂݍ��񂾓��e�����̂܂܏o�̓X�g���[���ɏ����o��
while (is.available() != 0) {
byte[] data = new byte[is.available()];
irStr.read(data, 0, is.available());
System.out.print(data);
} 
System.out.println();

// ���o�̓X�g���[�������
osStr.close();
irStr.close();

// �\�P�b�g�����
socket.close();
} catch(IOException e) {
e.printStackTrace();
}*/
}
}