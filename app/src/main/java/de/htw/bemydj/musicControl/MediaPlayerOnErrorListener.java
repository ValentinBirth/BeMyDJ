package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;

import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerOnErrorListener implements MediaPlayer.OnErrorListener{
    private final HomeFragment homeFragment;

    public MediaPlayerOnErrorListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
        //TODO implement OnErrorListener
    }
}
