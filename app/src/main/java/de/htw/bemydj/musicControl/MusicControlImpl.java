package de.htw.bemydj.musicControl;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.SeekBar;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MusicControlImpl implements IMusicControl{
    private MediaPlayer mediaPlayer;
    private final Handler handler = new Handler();
    private final Runnable musicPlayerThread;

    /** Constructor for a Media Player
     * @param homeFragment Fragment on which the player runs
     */
    public MusicControlImpl(HomeFragment homeFragment){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        mediaPlayer.setOnErrorListener(new MediaPlayerOnErrorListener(this));
        mediaPlayer.setOnPreparedListener(new MediaPlayerOnPreparedListener(homeFragment, this));

        musicPlayerThread = new Runnable() {

            @Override
            public void run() {
                SeekBar seekBar = homeFragment.requireView().findViewById(R.id.seekBar);
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        };
    }

    /** Constructor for test purposes
     * @param mediaPlayer media player
     */
    public MusicControlImpl(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        musicPlayerThread = null;
    }

    @Override
    public void play() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public String rewind(int milliseconds) {
        int currentPosition = mediaPlayer.getCurrentPosition();
        if (mediaPlayer.isPlaying() && currentPosition > milliseconds){
            currentPosition = currentPosition-milliseconds;
            mediaPlayer.seekTo(currentPosition);
            return convertFormat(currentPosition);
        }
        return "";
    }

    @Override
    public String fastForward(int milliseconds) {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int duration = mediaPlayer.getDuration();

        if (mediaPlayer.isPlaying() && duration != currentPosition){
            currentPosition = currentPosition+milliseconds;
            mediaPlayer.seekTo(currentPosition);
            return convertFormat(currentPosition);
        }
        return "";
    }

    @Override
    public void resetPlayer(FileDescriptor fileDescriptor) throws IOException {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileDescriptor);
            mediaPlayer.prepareAsync();
    }

    @Override
    public void releasePlayer() {
        if(mediaPlayer !=null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

    public String convertFormat(int duration) {
        return  String.format(Locale.GERMANY,"%02d:%02d", TimeUnit.MICROSECONDS.toMinutes(duration), TimeUnit.MICROSECONDS.toSeconds(duration)-TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(duration)));
    }

    @Override
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public Runnable getRunnable() {
        return musicPlayerThread;
    }
}
