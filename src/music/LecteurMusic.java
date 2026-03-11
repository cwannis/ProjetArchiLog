package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class LecteurMusic implements Runnable {

    private void lancerIndien() {
        String cheminVersMusic = "src/music/musicAttente.wav";

        File file = new File(cheminVersMusic);
        AudioInputStream audioInputStream;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            long dureeEnMillisecondes = clip.getMicrosecondLength() / 1000;
            Thread.sleep(dureeEnMillisecondes);

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void lancerMusicIndien()
    {
        new Thread(this).start();
    }

    public void run() {
        lancerIndien();
    }

}
