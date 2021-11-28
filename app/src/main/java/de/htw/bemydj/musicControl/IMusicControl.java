package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.net.Uri;

import java.io.InputStream;

/**
 * Interface for MusicControl
 */
public interface IMusicControl {
    /** tells the MediaPlayer to start playing
     * @return true if successful, else false
     */
    boolean play();

    /** tells MediaPlayer to pause playing
     * @return true if successful, else false
     */
    boolean pause();

    /** tells MediaPlayer to resume playing
     * @return true if successful, else false
     */
    boolean resume();

    /** tells MediaPlayer to rewind given amount of time
     * @param milliseconds amount of time to rewind
     * @return true if successful, else false
     */
    boolean rewind(int milliseconds);

    /** tells MediaPlayer to fast forward given amout of time
     * @param milliseconds amount of time to fast forward
     * @return true if successful, else false
     */
    boolean fastForward(int milliseconds);

    /** resets the MediaPlayer to a new input source
     * @param inputStream the input stream of music to be played
     * @return true if successful, else false
     */
    boolean resetPlayer(InputStream inputStream);

    /**
     * getter for MediaPlayer
     */
    MediaPlayer getMediaPlayer();

    /** chooses a music file
     * @return uri of chosen music file
     */
    Uri chooseMusicFile();
}
