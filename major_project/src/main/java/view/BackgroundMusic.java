package view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * A class that implements the background music functionality
 */

public class BackgroundMusic{
    MediaPlayer mediaPlayer;
    Media media = new Media(new File("src/main/resources/themesong.wav").toURI().toString());
    public BackgroundMusic(){
        startSong();
    }

    /**
     * In charge of starting the song
     */
    public void startSong(){
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.15);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(this::startSong);
        mediaPlayer.play();
    }

    /**
     * The logic for starting and stopping the music on request
     */
    public void songPlayAndPause(){
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED){
            mediaPlayer.play();
        }
        else{
            mediaPlayer.pause();
        }
    }
}
