package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.ImageView;

import de.htw.bemydj.R;

public class MediaPlayerPauseListener implements View.OnClickListener {
    private IMusicControl musicPlayerControl;
    private ImageView btnPlay;

    public MediaPlayerPauseListener(IMusicControl musicPlayerControl, ImageView btPlay) {
        this.musicPlayerControl = musicPlayerControl;
        this.btnPlay = btPlay;
    }

    @Override
    public void onClick(View v) {
        ImageView btnPause = v.findViewById(R.id.btnPause);
        btnPause.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);
        musicPlayerControl.pause();
        musicPlayerControl.getHandler().removeCallbacks(musicPlayerControl.getRunnable());
    }
}
