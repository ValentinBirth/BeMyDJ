package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import de.htw.bemydj.R;

public class MediaPlayerPlayListener implements View.OnClickListener{
    private final IMusicControl musicPlayerControl;
    private final ImageView btnPause;
    private final SeekBar seekBar;


    public MediaPlayerPlayListener(IMusicControl musicPlayerControl, ImageView btPause, SeekBar seekBar) {
        this.musicPlayerControl = musicPlayerControl;
        this.btnPause = btPause;
        this.seekBar = seekBar;
    }

    @Override
    public void onClick(View v) {
        ImageView btnPlay = v.findViewById(R.id.btnPlay);
        btnPlay.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);
        musicPlayerControl.play();
        musicPlayerControl.getMediaPlayer().setOnCompletionListener(new MediaPlayerOnCompletionListener(musicPlayerControl,btnPause,btnPlay));
        seekBar.setMax(musicPlayerControl.getMediaPlayer().getDuration());
        musicPlayerControl.getHandler().postDelayed(musicPlayerControl.getRunnable(),0);
    }
}
