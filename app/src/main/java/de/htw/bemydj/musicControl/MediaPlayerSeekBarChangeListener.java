package de.htw.bemydj.musicControl;

import android.widget.SeekBar;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
    private final HomeFragment homeFragment;

    public MediaPlayerSeekBarChangeListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser){
            homeFragment.getMediaPlayer().seekTo(progress);
        }
        TextView playerPosition = homeFragment.requireView().findViewById(R.id.playerPosition);
        playerPosition.setText(homeFragment.convertFormat(homeFragment.getMediaPlayer().getCurrentPosition()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
