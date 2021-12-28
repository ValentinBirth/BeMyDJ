package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.os.Handler;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for MusicControl
 */
public interface IMusicControl {
    /** tells the MediaPlayer to start playing
     */
    void play();

    /** tells MediaPlayer to pause playing
     */
    void pause();

    /** tells MediaPlayer to rewind given amount of time
     * @param milliseconds amount of time to rewind
     * @return string for new position in minute:seconds format
     */
    String rewind(int milliseconds);

    /** tells MediaPlayer to fast forward given amout of time
     * @param milliseconds amount of time to fast forward
     * @return string for new position in minute:seconds format
     */
    String fastForward(int milliseconds);

    /** resets the MediaPlayer to a new input source
     * @param fileDescriptor the file descriptor of music to be played
     */
    void resetPlayer(FileDescriptor fileDescriptor) throws IOException;

    /**
     * releases the MediaPlayer
     */
    void releasePlayer();

    /** converts integer position in milliseconds to string minute:second format
     * @param duration integer position in milliseconds
     * @return string minute:second format
     */
    String convertFormat(int duration);

    /**
     * getter for MediaPlayer
     */
    MediaPlayer getMediaPlayer();

    /**
     * getter for Handler
     */
    Handler getHandler();

    /**
     * getter for runnable
     */
    Runnable getRunnable();
}
