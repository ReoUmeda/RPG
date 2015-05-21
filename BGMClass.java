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
		 // �N���b�v�̃C�x���g���Ď�
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
            // �I�[�f�B�I�X�g���[�����J��
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
            
            // �I�[�f�B�I�`�����擾
            AudioFormat format = stream.getFormat();
            // ULAW/ALAW�`���̏ꍇ��PCM�`���ɕύX
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

            // ���C�������擾
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // ��̃N���b�v���쐬
            Clip clip = (Clip) AudioSystem.getLine(info);
            // �N���b�v�̃C�x���g���Ď�
            clip.addLineListener(this);
            // �I�[�f�B�I�X�g���[�����N���b�v�Ƃ��ĊJ��
            clip.open(stream);
            bgm.add(clip);
            bgmname.add(filename);
            // �X�g���[�������
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
	 * ���[�v���Ȃ�
	 */
	public void update(LineEvent event) {
        // �X�g�b�v���Ō�܂ōĐ����ꂽ�ꍇ
        if (event.getType() == LineEvent.Type.STOP) {
            Clip clip = (Clip) event.getSource();
            clip.stop();
            clip.setFramePosition(0); // �Đ��ʒu���ŏ��ɖ߂�

        }
		
	}
	/*
	 * ���[�v����
	 */
	public class loop implements LineListener {

		@Override
		public void update(LineEvent event) {
	        // �X�g�b�v���Ō�܂ōĐ����ꂽ�ꍇ
	        if (event.getType() == LineEvent.Type.STOP) {
	            Clip clip = (Clip) event.getSource();
	            clip.stop();
	            clip.setFramePosition(1000); // �Đ��ʒu���ŏ��ɖ߂�
	            clip.start();
	        }
			
		}
		
	}
	

	

}

