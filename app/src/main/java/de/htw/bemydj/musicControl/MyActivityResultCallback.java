package de.htw.bemydj.musicControl;


import android.net.Uri;

import de.htw.bemydj.ui.mainView.HomeFragment;

public class MyActivityResultCallback implements androidx.activity.result.ActivityResultCallback<Uri> {
    HomeFragment homeFragment;

    public MyActivityResultCallback(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onActivityResult(Uri result) {
        homeFragment.setMusicFileUriTest(result);
        homeFragment.resetMediaPlayer();
        //TODO handle returned Uri
    }
}
