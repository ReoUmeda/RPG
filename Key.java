import java.awt.event.KeyEvent;

//�L�[
public class Key {
	static int KeyUP = KeyEvent.VK_UP;
	static int KeyDOWN = KeyEvent.VK_DOWN;
	static int KeyLEFT = KeyEvent.VK_LEFT;
	static int KeyRIGTH = KeyEvent.VK_RIGHT;
	static int KeyCROSS = KeyEvent.VK_X; //�~
	static int KeyCIRCLE = KeyEvent.VK_Z; //�Z
	static int KeyTRIANGLE = KeyEvent.VK_S; //��
	static int KeySQUARE = KeyEvent.VK_A; //��
	static int KeySTART = KeyEvent.VK_ENTER;
	static int KeySELECT = KeyEvent.VK_SHIFT;
	static int KeyR1 = KeyEvent.VK_Q;
	static int KeyR2 = KeyEvent.VK_1;
	static int KeyL1 = KeyEvent.VK_W;
	static int KeyL2 = KeyEvent.VK_2;
	static int mode=0;
	//�����ŏ����̂��߂�ǂ��Ȃ̂œ����̂𑝂₵��
	static final String[] KeyStrings = {"KeyUP","KeyDOWN","KeyLEFT","KeyRIGTH",
			"KeyCROSS","KeyCIRCLE","KeyTRIANGLE","KeySQUARE","KeySTART",
			"KeySELECT","KeyR1","KeyR2","KeyL1","KeyL2"
	};
	private static int[] KeyKey = new int[KeyStrings.length];
	//static final int keynum = 14;
	
	//������key(���KeyUp�Ƃ�)�̂ǂꂩ�𔻒肵���l�Ƃ��ĕԂ�
	//KeyStrings�̔z��ԍ��ɑΉ�
	static public int KeyEquals(String KeyCode){
		int ret = -1;
		
		for(int counta=0;counta<KeyStrings.length;counta++)	
			if (KeyCode.equals(KeyStrings[counta])){
				ret = counta;
				break;
			}
		
		return ret;
	}
	//�L�[�ύX
	//�G���[�����Ƃ�����������΂����Ƃ��Ƃ���
	static synchronized int KeyCon(String KeyNum,int NewKeyNum){
		int ret = 0;
		int KeyNumTmp;
		
		switch (KeyEquals(KeyNum)) {
		case 0:
			KeyUP = NewKeyNum;
			break;
		case 1:
			KeyDOWN = NewKeyNum;
			break;
		case 2:
			KeyLEFT = NewKeyNum;
			break;
		case 3:
			KeyRIGTH = NewKeyNum;
			break;
		case 4:
			KeyCROSS = NewKeyNum;
			break;
		case 5:
			KeyCIRCLE = NewKeyNum;
			break;
		case 6:
			KeyTRIANGLE = NewKeyNum;
			break;
		case 7:
			KeySQUARE = NewKeyNum;
			break;
		case 8:
			KeySTART = NewKeyNum;
			break;
		case 9:
			KeySELECT = NewKeyNum;
			break;
		case 10:
			KeyR1 = NewKeyNum;
			break;
		case 11:
			KeyR2 = NewKeyNum;
			break;
		case 12:
			KeyL1 = NewKeyNum;
			break;
		case 13:
			KeyL2 = NewKeyNum;
			break;
		
		default:
			ret = -1;
			System.out.print("�w�肵������"+KeyNum+"�Ȃ�đ��݂��܂���");
		}
		if((KeyNumTmp = KeyEquals(KeyNum)) >= 0 && KeyNumTmp < KeyKey.length)
			KeyKey[KeyNumTmp] = NewKeyNum;
		else{
			ret = -1;
			System.out.print("�w�肵������"+KeyNum+"�Ȃ�đ��݂��܂���");
		}
			
		
		return ret;
	}
	//���l���ړ��͔�
	static synchronized int KeyCon(int KeyNum,int NewKeyNum){
		int ret = 0;
		
		switch (KeyNum) {
		case 0:
			KeyUP = NewKeyNum;
			break;
		case 1:
			KeyDOWN = NewKeyNum;
			break;
		case 2:
			KeyLEFT = NewKeyNum;
			break;
		case 3:
			KeyRIGTH = NewKeyNum;
			break;
		case 4:
			KeyCROSS = NewKeyNum;
			break;
		case 5:
			KeyCIRCLE = NewKeyNum;
			break;
		case 6:
			KeyTRIANGLE = NewKeyNum;
			break;
		case 7:
			KeySQUARE = NewKeyNum;
			break;
		case 8:
			KeySTART = NewKeyNum;
			break;
		case 9:
			KeySELECT = NewKeyNum;
			break;
		case 10:
			KeyR1 = NewKeyNum;
			break;
		case 11:
			KeyR2 = NewKeyNum;
			break;
		case 12:
			KeyL1 = NewKeyNum;
			break;
		case 13:
			KeyL2 = NewKeyNum;
			break;
		
		default:
			ret = -1;
			System.out.print("�w�肵���ԍ�"+KeyNum+"�Ȃ�đ��݂��܂���");
		}
		if(KeyNum >= 0 && KeyNum < KeyKey.length)
			KeyKey[KeyNum] = NewKeyNum;
		else{
			ret = -1;
			System.out.print("�w�肵���ԍ�"+KeyNum+"�Ȃ�đ��݂��܂���");
		}
			
		
		return ret;
	}
	
	//Key�̃i���o�[��Get����
	//�d�l�ύX?�ɂƂ��Ȃ����݂�������config�Ƃ�Mainmode�ł͂���܂�����ĂȂ��͂�?
	static  int KeyGet(String KeyNum){
		int ret = -1;
		int KeyNumTmp;
		
		if((KeyNumTmp = KeyEquals(KeyNum)) >= 0 && KeyNumTmp < KeyKey.length)
			ret = KeyKey[KeyNumTmp];
		else
			System.out.print("�w�肵������"+KeyNum+"�Ȃ�đ��݂��܂���");
		
		return ret;
	}
	
	//Key�̃i���o�[��Get����Part�U
	//Part�̌��2�Ȃ̂�Part2�ƂȂ��Ă��邯�Ǌ��ˑ����������Ă�̂ł킩��񂩂�
	//���l�łł�������ł̂��ʓ|�Ȃ��Ȃ���
	static  int KeyGet(int KeyNum){
		int ret = -1;
		
		if(KeyNum >= 0 && KeyNum < KeyKey.length)
			ret = KeyKey[KeyNum];
		else
			System.out.print("�w�肵���ԍ�"+KeyNum+"�Ȃ�đ��݂��܂���");
		
		return ret;
	}
	
	//mode�ύX�֐�
	//�Ȃ�ƂȂ�����Ƃ���
	static synchronized int ModeCon(int ModeNum){
		int ret = 0;
		
		if(ModeNum > 0)
			mode = ModeNum;
		else
			ret = -1;
		
		return ret;
	}
}
