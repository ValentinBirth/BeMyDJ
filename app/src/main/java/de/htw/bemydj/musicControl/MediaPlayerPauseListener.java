package de.htw.bemydj.musicControl;

import android.view.View;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.mainView.HomeFragment;

public class MediaPlayerPauseListener implements View.OnClickListener {
    private final HomeFragment homeFragment;

    public MediaPlayerPauseListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onClick(View v) {
        v.findViewById(R.id.btnPause).setVisibility(View.GONE);
        v.findViewById(R.id.btnPlay).setVisibility(View.VISIBLE);
        homeFragment.getMediaPlayer().pause();
        homeFragment.getHandler().removeCallbacks(homeFragment.getRunnable());
    }
}
