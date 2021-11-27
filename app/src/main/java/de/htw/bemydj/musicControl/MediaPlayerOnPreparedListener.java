package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.mainView.HomeFragment;

public class MediaPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener{
    private final HomeFragment homeFragment;

    public MediaPlayerOnPreparedListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        ImageView btRew = homeFragment.requireView().findViewById(R.id.btnRew);
        ImageView btFor = homeFragment.requireView().findViewById(R.id.btwFor);
        ImageView btPlay = homeFragment.requireView().findViewById(R.id.btnPlay);
        ImageView btPause = homeFragment.requireView().findViewById(R.id.btnPause);
        SeekBar seekBar = homeFragment.requireView().findViewById(R.id.seekBar);
        btPlay.setOnClickListener(new MediaPlayerPlayListener(homeFragment));
        btPause.setOnClickListener(new MediaPlayerPauseListener(homeFragment));
        btFor.setOnClickListener(new MediaPlayerFfListener(homeFragment));
        btRew.setOnClickListener(new MediaPlayerRewindListener(homeFragment));
        seekBar.setOnSeekBarChangeListener(new MediaPlayerSeekBarChangeListener(homeFragment));
    }
}
