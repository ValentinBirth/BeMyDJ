package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerFfListener implements View.OnClickListener{
    private final HomeFragment homeFragment;

    public MediaPlayerFfListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onClick(View v) {
        int currentPosition = homeFragment.getMediaPlayer().getCurrentPosition();
        int duration = homeFragment.getMediaPlayer().getDuration();

        if (homeFragment.getMediaPlayer().isPlaying() && duration != currentPosition){
            currentPosition = currentPosition+5000;
            TextView playerPosition = v.findViewById(R.id.playerPosition);
            playerPosition.setText(homeFragment.convertFormat(currentPosition));
            homeFragment.getMediaPlayer().seekTo(currentPosition);
        }
    }
}
