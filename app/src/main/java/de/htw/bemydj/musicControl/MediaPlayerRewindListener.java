package de.htw.bemydj.musicControl;

import android.view.View;
import android.widget.TextView;

public class MediaPlayerRewindListener implements View.OnClickListener{
    private final IMusicControl musicPlayerControl;
    private final TextView playerPosition;

    public MediaPlayerRewindListener(IMusicControl musicPlayerControl, TextView playerPosition) {
        this.musicPlayerControl = musicPlayerControl;
        this.playerPosition = playerPosition;
    }

    @Override
    public void onClick(View v) {
        String rewindTime = musicPlayerControl.rewind(5000);
        if (!rewindTime.equals("")){
            playerPosition.setText(rewindTime);
        }
    }
}
