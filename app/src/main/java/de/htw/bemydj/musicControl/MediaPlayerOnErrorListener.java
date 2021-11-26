package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;

public class MediaPlayerOnErrorListener implements MediaPlayer.OnErrorListener{
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
        //TODO implement OnErrorListener
    }
}
