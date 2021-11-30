package de.htw.bemydj.ui.homeScreen;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.htw.bemydj.R;
import de.htw.bemydj.musicControl.ChooseMusicFileListener;

import de.htw.bemydj.musicControl.IMusicControl;
import de.htw.bemydj.musicControl.MusicControlImpl;
import de.htw.bemydj.musicControl.MyLifecycleObserver;

public class HomeFragment extends Fragment {

    private IMusicControl musicPlayerController;
    private MyLifecycleObserver lifecycleObserver;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button btmusic = v.findViewById(R.id.musicButton);

        btmusic.setOnClickListener(new ChooseMusicFileListener(this));
        lifecycleObserver = new MyLifecycleObserver(requireActivity().getActivityResultRegistry(), this);
        getLifecycle().addObserver(lifecycleObserver);
        musicPlayerController = new MusicControlImpl(this);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        musicPlayerController.releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayerController.releasePlayer();
    }

    public MyLifecycleObserver getLifecycleObserver() {
        return lifecycleObserver;
    }

    public IMusicControl getMusicPlayerController() {
        return musicPlayerController;
    }
}