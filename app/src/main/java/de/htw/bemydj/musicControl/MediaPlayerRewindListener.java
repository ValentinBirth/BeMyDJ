package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerRewindListener implements View.OnClickListener{
    private final HomeFragment homeFragment;

    public MediaPlayerRewindListener(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onClick(View v) {
        int currentPosition = homeFragment.getMediaPlayer().getCurrentPosition();
        if (homeFragment.getMediaPlayer().isPlaying() && currentPosition > 5000){
            currentPosition = currentPosition-5000;
            TextView playerPosition = v.findViewById(R.id.playerPosition);
            playerPosition.setText(homeFragment.convertFormat(currentPosition));
            homeFragment.getMediaPlayer().seekTo(currentPosition);
        }

    }
}
