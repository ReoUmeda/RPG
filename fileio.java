import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class fileio{
	//テキスト書き込みArrayList<Charater>版
	static void twrite(File file,ArrayList<Character> alout,int size,int lf) throws IOException{
		FileWriter filewriter = new FileWriter(file);
		
	    try{
			BufferedWriter bw = new BufferedWriter(filewriter);
	        if (checkBeforeWritefile(file)){
	        	for(int i=0;i<size;i++){
					bw.write(alout.get(i));
					if(lf == 1 && i == 0) bw.newLine();
					 try{
						 if((i+1)%lf== 0 && i != 0) bw.newLine();
					 }catch(ArithmeticException e){}
				}

	          
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
	        bw.close();
	      }catch(IOException e){

	      }
	}
	
	//テキスト書き込みchar[]版
	static void twrite(File file,char[] alout,int size,int lf) throws IOException{
		FileWriter filewriter = new FileWriter(file);
		
	    try{
			BufferedWriter bw = new BufferedWriter(filewriter);
	        if (checkBeforeWritefile(file)){
	        	for(int i=0;i<size;i++){
					bw.write(alout[i]);
					if(lf == 1 && i == 0) bw.newLine();
					 try{
						 if((i+1)%lf== 0 && i != 0) bw.newLine();
					 }catch(ArithmeticException e){}
				}

	          
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
	        bw.close();
	      }catch(IOException e){

	      }
	}
	//テキスト書き込みchar[]追記あり版
	static void twrite(File file,char[] alout,int size,int lf,boolean postscript) throws IOException{
		FileWriter filewriter = new FileWriter(file,postscript);
		
	    try{
			BufferedWriter bw = new BufferedWriter(filewriter);
	        if (checkBeforeWritefile(file)){
	        	for(int i=0;i<size;i++){
					bw.write(alout[i]);
					if(lf == 1 && i == 0) bw.newLine();
					 try{
						 if((i+1)%lf== 0 && i != 0) bw.newLine();
					 }catch(ArithmeticException e){}
				}

	          
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
	        bw.close();
	      }catch(IOException e){

	      }
	}
	//テキスト書き込みchar[]追記あり改行補正版
	static void twrite(File file,char[] alout,int size,int lf,boolean postscript,int crlfkeep) throws IOException{
		FileWriter filewriter = new FileWriter(file,postscript);
	    try{
			BufferedWriter bw = new BufferedWriter(filewriter);
	        if (checkBeforeWritefile(file)){
	        	for(int i=0;i<size;i++){
					bw.write(alout[i]);
					if(lf != 0){
						if(lf == 1 && i == 0) bw.newLine();
						if((i+1+crlfkeep)%lf== 0 && i != 0) bw.newLine();
					}
					
	        	}

	          
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
	        bw.close();
	      }catch(IOException e){

	      }
	}
	private static boolean checkBeforeWritefile(File file){
		if (file.exists()){
			if (file.isFile() && file.canWrite()){
				return true;
			}
		}

		return false;
	}
/*private static boolean checkAfterWritefile(File file){
    if (file.exists()){
    	return true;
    }
	Container cnt = getContentPane();
	int type = JOptionPane.WARNING_MESSAGE;
	type=JOptionPane.showConfirmDialog(cnt, "保存されていないデータは消えますがよろしいですか?", "終了",JOptionPane.YES_NO_OPTION, type);
	if(type==JOptionPane.YES_OPTION) ;

    return false;
  }*/
	//バイナリ読み込み
	static void bread(File file,ArrayList<Character> alin){
		try{
			FileInputStream 	filestream = new FileInputStream(file);

			int ch = filestream.read();
		  
			while(ch != -1){
				alin.add((char)ch);
			    ch = filestream.read();
			}
			filestream.close();
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}
	}
	//テキスト読み込み
	static boolean textread(File file, ArrayList<String> keyread){
		try{
			FileReader filestream = new FileReader(file);
			BufferedReader br = new BufferedReader(filestream);
			String str = br.readLine();
		  
			while(str != null){
				keyread.add(str);
			    str = br.readLine();
			}
			br.close();
			filestream.close();
		}catch(FileNotFoundException e){
			System.out.println(e);
			return false;
		}catch(IOException e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	//テキスト書き込みArrayList<String>版
	static void textwrite(File file,ArrayList<String> keywrite,int size) throws IOException{
		FileWriter filewriter = new FileWriter(file);
		
	    try{
			BufferedWriter bw = new BufferedWriter(filewriter);
			
	        if (checkBeforeWritefile(file)){
	        	for(int i=0;i<size;i++){
					bw.write(keywrite.get(i));
					bw.newLine();
				}

	          
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
	        bw.close();
	      }catch(IOException e){

	      }
	}
	//バイナリ指定読み込みArrayList<Short>版
	static int BinarySelectRead(File file,ArrayList<Short> bsshort,int offset, int len){
		if(len <= 0) return 1;
		try{
			FileInputStream 	filestream = new FileInputStream(file);
			byte[] bytein = new byte[len];
			filestream.skip(offset);
			filestream.read(bytein, 0, len);
			for(int i=0;i<len;i++) bsshort.add((short) (bytein[i] & 0xFF));
			filestream.close();
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}
		return 0;
	}
	//バイナリ指定読み込みchar[]版
	static int BinarySelectRead(File file,char[] bschar,long offset, int len){
		if(len <= 0) return 1;
		int i = 0;
		try{
			FileInputStream 	filestream = new FileInputStream(file);
			byte[] bytein = new byte[len];
			filestream.skip(offset);
			filestream.read(bytein, 0, len);
			for(i=0;i<len;i++) bschar[i] = (char) (bytein[i] & 0xFF);
			filestream.close();
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}

		return i;
	}
	
	static BufferedImage BufferLoadImage(String fileName){
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
			//BufferedImage img = new BufferedImage(5000, 5000, BufferedImage.TYPE_4BYTE_ABGR_PRE);
			BufferedImage img = ImageIO.read(is);
			return img;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) try { is.close(); } catch (IOException e) {}
		}
		
	}

}
