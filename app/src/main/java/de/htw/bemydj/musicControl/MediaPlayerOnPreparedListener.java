package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener{
    private final HomeFragment homeFragment;
    private final MusicControlImpl musicPlayerControl;

    public MediaPlayerOnPreparedListener(HomeFragment homeFragment, MusicControlImpl musicControl) {
        this.homeFragment = homeFragment;
        this.musicPlayerControl = musicControl;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        ImageView btRew = homeFragment.requireView().findViewById(R.id.btnRew);
        ImageView btFor = homeFragment.requireView().findViewById(R.id.btwFor);
        ImageView btPlay = homeFragment.requireView().findViewById(R.id.btnPlay);
        ImageView btPause = homeFragment.requireView().findViewById(R.id.btnPause);
        TextView playerDuration = homeFragment.requireView().findViewById(R.id.playerDuration);
        TextView playerPosition = homeFragment.requireView().findViewById(R.id.playerPosition);
        SeekBar seekBar = homeFragment.requireView().findViewById(R.id.seekBar);
        btPlay.setOnClickListener(new MediaPlayerPlayListener(musicPlayerControl,btPause, seekBar));
        btPause.setOnClickListener(new MediaPlayerPauseListener(musicPlayerControl,btPlay));
        btFor.setOnClickListener(new MediaPlayerFastForwardListener(playerPosition,musicPlayerControl));
        btRew.setOnClickListener(new MediaPlayerRewindListener(musicPlayerControl,playerPosition));
        seekBar.setOnSeekBarChangeListener(new MediaPlayerSeekBarChangeListener(musicPlayerControl,playerPosition));

        int duration = mp.getDuration();
        String sduration = musicPlayerControl.convertFormat(duration);
        playerDuration.setText(sduration);
    }
}
