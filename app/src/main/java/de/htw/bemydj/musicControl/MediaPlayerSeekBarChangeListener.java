package de.htw.bemydj.musicControl;

import android.widget.SeekBar;
import android.widget.TextView;

public class MediaPlayerSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
    private final MusicControlImpl musicPlayerControl;
    private final TextView playerPosition;


    public MediaPlayerSeekBarChangeListener(MusicControlImpl musicPlayerControl, TextView playerPosition) {
        this.musicPlayerControl = musicPlayerControl;
        this.playerPosition = playerPosition;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser){
            musicPlayerControl.getMediaPlayer().seekTo(progress);
        }
        playerPosition.setText(musicPlayerControl.convertFormat(musicPlayerControl.getMediaPlayer().getCurrentPosition()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
