/*// ���e�X�g�R�z�z�\�[�X
// GUI �t���[�� (�O���t�B�b�N�X�C���C�A�E�g�C���x���C�}�E�X,
//               ���W�I�{�^���C�L�[���̓C�x���g�C�_�C�A���O)
//                                     2013.12.16
import java.io.*;
import java.awt.*;

class Input
{
	static int Integer(int from, int to, String text) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num;

		do {
			System.out.print(text);
			String str = br.readLine();
			num = Integer.parseInt(str);
		} while(num < from || to < num);

		return num;
	}
}

class Map
{
	int Width=20;   // �}�b�v�̕�
	int Height=9;   // �}�b�v�̍���
	int Nobstacle;  // ��Q���̌�
	int Nesa=0;     // �a�̌�
	int[][] Field;  // �}�b�v�t�B�[���h
	int Mode;       // ���샂�[�h�i0:��Q���C1:�ړ��C2:�a�j

	Map(int p) // �}�b�v�̏�����
	{
		Field = new int[Height][Width];
		for (int i=0; i<Height; i++) {
			for (int j=0; j<Width; j++) Field[i][j] = 0;
		}
		Nobstacle=p*Height*Width/100;
		for (int k=0; k<Nobstacle;) {
			int j = (int)(Math.random()*Width);
			int i = (int)(Math.random()*Height);
			if (Field[i][j]!=0) continue;
			Field[i][j] = 9;
			k++;
		}
	}

	void printMap()
	{
		for (int i=0; i<Height; i++) {
			for (int j=0; j<Width; j++) {
				System.out.print(Field[i][j]);
			}
			System.out.println("");
		}
	}

}

//Runnable�C���^�[�t�F�C�X��
class Mover implements Runnable
{
	GuiMap M;    // �}�b�v
	int X, Y;    // ���݈ʒu
	int C;       // �ړ��̎��
	int count=0; // �ړ�����
	int crash=0; // �Փˉ�
	int Key;     // ������Ă���L�[
	int HP;      // �ړ��\����

	Mover(int c, GuiMap map)
	{
		HP = 10;
		C = c;
		M = map;
		for (;;) {
			X = (int)(Math.random()*M.Width);
			Y = (int)(Math.random()*M.Height);
			if (M.Field[Y][X]==0) break;
		}
		M.Field[Y][X] = c;
	}

	public void run()
	{
		for (;;) {
			System.out.println("run(Mover)"+C);
			if (M.Mode==1 && HP>0) move();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		}
	}

	synchronized void move()
	{
		int key, x, y;
		if (C==1) key = Key;
		else      key = (int)(Math.random()*4 + 1)*2;
		x=X; y=Y;
		HP--;
		count++;
		M.Field[Y][X] = 0;
		if      (key==6 && X<M.Width-1 ) X++;
		else if (key==4 && 0<X         ) X--;
		else if (key==8 && 0<Y         ) Y--;
		else if (key==2 && Y<M.Height-1) Y++;
		else {count--; crash++; HP++;}

		if       (M.Field[Y][X]==8) {
			HP += 10; M.Nesa--;
		} else if(M.Field[Y][X]!=0) {
			X=x; Y=y; count--; crash++; HP++;
		}
		M.Field[Y][X]=C;
	}

	String direction()
	{
		String str;
		if      (Key==8) str = "< �� >";
		else if (Key==2) str = "< �� >";
		else if (Key==4) str = "< �� >";
		else if (Key==6) str = "< �E >";
		else             str = "< �~>";
		return str;
	}
}

class GuiMap extends Map
{
	int Size=50;     // ��}�X�̃h�b�g��
	int DX=8, DY=30; // �E�C���h�E�g�̃h�b�g��

	GuiMap(int p)
	{
		super(p);
	}

	void paintMap(Graphics g, Mover[] mv, double time)
	{
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 24));
		String str = Height + "�s" + Width	+ "�� " + " ��Q��" + Nobstacle + "�� "
		             + " �a" + Nesa + "��" +  "   ����" + time + "�b����";
		g.drawString(str, 0, (int)(0.9*Size));

		for (int i=0; i<Height; i++) {
			for (int j=0; j<Width; j++) {
				int m = Field[i][j];
				int x = j*Size;
				int y = (i+1)*Size;
				if        (m==0) {
					g.setColor(Color.green);
					g.fillRect(x, y, Size, Size);
				} else if (m==8) {
					g.setColor(Color.red);
					g.fillRect(x, y, Size, Size);
				} else if (m==9) {
					g.setColor(Color.black);
					g.fillRect(x, y, Size, Size);
				} else if (m==1) {
					g.setColor(Color.orange);
					g.fillOval(x, y, Size, Size);
					g.setColor(Color.black);
					g.drawString(Integer.toString(mv[0].HP), x, y+Size);
				} else {
					g.setColor(Color.blue);
					g.fillOval(x, y, Size, Size);
					g.setColor(Color.red);
					g.drawString(Integer.toString(mv[m-1].HP), x, y+Size);
				}
			}
		}
	}

	void setGuiMap(int x, int y, int b)
	{
		int i = (y-DY)/Size-1;
		int j = (x-DX)/Size;
		int p = 0;
		if (Mode==0) p = 9;
		if (Mode==2) p = 8;
		if (0<=i && i<Height && 0<=j && j<Width) {
			if (b==1) {
				if (Field[i][j]==0) {
					Field[i][j]=p;
					if (Mode==0) Nobstacle++;
					if (Mode==2) Nesa++;
				}
			} else if (b==3) {
				if (Field[i][j]==p) {
					Field[i][j]=0;
					if (Mode==0) Nobstacle--;
					if (Mode==2) Nesa--;
				}
			}
		}
	}
}*/
