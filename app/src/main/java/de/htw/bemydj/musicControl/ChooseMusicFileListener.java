package de.htw.bemydj.musicControl;

import android.view.View;

import androidx.lifecycle.DefaultLifecycleObserver;

import de.htw.bemydj.ui.mainView.HomeFragment;

public class ChooseMusicFileListener implements View.OnClickListener {
    HomeFragment homeFragment;
    public ChooseMusicFileListener(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onClick(View v) {
        homeFragment.getLifecycleObserver().selectMusicFile();
    }
}
