package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerPlayListener implements View.OnClickListener{
    private MusicControlImpl musicPlayerControl;
    private ImageView btnPause;
    private  SeekBar seekBar;


    public MediaPlayerPlayListener(MusicControlImpl musicPlayerControl, ImageView btPause, SeekBar seekBar) {
        this.musicPlayerControl = musicPlayerControl;
        this.btnPause = btPause;
        this.seekBar = seekBar;
    }

    @Override
    public void onClick(View v) {
        //TODO     java.lang.NullPointerException: Attempt to invoke virtual method 'void android.view.View.setVisibility(int)' on a null object reference see Logcat when pushing Play Button
        ImageView btnPlay = v.findViewById(R.id.btnPlay);
        btnPlay.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);
        musicPlayerControl.play();
        musicPlayerControl.getMediaPlayer().setOnCompletionListener(new MediaPlayerOnCompletionListener(musicPlayerControl,btnPause,btnPlay));
        seekBar.setMax(musicPlayerControl.getMediaPlayer().getDuration());
        musicPlayerControl.getHandler().postDelayed(musicPlayerControl.getRunnable(),0);
    }
}
