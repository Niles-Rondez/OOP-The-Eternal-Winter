package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayer {
    private Clip clip;
    private Random random;

    public void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                // Set volume (gain control)
                setVolume(-10.0f); // Lower value = softer. Adjust as needed.

                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                scheduleNextPlay();
            } else {
                System.out.println("Music file not found: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void setVolume(float gain) {
        if (clip != null) {
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(gain); // Gain value in decibels. Typical range: -80.0 to 6.0
        }
    }

    private void scheduleNextPlay() {
        if (clip == null || !clip.isRunning()) return;

        int randomDelay = random.nextInt(10, 20) * 1000; // Random delay between 10 to 20 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                playMusic(clip.getFormat().toString());
            }
        }, randomDelay);
    }
}