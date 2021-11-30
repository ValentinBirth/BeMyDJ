package de.htw.bemydj.musicControl;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener{
    private ImageView btnPause;
    private ImageView btnPlay;
    private IMusicControl musicPlayerControl;


    public MediaPlayerOnCompletionListener(IMusicControl musicPlayerControl, ImageView btnPause, ImageView btnPlay) {
        this.musicPlayerControl = musicPlayerControl;
        this.btnPause = btnPause;
        this.btnPlay = btnPlay;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btnPause.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);
        musicPlayerControl.getMediaPlayer().seekTo(0);
    }
}
