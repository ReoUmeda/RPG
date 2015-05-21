import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BGMClass implements LineListener {
	private ArrayList<Clip> bgm = new ArrayList<Clip>();
	private ArrayList<String> bgmname = new ArrayList<String>();
	public Clip soundLoad(String s,int flag){
		Clip ret = null;
		File file = new File(s);
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	try {
		ret = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
		 // クリップのイベントを監視
		if(flag == 0){
			ret.addLineListener(this);
		}
		else{
			ret.addLineListener(new loop());
		}
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		((Clip) ret).open(ais);
		ais.close();
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	bgm.add(ret);
	bgmname.add(s);
	return ret;
	}
   /* public void load(String filename) {
        try {
            // オーディオストリームを開く
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
            
            // オーディオ形式を取得
            AudioFormat format = stream.getFormat();
            // ULAW/ALAW形式の場合はPCM形式に変更
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW)
                    || (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2, format.getChannels(),
                        format.getFrameSize() * 2, format.getFrameRate(), true);
                stream = AudioSystem.getAudioInputStream(newFormat, stream);
                format = newFormat;
            }

            // ライン情報を取得
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 空のクリップを作成
            Clip clip = (Clip) AudioSystem.getLine(info);
            // クリップのイベントを監視
            clip.addLineListener(this);
            // オーディオストリームをクリップとして開く
            clip.open(stream);
            bgm.add(clip);
            bgmname.add(filename);
            // ストリームを閉じる
            stream.close();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        
    }*/
	public void start(int bgmnum){
		bgm.get(bgmnum).start();
	}
	public void stop(int bgmnum){
		bgm.get(bgmnum).stop();
	}
	@Override
	/*
	 * ループしない
	 */
	public void update(LineEvent event) {
        // ストップか最後まで再生された場合
        if (event.getType() == LineEvent.Type.STOP) {
            Clip clip = (Clip) event.getSource();
            clip.stop();
            clip.setFramePosition(0); // 再生位置を最初に戻す

        }
		
	}
	/*
	 * ループする
	 */
	public class loop implements LineListener {

		@Override
		public void update(LineEvent event) {
	        // ストップか最後まで再生された場合
	        if (event.getType() == LineEvent.Type.STOP) {
	            Clip clip = (Clip) event.getSource();
	            clip.stop();
	            clip.setFramePosition(1000); // 再生位置を最初に戻す
	            clip.start();
	        }
			
		}
		
	}
	

	

}

