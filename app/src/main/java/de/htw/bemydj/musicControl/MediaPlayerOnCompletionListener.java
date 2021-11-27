package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.view.View;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.mainView.HomeFragment;

public class MediaPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener{
    private final HomeFragment homeFragment;

    public MediaPlayerOnCompletionListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        homeFragment.requireView().findViewById(R.id.btnPause).setVisibility(View.GONE);
        homeFragment.requireView().findViewById(R.id.btnPlay).setVisibility(View.VISIBLE);
        homeFragment.getMediaPlayer().seekTo(0);
    }
}
