package de.htw.bemydj.musicControl;

import android.view.View;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.mainView.HomeFragment;

public class MediaPlayerPlayListener implements View.OnClickListener{
    private final HomeFragment homeFragment;
    public MediaPlayerPlayListener(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onClick(View v) {
        //TODO     java.lang.NullPointerException: Attempt to invoke virtual method 'void android.view.View.setVisibility(int)' on a null object reference see Logcat when pushing Play Button
        v.findViewById(R.id.btnPlay).setVisibility(View.GONE);
        v.findViewById(R.id.btnPause).setVisibility(View.VISIBLE);
        homeFragment.getMediaPlayer().start();
        homeFragment.getSeekBar().setMax(homeFragment.getMediaPlayer().getDuration());
        homeFragment.getHandler().postDelayed(homeFragment.getRunnable(),0);
    }
}
