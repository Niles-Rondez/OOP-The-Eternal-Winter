package audio;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    private static boolean isToolkitInitialized = false;

    // Constructor to ensure the JavaFX toolkit is initialized
    public AudioPlayer() {
        initializeToolkit();
    }

    private synchronized void initializeToolkit() {
        if (!isToolkitInitialized) {
            Platform.startup(() -> {}); // Start the JavaFX toolkit
            isToolkitInitialized = true;
        }
    }

    public void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            if (musicFile.exists()) {
                Media media = new Media(musicFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                // Set volume
                setVolume(0.5); // Value between 0.0 (mute) to 1.0 (max volume)

                // Play and loop
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(mediaPlayer.getStartTime()));
                mediaPlayer.play();

            } else {
                System.out.println("Music file not found: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume); // Volume range: 0.0 (mute) to 1.0 (full)
        }
    }
}
