package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MediaPlayerFastForwardListener implements View.OnClickListener{
    private TextView playerPosition;
    private IMusicControl musicPlayerControl;


    public MediaPlayerFastForwardListener(TextView playerPosition, IMusicControl musicPlayerControl) {
        this.musicPlayerControl = musicPlayerControl;
        this.playerPosition = playerPosition;
    }

    @Override
    public void onClick(View v) {
        String fastForwardTime = musicPlayerControl.fastForward(5000);
        if (!fastForwardTime.equals("")){
            playerPosition.setText(fastForwardTime);
        }
    }
}
