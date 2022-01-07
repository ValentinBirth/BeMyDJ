package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.TextView;

public class MediaPlayerFastForwardListener implements View.OnClickListener{
    private final TextView playerPosition;
    private final IMusicControl musicPlayerControl;


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
