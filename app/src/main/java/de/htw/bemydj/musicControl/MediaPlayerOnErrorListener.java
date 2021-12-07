package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.util.Log;

public class MediaPlayerOnErrorListener implements MediaPlayer.OnErrorListener{
    private static final String TAG = MusicControlImpl.class.getName();
    private final IMusicControl musicPlayerControl;

    public MediaPlayerOnErrorListener(IMusicControl musicPlayerControl) {
        this.musicPlayerControl = musicPlayerControl;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        String errorMsg;
        if(what == MediaPlayer.MEDIA_ERROR_UNKNOWN){
            errorMsg = "Unknown media error ";
        }else{
            errorMsg = "Media server died";
        }
        switch (extra) {
            case MediaPlayer.MEDIA_ERROR_IO: {
                Log.e(TAG, errorMsg + "with IO error");
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
            case MediaPlayer.MEDIA_ERROR_MALFORMED: {
                Log.e(TAG, errorMsg + "with bitstream malformed");
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED: {
                Log.e(TAG, errorMsg + "with no framework support");
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT: {
                Log.e(TAG, errorMsg + "with timeout");
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
            case -2147483648: {
                Log.e(TAG, errorMsg + " Low level System Error");
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
            default:{
                Log.e(TAG,errorMsg);
                musicPlayerControl.getMediaPlayer().reset();
                return true;
            }
        }
    }
}
